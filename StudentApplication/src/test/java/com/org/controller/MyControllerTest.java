package com.org.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.org.service.StudentService;
import com.org.entities.Student;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class MyControllerTest {
	
	@Mock
	private StudentService studentService;
	
	@InjectMocks
	private MyController myController;
	
	@Test
	void getAllStudentTest() {

		// Setting up test data 
		Student stu1 = new Student(1,"Nikhil Shinde","Sangli","shindenikhil@gmail.com");
		when(studentService.addStudent(any(Student.class))).thenReturn(stu1);
		Student student1 = myController.addStudent(stu1);

		Student stu2 =new Student(2,"Anil Shinde","Sangli","shindeanil@gmail.com");
		when(studentService.addStudent(any(Student.class))).thenReturn(stu2);
		Student student2 = myController.addStudent(stu2);

		List<Student> expected=new ArrayList<>();
		expected.add(student1);
		expected.add(student2);
		
		// mocking behaviour 
		when(studentService.getAllStudent()).thenReturn(expected);
		List<Student> actual = myController.getAllStudent();
		assertEquals(2,actual.size());
	}
	
	@Test
	void getStudentTest() {

		Student student = new Student(1,"Nikhil Shinde","Sangli","shindenikhil@gmail.com");  
		when(studentService.addStudent(any(Student.class))).thenReturn(student);
		Student student1 = myController.addStudent(student); 
		
		when(studentService.getStudent(anyLong())).thenReturn(student1); 
		Student stud = myController.getStudent(1L);  
		
		assertEquals(1, stud.getId());
		assertNotEquals(2, stud.getId());		
		
	}
	
	@Test
	void addStudentTest() {

		Student student  = new Student(1,"Nikhil Shinde","Sangli","shindenikhil@gmail.com");
		when(studentService.addStudent(any(Student.class))).thenReturn(student);
		Student addStudent = myController.addStudent(student);
		
		assertEquals(student,addStudent);
	}
	
	@Test
	void UpdateStudentTest() {

		Student student = new Student(1,"Nikhil Shinde","Pune","shindenikhil@gmail.com");
		when(studentService.addStudent(any(Student.class))).thenReturn(student);
		Student addStud = myController.addStudent(student);
		
		Student student2 = new Student(1,"Nikhil Shinde","Sangli","shindenikhil@gmail.com");
		when(studentService.updateStudent(any(Student.class))).thenReturn(student2);
		Student updateStudent = myController.updateStudent(student2);
		
		assertEquals("Sangli", updateStudent.getCity());
		assertNotEquals(addStud, updateStudent);
		
	}
	
	@Test
	void deleteStudentTest() {
		
		Student student = new Student(1,"Nikhil Shinde","Sangli","shindenikhil@gmail.com");
		when(studentService.addStudent(any(Student.class))).thenReturn(student);
		Student addStudent = myController.addStudent(student);
		
		when(studentService.deleteStudent(anyLong())).thenReturn(true);
		ResponseEntity<Object> deleteStudent = myController.deleteStudent(1L);
		assertEquals(HttpStatus.OK, deleteStudent.getStatusCode());
		
		when(studentService.deleteStudent(anyLong())).thenReturn(false);
		ResponseEntity<Object> deleteStudent2 = myController.deleteStudent(2L);
		assertNotEquals(HttpStatus.OK, deleteStudent2.getStatusCode());
		
	}
	
}