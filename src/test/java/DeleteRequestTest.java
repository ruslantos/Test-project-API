import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import response.PostsResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteRequestTest {

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.basePath = "/posts";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }

    @Epic("Placeholder")
    @Feature("DELETE request")
    @Description("Verify DELETE request")
    @Test
    public void deleteRequestTest() {
        PostsResponse response =
                given().contentType(ContentType.JSON).accept(ContentType.JSON).
                        filter(new AllureRestAssured()).
                        when().
                        delete("/1").
                        then().
                        assertThat().statusCode(200).
                        extract().response().as(PostsResponse.class);
        assertThat(response.getId(), is(nullValue()));
    }
}