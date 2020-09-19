package com.domain;

import java.io.Serializable;

public class CodeMessage implements Serializable {

    private static final long serialVersionUID = 1837412743;

    private Integer code;
    private String  message;

    public CodeMessage() {}

    public CodeMessage(CodeMessage value) {
        this.code = value.code;
        this.message = value.message;
    }

    public CodeMessage(
        Integer code,
        String  message
    ) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public CodeMessage setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return this.message;
    }

    public CodeMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final CodeMessage other = (CodeMessage) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        }
        else if (!code.equals(other.code))
            return false;
        if (message == null) {
            if (other.message != null)
                return false;
        }
        else if (!message.equals(other.message))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CodeMessage (");

        sb.append(code);
        sb.append(", ").append(message);

        sb.append(")");
        return sb.toString();
    }
}
