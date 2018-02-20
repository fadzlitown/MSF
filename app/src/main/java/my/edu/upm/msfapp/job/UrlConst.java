package my.edu.upm.msfapp.job;

import android.net.Uri;


public class UrlConst {

    static final String HTTP_CONTENT_TYPE = "Content-Type";
    static final String TYPE_JSON = "application/json";

    static final String SERVER_URL = "http://medeval.azurewebsites.net";
    private static final String API_DEVICE = SERVER_URL + "/Api/Device/";
    private static final String API_ACCOUNT = SERVER_URL + "/Api/Account";
    private static final String API_GROUP = SERVER_URL + "/Api/Group";
    private static final String API_Evaluation = SERVER_URL + "/Api/Evaluation";
    private static final String API_Section = SERVER_URL + "/Api/Section";
    private static final String API_Question = SERVER_URL + "/Api/Question";
    private static final String API_Response = SERVER_URL + "/Api/Response";

    private static final String API_ResponseChoice = SERVER_URL + "/Api/ResponseChoice";





    public static String getBaseUrl() {
        return SERVER_URL;
    }

    public static String registerDevice() {
        return Uri.parse(API_DEVICE).buildUpon()
                .appendPath("Register")
                .toString();
    }

    static String registerUser(){
        return Uri.parse(API_ACCOUNT).buildUpon()
                .appendPath("Register")
                .toString();
    }

    static String login() {
        return Uri.parse(API_ACCOUNT).buildUpon()
                .appendPath("Login")
                .toString();
    }

    static String logout(String token) {
        return Uri.parse(API_ACCOUNT).buildUpon()
                .appendPath("Logout")
                .appendQueryParameter("accessToken", token)
                .toString();
    }

    static String getProfile(String token) {
        return Uri.parse(API_ACCOUNT).buildUpon()
                .appendPath("Get")
                .appendQueryParameter("accessToken", token)
                .toString();
    }

    static String updateProfile(String token) {
        return Uri.parse(API_ACCOUNT).buildUpon()
                .appendPath("Update")
                .appendQueryParameter("accessToken", token)
                .toString();
    }

    static String getMemberList(String token, int take, int skip) {
        return Uri.parse(API_ACCOUNT).buildUpon()
                .appendPath("GetMemberList")
                .appendQueryParameter("accessToken", token)
                .appendQueryParameter("take", String.valueOf(take))
                .appendQueryParameter("skip", String.valueOf(skip))
                .toString();
    }


    static String getStudentList(String token, int take, int skip, int filterType) {
        return Uri.parse(API_ACCOUNT).buildUpon()
                .appendPath("GetStudentList")
                .appendQueryParameter("accessToken", token)
                .appendQueryParameter("filterType", String.valueOf(filterType))
                .appendQueryParameter("take", String.valueOf(take))
                .appendQueryParameter("skip", String.valueOf(skip))
                .toString();
    }

    //Group
    static String getGroupList(String token, int take, int skip) {
        return Uri.parse(API_GROUP).buildUpon()
                .appendPath("GetList")
                .appendQueryParameter("accessToken", token)
                .appendQueryParameter("take", String.valueOf(take))
                .appendQueryParameter("skip", String.valueOf(skip))
                .toString();
    }

    public static String getEvaluationList(String token, int take, int skip) {
        return Uri.parse(API_Evaluation).buildUpon()
                .appendPath("GetList")
                .appendQueryParameter("accessToken", token)
                .appendQueryParameter("take", String.valueOf(take))
                .appendQueryParameter("skip", String.valueOf(skip))
                .toString();

    }

    public static String getSectionList(String token,long id,  int take, int skip) {
        return Uri.parse(API_Section).buildUpon()
                .appendPath("GetList")
                .appendQueryParameter("accessToken", token)
                .appendQueryParameter("evaluationId", String.valueOf(id))
                .appendQueryParameter("take", String.valueOf(take))
                .appendQueryParameter("skip", String.valueOf(skip))
                .toString();

    }

    public static String getQuestionList(String token, long sectionId, int take, int skip) {
        return Uri.parse(API_Question).buildUpon()
                .appendPath("GetList")
                .appendQueryParameter("accessToken", token)
                .appendQueryParameter("sectionId", String.valueOf(sectionId))
                .appendQueryParameter("take", String.valueOf(take))
                .appendQueryParameter("skip", String.valueOf(skip))
                .toString();
    }


    public static String startEvaluation(String token, String groupId, String evalId, String username) {
        return Uri.parse(API_Response).buildUpon()
                .appendPath("Start")
                .appendQueryParameter("accessToken", token)
                .appendQueryParameter("groupdId", groupId)
                .appendQueryParameter("evaluationId", evalId)
                .appendQueryParameter("userName", username)
                .toString();
    }

    public static String submitEvaluation(String token) {
        return Uri.parse(API_Response).buildUpon()
                .appendPath("Submit")
                .appendQueryParameter("accessToken", token)
                .toString();
    }

    public static String endEvaluation(String token, long responseId) {
        return Uri.parse(API_Response).buildUpon()
                .appendPath("End")
                .appendQueryParameter("accessToken", token)
                .appendQueryParameter("responseId", String.valueOf(responseId))
                .toString();
    }

    public static String getResponseChoiceList(String token, long evalId, int take, int skip) {
        return Uri.parse(API_ResponseChoice).buildUpon()
                .appendPath("GetList")
                .appendQueryParameter("accessToken", token)
                .appendQueryParameter("evaluationId", String.valueOf(evalId))
                .appendQueryParameter("take", String.valueOf(take))
                .appendQueryParameter("skip", String.valueOf(skip))
                .toString();
    }
}
