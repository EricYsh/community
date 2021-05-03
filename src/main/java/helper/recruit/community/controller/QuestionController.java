package helper.recruit.community.controller;

import helper.recruit.community.dto.QuestionDTO;
import helper.recruit.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping("question/{id}")
    public String question(@PathVariable(name = "id") Long id,
                           Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        // 每次打开问题后，累加查看次数
        questionService.incView(id); //访问页面调用这个方法
        model.addAttribute("question", questionDTO);
        return "question";
    }
}
