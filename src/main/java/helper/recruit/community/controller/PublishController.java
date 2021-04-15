package helper.recruit.community.controller;

import helper.recruit.community.mapper.QuestionMapper;
import helper.recruit.community.mapper.UserMapper;
import model.Question;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            @RequestParam("joblink") String joblink,
            @RequestParam("company") String company,
            @RequestParam("place") String place,
            HttpServletRequest request,
            Model model
    ) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("joblink", joblink);
        model.addAttribute("company", company);
        model.addAttribute("place", place);

        if (title == null || title == "") {
            model.addAttribute("error", "Please input title!");
            return "publish";
        }

        if (company == null || company == "") {
            model.addAttribute("error", "Please input a company name!");
            return "publish";
        }

        if (place == null || place == "") {
            model.addAttribute("error", "Please input place of work!");
            return "publish";
        }

        if (description == null || description == "") {
            model.addAttribute("error", "Please input description!");
            return "publish";
        }

        if (tag == null || tag == "") {
            model.addAttribute("error", "Please input a tag!");
            return "publish";
        }

        if (joblink == null || joblink == "") {
            model.addAttribute("error", "Please input job link!");
            return "publish";
        }

//        User user = null;
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null && cookies.length != 0) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("token")) {
//                    String token = cookie.getValue();
//                    user = userMapper.findByToken(token);
//                    if (user != null) {
//                        request.getSession().setAttribute("user", user);
//                    }
//                    break;
//                }
//            }
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
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setJoblink(joblink);

        questionMapper.create(question);
        return "redirect:/";
    }
}
