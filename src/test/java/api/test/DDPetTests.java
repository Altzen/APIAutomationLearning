package api.test;

import api.endpoints.PetEndPoints;
import api.payload.Category;
import api.payload.Pet;
import api.payload.Tag;
import api.utilities.DataProviders;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class DDPetTests {

    @Test(priority = 1,dataProvider = "getAllPetsData",dataProviderClass = DataProviders.class)
    public void testPostPet(String id,String categoryId,String categoryName,String petName, String photoUrl,String tagId, String tagName,String status){

        Category category = new Category();
        category.setId(Integer.parseInt(categoryId));
        category.setName(categoryName);

        Tag tag = new Tag();
        tag.setId(Integer.parseInt(tagId));
        tag.setName(tagName);
        ArrayList<Tag> tags = new ArrayList<>();
        tags.add(tag);

        Pet pet = new Pet();
        pet.setId(Integer.parseInt(id));
        pet.setCategory(category);
        pet.setName(petName);
        ArrayList<String> photoUrls = new ArrayList<>();
        photoUrls.add(photoUrl);
        pet.setPhotoUrls(photoUrls);

        pet.setTags(tags);
        pet.setStatus(status);

        Response response = PetEndPoints.createPet(pet);
        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 2, dataProvider = "PetsIDs",dataProviderClass = DataProviders.class)
    public void testImageUpload(String petID){

        DataProviders data = new DataProviders();

        Response response = PetEndPoints.updateImage(Integer.parseInt(petID),data.getImage());
        Assert.assertEquals(response.getStatusCode(),200);

    }
    @Test(priority = 3, dataProvider = "PetsIDs",dataProviderClass = DataProviders.class)
    public void testGetPet(String petID){
        Response response = PetEndPoints.readPet(Integer.parseInt(petID));
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 4, dataProvider = "PetsIDs",dataProviderClass = DataProviders.class)
    public void editPetStatusAndName(String petID){
        Faker faker = new Faker();
        Response response = PetEndPoints.updateStatusAndName(Integer.parseInt(petID), faker.name().username(), "active");

        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 5, dataProvider = "PetsIDs", dataProviderClass = DataProviders.class)
    public void testDeletePet(String petID)
    {
        Response response = PetEndPoints.deletePet(Integer.parseInt(petID));
        Assert.assertEquals(response.getStatusCode(),200);
    }


}