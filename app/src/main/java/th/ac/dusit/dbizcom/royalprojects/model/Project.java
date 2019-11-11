package th.ac.dusit.dbizcom.royalprojects.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Project {

    @SerializedName("id")
    public final int id;
    @SerializedName("name")
    public final String name;
    @SerializedName("address")
    public final String address;
    @SerializedName("details")
    public final String details;
    @SerializedName("phone")
    public final String phone;
    @SerializedName("opening_time")
    public final String openingTime;
    @SerializedName("latitude")
    public final double latitude;
    @SerializedName("longitude")
    public final double longitude;
    @SerializedName("cover_image")
    public final String coverImage;
    @SerializedName("image_list")
    public final List<String> galleryImages;

    public Project(int id, String name, String address, String details, String phone,
                   String openingTime, double latitude, double longitude, String coverImage,
                   List<String> galleryImages) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.details = details;
        this.phone = phone;
        this.openingTime = openingTime;
        this.latitude = latitude;
        this.longitude = longitude;
        this.coverImage = coverImage;
        this.galleryImages = galleryImages;
    }

    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
