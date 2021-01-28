package response;

public class PostsResponse {
    Integer userId;
    Integer id;
    String title;
    String body;
    Boolean completed;

    public Integer getUserId() {
        return userId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Boolean getCompleted() {
        return completed;
    }

}
