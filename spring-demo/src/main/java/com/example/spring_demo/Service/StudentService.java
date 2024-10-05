package com.example.spring_demo.Service;

import com.example.spring_demo.Entity.Student;
import com.example.spring_demo.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Long id, Student studentDetails){
        Student student = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Student not found"));
        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        student.setCourse(studentDetails.getCourse());
        student.setAge(studentDetails.getAge());

        return studentRepository.save(student);
    }
}
