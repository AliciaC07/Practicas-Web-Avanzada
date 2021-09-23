package practica.practica1pwa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practica.practica1pwa.models.Student;
import practica.practica1pwa.services.StudentService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/students")
    @ResponseStatus(HttpStatus.OK)
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping("/student")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> addStudent(@RequestBody Student student){
        if (studentService.findByIdCollege(student.getIdCollege()).isPresent()){
            return ResponseEntity.badRequest().body("This student is already registered");

        }else {
            studentService.addStudent(student);
            return ResponseEntity.ok().body(student);
        }
    }

    @PutMapping("/student/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student student){
        Student old = studentService.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Student not found"));
        return studentService.updateStudentById(old, student);

    }

    @GetMapping("/student-idcollege/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student findByIdCollege(@PathVariable Integer id){
        return studentService.findByIdCollege(id)
                .orElseThrow(()-> new EntityNotFoundException("Student not found"));

    }

    @DeleteMapping("/student/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Student deleteStudent(@PathVariable Integer id){
        Student student = studentService.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Student not found"));
        return studentService.deleteById(student.getId());
    }
}
