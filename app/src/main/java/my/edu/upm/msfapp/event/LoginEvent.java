package my.edu.upm.msfapp.event;

import my.edu.upm.msfapp.model.User;

public class LoginEvent {

    public static class OnLogin {
        public User user;

        public OnLogin(User user) {
            this.user = user;
        }

        public User getUser() {
            return user;
        }

    }

    public static class OnFail {

    }

    public static class OnLogout{
    }

    public static class OnLogoutFail{
    }

}