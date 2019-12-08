<?php
session_start();
require_once 'global.php';

error_reporting(E_ERROR | E_PARSE);
header('Content-type: application/json; charset=utf-8');

header('Expires: Sun, 01 Jan 2014 00:00:00 GMT');
header('Cache-Control: no-store, no-cache, must-revalidate');
header('Cache-Control: post-check=0, pre-check=0', FALSE);
header('Pragma: no-cache');

$response = array();

$request = explode('/', trim($_SERVER['PATH_INFO'], '/'));
$action = strtolower(array_shift($request));
$id = array_shift($request);

require_once 'db_config.php';
$db = new mysqli(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);

if ($db->connect_errno) {
    $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
    $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการเชื่อมต่อฐานข้อมูล';
    $response[KEY_ERROR_MESSAGE_MORE] = $db->connect_error;
    echo json_encode($response);
    exit();
}
$db->set_charset("utf8");

//sleep(1); //todo:

switch ($action) {
    case 'get_project':
        doGetProject();
        break;
    case 'add_project':
        doAddProject();
        break;
    case 'update_project':
        doUpdateProject();
        break;
    case 'delete_project':
        doDeleteProject();
        break;
    case 'delete_project_asset':
        doDeleteProjectAsset();
        break;
    default:
        $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
        $response[KEY_ERROR_MESSAGE] = "No action specified or invalid action: [{$action}]";
        $response[KEY_ERROR_MESSAGE_MORE] = '';
        break;
}

$db->close();
echo json_encode($response);
exit();

function doGetProject()
{
    global $db, $response;

    $sql = "SELECT * FROM rp_project ORDER BY sort_index";
    if ($result = $db->query($sql)) {
        $response[KEY_ERROR_CODE] = ERROR_CODE_SUCCESS;
        $response[KEY_ERROR_MESSAGE] = 'อ่านข้อมูลสำเร็จ';
        $response[KEY_ERROR_MESSAGE_MORE] = '';

        $placeList = array();
        while ($row = $result->fetch_assoc()) {
            $project = array();
            $project['id'] = (int)$row['id'];
            $project['name'] = $row['name'];
            $project['details'] = $row['details'];
            $project['address'] = $row['address'];
            $project['opening_time'] = $row['opening_time'];
            $project['phone'] = $row['phone'];
            $project['cover_image'] = $row['cover_image'];
            $project['latitude'] = floatval($row['latitude']);
            $project['longitude'] = floatval($row['longitude']);
            $project['image_list'] = array();

            $sql = "SELECT image_file_name FROM rp_project_image WHERE project_id = {$project['id']}";
            if ($galleryResult = $db->query($sql)) {
                while ($galleryRow = $galleryResult->fetch_assoc()) {
                    array_push($project['image_list'], $galleryRow['image_file_name']);
                }
                $galleryResult->close();
            } else {
                $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
                $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการอ่านข้อมูล (2)';
                $errMessage = $db->error;
                $response[KEY_ERROR_MESSAGE_MORE] = "$errMessage\nSQL: $sql";
                return;
            }

            array_push($placeList, $project);
        }
        $result->close();
        $response[KEY_DATA_LIST] = $placeList;
    } else {
        $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
        $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการอ่านข้อมูล (1)';
        $errMessage = $db->error;
        $response[KEY_ERROR_MESSAGE_MORE] = "$errMessage\nSQL: $sql";
    }
}

