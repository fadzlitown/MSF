package my.edu.upm.msfapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.threeten.bp.Instant;

import java.util.ArrayList;

import my.edu.upm.msfapp.util.Util;

/**
 * Created by mohdfadzli on 29/5/2017.
 * {
 "Evaluation": {
 },
 "SectionId": 1,
 "Name": "Section 1",
 "Description": "This is the first section",
 "CreatedDate": "2017-05-28T17:19:07.9560452Z",
 "LastUpdatedDate": "2017-05-28T17:19:07.9560452Z",
 "QuestionList": [
 {
 "QuestionId": 1,
 "Text": "Question 1",
 "TextBoxFlag": true,
 "CreatedDate": "2017-05-28T17:22:41.4504731Z"
 },
 {
 "QuestionId": 2,
 "Text": "Question 2",
 "TextBoxFlag": true,
 "CreatedDate": "2017-05-28T17:22:45.0717898Z"
 }
 ],
 "IsDelete": false
 }
 */

public class Section {
    @SerializedName("SectionId")
    @Expose
    private long SectionId;
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
    @SerializedName("QuestionList")
    @Expose
    private ArrayList<Question> QuestionList;

    public long getSectionId() {
        return SectionId;
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

    public ArrayList<Question> getQuestionList() {
        return QuestionList;
    }

    public String toJson() {
        return Util.getGson().toJson(this);
    }

    public static Section fromJson(String json) {
        return Util.getGson().fromJson(json, Section.class);
    }

    public static ArrayList<Section> toList(String json) {
        return Util.getGson().fromJson(json, new TypeToken<ArrayList<Section>>() {
        }.getType());
    }
}
