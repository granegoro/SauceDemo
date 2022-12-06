package data;

import lombok.Value;

public class DataHelper {

    public static String getPassword() {
        return ("secret_sauce");
    }

    public static String getStandardUserLogin() {
        return ("standard_user");
    }

    public static String getLockedOutUserLogin() {
        return ("locked_out_user");
    }

    public static String getProblemUserLogin() {
        return ("problem_user");
    }

    public static String getPerformanceGlitchUserLogin() {
        return ("performance_glitch_use");
    }


    public static class Auth {
        private Auth() {
        }

        public static UserInfo getStandardUser() {

            return new UserInfo(
                    getStandardUserLogin(),
                    getPassword()
            );
        }

        public static UserInfo getLockedOutUser() {

            return new UserInfo(
                    getLockedOutUserLogin(),
                    getPassword()
            );
        }



        @Value
        public static class UserInfo {
            String login;
            String password;
        }

    }
}
