package helper.recruit.community.mapper;

import helper.recruit.community.dto.SearchDTO;
import helper.recruit.community.model.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionExtMapper {
    int incView(Question record);
    int incCommentCount(Question record);
    List<Question> selectRelated(Question question);

    Integer countBySearch(SearchDTO searchDTO);

    List<Question> selectBySearch(SearchDTO searchDTO);
}