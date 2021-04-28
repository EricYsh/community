package helper.recruit.community.advice;

import helper.recruit.community.exception.CustomizeExpection;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
//        HttpStatus status = getStatus(request);

        if (e instanceof CustomizeExpection){
            model.addAttribute("message", e.getMessage());
        }else {
            model.addAttribute("message", "Something wrong, please try later!");
        }
        return new ModelAndView("error");
    }

//    private HttpStatus getStatus(HttpServletRequest request){
//        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
//        if (statusCode==null){
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return HttpStatus.valueOf(statusCode);
//    }
}
