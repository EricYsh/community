package helper.recruit.community.model;

import lombok.Data;

@Data
public class Question {
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
    private String company;
    private String place;
}
