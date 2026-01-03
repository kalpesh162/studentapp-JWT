package com.exampple.student_app.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exampple.student_app.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
