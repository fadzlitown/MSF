package my.edu.upm.msfapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import my.edu.upm.msfapp.util.Util;

/**
 * Created by mohdfadzli on 8/6/2017.
 * 	{
 "Response":
 {
 "ResponseId":1
 },
 "Question":
 {
 "QuestionId":1
 },
 "ResponseChoice":
 {
 "ResponseChoiceId":1
 },
 "Comment":""
 }
 */

public class Submit {

    private long id;

    @SerializedName("Response")
    @Expose
    private Response Response;

    @SerializedName("Question")
    @Expose
    private Question Question;

    @SerializedName("ResponseChoice")
    @Expose
    private ResponseChoice ResponseChoice;

    @SerializedName("Comment")
    @Expose
    private String Comment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public my.edu.upm.msfapp.model.Response getResponse() {
        return Response;
    }

    public void setResponse(my.edu.upm.msfapp.model.Response response) {
        Response = response;
    }

    public my.edu.upm.msfapp.model.Question getQuestion() {
        return Question;
    }

    public void setQuestion(my.edu.upm.msfapp.model.Question question) {
        Question = question;
    }

    public my.edu.upm.msfapp.model.ResponseChoice getResponseChoice() {
        return ResponseChoice;
    }

    public void setResponseChoice(my.edu.upm.msfapp.model.ResponseChoice responseChoice) {
        ResponseChoice = responseChoice;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String toJson() {
        return Util.getGson().toJson(this);
    }

    public static Submit fromJson(String json) {
        return Util.getGson().fromJson(json, Submit.class);
    }

    public static ArrayList<Submit> toList(String json) {
        return Util.getGson().fromJson(json, new TypeToken<ArrayList<Submit>>() {
        }.getType());
    }
}
