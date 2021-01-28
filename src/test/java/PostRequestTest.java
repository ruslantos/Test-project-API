import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import response.PostsResponse;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostRequestTest {

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.basePath = "/posts";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Epic("Placeholder")
    @Feature("POST request")
    @Description("Verify POST request")
    @Test
    public void postRequestTest() {
        int postId = 77;
        String title = "Test title";
        String body = "Test body";
        Map<String, Object> putPost = new HashMap<>();
        putPost.put("userId", postId);
        putPost.put("title", title);
        putPost.put("body", body);

        PostsResponse response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(putPost)
                .filter(new AllureRestAssured())
                .when()
                .post()
                .then()
                .assertThat().statusCode(201)
                .extract().response().as(PostsResponse.class);

        assertThat("User id", response.getUserId(), equalTo(postId));
        assertThat("Title", response.getTitle(), equalTo(title));
        assertThat("Body", response.getBody(), equalTo(body));
    }
}