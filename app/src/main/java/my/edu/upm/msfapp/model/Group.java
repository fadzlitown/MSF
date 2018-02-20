package my.edu.upm.msfapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.threeten.bp.Instant;

import java.util.ArrayList;

import my.edu.upm.msfapp.util.Util;

/**
 * Created by mohdfadzli on 4/6/2017.
 *   {
 "Evaluation": {
 "EvaluationId": 3,
 "Name": "Evaluation 1",
 "Description": "This is the first evaluation",
 "CreatedDate": "2017-05-28T17:18:21.4448286Z",
 "LastUpdatedDate": "2017-05-28T17:18:21.4448286Z"
 },
 "GroupId": 5,
 "Name": "Group 1",
 "WardName": "Ward tingkat 1",
 "CreatedDate": "2017-06-04T05:08:36.3850363Z",
 "LastUpdatedDate": "2017-06-04T05:18:17.5278347Z",
 "MemberList": [

 ]
 }
 */

public class Group {
    @SerializedName("Evaluation")
    @Expose
    private Evaluation Evaluation;
    @SerializedName("GroupId")
    @Expose
    private long GroupId;
    @SerializedName("Name")
    @Expose
    private String Name;
    @SerializedName("WardName")
    @Expose
    private String WardName;
    @SerializedName("CreatedDate")
    @Expose
    private Instant CreatedDate;
    @SerializedName("LastUpdatedDate")
    @Expose
    private Instant LastUpdatedDate;
    @SerializedName("MemberList")
    @Expose
    private ArrayList<User> MemberList;

    public my.edu.upm.msfapp.model.Evaluation getEvaluation() {
        return Evaluation;
    }

    public long getGroupId() {
        return GroupId;
    }

    public String getName() {
        return Name;
    }

    public String getWardName() {
        return WardName;
    }

    public Instant getCreatedDate() {
        return CreatedDate;
    }

    public Instant getLastUpdatedDate() {
        return LastUpdatedDate;
    }

    public ArrayList<User> getMemberList() {
        return MemberList;
    }

    public String toJson() {
        return Util.getGson().toJson(this);
    }

    public static Group fromJson(String json) {
        return Util.getGson().fromJson(json, Group.class);
    }

    public static ArrayList<Group> toList(String json) {
        return Util.getGson().fromJson(json, new TypeToken<ArrayList<Group>>() {
        }.getType());
    }
}
