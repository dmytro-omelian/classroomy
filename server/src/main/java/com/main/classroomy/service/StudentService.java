package com.main.classroomy.service;

import com.main.classroomy.entity.Student;
import com.main.classroomy.entity.dto.StudentDto;
import com.main.classroomy.exception.StudentNotFoundException;
import com.main.classroomy.repository.StudentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private static final Logger logger = LogManager.getLogger(StudentService.class);

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public List<Student> getAll() {
        return this.studentRepository.findAll();
    }

    public Student getById(Long id) {
        return this.studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student with id=" + id + " was not found!"));
    }

    public Student getByEmail(String email) {
        return this.studentRepository.findByEmail(email)
                .orElseThrow(() -> new StudentNotFoundException("Student with email=" + email + " was not found!"));
    }

    public void create(StudentDto studentDto) {
        this.studentRepository.save(
                this.modelMapper.map(studentDto, Student.class)
        );
    }

    public void updateById(Long id, StudentDto studentDto) {
        if (!this.studentRepository.existsById(id)) {
            throw new StudentNotFoundException(String.format("Student with id=%s was not found!", id));
        }
        Student student = this.modelMapper.map(studentDto, Student.class);
        student.setId(id);
        this.studentRepository.save(student);
    }

    public void deleteById(Long id) {
        if (!this.studentRepository.existsById(id)) {
            throw new StudentNotFoundException(String.format("Student with id=%s was not found!", id));
        }
        this.studentRepository.deleteById(id);
    }

}
