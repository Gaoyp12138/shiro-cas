package com.example.shirodemo.dao;

import com.example.shirodemo.domin.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: Gaoyp
 * @Description:
 * @Date: Created in 下午3:28 18-2-1
 * @Modified By:
 */
@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    User findUserByUsername(String username);

}
