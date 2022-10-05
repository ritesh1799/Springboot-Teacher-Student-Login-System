package com.DRCProject.TeacherRegistration.repository;

import com.DRCProject.TeacherRegistration.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}
