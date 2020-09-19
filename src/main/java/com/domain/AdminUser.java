package com.domain;

import java.io.Serializable;

public class AdminUser implements Serializable {

    private static final long serialVersionUID = -262708972;

    private String  username;
    private String  password;
    private Integer id;

    public AdminUser() {}

    public AdminUser(AdminUser value) {
        this.username = value.username;
        this.password = value.password;
        this.id = value.id;
    }

    public AdminUser(
        String  username,
        String  password,
        Integer id
    ) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public AdminUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public AdminUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public Integer getId() {
        return this.id;
    }

    public AdminUser setId(Integer id) {
        this.id = id;
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
        final AdminUser other = (AdminUser) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        }
        else if (!username.equals(other.username))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        }
        else if (!password.equals(other.password))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AdminUser (");

        sb.append(username);
        sb.append(", ").append(password);
        sb.append(", ").append(id);

        sb.append(")");
        return sb.toString();
    }
}