function doAddProject()
{
    global $db, $response;

    $name = trim($db->real_escape_string($_POST['name']));
    $phone = trim($db->real_escape_string($_POST['phone']));
    $openingTime = trim($db->real_escape_string($_POST['openingTime']));
    $address = trim($db->real_escape_string($_POST['address']));
    $latitude = $db->real_escape_string($_POST['latitude']);
    $longitude = $db->real_escape_string($_POST['longitude']);
    $details = trim($db->real_escape_string($_POST['details']));

    if (!moveUploadedFile('coverImageFile', DIR_IMAGES, $coverImageFileName)) {
        $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
        $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการอัพโหลดไฟล์';
        $response[KEY_ERROR_MESSAGE_MORE] = '';
        return;
    }

    $db->query('START TRANSACTION');

    $sql = "INSERT INTO rp_project (name, details, phone, opening_time, address, 
                        latitude, longitude, cover_image) 
                VALUES ('$name', '$details', '$phone', '$openingTime', '$address', 
                        $latitude, $longitude, '$coverImageFileName')";

    if ($result = $db->query($sql)) {
        $insertId = $db->insert_id;

        for ($i = 0; $i < sizeof($_FILES[KEY_IMAGE_FILES]['name']); $i++) {
            if ($_FILES[KEY_IMAGE_FILES]['name'][$i] !== '') {
                $fileName = null;

                if (!moveUploadedFile(KEY_IMAGE_FILES, DIR_IMAGES_GALLERY, $fileName, $i)) {
                    $db->query('ROLLBACK');

                    $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
                    $errorValue = $_FILES[KEY_IMAGE_FILES]['error'][$i];
                    $response[KEY_ERROR_MESSAGE] = "เกิดข้อผิดพลาดในการอัพโหลดรูปภาพ [Error: $errorValue]";
                    $response[KEY_ERROR_MESSAGE_MORE] = '';
                    return;
                }

                $sql = "INSERT INTO rp_project_image (project_id, image_file_name) 
                    VALUES ($insertId, '$fileName')";
                if (!($insertCourseAssetResult = $db->query($sql))) {
                    $db->query('ROLLBACK');

                    $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
                    $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการบันทึกข้อมูลรูปภาพ Gallery: ' . $db->error;
                    $response[KEY_ERROR_MESSAGE_MORE] = '';
                    return;
                }
            }
        }

        $response[KEY_ERROR_CODE] = ERROR_CODE_SUCCESS;
        $response[KEY_ERROR_MESSAGE] = 'เพิ่มข้อมูลสำเร็จ';
        $response[KEY_ERROR_MESSAGE_MORE] = '';

        $db->query('COMMIT');
    } else {
        $db->query('ROLLBACK');

        $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
        $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการเพิ่มข้อมูล: ' . $db->error;
        $errMessage = $db->error;
        $response[KEY_ERROR_MESSAGE_MORE] = "$errMessage\nSQL: $sql";
    }
}

function doUpdateProject()
{
    global $db, $response;

    $id = $db->real_escape_string($_POST['placeId']);
    $name = trim($db->real_escape_string($_POST['name']));
    $phone = trim($db->real_escape_string($_POST['phone']));
    $openingTime = trim($db->real_escape_string($_POST['openingTime']));
    $address = trim($db->real_escape_string($_POST['address']));
    $latitude = $db->real_escape_string($_POST['latitude']);
    $longitude = $db->real_escape_string($_POST['longitude']);
    $details = trim($db->real_escape_string($_POST['details']));

    $coverImageFileName = NULL;
    if ($_FILES['coverImageFile']['name'] !== '') {
        if (!moveUploadedFile('coverImageFile', DIR_IMAGES, $coverImageFileName)) {
            $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
            $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการอัพโหลดไฟล์ (รูปภาพ Cover)';
            $response[KEY_ERROR_MESSAGE_MORE] = '';
            return;
        }
    }
    $setCoverFileName = $coverImageFileName ? "cover_image = '$coverImageFileName', " : '';

    $db->query('START TRANSACTION');

    $sql = "UPDATE rp_project 
                SET $setCoverFileName 
                    name = '$name', details = '$details', phone = '$phone', 
                    opening_time = '$openingTime', address = '$address', 
                    latitude = $latitude, longitude = '$longitude' 
                WHERE id = $id";

    if ($result = $db->query($sql)) {
        for ($i = 0; $i < sizeof($_FILES[KEY_IMAGE_FILES]['name']); $i++) {
            if ($_FILES[KEY_IMAGE_FILES]['name'][$i] !== '') {
                $fileName = null;

                if (!moveUploadedFile(KEY_IMAGE_FILES, DIR_IMAGES_GALLERY, $fileName, $i)) {
                    $db->query('ROLLBACK');

                    $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
                    $errorValue = $_FILES[KEY_IMAGE_FILES]['error'][$i];
                    $response[KEY_ERROR_MESSAGE] = "เกิดข้อผิดพลาดในการอัพโหลดรูปภาพ [Error: $errorValue]";
                    $response[KEY_ERROR_MESSAGE_MORE] = '';
                    return;
                }

                $sql = "INSERT INTO rp_project_image (project_id, image_file_name) 
                    VALUES ($id, '$fileName')";
                if (!($insertCourseAssetResult = $db->query($sql))) {
                    $db->query('ROLLBACK');

                    $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
                    $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการบันทึกข้อมูลรูปภาพ Gallery: ' . $db->error;
                    $response[KEY_ERROR_MESSAGE_MORE] = '';
                    return;
                }
            }
        }

        $response[KEY_ERROR_CODE] = ERROR_CODE_SUCCESS;
        $response[KEY_ERROR_MESSAGE] = 'แก้ไขข้อมูลสำเร็จ';
        $response[KEY_ERROR_MESSAGE_MORE] = '';

        $db->query('COMMIT');
    } else {
        $db->query('ROLLBACK');

        $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
        $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการแก้ไขข้อมูล: ' . $db->error;
        $errMessage = $db->error;
        $response[KEY_ERROR_MESSAGE_MORE] = "$errMessage\nSQL: $sql";
    }
}

