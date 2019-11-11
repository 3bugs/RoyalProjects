package th.ac.dusit.dbizcom.royalprojects.net;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import th.ac.dusit.dbizcom.royalprojects.model.Project;

public class GetProjectResponse extends BaseResponse {

    @SerializedName("data_list")
    public List<Project> projectList;

}