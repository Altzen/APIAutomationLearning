package api.test;

import api.endpoints.UserEndPoints;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class POSTUserArrayTest {

    @Test(priority = 1)
    public void testPostUsers() throws IOException {
        DataProviders dataProviders = new DataProviders();

        Response response = UserEndPoints.createUserWithArray(dataProviders.getUsersFromFile());
        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void testDeleteUser(String userName)
    {
        Response response = UserEndPoints.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
