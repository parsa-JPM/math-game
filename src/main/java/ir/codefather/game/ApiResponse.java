package ir.codefather.game;

public class ApiResponse {
    private Integer errorCode;
    private String errorMessage;
    private Object responseObject;

    public ApiResponse(Object responseObject) {
        this.responseObject = responseObject;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public ApiResponse setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ApiResponse setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public Object getResponseObject() {
        return responseObject;
    }

    public ApiResponse setResponseObject(Object responseObject) {
        this.responseObject = responseObject;
        return this;
    }
}
