package com.shahaz.hibernatecrud.dao;

import com.shahaz.hibernatecrud.entity.Student;

import java.util.List;

public interface StudentDao {
    void save(Student student);
    Student findById(Integer id);


    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    void update(Student student);

    void delete(Integer id);

    int deleteAll();
}
