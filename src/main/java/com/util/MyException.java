package com.util;

import com.model.CodeMessage;
/**
 * 自定义异常类
 * @author sheng
 *
 */
public class MyException extends RuntimeException {

    private static final long serialVersionUID = -8342427634552193107L;

    private int code;
    private String message;
    
    private String sheet;

    public MyException() {
        super();
    }

    public MyException(CodeMessage codeMessage) {
        super(codeMessage.getCode() + "->" + codeMessage.getMessage());
        this.code = codeMessage.getCode();
        this.message = codeMessage.getMessage();
    }
    
    public MyException(CodeMessage codeMessage, String sheet) {
        super(codeMessage.getCode() + "->" + codeMessage.getMessage());
        this.code = codeMessage.getCode();
        this.message = codeMessage.getMessage();
        this.sheet = sheet;
    }

    public int getCode() {
        return code;
    }

    public MyException setCode(int code) {
        this.code = code;
        return this;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public MyException setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getSheet()
    {
        return sheet;
    }

    public void setSheet(String sheet)
    {
        this.sheet = sheet;
    }

    @Override
    public String toString() {
        return "MyException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", sheet='" + sheet + '\'' +
                '}';
    }
}