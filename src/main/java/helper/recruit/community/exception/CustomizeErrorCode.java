package helper.recruit.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    // 枚举方式
    QUESTION_NOT_FOUND(2001,"This job information has gone! Please try other job information."),
    TARGET_PARAM_NOT_FOUND(2002,"No post or comment is seleted to be commented."),
    NO_LOGIN(2003,"Please log in and try again."),
    SYS_ERROR(2004, "Something wrong, please try later!"),
    TYPE_PARAM_WRONG(2005, "Wrong comment type or comment does not exist."),
    COMMENT_NOT_FOUND(2006,"The comment doesn't exist")
    ;

    @Override
    public String getMessage(){
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message){
        this.message = message;
        this.code = code;
    }
}
