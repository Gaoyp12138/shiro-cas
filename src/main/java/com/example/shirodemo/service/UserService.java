package com.example.shirodemo.service;

import com.example.shirodemo.dao.UserDao;
import com.example.shirodemo.domin.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Gaoyp
 * @Description:
 * @Date: Created in 下午3:33 18-2-1
 * @Modified By:
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;


    public User getUserByUsername(String username){
        return userDao.findUserByUsername(username);
    }
}
