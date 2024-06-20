package com.org.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.dao.StudentDao;
import com.org.entities.Student;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger log = LogManager.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> getAllStudent(){
        log.info("Fetching All Students Data");
        List<Student> students = studentDao.findAll();
        log.info("{} Students Data is Fetched.",students.size());
        return students;
    }


    @Override
    public Student getStudent(long studentid){
        log.info("Fetching Student Details With ID {}",studentid);
        Optional<Student> student = studentDao.findById(studentid);

        if(student!=null){
            log.info("Student With Id {} fetched Successfully..",student);
            return student.get();
        }else{
            log.warn("Student With Id {} is Not Found ",studentid);
            return null;
        }
		
    }

    @Override
    public Student addStudent(Student student){
        log.info("Adding New Student to Database");
        Student addStudent = studentDao.save(student);
        log.info("New Student {} is Added Successfully.",student);
        return addStudent;
    }


    @Override
    public Student updateStudent(Student student){
        log.info("Updating Existing Student Data..");
        Student updateStudent = studentDao.save(student);
        log.info("Student Data is Updated Successfully...");
        return updateStudent;
    }

    @Override
    public boolean deleteStudent(long studentid){
        log.info("Deleting Student With Id {} ",studentid);

        try{
            Optional<Student> studopt = studentDao.findById(studentid);

            if(studopt.isPresent()) {
                studentDao.delete(studopt.get());
                log.info("Student With Id {} is Deleted Successfully...",studentid);
                return true;
            }else {
                log.warn("Student With Id {} is Not Found.",studentid);
                return false;
            }
        }catch(Exception e){
            log.error("Invalid Student..!",e);
            return false;
        }
    }

    
}
