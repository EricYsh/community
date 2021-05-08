package helper.recruit.community.controller;

import helper.recruit.community.cache.TagCache;
import helper.recruit.community.dto.QuestionDTO;
import helper.recruit.community.dto.TagDTO;
import helper.recruit.community.mapper.QuestionMapper;
import helper.recruit.community.service.QuestionService;
import helper.recruit.community.model.Question;
import helper.recruit.community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    // 编辑的时候
    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model) {
        // 点击后获取 question id 获取到 question
        QuestionDTO question = questionService.getById(id);
        // 查询后回显到页面
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("joblink", question.getJoblink());
        model.addAttribute("company", question.getCompany());
        model.addAttribute("place", question.getPlace());
        // 编辑edit 的特殊处理，获取id作为唯一标识，和 new post 分开
        model.addAttribute("id", question.getId());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    // 一开始创建的时候
    @GetMapping("/publish")
    public String publish(Model model) {
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    // 返回错误信息的时候
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam("joblink") String joblink,
            @RequestParam("company") String company,
            @RequestParam("place") String place,
            @RequestParam(value = "id", required = false) Long id,
            HttpServletRequest request,
            Model model
    ) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("joblink", joblink);
        model.addAttribute("company", company);
        model.addAttribute("place", place);

        model.addAttribute("tags", TagCache.get());

        if (title == null || title.equals("")) {
            model.addAttribute("error", "Please input title!");
            return "publish";
        }

        if (company == null || company.equals("")) {
            model.addAttribute("error", "Please input a company name!");
            return "publish";
        }

        if (place == null || place.equals("")) {
            model.addAttribute("error", "Please input place of work!");
            return "publish";
        }

        if (description == null || description.equals("")) {
            model.addAttribute("error", "Please input description!");
            return "publish";
        }

        if (tag == null || tag.equals("")) {
            model.addAttribute("error", "Please input a tag!");
            return "publish";
        }

        if (joblink == null || joblink.equals("")) {
            model.addAttribute("error", "Please input job link!");
            return "publish";
        }

//        String invalid = TagCache.filterInvalid(tag);
//        if (StringUtils.isNotBlank(invalid)) {
//            model.addAttribute("error", "Illegal tag: " + invalid);
//            return "publish";
//        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "Please login!");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setCompany(company);
        question.setPlace(place);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setJoblink(joblink);
        question.setId(id);

        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
