package api.endpoints;

import api.payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;
public class PetEndPoints {

    public static Response readPet(int petId){
        return given()
                .pathParam("petId",petId)
                .accept(ContentType.JSON)
                .when()
                .get(Routes.get_Pet_url);
    }

    public static Response createPet(Pet pet){
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(pet)
                .when()
                .post(Routes.post_Pet_url);
    }
    public static Response deletePet(int petID){
        return given()
                .accept(ContentType.JSON)
                .pathParam("petId",petID)
                .when()
                .delete(Routes.delete_Pet_url);
    }

    public static Response updateImage(int id, File file){
        return given()
                .accept(ContentType.JSON)
                .contentType(ContentType.MULTIPART)
                .pathParam("petId", id)
                .multiPart("file", file)
                .when()
                .post(Routes.post_Pet_UploadImage_url);
    }

    public static Response updateStatusAndName(int id, String name, String status){
        return given()
                .accept(ContentType.JSON)
                .pathParam("petId", id)
                .formParam("name",name)
                .formParam("status", status)
                .when()
                .post(Routes.post_Pet_UpdateNameStatus_url);
    }
}
