package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {

    Faker faker;
    User user;
    public Logger logger;

    @BeforeClass
    public void setup() {
        faker = new Faker();
        user = new User();

        user.setId(faker.idNumber().hashCode());
        user.setUsername(faker.name().username());
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().safeEmailAddress());
        user.setPassword(faker.internet().password(5, 10));
        user.setPhone(faker.phoneNumber().cellPhone());

        //logs
        logger = LogManager.getLogger(this.getClass());

    }

    @Test(priority = 1)
    public void testPostUser() {
        logger.info("||||||||| Creating User |||||||||");
        Response response = UserEndPoints.createUser(user);

        Assert.assertEquals(response.getStatusCode(), 200);
        logger.info("||||||||| User is Created |||||||||");
    }

    @Test(priority = 2)
    public void testGetUserByName() {
        Response response = UserEndPoints.readUser(this.user.getUsername());

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 3)
    public void testLogInOut() {
        Response loginResponse = UserEndPoints.userLogin(user.getUsername(), user.getPassword());
        loginResponse.then().log().all();
        Assert.assertEquals(loginResponse.getStatusCode(), 200);
        Response logoutResponse = UserEndPoints.userLogout();
        Assert.assertEquals(logoutResponse.getStatusCode(), 200);
    }

    @Test(priority = 4)
    public void testUpdateUserByName() {
        user.setFirstName(faker.name().firstName());
        user.setLastName(faker.name().lastName());
        user.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndPoints.updateUser(user.getUsername(), user);

        Assert.assertEquals(response.getStatusCode(), 200);

        Response responseAfterUpdate = UserEndPoints.readUser(this.user.getUsername());
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
        User responseUser = responseAfterUpdate.getBody().as(User.class);
        Assert.assertEquals(responseUser.getFirstName(), user.getFirstName());
        Assert.assertEquals(responseUser.getLastName(), user.getLastName());
        Assert.assertEquals(responseUser.getEmail(), user.getEmail());

    }

    @Test(priority = 5)
    public void testDeleteUserByName() {
        Response response = UserEndPoints.deleteUser(user.getUsername());
        Assert.assertEquals(response.getStatusCode(), 200);
    }
}
