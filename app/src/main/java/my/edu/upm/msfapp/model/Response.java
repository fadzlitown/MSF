package my.edu.upm.msfapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.threeten.bp.Instant;

import my.edu.upm.msfapp.util.Util;

/**
 * Created by mohdfadzli on 4/6/2017.
 *   "Evaluator": {

 },
 "Evaluatee": {

 },
 "ResponseId": 1,
 "StartDate": "2017-06-04T05:34:50.4129562Z",
 "StartTime": "05:34:50.4129562",
 "Group": {
 "Evaluation": {
 },
 "GroupId": 5,
 "Name": "Group 1",
 "WardName": "Ward tingkat 1",
 "CreatedDate": "2017-06-04T05:08:36.3850363Z",
 "LastUpdatedDate": "2017-06-04T05:18:17.5278347Z",
 "MemberList": [
 {

 }
 ]
 }
 */

public class Response {
    @SerializedName("Evaluator")
    @Expose
    private User Evaluator;
    @SerializedName("Evaluatee")
    @Expose
    private User Evaluatee;
    @SerializedName("ResponseId")
    @Expose
    private long ResponseId;

    @SerializedName("StartDate")
    @Expose
    private Instant StartDate;
    @SerializedName("StartTime")
    @Expose
    private String StartTime;
    @SerializedName("EndDate")
    @Expose
    private Instant EndDate;
    @SerializedName("EndTime")
    @Expose
    private String EndTime;
    @SerializedName("Group")
    @Expose
    private Group Group;

    public void setResponseId(long responseId) {
        ResponseId = responseId;
    }

    public User getEvaluator() {
        return Evaluator;
    }

    public User getEvaluatee() {
        return Evaluatee;
    }

    public long getResponseId() {
        return ResponseId;
    }

    public Instant getStartDate() {
        return StartDate;
    }

    public String getStartTime() {
        return StartTime;
    }

    public Instant getEndDate() {
        return EndDate;
    }

    public String getEndTime() {
        return EndTime;
    }

    public my.edu.upm.msfapp.model.Group getGroup() {
        return Group;
    }

    public String toJson() {
        return Util.getGson().toJson(this);
    }

    public static Response fromJson(String json) {
        return Util.getGson().fromJson(json, Response.class);
    }
}
