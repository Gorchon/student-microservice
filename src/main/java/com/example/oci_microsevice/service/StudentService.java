package com.example.oci_microsevice.service;

import com.example.oci_microsevice.model.Student;
import com.example.oci_microsevice.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentByMatricula(String matricula) {
        return studentRepository.findByMatricula(matricula);
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
}
