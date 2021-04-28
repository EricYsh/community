package helper.recruit.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode{
    // 枚举方式
    QUESTION_NOT_FOUND("This job information has gone! Please try other job information.");

    @Override
    public String getMessage(){
        return message;
    }

    private String message;

    CustomizeErrorCode(String message){
        this.message = message;
    }
}
