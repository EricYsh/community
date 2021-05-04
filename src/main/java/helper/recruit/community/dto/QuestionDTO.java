package helper.recruit.community.dto;

import lombok.Data;
import helper.recruit.community.model.User;

@Data
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;  // connect to user ID in USERS Table (database)
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private String joblink;
    private String company;
    private String place;
    // 丰富对象，增加 user 的模型
    private User user;
}
