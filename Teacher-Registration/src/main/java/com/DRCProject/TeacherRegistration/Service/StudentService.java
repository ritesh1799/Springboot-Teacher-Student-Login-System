package com.DRCProject.TeacherRegistration.Service;

import com.DRCProject.TeacherRegistration.model.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    //list all the student data
    List<Student> getAllStudents();

    //create method to save student's details

    Student saveStudent(Student student);

    //create method to get student details by Id
    Student getStudentById(Long id);

    //create method to update the student data
    Student updateStudent(Student student);

    //create method for pagination

    Page<Student> findPaginated(int pageNo, int pageSize);
}
