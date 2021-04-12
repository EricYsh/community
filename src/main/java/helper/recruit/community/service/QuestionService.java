package helper.recruit.community.service;

import helper.recruit.community.dto.PaginationDTO;
import helper.recruit.community.dto.QuestionDTO;
import helper.recruit.community.mapper.QuestionMapper;
import helper.recruit.community.mapper.UserMapper;
import model.Question;
import model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    // springboot 自动管理，组装作用：当请求需要user 和 question的时候，需要中间层做这个事情
    // 同时使用 questionmapper 和 user mapper
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        // page operation
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);

        if (page<1)
            page = 1;
        if (page > paginationDTO.getTotalPage())
            page = paginationDTO.getTotalPage();

        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
//            questionDTO.setId(question.getId());
            BeanUtils.copyProperties(question, questionDTO); // copy source attribute to target
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

}
