package com.DRCProject.TeacherRegistration.Service;

import com.DRCProject.TeacherRegistration.model.Student;
import com.DRCProject.TeacherRegistration.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {

        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        Optional<Student> optional = studentRepository.findById(id);
        Student student = null;
        if(optional.isPresent()){
            student = optional.get();
        }
        else {
            throw new RuntimeException("student not found ::" +id);
        }
        return student;
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

   @Override
    public Page<Student> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return this.studentRepository.findAll(pageable);
    }
}
