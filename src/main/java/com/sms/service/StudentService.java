package com.sms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sms.model.Student;
import com.sms.repository.StudentRepo;
@Service
public class StudentService {
	@Autowired
	private StudentRepo repo;
	
	
	public List<Student> getAllStudent(){
		List<Student> list = this.repo.findAll();
		return list;
	}
	
	
	public Student findById(int studentID) {
		Student student = null;
		try {
			student = this.repo.findById(studentID);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return student;
	}
	
	
	
	public Student addStudent(Student student) {
		Student student2 = this.repo.save(student);
		return student2;
	}
	
	
	public void deleteStudent(int id) {
		this.repo.deleteById(id);
	}
	
	public void updateStudent(int studentId, Student student)
	{
		student.setStudentId(studentId);
		this.repo.save(student);
	}
}
