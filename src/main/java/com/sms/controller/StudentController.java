package com.sms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sms.model.Student;
import com.sms.service.StudentService;

@RestController
public class StudentController {
	@Autowired
	private StudentService studentService;
	@GetMapping("/student")
	public ResponseEntity<List<Student>>getAllStudent(){
		List<Student> list = this.studentService.getAllStudent();
		if(list.size()<=0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.of(Optional.of(list));
	}
	
	
	@GetMapping("/student/{id}")
	public ResponseEntity<Student>getById(@PathVariable("id") int id){
		Student student = this.studentService.findById(id);
		if(student == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(student));
		
		
	}
	@PostMapping("/student")
	public ResponseEntity<Student> addStudent(Student student){
		try {
			Student student2 = this.studentService.addStudent(student);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		} catch (Exception e) {
			// TODO: handle exception\
			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@DeleteMapping("/student/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable("id") int id){
		try {
			this.studentService.deleteStudent(id);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PutMapping("/student/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") int id, @RequestBody Student student){
		
		try {
			this.studentService.updateStudent(id, student);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
	}

}
