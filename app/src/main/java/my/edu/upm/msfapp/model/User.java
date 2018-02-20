package my.edu.upm.msfapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import org.threeten.bp.Instant;

import java.util.ArrayList;

import my.edu.upm.msfapp.util.Util;

/**
 * Created by mohdfadzli on 28/5/2017.
 *
 {
 "UserId": "eb097737-6319-4ffe-8c99-419817bfb85a",
 "UserName": "165026",
 "FullName": "Fadzli Name",
 "ContactNumber": "0377000000",
 "CreatedDate": "0001-01-01T00:00:00Z",
 "AccessToken": "r2rWVvUQLgsXfzPgSwz2ZGv1XIPOunKkK8DtMuw3f9-V9X7ratAk41myf9tuDWYQMM1SwKdBJWEBEDJ_C9E6cg",
 "Role": 5,
 "IsEvaluated": false
 }
 */

public class User {
    @SerializedName("UserId")
    @Expose
    private String UserId;
    @SerializedName("UserName")
    @Expose
    private String UserName;
    @SerializedName("FullName")
    @Expose
    private String FullName;

    @SerializedName("ContactNumber")
    @Expose
    private String ContactNumber;
    @SerializedName("CreateDate")
    @Expose
    private Instant CreateDate;
    @SerializedName("AccessToken")
    @Expose
    private String AccessToken;
    @SerializedName("Role")
    @Expose
    private int Role;
    @SerializedName("IsEvaluated")
    @Expose
    private boolean IsEvaluated;

    @SerializedName("Group")
    @Expose
    private Group Group;

    public String getUserId() {
        return UserId;
    }

    public String getUserName() {
        return UserName;
    }

    public String getFullName() {
        return FullName;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public Instant getCreateDate() {
        return CreateDate;
    }

    public int getRole() {
        return Role;
    }

    public String getAccessToken() {
        return AccessToken;
    }

    public boolean isEvaluated() {
        return IsEvaluated;
    }

    public my.edu.upm.msfapp.model.Group getGroup() {
        return Group;
    }

    public String toJson() {
        return Util.getGson().toJson(this);
    }

    public static User fromJson(String json) {
        return Util.getGson().fromJson(json, User.class);
    }

    public static ArrayList<User> toList(String json) {
        return Util.getGson().fromJson(json, new TypeToken<ArrayList<User>>() {
        }.getType());
    }
}
