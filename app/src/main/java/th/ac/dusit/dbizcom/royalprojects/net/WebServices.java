package th.ac.dusit.dbizcom.royalprojects.net;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebServices {

    @GET("get_project")
    Call<GetProjectResponse> getProject(
    );

    /*@FormUrlEncoded
    @POST("add_rating")
    Call<AddRatingResponse> addRating(
            @Field("id") int itemId,
            @Field("type") String itemType,
            @Field("rate") int rate
    );*/

}