function doDeleteProject()
{
    global $db, $response;

    $id = $db->real_escape_string($_POST['id']);

    $deleteNewsSql = "DELETE FROM rp_project WHERE id = $id";

    if ($deleteResult = $db->query($deleteNewsSql)) {
        $deletePlaceAssetsSql = "DELETE FROM rp_project_image WHERE project_id = $id";

        if ($deletePlaceAssetsResult = $db->query($deletePlaceAssetsSql)) {
            $response[KEY_ERROR_CODE] = ERROR_CODE_SUCCESS;
            $response[KEY_ERROR_MESSAGE] = 'ลบข้อมูลสำเร็จ';
            $response[KEY_ERROR_MESSAGE_MORE] = '';
        } else {
            $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
            $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการลบข้อมูล (2): ' . $db->error;
            $errMessage = $db->error;
            $response[KEY_ERROR_MESSAGE_MORE] = "$errMessage\nSQL: $deletePlaceAssetsSql";
        }
    } else {
        $response[KEY_ERROR_CODE] = ERROR_CODE_ERROR;
        $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการลบข้อมูล (1): ' . $db->error;
        $errMessage = $db->error;
        $response[KEY_ERROR_MESSAGE_MORE] = "$errMessage\nSQL: $deleteNewsSql";
    }
}

function doDeleteProjectAsset()
{
    global $db, $response;

    $assetId = $db->real_escape_string($_POST['assetId']);

    $sql = "DELETE FROM rp_project_image WHERE id = $assetId";
    if ($result = $db->query($sql)) {
        $response[KEY_ERROR_CODE] = ERROR_CODE_SUCCESS;
        $response[KEY_ERROR_MESSAGE] = 'ลบข้อมูลสำเร็จ';
        $response[KEY_ERROR_MESSAGE_MORE] = '';
    } else {
        $response[KEY_ERROR_CODE] = ERROR_CODE_SQL_ERROR;
        $response[KEY_ERROR_MESSAGE] = 'เกิดข้อผิดพลาดในการลบข้อมูล';
        $errMessage = $db->error;
        $response[KEY_ERROR_MESSAGE_MORE] = "$errMessage\nSQL: $sql";
    }
}

function createRandomString($length)
{
    $key = '';
    $keys = array_merge(range(0, 9), range('a', 'z'));

    for ($i = 0; $i < $length; $i++) {
        $key .= $keys[array_rand($keys)];
    }

    return $key;
}

function moveUploadedFile($key, $dest, &$randomFileName, $index = -1)
{
    global $response;

    $clientName = $index === -1 ? $_FILES[$key]['name'] : $_FILES[$key]['name'][$index];
    $response['name'] = $clientName;
    $response['type'] = $index === -1 ? $_FILES[$key]['type'] : $_FILES[$key]['type'][$index];
    $response['size'] = $index === -1 ? $_FILES[$key]['size'] : $_FILES[$key]['size'][$index];
    $response['tmp_name'] = $index === -1 ? $_FILES[$key]['tmp_name'] : $_FILES[$key]['tmp_name'][$index];

    $src = $index === -1 ? $_FILES[$key]['tmp_name'] : $_FILES[$key]['tmp_name'][$index];
    $response['upload_src'] = $src;
    $response['upload_dest'] = $dest;

    //$date = date('Y-m-d H:i:s');
    //$timestamp = time();
    $timestamp = round(microtime(true) * 1000);
    $randomFileName = "{$timestamp}-{$clientName}";
    return move_uploaded_file($src, "{$dest}{$randomFileName}");
}

function moveUploadedFile_Old($key, $dest)
{
    global $response;

    $response['name'] = $_FILES[$key]['name'];
    $response['type'] = $_FILES[$key]['type'];
    $response['size'] = $_FILES[$key]['size'];
    $response['tmp_name'] = $_FILES[$key]['tmp_name'];

    $src = $_FILES[$key]['tmp_name'];
    $response['upload_src'] = $src;

    $response['upload_dest'] = $dest;

    return move_uploaded_file($src, $dest);
}

function getUploadErrorMessage($errCode)
{
    $message = '';
    switch ($errCode) {
        case UPLOAD_ERR_OK:
            break;
        case UPLOAD_ERR_INI_SIZE:
        case UPLOAD_ERR_FORM_SIZE:
            $message .= 'File too large (limit of ' . get_max_upload() . ' bytes).';
            break;
        case UPLOAD_ERR_PARTIAL:
            $message .= 'File upload was not completed.';
            break;
        case UPLOAD_ERR_NO_FILE:
            $message .= 'Zero-length file uploaded.';
            break;
        default:
            $message .= 'Internal error #' . $errCode;
            break;
    }
    return $message;
}

?>
