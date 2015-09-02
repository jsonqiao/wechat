package com.linju.framework.model;

/**
 * @author jsonqiao
 *
 * @date 2015/6/17
 */
public class ResultModel<T> extends BaseModel {

    public static final String SUCCESS_CODE = "0";

    private String errCode;

    private String errMsg;

    private T data;

    public String getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setError(String errorCode, String errorMsg) {
        this.errCode = errorCode;
        this.errMsg = errorMsg;
    }
}
