package com.exampple.student_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.exampple.student_app.entity.Student;
import com.exampple.student_app.service.StudentService;

@Controller
public class StudentController {

	@Autowired
	private StudentService service;

	// DISPLAY + PAGINATION
	@GetMapping("/")
	public String viewHomePage(@RequestParam(defaultValue = "0") int page, Model model) {

		Page<Student> studentPage = service.getAllStudents(page, 5);

		model.addAttribute("students", studentPage.getContent());
		model.addAttribute("totalPages", studentPage.getTotalPages());
		model.addAttribute("currentPage", page);

		return "students";
	}

	// SHOW ADD FORM
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("student", new Student());
		return "add-student";
	}

	// SAVE (ADD + UPDATE)
	@PostMapping("/save")
	public String saveStudent(@ModelAttribute Student student) {
		service.saveStudent(student);
		return "redirect:/";
	}

	// SHOW UPDATE FORM
	@GetMapping("/edit/{id}")
	public String showEditForm(@PathVariable Long id, Model model) {
		model.addAttribute("student", service.getStudentById(id));
		return "edit-student";
	}

	// DELETE
	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable Long id) {
		service.deleteStudent(id);
		return "redirect:/";
	}

}
