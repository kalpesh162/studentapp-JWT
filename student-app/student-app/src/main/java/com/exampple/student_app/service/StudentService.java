package com.exampple.student_app.service;

import org.springframework.data.domain.Page;

import com.exampple.student_app.entity.Student;

public interface StudentService {
	
	
	Page<Student> getAllStudents(int pageNo, int pageSize);

	void saveStudent(Student student);

	Student getStudentById(Long id);

	void deleteStudent(Long id);
}
