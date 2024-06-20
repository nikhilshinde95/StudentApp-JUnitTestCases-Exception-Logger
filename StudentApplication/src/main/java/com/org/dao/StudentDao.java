package com.org.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.entities.Student;

public interface StudentDao extends JpaRepository<Student, Long> {
    
}
