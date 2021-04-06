package life.majiang.community.dto;

import lombok.Data;
import model.User;

@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;  // connect to user ID in USERS Table (database)
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private String joblink;

    private User user;
}
