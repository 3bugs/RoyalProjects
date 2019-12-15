package th.ac.dusit.dbizcom.royalprojects.fragment;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import th.ac.dusit.dbizcom.royalprojects.R;
import th.ac.dusit.dbizcom.royalprojects.model.InfoMarker;

public class InfoFragment extends Fragment {

    private static final String TAG = InfoFragment.class.getName();
    private static final String ARG_PROJECT_ID = "project_id";

    private RecyclerView mRecyclerView;
    private ImageView mInfoImageView;
    //private View mProgressView;
    private int mProjectId;
    private List<InfoMarker> mInfoMarkerList;

    private InfoFragmentListener mListener;

    public InfoFragment() {
        // Required empty public constructor
    }

    public static InfoFragment newInstance(int projectId) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PROJECT_ID, projectId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProjectId = getArguments().getInt(ARG_PROJECT_ID, 0);
            //Utils.showShortToast(getContext(), String.valueOf(mProjectId));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.info_recycler_view);
        mInfoImageView = view.findViewById(R.id.info_image_view);
        //mProgressView = view.findViewById(R.id.progress_view);

        if (mInfoMarkerList == null) {
            doGetInfoMarker();
        } else {
            setupRecyclerView();
        }
    }

    private void doGetInfoMarker() {
        mInfoMarkerList = new ArrayList<>();

        switch (mProjectId) {
            case 1:
                mInfoImageView.setImageResource(R.drawable.info_01_w800);

                mInfoMarkerList.add(new InfoMarker(
                        "จุดลงทะเบียน",
                        "เป็นจุดที่ 2 เมื่อมาถึงจุดนี้ต้องลงทะเบียนเข้าชมหรือลงทะเบียนศึกษาธรรมชาติ ถ้าไม่ลงทะเบียนจะไม่สามารถเข้าชมศูนย์ศึกษาเรียนรู้ระบบนิเวศป่าชายเลนสิรินาถราชินีได้\n\nไม่เสียค่าเข้าชม",
                        "สิรินาถ-จุดลงทะเบียน.jpg",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ห้องประชุม",
                        "เป็นห้องที่เอาไว้จัดการประชุมงานและจัดกิจกรรมต่างๆทางศูนย์ศึกษาเรียนรู้ระบบนิเวศป่าชายเลนสิรินาถราชินี",
                        "สิรินาถ-ห้องประชุม.jpg",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "อาคารนิทรรศการ",
                        "เป็นอาคารที่มีการจัดแสดงความเป็นมาของศูนย์ศึกษาเรียนรู้ระบบนิเวศป่าชายเลนสิรินาถราชินี และความเป็นอยู่ของชาวบ้านต.ปากน้ำปราณ",
                        "สิรินาถ-อาคารนิทรรศการ.jpg",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ศาลาท่าตะบูน",
                        "สถานที่ที่ครั้งหนึ่ง พระบาทสมเด็จพระปรมินทรมหาภูมิพลอดุลยเดช ทรงประทับ ครั้งที่เสด็จติดตามการดําเนินงาน ศูนย์ฯ สิรินาถราชินี เมื่อวันที่ 9 มกราคม พ.ศ. 2557 และยังเป็นสถานที่ที่เคยเป็นจุดทําพิธีน้อมเกล้าฯ ถวายผืนป่า 1 ล้านไร่ในส่วนที่บริษัท ปตท. จํากัด (มหาชน) ร่วมกับกรมป่าไม้และประชาชนชาวไทยทั่วประเทศ ร่วมกันปลูกถวายพระองค์ท่าน เมื่อวันเสาร์ที่ 16 พฤศจิกายน พ.ศ. 2545",
                        "สิรินาถ-ศาลาท่าตะบูน.jpg",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "หอชะคราม",
                        "จุดชมวิวที่สามารถมองเห็นทัศนียภายของศูนย์ศึกษาเรียนรู้ระบบนิเวศป่าชายเลนสิรินาถราชินีได้โดยรอบ ตัวหอถูกออกแบบให้เป็นชั้นที่มีบันไดเวียนสลับ คล้ายการเรียงตัวเป็นชั้นของใบต้นชะคราม",
                        "สิรินาถ-หอชะคราม.jpg",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ศาลาท่าน้ำ",
                        "(ยังไม่มีข้อมูล)",
                        "",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ลานโพธิ์",
                        "(ยังไม่มีข้อมูล)",
                        "",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "จุดจอดรถ",
                        "(ยังไม่มีข้อมูล)",
                        "",
                        0, 0
                ));
                break;
            case 2:
                mInfoImageView.setImageResource(R.drawable.info_02_w800);

                mInfoMarkerList.add(new InfoMarker(
                        "ศาลาป่าชายเลน",
                        "(ยังไม่มีข้อมูล)",
                        "",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "พลับพลาปล่อยปู",
                        "(ยังไม่มีข้อมูล)",
                        "",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ศาลาสืบพันธุ์ไม้ป่าชายเลน",
                        "(ยังไม่มีข้อมูล)",
                        "",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ศาลาธรรมชาติ",
                        "(ยังไม่มีข้อมูล)",
                        "",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ท่าเทียบเรือ",
                        "ท่าเรือขนาดเล็กเชื่อมกับสะพานทางเดิน ซึ่งเป็นจุดที่พักเรือ สามารถทำกิจกรรมล่องเรือ สัมผัสกับความงามของธรรมชาติและศึกษาระบบนิเวศป่าชายเลนทางน้ำ มีพันธุ์ไม้หลากหลายชนิด รวมทั้งชมวิถีชีวิตชาวบ้านที่อาศัยอยู่ริมคลอง ราคาประมาณ 400 บาท (8 คน) ใช้เวลา 40 นาที",
                        "ปราณ-ท่าเทียบเรือ.jpg",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ทางเดินศึกษาธรรมชาติป่าบก",
                        "ทางเดินศึกษาธรรมชาติ ระยะทางประมาณ 1 กิโลเมตร ตลอดทางจะมีป้ายบอกความหมายของสิ่งต่างๆที่เราเดินผ่าน เช่น ต้นไม้ต่างๆ สัตว์น้ำต่างๆ ตลอดระยะ 1กิโลเมตร",
                        "ปราณ-ทางเดินธรรมชาติป่าบก.jpg",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ทุ่งโปร่งทอง",
                        "เป็นไฮไลท์ของอุทยานปราณบุรี ที่ต้องมาถ่ายรูปมิฉะนั้นจะเหมือนมาไม่ถึง สะพานไม้ที่มีต้นไม้สีเขียวขนาบสองข้าง มองเห็นวิวภูเขา และมีหอให้ขึ้นไปชมวิวด้านบน มีที่นั่งให้นั่งถ่ายรูปแบบ 360 องศา",
                        "ปราณ-ทุ่งโปร่งทอง.jpg",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "หอชมเรือนยอดป่าชายเลน",
                        "เป็นหอชมวิวทิวทัศน์ 360องศา โดยทางขึ้นไปบนหอจะมีข้อมูลของต้นไม้นานาชนิดให้ได้อ่านระหว่างทางที่จะเดินขึ้นไปบนยอดหอ เพื่อให้นักท่องเที่ยวได้ความรู้ในระหว่างการเที่ยว และยังทำให้เพลิดเพลินไปกับการอ่านและขึ้นไปถึงบนยอดหอได้อย่างไม่เหนื่อยมาก เห็นวิวของป่าชายเลนและทิวภูเขาที่สวยงามของธรรมชาติ และพื้นที่ทั้งหมดของโครงการที่มีสีเขียวขจี",
                        "ปราณ-หอชมเรือนยอดป่าชายเลน.jpg",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ป่าตะกาล",
                        "(ยังไม่มีข้อมูล)",
                        "",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ศาลาปูดำ",
                        "(ยังไม่มีข้อมูล)",
                        "",
                        0, 0
                ));
                break;
            case 3:
                mInfoImageView.setImageResource(R.drawable.info_03_w800);

                mInfoMarkerList.add(new InfoMarker(
                        "กองอำนวยการ",
                        "กองอำนวยการมีหน้าที่ทำภารกิจที่ได้รับมอบหมาย ฝ่ายอำนวยการจะต้องประกอบด้วย การวางแผน การจัดการ การอำนวยการการประสานงาน การควบคุม การดูแลและช่วยเหลือนักท่องเที่ยวให้ปลอดภัย",
                        "",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "จุดชมวิวอุทยาน",
                        "(ยังไม่มีข้อมูล)",
                        "",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "พื้นที่ทำโป่งเทียม",
                        "การจัดทำโป่งเทียมดึงดูดสัตว์ป่าเพื่อเพิ่มคุณค่าด้านการศึกษาและอนุรักษ์สัตว์ป่า การทำโป่งเทียมดึงดูดสัตว์ป่าที่บาดเจ็บเพื่อทำการควบคุมรักษา การจัดทำโป่งร่วมกับแปลงพืชอาหารเพื่อเพิ่มความสมบูรณ์ร่างกาย และเพิ่มโอกาสความสมบูรณ์พันธุ์ให้กับสัตว์ป่าที่ปล่อยคืนถิ่นในระยะแรก การจัดทำโป่งเทียมการเสริมแหล่งอาหารและเพิ่มโอกาสให้สัตว์ป่าได้ใช้เพื่อเพิ่มความสมบูรณ์ของร่างกาย เช่นเดียวกับการจัดการทุ่งหญ้าที่ใช้ไฟเผาให้หญ้าระบัด เพื่อเพิ่มปริมาณอาหารให้สัตว์กินพืช",
                        "กุยบุรี-พื้นที่ทำโป่งเทียม.jpg",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "จุดเพาะพันธุ์ต้นไม้",
                        "คือการเพิ่มจำนวนต้นพืชให้ได้จำนวนมากพอกับปริมาณความต้องการที่เพิ่มขึ้น โดยพืชต้นใหม่ที่ได้ยังคงลักษณะของพันธุ์และคุณสมบัติที่ดีไว้เหมือนเดิม อาจกล่าวได้ว่า การขยายพันธุ์พืชหายาก เป็นการช่วยรักษาลักษณะที่ดีของพันธุ์ไว้ไม่ให้สูญหาย",
                        "",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "จุดกางเต็นท์",
                        "เป็นจุดที่เอาไว้ให้นักท่องเที่ยวที่มีจุดประสงค์จะกางเต็นท์ที่โครงการอนุรักษ์และฟื้นฟูสภาพป่าบริเวณป่าสงวนแห่งชาติกุยบุรี",
                        "กุยบุรี-จุดกางเต็นท์.jpg",
                        0, 0
                ));
                break;
            case 4:
                mInfoImageView.setImageResource(R.drawable.info_04_w800);

                mInfoMarkerList.add(new InfoMarker(
                        "สำนักงาน",
                        "เป็นศูนย์รวมของการให้บริการอำนวยความสะดวก และงานให้บริการอำนวยความสะดวกแก่บุคคลภายใน และภายนอกหน่วยงานทุกระดับ หรือนักท่องเที่ยวที่เข้าชมศูนย์ปฏิบัติการจัดการที่ดินชัยพัฒนา-แม่ฟ้าหลวง",
                        "ชัยพัฒนา-สำนักงาน.jpg",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "สวนสมุนไพร",
                        "บริเวณที่ปลูกพืชที่ใช้เป็นสมุนไพร เช่น คำฝอย กระเจี๊ยบแดง กระเพรา กระเทียม บอระเพ็ด ขี้เหล็ก",
                        "ชัยพัฒนา-สวนสมุนไพร.jpg",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "จุดกางเต็นท์",
                        "เป็นจุดที่เอาไว้ให้นักเที่ยวเที่ยวที่มีจุดประสงค์จะกางเต็นท์ที่ศูนย์ปฏิบัติการจัดการที่ดินชัยพัฒนา-แม่ฟ้าหลวง",
                        "ชัยพัฒนา-จุดกางเต็นท์.jpg",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ซุ้มไม้เลื้อย",
                        "ทำหน้าที่เหมือนอุโมงค์ต้นไม้ ที่ปกคลุมทางเดินด้วยพันธุ์ไม้เลื้อย หรือเป็นโครงหลังคาคลุมทางเดินให้ร่มเงา ซุ้มทางเดินในสวนจึงเป็นอีกรูปแบบหนึ่งที่ได้รับความนิยม เพราะนอกจากจะช่วยกรองแสงแดดแล้ว ยังช่วยให้รู้สึกมีมิติน่าค้นหา สร้างบรรยากาศของสวนให้มีความน่าสนใจมากขึ้น",
                        "ชัยพัฒนา-ซุ้มไม้เลื้อย.jpg",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "เรือนเพาะชำหญ้าแฝก",
                        "หญ้าแฝก เป็นพืชใบเลี้ยงเดี่ยวตระกูลหญ้าชนิดหนึ่ง เช่นเดียวกับข้าวโพด ข้าวฟ่าง อ้อย ซึ่งพบกระจายอยู่ทั่วไปหลายพื้นที่ตามธรรมชาติ จากการสำรวจพบว่า มีกระจายอยู่ทั่วโลกประมาณ 12 ชนิด และสำรวจพบในประเทศไทย 2 ชนิด ได้แก่\n\n" +
                                "1. กลุ่มพันธุ์หญ้าแฝกลุ่ม ได้แก่ พันธุ์สุราษฎร์ธานี กำแพงเพชร 2 ศรีลังกา สงขลา 3 และพระราชทาน ฯลฯ\n\n" +
                                "2. กลุ่มพันธุ์หญ้าแฝกดอน ได้แก่ พันธุ์ราชบุรี ประจวบคีรีขันธ์ ร้อยเอ็ด กำแพงเพชร 1 นครสวรรค์ และเลย เป็นต้น\n\n" +
                                "หญ้าแฝกเป็นหญ้าที่ขึ้นเป็นกอ หน่อเบียดกันแน่น ใบของหญ้าแฝกมีลักษณะแคบยาว ขอบขนานปลายสอบแหลม ด้านท้องใบจะมีสีจางกว่าด้านหลังใบ มีรากเป็นระบบรากฝอยที่สานกันแน่นยาว หยั่งลึกในดิน มีข้อดอกตั้ง ประกอบด้วยดอกขนาดเล็ก ดอกจำนวนครึ่งหนึ่งเป็นหมัน",
                        "ชัยพัฒนา-เรือนเพาะหญ้าแฝก.jpg",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ลานจอดรถ",
                        "(ยังไม่มีข้อมูล)",
                        "",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ฉก. ตำรวจพลร่ม",
                        "(ยังไม่มีข้อมูล)",
                        "",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "แปลงรวบรวมพันธุ์ไม้หายาก",
                        "บริเวณที่ปลูกพันธุ์ไม้หายาก เช่น ชมพูภูคา เทพทาโร สบู่ดำ นางพญาเสือโคร่ง สะเดาเทียม",
                        "",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "แปลงรวบรวมสายพันธุ์หญ้าแฝก",
                        "(ยังไม่มีข้อมูล)",
                        "",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "สวนพฤกษศาสตร์",
                        "สวนพฤกษศาสตร์มีความสำคัญในด้านเป็นสิ่งจำเป็นสำหรับคนในท้องถิ่นที่จะได้เรียนรู้และรู้จักสังคมพืช และพืชประจำถิ่นของตน รวมทั้งคุณค่าและประโยชน์ของพืชท้องถิ่น เพื่อนำไปสู่การพัฒนาท้องถิ่นอย่างยั่งยืนได้ในอนาคต สวนพฤกษศาสตร์มีส่วนหนึ่งที่ตกแต่งสวยงามด้วยไม้ดอก ที่หมุนเวียนเปลี่ยนไปตลอดปี มองดูเหมือนเป็นสวนไม้ดอกไม้ประดับ แต่แท้จริงแล้วสวนพฤกษศาสตร์เป็นสวนที่ปลูกและรวบรวมพันธุ์พืชนานาชนิดที่พบในธรรมชาติ จำแนก และระบุชนิด ตามหลักพฤกษศาสตร์ มีป้ายบอกแหล่งที่มาของพืช โดยมีจุดประสงค์ให้การศึกษาด้านพฤกษศาสตร์แก่ผู้ชม ดังนั้นจุดประสงค์หลักของสวนพฤกษศาสตร์คือ การให้การศึกษา การอนุรักษ์ และการวิจัยซึ่งเป็นพื้นฐานที่สำคัญในการปรับปรุงพันธุ์พืช",
                        "",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "บ้านพักรับรอง",
                        "เป็นบ้านที่เอาไว้รับรองนักท่องเที่ยวที่มีจุดประสงค์จะนอนพักแต่ไม่ต้องการกางเต็นท์ ในศูนย์ปฏิบัติการจัดการที่ดินชัยพัฒนา-แม่ฟ้าหลวง",
                        "",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "อ่างเก็บน้ำหญ้าแฝก",
                        "อยู่ภายในโครงการจัดการที่ดินชัยพัฒนา-แม่ฟ้าหลวง เป็นอ่างเก็บน้ำที่มีสะพานทอดยาวพุ่งออกไปในน้ำ เป็นอ่างเก็บน้ำที่ส่งน้ำไปไร่นาของเกษตรกรที่ช่วยในเรื่องของการเกษตรต่างๆ และยังเป็นแหล่งถ่ายรูปที่สวยงามของนักท่องเที่ยวที่ไปเที่ยวในศูนย์ปฏิบัติการจัดการที่ดินชัยพัฒนา-แม่ฟ้าหลวง",
                        "",
                        0, 0
                ));
                break;
            case 5:
                mInfoImageView.setImageResource(R.drawable.info_05_w800);

                mInfoMarkerList.add(new InfoMarker(
                        "อ่างเก็บน้ำห้วยมงคล",
                        "(ยังไม่มีข้อมูล)",
                        "",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "จุดชมวิวอ่างเก็บน้ำห้วยมงคล",
                        "สามารถชมวิวอ่างเก็บน้ำได้ 360 องศา",
                        "ห้วยมงคล-จุดชมวิว.jpg",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "ชลประทาน",
                        "เป็นหน่วยงานที่ควบคุมน้ำและดูแลปริมาณน้ำที่อยู่ในอ่างเก็บน้ำห้วยมงคล ใช้เพื่อช่วยให้พืชผลการเกษตรเติบโต บำรุงรักษาภูมิประเทศ และปลูกพืชคืนสภาพดิน เปลี่ยนสภาพในพื้นที่แห้งแล้งระหว่างช่วงฝนตกไม่เพียงพอ นอกเหนือจากนี้ ชลประทานยังมีประโยชน์อื่นในการผลิตพืชผล ซึ่งรวมถึงการปกป้องพืชจากความเย็น การยับยั้งการเติบโตของวัชพืชในไร่ธัญพืช และการช่วยป้องกันดินอัดตัวคายน้ำ ระบบชลประทานยังใช้ยับยั้งฝุ่น การกำจัดสิ่งปฏิกูลและในการเหมืองแร่",
                        "ห้วยมงคล-ชลประทาน.jpg",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "แปลงเกษตร",
                        "(ยังไม่มีข้อมูล)",
                        "",
                        0, 0
                ));
                break;
            case 6:
                mInfoImageView.setImageResource(R.drawable.info_06_w800);

                mInfoMarkerList.add(new InfoMarker(
                        "อ่างเก็บน้ำป่าละอู",
                        "อ่างเก็บน้ำป่าละอู",
                        "ป่าละอู.jpg",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "จุดชมวิวอ่างเก็บน้ำป่าละอู",
                        "มีวิวชายเขา อากาศดี และเห็นวิวของอ่างเก็บน้ำป่าละอูได้รอบๆ มีวิวทิวทัศน์สวยงามตามธรรมชาติ มีน้ำตลอดปี และยังเหมาะแก่การดูพระอาทิตย์ขึ้น นับว่าเป็นทัศนียภาพที่สวยงามมาก",
                        "ป่าละอู-จุดชมวิว.jpg",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "หน่วยพิทักษ์อุทยานแห่งชาติ",
                        "ทำหน้าที่คุ้มครองและรักษาอุทยานแห่งชาติ อุทยานประจำรัฐหรือจังหวัด เขตรักษาพันธุ์สัตว์ป่า พื้นที่คุ้มครองทางธรรมชาติต่างๆ",
                        "ป่าละอู-หน่วยพิทักษ์.jpg",
                        0, 0
                ));
                mInfoMarkerList.add(new InfoMarker(
                        "จุดกางเต็นท์",
                        "เป็นจุดที่เอาไว้ให้นักเที่ยวเที่ยวที่มีจุดประสงค์ที่อยากจะกางเต็นท์ แคมป์ปิ้ง ที่อ่างเก็บน้ำป่าละอู",
                        "ป่าละอู-จุดกางเต็นท์.jpg",
                        0, 0
                ));
                break;
        }
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (getContext() != null) {
            InfoAdapter mAdapter = new InfoAdapter(
                    getContext(),
                    mInfoMarkerList,
                    mListener
            );
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            mRecyclerView.addItemDecoration(new InfoFragment.SpacingDecoration(getContext()));
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InfoFragmentListener) {
            mListener = (InfoFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement InfoFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface InfoFragmentListener {
        void onClickInfoMarker(InfoMarker infoMarker);
    }

    private static class InfoAdapter extends RecyclerView.Adapter<InfoFragment.InfoAdapter.InfoViewHolder> {

        private final Context mContext;
        private final List<InfoMarker> mInfoMarkerList;
        private final InfoFragmentListener mListener;

        InfoAdapter(Context context, List<InfoMarker> infoMarkerList, InfoFragmentListener listener) {
            mContext = context;
            mInfoMarkerList = infoMarkerList;
            mListener = listener;
        }

        @NonNull
        @Override
        public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.item_info_button, parent, false
            );
            return new InfoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
            final InfoMarker infoMarker = mInfoMarkerList.get(position);

            holder.mInfoMarker = infoMarker;
            holder.mInfoButton.setText(
                    String.valueOf(position + 1)
                            .concat(") ")
                            .concat(infoMarker.name)
            );

            /*holder.mNameTextView.setText(project.name);

            CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(mContext);
            circularProgressDrawable.setStrokeWidth(5f);
            circularProgressDrawable.setCenterRadius(30f);
            circularProgressDrawable.start();

            Glide.with(mContext)
                    .load(ApiClient.IMAGE_BASE_URL.concat(project.coverImage))
                    .placeholder(circularProgressDrawable)
                    .into(holder.mProjectImageView);*/
        }

        @Override
        public int getItemCount() {
            return mInfoMarkerList.size();
        }

        class InfoViewHolder extends RecyclerView.ViewHolder {

            private final View mRootView;
            private final Button mInfoButton;

            private InfoMarker mInfoMarker;

            InfoViewHolder(View itemView) {
                super(itemView);

                mRootView = itemView;
                mInfoButton = itemView.findViewById(R.id.info_button);

                mInfoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i(TAG, mInfoMarker.name);
                        mListener.onClickInfoMarker(mInfoMarker);
                    }
                });
            }
        }
    }

    public class SpacingDecoration extends RecyclerView.ItemDecoration {

        private final static int MARGIN_TOP_IN_DP = 0;
        private final static int MARGIN_BOTTOM_IN_DP = 16;
        private final int mMarginTop, mMarginBottom;

        SpacingDecoration(@NonNull Context context) {
            DisplayMetrics metrics = context.getResources().getDisplayMetrics();
            mMarginTop = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    MARGIN_TOP_IN_DP,
                    metrics
            );
            mMarginBottom = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    MARGIN_BOTTOM_IN_DP,
                    metrics
            );
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                                   @NonNull RecyclerView parent,
                                   @NonNull RecyclerView.State state) {
            final int itemPosition = parent.getChildAdapterPosition(view);
            if (itemPosition == RecyclerView.NO_POSITION) {
                return;
            }
            if (itemPosition == 0) {
                outRect.top = mMarginTop;
            }
            final RecyclerView.Adapter adapter = parent.getAdapter();
            if ((adapter != null) && (itemPosition == adapter.getItemCount() - 1)) {
                outRect.bottom = mMarginBottom;
            }
        }
    }
}
