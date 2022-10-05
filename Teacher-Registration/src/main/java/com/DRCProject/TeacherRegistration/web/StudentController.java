package com.DRCProject.TeacherRegistration.web;

import com.DRCProject.TeacherRegistration.Service.StudentService;
import com.DRCProject.TeacherRegistration.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;



    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }


    @GetMapping("/students")
    public String listStudents(Model model) {
      //  model.addAttribute("students", studentService.getAllStudents());
       return findPaginated(1,model);

    }


    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        Student student = new Student();
        model.addAttribute("student", student);
        return "create_student";

    }

    @PostMapping("/students")
    public String saveStudent(@ModelAttribute("student") Student student) {
        //save students to DB
        studentService.saveStudent(student);
        return "redirect:/students";

    }

    @GetMapping("/students/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {
        //get Student from the service
        model.addAttribute("student", studentService.getStudentById(id));
        return "update_student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") Student student,
                                Model model) {
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setName(student.getName());
        existingStudent.setRollNo(student.getRollNo());
        existingStudent.setDepartment(student.getDepartment());
        existingStudent.setStandard(student.getStandard());
        existingStudent.setGender(student.getGender());
        existingStudent.setAge(student.getAge());

        studentService.updateStudent(existingStudent);
        return "redirect:/students";
    }

    @GetMapping("/students/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 10;

        Page< Student > page = studentService.findPaginated(pageNo, pageSize);
        List<Student> list=page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listStudents",list);
        return "students";
    }
}