package com.example.oci_microsevice.controller;

import com.example.oci_microsevice.model.Student;
import com.example.oci_microsevice.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Student> getStudentByMatricula(@PathVariable String matricula) {
        Optional<Student> student = studentService.getStudentByMatricula(matricula);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    // ✅ Add multiple students at once
    @PostMapping("/bulk")
    public ResponseEntity<List<Student>> createMultipleStudents(@RequestBody List<Student> students) {
        List<Student> savedStudents = studentService.saveAllStudents(students);
        return ResponseEntity.ok(savedStudents);
    }

    // ✅ Delete a student by matricula
    @DeleteMapping("/{matricula}")
    public ResponseEntity<String> deleteStudent(@PathVariable String matricula) {
        boolean deleted = studentService.deleteStudentByMatricula(matricula);
        if (deleted) {
            return ResponseEntity.ok("{\"message\": \"Student deleted successfully\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"message\": \"Student not found\"}");
        }
    }

    // ✅ Delete all students
    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllStudents() {
        studentService.deleteAllStudents();
        return ResponseEntity.ok("{\"message\": \"All students deleted successfully\"}");
    }
}
