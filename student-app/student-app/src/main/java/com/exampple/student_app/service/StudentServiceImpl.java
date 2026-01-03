package com.exampple.student_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.exampple.student_app.entity.Student;
import com.exampple.student_app.repos.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository repo;

	@Override
	public Page<Student> getAllStudents(int pageNo, int pageSize) {
		PageRequest pageable = PageRequest.of(pageNo, pageSize);
		return repo.findAll(pageable);
	}

	@Override
	public void saveStudent(Student student) {
		repo.save(student);
	}

	@Override
	public Student getStudentById(Long id) {
		return repo.findById(id).orElseThrow();
	}

	@Override
	public void deleteStudent(Long id) {
		repo.deleteById(id);
	}
}
