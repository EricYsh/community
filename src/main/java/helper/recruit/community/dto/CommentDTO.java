package helper.recruit.community.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long parentID;
    private String content;
    private Integer type;
}
