package karawana.web.controller;


public class SESSION_VAR {


    public static final String GROUP_ID = "groupId";
    public static final String USER_ID = "userId";
    public static final String USER_NAME = "userName";
    public static final String SESSION_ID = "sessionId";
    public static final String TEST_STATIC_NAME = "staticName";


    public static String latestLocations(Long groupId){
        return "latestLocations" + groupId;
    }
}
