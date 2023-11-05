package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DDUserTests {

    @Test(priority = 1,dataProvider = "getAllUserData", dataProviderClass = DataProviders.class)
    public void testPostUser(String userID, String userName, String fName, String lName,String userEmail, String pwd, String ph){

        User user = new User();
        user.setId(Integer.parseInt(userID));
        user.setUsername(userName);
        user.setFirstName(fName);
        user.setLastName(lName);
        user.setEmail(userEmail);
        user.setPassword(pwd);
        user.setPhone(ph);


        Response response = UserEndPoints.createUser(user);
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUser(String userName)
    {
        Response response = UserEndPoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(),200);
    }
}