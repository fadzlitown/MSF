package my.edu.upm.msfapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.threeten.bp.Instant;

import java.util.ArrayList;

import my.edu.upm.msfapp.util.Util;

/**
 * Created by mohdfadzli on 8/6/2017.
 *     "ResponseChoiceId": 2,
 "Text": "No",
 "Comment": "Comment 2",
 "CreatedDate": "2017-06-04T05:30:06.8471012Z"
 */

public class Answer {
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

    public static Answer fromJson(String json) {
        return Util.getGson().fromJson(json, Answer.class);
    }

    public static ArrayList<Answer> toList(String json) {
        return Util.getGson().fromJson(json, new TypeToken<ArrayList<Answer>>() {
        }.getType());
    }
}
