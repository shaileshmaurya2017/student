package com.school.student.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.school.student.entity.Student;
import com.school.student.repository.StudentRepository;

@RestController
public class StudentController {
	
	@Autowired
	StudentRepository repo;
	
	@GetMapping("/students")
	public List<Student> getStudents() {
		
		return repo.findAll();		
	}
	
	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable int id) {
		return repo.findById(id).get();
		
	}
	
	@PostMapping("/student/add")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Student addStudent(@RequestBody Student student) {
		if(student != null) {
		Student stud = repo.save(student);
		return stud;
		}else return null;
	}
	
	@PutMapping("/student/update/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Student updateStudent(@PathVariable int id,@RequestBody Student student)
	{
		Student stud = repo.findById(id).get();
		stud.setName(student.getName());
		stud.setAge(student.getAge());
		stud.setRollno(student.getRollno());
		
		return repo.save(stud);
	}
	
	@DeleteMapping("/student/delete/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public List<Student> deleteStudent(@PathVariable int id)
	{
		repo.deleteById(id);
		return repo.findAll();
	}
}
