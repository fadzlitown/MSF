package my.edu.upm.msfapp.event;

import my.edu.upm.msfapp.model.Member;

/**
 * Created by mohdfadzli on 1/6/2017.
 */

public class UserEvent {

    public static class MemberList {
        private Member visitors;

        public MemberList(Member visitors) {
            this.visitors = visitors;
        }


        public Member getMemberList() {
            return visitors;
        }
    }

    public static class MemberListFailed {

    }

    public static class MemberUpdateList {
        private Member visitors;

        public MemberUpdateList(Member visitors) {
            this.visitors = visitors;
        }


        public Member getMemberList() {
            return visitors;
        }
    }

    public static class MemberUpdateListFailed {

    }

}
