package helper.recruit.community.exception;

public class CustomizeExpection extends RuntimeException{
    private String message;
    private Integer code;

    public CustomizeExpection(ICustomizeErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message=errorCode.getMessage();
    }

//    public CustomizeExpection(String message){
//        this.message=message;
//    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode(){
        return code;
    }
}
