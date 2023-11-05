package api.utilities;

import api.payload.User;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "getAllUserData")
    public String[][] getAllUserData() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        XLUtility xl = new XLUtility(path);

        int rownum = xl.getRowCount("Sheet1");
        int colcount = xl.getCellCount("Sheet1", 1);

        String[][] apidata = new String[rownum][colcount];

        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colcount; j++) {
                apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
            }

        }
        return apidata;
    }

    @DataProvider(name = "UserNames")
    public String[] getUserNames() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        XLUtility xl = new XLUtility(path);

        int rownum = xl.getRowCount("Sheet1");

        String[] apidata = new String[rownum];

        for (int i = 1; i <= rownum; i++) {
            apidata[i - 1] = xl.getCellData("Sheet1", i, 1);
        }
        return apidata;

    }

    @DataProvider(name = "getAllPetsData")
    public String[][] getAllPetsData() throws IOException{
    String path = System.getProperty("user.dir") + "//testData//Petdata.xlsx";
    XLUtility xl = new XLUtility(path);

    int rownum = xl.getRowCount("Sheet1");
    int colcount = xl.getCellCount("Sheet1", 1);

    String[][] apidata = new String[rownum][colcount];

        for (int i = 1; i <= rownum; i++) {
        for (int j = 0; j < colcount; j++) {
            apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
        }

    }
        return apidata;
}

    @DataProvider(name = "PetsIDs")
    public String[] getPetsIDs() throws IOException {
        String path = System.getProperty("user.dir") + "//testData//Petdata.xlsx";
        XLUtility xl = new XLUtility(path);

        int rownum = xl.getRowCount("Sheet1");

        String[] apidata = new String[rownum];

        for (int i = 1; i <= rownum; i++) {
            apidata[i - 1] = xl.getCellData("Sheet1", i, 0);
        }
        return apidata;

    }

    public File getImage() {
        String path = System.getProperty("user.dir") + "//testData//Kenchan.jpg";
        return new File(path);
    }

    public User[] getUsersFromFile() throws IOException {
        String[][] mass = this.getAllUserData();
        User[] users = new User[mass.length];
        for (int i = 0; i < mass.length; i++) {
            String[] elem = mass[i];
            users[i] = new User();
            users[i].setId(Integer.parseInt(elem[0]));
            users[i].setUsername(elem[1]);
            users[i].setFirstName(elem[2]);
            users[i].setLastName(elem[3]);
            users[i].setEmail(elem[4]);
            users[i].setPassword(elem[5]);
            users[i].setPhone(elem[6]);
        }
        return users;
    }
}
