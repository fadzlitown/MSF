package my.edu.upm.msfapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.threeten.bp.Instant;

import java.util.ArrayList;

import my.edu.upm.msfapp.util.Util;

/**
 * Created by mohdfadzli on 4/6/2017.
 *       "Section": {
 "Evaluation": {
 "EvaluationId": 3,
 "Name": "Evaluation 1",
 "Description": "This is the first evaluation",
 "CreatedDate": "2017-05-28T17:18:21.4448286Z",
 "LastUpdatedDate": "2017-05-28T17:18:21.4448286Z"
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
 },
 "ResponseChoiceId": 1,
 "Text": "Yes",
 "Comment": "Comment 1",
 "CreatedDate": "2017-06-04T05:29:41.7409434Z"
 */

public class ResponseChoice {

    @SerializedName("ResponseChoiceId")
    @Expose
    private long ResponseChoiceId;

    @SerializedName("Text")
    @Expose
    private String Text;
    @SerializedName("Comment")
    @Expose
    private String Comment;
    @SerializedName("CreatedDate")
    @Expose
    private Instant CreatedDate;

    public void setResponseChoiceId(long responseChoiceId) {
        ResponseChoiceId = responseChoiceId;
    }

    public long getResponseChoiceId() {
        return ResponseChoiceId;
    }

    public String getText() {
        return Text;
    }

    public String getComment() {
        return Comment;
    }

    public Instant getCreatedDate() {
        return CreatedDate;
    }

    public String toJson() {
        return Util.getGson().toJson(this);
    }

    public static ResponseChoice fromJson(String json) {
        return Util.getGson().fromJson(json, ResponseChoice.class);
    }

    public static ArrayList<ResponseChoice> toList(String json) {
        return Util.getGson().fromJson(json, new TypeToken<ArrayList<ResponseChoice>>() {
        }.getType());
    }

}
