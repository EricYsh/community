package helper.recruit.community.mapper;

import helper.recruit.community.model.Question;
import helper.recruit.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionExtMapper {
    int incView(Question record);
}