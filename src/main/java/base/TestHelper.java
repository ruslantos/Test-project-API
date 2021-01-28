package base;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import response.PostsResponse;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestHelper {

    public static PostsResponse postRequest(int postId, String title, String body) {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.basePath = "/posts";
        Map<String, Object> putPost = new HashMap<>();
        putPost.put("userId", postId);
        putPost.put("title", title);
        putPost.put("body", body);
        return given().contentType(ContentType.JSON).accept(ContentType.JSON)
                .body(putPost)
                .filter(new AllureRestAssured())
                .when()
                .post()
                .then()
                .assertThat().statusCode(201)
                .extract().response().as(PostsResponse.class);
    }
}
