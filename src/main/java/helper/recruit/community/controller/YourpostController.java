package helper.recruit.community.controller;

import helper.recruit.community.dto.PaginationDTO;
import helper.recruit.community.mapper.UserMapper;
import helper.recruit.community.service.QuestionService;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class YourpostController {

    @Autowired
    private UserMapper userMapper;  // load user database

    @Autowired
    private QuestionService questionService; // load question database

    @GetMapping("/yourpost/{action}")
    public String yourpost(@PathVariable(name = "action") String action,
                           Model model,
                           HttpServletRequest request,
                           @RequestParam(name = "page", defaultValue = "1") Integer page,
                           @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {

//        // 检验cookie，做出持久化登陆状态的功能
//        Cookie[] cookies = request.getCookies();
//        User user = null;
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

        if (user==null)
            return "redirect:/";

        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "My Post");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "Latest Reply");
        }

        //通过 user id 找到你 post的信息，并展示出来
        PaginationDTO paginationDTO = questionService.list(user.getId(), page, size);
        model.addAttribute("pagination", paginationDTO);
        return "yourpost";
    }
}
