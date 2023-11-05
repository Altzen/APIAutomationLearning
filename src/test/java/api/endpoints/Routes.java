package api.endpoints;

public class Routes {

    public static String base_url = "https://petstore.swagger.io/v2";

    //User module

    public static String post_User_url = base_url + "/user";
    public static String get_User_url = base_url + "/user/{username}";
    public static String update_User_url = base_url + "/user/{username}";
    public static String delete_User_url = base_url + "/user/{username}";
    public static String post_User_createWithArray_url = post_User_url + "/createWithArray";
    public static String post_User_createWithList_url = post_User_url + "/createWithList";
    public static String get_login_url = post_User_url + "/login";
    public static String get_logout_url = post_User_url + "/logout";


    //Store module

    //Pet module
    public static String post_Pet_url = base_url + "/pet";
    public static String put_Pet_url = base_url + "/pet";
    public static String get_Pet_url = post_Pet_url + "/{petId}";
    public static String delete_Pet_url = post_Pet_url + "/{petId}";
    public static String post_Pet_UploadImage_url = get_Pet_url + "/uploadImage";
    public static String post_Pet_UpdateNameStatus_url = get_Pet_url;

}
