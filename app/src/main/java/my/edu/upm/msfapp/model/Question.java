package my.edu.upm.msfapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.threeten.bp.Instant;

import java.util.ArrayList;

import my.edu.upm.msfapp.util.Util;

/**
 * Created by mohdfadzli on 29/5/2017.
 *   "QuestionId": 1,
 "Text": "Question 1",
 "TextBoxFlag": true,
 "CreatedDate": "2017-05-28T17:22:41.4504731Z"
 */

public class Question {

    @SerializedName("QuestionId")
    @Expose
    private long QuestionId;
    @SerializedName("Text")
    @Expose
    private String Text;
    @SerializedName("TextBoxFlag")
    @Expose
    private boolean TextBoxFlag;
    @SerializedName("CreatedDate")
    @Expose
    private Instant CreatedDate;

    public void setQuestionId(long questionId) {
        QuestionId = questionId;
    }

    public long getQuestionId() {
        return QuestionId;
    }

    public String getText() {
        return Text;
    }

    public boolean isTextBoxFlag() {
        return TextBoxFlag;
    }

    public Instant getCreatedDate() {
        return CreatedDate;
    }

    public String toJson() {
        return Util.getGson().toJson(this);
    }

    public static Question fromJson(String json) {
        return Util.getGson().fromJson(json, Question.class);
    }

    public static ArrayList<Question> toList(String json) {
        return Util.getGson().fromJson(json, new TypeToken<ArrayList<Question>>() {
        }.getType());
    }
}
