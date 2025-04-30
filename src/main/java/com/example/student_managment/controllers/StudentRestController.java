package com.example.student_managment.controllers;

import com.example.student_managment.domain.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentRestController {

    private List <Student> students = new ArrayList<>(Arrays.asList(
            new Student(123,"Gerardo Lopez", "gerardo1@gmail.com", 22,"Futbol"),
            new Student(1244, "Adriana Torres", "adriana2@gmai.com",22,"Ajedrez"),
            new Student(9172, "Laura Sanchez", "laur43@gmail.com",25, "Sistemas"),
            new Student(18, "Perla arizmendi", "perlita@gmail.com",24, "Ajedrez")
    ));

    @GetMapping
    public ResponseEntity<List<Student>> getStudents(){
        //return students;
        return ResponseEntity.ok(students);
    }
    @GetMapping("/{name}")
    public ResponseEntity<?> getStudent(@PathVariable String name){
        for (Student c : students){
            if (c.getName().equalsIgnoreCase(name)) {
                //return c;
                return ResponseEntity.ok(c);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudiante no encontrado con nombre: " + name);
        //return null;
    }
    @PostMapping
    public  ResponseEntity<?> postStudent(@RequestBody Student student){
        students.add(student);
        //return Student;
        return ResponseEntity.status(HttpStatus.CREATED).body("Estudiante agregado correctamente! "+student.getName());
    }
    @PutMapping
    public ResponseEntity<?> putStudent(@RequestBody Student student){
        for (Student c : students){
            if (c.getId()== student.getId()){
                c.setName(student.getName());
                c.setEmail(student.getEmail());
                c.setAge(student.getAge());
                c.setCourse(student.getCourse());
                return ResponseEntity.ok("Estudiante modificado correctamente! "+c.getName());
                //return c;
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudiante no encontrado "+student.getName());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id){
        for (Student c : students){
            if (c.getId()==id){
                students.remove(c);
                return ResponseEntity.ok("Estudiante eliminado correctamente "+ c.getName());
                //   return c;
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudiante con ID: "+id+" NO encontrado");
    }

    @PatchMapping
    public ResponseEntity<?> patchStudent(@RequestBody Student student){
        for (Student c :students){
            if (c.getId() == student.getId()){
                if (student.getName()!=null){
                    c.setName(student.getName());
                }
                if (student.getEmail()!=null){
                    c.setEmail(student.getEmail());
                }
                if (student.getCourse()!=null){
                    c.setCourse(student.getCourse());
                }
                return ResponseEntity.ok("Estudiante actualizado correctamente "+ student.getName());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estudiante: "+student.getId()+" NO encontrado");
    }


}
