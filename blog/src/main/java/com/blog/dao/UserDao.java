package com.blog.dao;

import com.blog.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
    /**
     * 验证登录
     * @param username
     * @param password
     * @return
     */
    User queryByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
