package helper.recruit.community.exception;

public class CustomizeExpection extends RuntimeException{
    private String message;

    public CustomizeExpection(ICustomizeErrorCode errorCode){
        this.message=errorCode.getMessage();
    }

    public CustomizeExpection(String message){
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
