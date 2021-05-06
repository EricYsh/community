package helper.recruit.community.controller;

import helper.recruit.community.dto.CommentCreateDTO;
import helper.recruit.community.dto.ResultDTO;
import helper.recruit.community.exception.CustomizeErrorCode;
import helper.recruit.community.model.Comment;
import helper.recruit.community.model.User;
import helper.recruit.community.service.CommentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CommentControl {
    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentCreateDTO,
                       HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        // 用户登陆后才能发布评论
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        // 检验回复内容不能为空
        if (commentCreateDTO == null || commentCreateDTO.getContent() == null || commentCreateDTO.getContent() == "")
            return ResultDTO.errorOf(CustomizeErrorCode.CONTENT_IS_EMPTY);
//        if (commentCreateDTO==null || StringUtils.isBlank(commentCreateDTO.getContent()))

        //JSON { key:value} 格式，前后端传输
        Comment comment = new Comment();
        comment.setParentId(commentCreateDTO.getParentId());
        comment.setContent(commentCreateDTO.getContent());
        comment.setType(commentCreateDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment);
        return ResultDTO.okOf();
    }
}
