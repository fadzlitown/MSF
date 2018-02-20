package my.edu.upm.msfapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.threeten.bp.Instant;

import java.util.ArrayList;

import my.edu.upm.msfapp.util.Util;

/**
 * Created by mohdfadzli on 29/5/2017.
 *     "EvaluationId": 4,
 "Name": "Evaluation 2",
 "Description": "This is the second evaluation",
 "CreatedDate": "2017-05-28T17:18:27.8918491Z",
 "LastUpdatedDate": "2017-05-28T17:18:27.8918491Z"
 */

public class Evaluation {
    @SerializedName("EvaluationId")
    @Expose
    private long EvaluationId;
    @SerializedName("Name")
    @Expose
    private String Name;
    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("CreatedDate")
    @Expose
    private Instant CreatedDate;
    @SerializedName("LastUpdatedDate")
    @Expose
    private Instant LastUpdatedDate;
    @SerializedName("SectionList")
    @Expose
    private ArrayList<Section> SectionList;

    public long getEvaluationId() {
        return EvaluationId;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public Instant getCreatedDate() {
        return CreatedDate;
    }

    public Instant getLastUpdatedDate() {
        return LastUpdatedDate;
    }

    public ArrayList<Section> getSectionList() {
        return SectionList;
    }

    public String toJson() {
        return Util.getGson().toJson(this);
    }

    public static Evaluation fromJson(String json) {
        return Util.getGson().fromJson(json, Evaluation.class);
    }

    public static ArrayList<Evaluation> toList(String json) {
        return Util.getGson().fromJson(json, new TypeToken<ArrayList<Evaluation>>() {
        }.getType());
    }

}
