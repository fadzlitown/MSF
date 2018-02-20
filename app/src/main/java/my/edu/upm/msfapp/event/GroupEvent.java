package my.edu.upm.msfapp.event;

import java.util.ArrayList;

import my.edu.upm.msfapp.model.Group;

public class GroupEvent {

    public static class GroupList {
        private ArrayList<Group> visitors;

        public GroupList(ArrayList<Group> visitors) {
            this.visitors = visitors;
        }

        public ArrayList<Group> getGroupList() {
            return visitors;
        }
    }

    public static class GroupListFailed {

    }

}