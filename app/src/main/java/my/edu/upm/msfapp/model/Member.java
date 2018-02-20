package my.edu.upm.msfapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import my.edu.upm.msfapp.util.Util;

/**
 * Created by mohdfadzli on 11/6/2017.
 */

public class Member {
    @SerializedName("Group")
    @Expose
    private Group Group;
    @SerializedName("Member")
    @Expose
    private ArrayList<User> Member;

    public my.edu.upm.msfapp.model.Group getGroup() {
        return Group;
    }

    public void setGroup(my.edu.upm.msfapp.model.Group group) {
        Group = group;
    }

    public ArrayList<User> getMember() {
        return Member;
    }

    public void setMember(ArrayList<User> member) {
        Member = member;
    }

    public String toJson() {
        return Util.getGson().toJson(this);
    }

    public static Member fromJson(String json) {
        return Util.getGson().fromJson(json, Member.class);
    }

}
