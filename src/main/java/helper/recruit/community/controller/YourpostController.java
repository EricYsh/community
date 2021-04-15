package helper.recruit.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class YourpostController {

    @GetMapping("/yourpost/{action}")
    public String yourpost(@PathVariable(name = "action") String action,
                           Model model) {

        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "My Post");
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "Latest Reply");
        }

        return "yourpost";
    }
}
