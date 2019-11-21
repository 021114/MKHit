package com.mk.dao;

import com.mk.po.Student;

import java.util.List;

/**
 * @author 86185
 * @create 2019-11-21-11:00
 */
public interface StudentDao {

    /**
     * 查询全部
     * @return
     */
    List<Student> selectAll();
}
