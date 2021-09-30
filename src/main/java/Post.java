import com.fasterxml.jackson.annotation.JsonProperty;

public class Post {
    private String id;
    private String text;
    private String type;
    private String user;
    private int upvotes;

    public Post(@JsonProperty("id") String id, @JsonProperty("text") String text, @JsonProperty("type") String type, @JsonProperty("user") String user, @JsonProperty("upvotes") int upvotes) {
            this.id = id;
            this.text = text;
            this.type = type;
            this.user = user;
            this.upvotes = upvotes;
    }

    @Override
    public String toString() {
        return "\n ID: " + id +
                "\n TEXT: " + text +
                "\n TYPE: " + type +
                "\n USER: " + user +
                "\n UPVOTES: " + upvotes;
    }

    public int getUpvotes() {
        return upvotes;
    }
}