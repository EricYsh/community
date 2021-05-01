package helper.recruit.community.service;

import helper.recruit.community.dto.PaginationDTO;
import helper.recruit.community.dto.QuestionDTO;
import helper.recruit.community.exception.CustomizeErrorCode;
import helper.recruit.community.exception.CustomizeExpection;
import helper.recruit.community.mapper.QuestionExtMapper;
import helper.recruit.community.mapper.QuestionMapper;
import helper.recruit.community.mapper.UserMapper;
import helper.recruit.community.model.Question;
import helper.recruit.community.model.QuestionExample;
import helper.recruit.community.model.User;
import org.apache.ibatis.session.RowBounds;
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
    private QuestionExtMapper questionExtMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        // page operation
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample());
        // total count is the number of job list in the question database
        // page number
        // size is 5 : one page 5 rows
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1)
            page = 1;
        if (page > totalPage)
            page = totalPage;

        paginationDTO.setPagination(totalPage, page);

        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO); // copy source attribute to target
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        // page operation
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalPage;

//        Integer totalCount = questionMapper.countByUserId(userId);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria().andCreatorEqualTo(userId);
        Integer totalCount = (int) questionMapper.countByExample(questionExample);

        // total count is the number of job list in the question database
        // page number
        // size is 5 : one page 5 rows
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1)
            page = 1;
        if (page > totalPage)
            page = totalPage;

        paginationDTO.setPagination(totalPage, page);

        Integer offset = size * (page - 1);
//        List<Question> questions = questionMapper.yourPostList(userId, offset, size);
        QuestionExample example = new QuestionExample();
        example.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO); // copy source attribute to target
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        // 问题不存的异常处理
        if (question==null){
            throw new CustomizeExpection(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO); // copy source attribute to target
        User user = userMapper.selectByPrimaryKey(question.getCreator());//get user
        questionDTO.setUser(user);
        return  questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null){
            // create a new
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insert(question);
        }else{
            // update
//            question.setGmtModified(System.currentTimeMillis());
            Question updateQuestion = new Question();
            updateQuestion.setGmtCreate(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setCompany(question.getCompany());
            updateQuestion.setTag(question.getTag());
            updateQuestion.setPlace(question.getPlace());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setJoblink(question.getJoblink());

            QuestionExample example = new QuestionExample();
            example.createCriteria().andIdEqualTo(question.getId());
            int updated = questionMapper.updateByExampleSelective(updateQuestion, example);
            // 问题不存的异常处理
            if (updated != 1){
                throw new CustomizeExpection(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    // 访问页面调用这个方法增加阅读数
    public void incView(Integer id) {
        // 这个方法不能解决并发问题
//        Question question = questionMapper.selectByPrimaryKey(id);
//        Question updateQuestion = new Question();
//        updateQuestion.setViewCount(question.getViewCount() + 1);
//        QuestionExample questionExample = new QuestionExample();
//        questionExample.createCriteria().andIdEqualTo(id);
//        questionMapper.updateByExampleSelective(updateQuestion, questionExample);
        // 巧妙解决并发问题的方法， 但是面对大量的同时请求也不行
        Question record = new Question();
        record.setId(id);
        record.setViewCount(1);
        questionExtMapper.incView(record);

    }
}
