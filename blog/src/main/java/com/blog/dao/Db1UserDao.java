package com.blog.dao;

import com.blog.pojo.Db1User;
import com.blog.pojo.Db1UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Db1UserDao {
    long countByExample(Db1UserExample example);

    int deleteByExample(Db1UserExample example);

    int insert(Db1User record);

    int insertSelective(Db1User record);

    List<Db1User> selectByExample(Db1UserExample example);

    int updateByExampleSelective(@Param("record") Db1User record, @Param("example") Db1UserExample example);

    int updateByExample(@Param("record") Db1User record, @Param("example") Db1UserExample example);
}