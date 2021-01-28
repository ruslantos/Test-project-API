import base.TestHelper;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
//import static org.testng.Assert.*;
import response.PostsResponse;

public class GetRequestTest {

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.basePath = "/posts/";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }

    @Epic("Placeholder")
    @Feature("GET request")
    @Description("Verify get request")
    @Test
    public void getRequest() {
        PostsResponse response =
                given().contentType(ContentType.JSON).accept(ContentType.JSON).
                        filter(new AllureRestAssured()).
                        when().
                        get(String.valueOf(1)).
                        then().
                        assertThat().statusCode(200).
                        extract().response().as(PostsResponse.class);

        assertThat("User id", response.getUserId(), equalTo(1));
        assertThat("Title", response.getTitle(), containsString("sunt aut facere"));
    }
}