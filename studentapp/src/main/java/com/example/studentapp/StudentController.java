package com.example.studentapp;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Student create(@RequestBody Student student) {
        return repo.save(student);
    }

    @GetMapping
    public List<Student> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Student getOne(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student newData) {
        Student s = repo.findById(id).orElseThrow();
        s.setName(newData.getName());
        s.setEmail(newData.getEmail());
        return repo.save(s);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}