package ${package}.base.exception;

import ${package}.base.result.ResultCode;

public class AppException extends RuntimeException {
    private ResultCode resultCode;//错误代码

    public AppException() {
    }

    public AppException(String message) {
        super(message);
        this.resultCode = ResultCode.INTERNAL_SERVER_ERROR;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public void setCode(ResultCode code) {
        this.resultCode = code;
    }
}
