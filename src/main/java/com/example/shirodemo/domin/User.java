package com.example.shirodemo.domin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @Author: Gaoyp
 * @Description:
 * @Date: Created in 下午3:22 18-2-1
 * @Modified By:
 */
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    private String username;
    private String password;
    private String authorityInfo;

    public User() {
    }

    public User(String username, String password, String authorityInfo) {
        this.username = username;
        this.password = password;
        this.authorityInfo = authorityInfo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAuthorityInfo() {
        return authorityInfo;
    }

    public void setAuthorityInfo(String authorityInfo) {
        this.authorityInfo = authorityInfo;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorityInfo='" + authorityInfo + '\'' +
                '}';
    }
}
