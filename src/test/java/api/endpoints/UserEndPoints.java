package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserEndPoints {

    public static Response createUser(User user) {

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(user)
                .when()
                .post(Routes.post_User_url);
    }

    public static Response readUser(String username) {

        return given()
                .pathParam("username",username)
                .accept(ContentType.JSON)
                .when()
                .get(Routes.get_User_url);
    }

    public static Response updateUser(String username,User user) {

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",username)
                .body(user)
                .when()
                .put(Routes.update_User_url);
    }

    public static Response deleteUser(String username) {

        return given()
                .pathParam("username",username)
                .accept(ContentType.JSON)
                .when()
                .delete(Routes.delete_User_url);
    }

    public static Response createUserWithArray(User[] users){
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(users)
                .when()
                .post(Routes.post_User_createWithArray_url);
    }

    public static Response userLogin(String username, String password){
        return given()
                .accept(ContentType.JSON)
                .header("username",username)
                .header("password",password)
                .when()
                .get(Routes.get_login_url);
    }

    public static Response userLogout(){
        return given()
                .accept(ContentType.JSON)
                .when()
                .get(Routes.get_logout_url);
    }
}
