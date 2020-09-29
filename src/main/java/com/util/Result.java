package com.util;

import java.io.Serializable;


/**
 * 
 * @author sheng
 *
 */
public class Result implements Serializable {
 
	private static final long serialVersionUID = 1L;
	private Integer code;
    private String message;
    private Object data;
    
    

    public Result() {
    }

    public Result(Integer code) {
    	this.code = code;
    }
    
    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public Result setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public Result data(Object data) {
		this.data = data;
		return this;
	}
    
	public static Result ok() {
		return new Result(1);
	}
    
    public static Result ok(Object data) {
		return ok().data(data);
	}
    
    public static Result error() {
    	return new Result(0);
    }
    
    public static Result error(Object data) {
    	return error().data(data);
    }
    
    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
