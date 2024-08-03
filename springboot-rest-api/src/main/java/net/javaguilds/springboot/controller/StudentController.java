package net.javaguilds.springboot.controller;

import net.javaguilds.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.ArrayList;

@RestController
@RequestMapping("students")
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent() {
        Student student = new Student(1, "firstName", "lastName");
        // return student;
        // return new ResponseEntity<>(student, HttpStatus.OK);
        // or the way below
        // return ResponseEntity.ok(student)
        return ResponseEntity.ok().header("custom-header", "student-name")
                .body(student);
    }
    @GetMapping
    public ResponseEntity<ArrayList<Student>> getStudents() {
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student(2, "Johnson", "Chen"));
        students.add(new Student(3, "Test", "2"));
        students.add(new Student(4, "Test", "3"));
        students.add(new Student(5, "Test", "4"));
        // return students;
        return ResponseEntity.ok(students);
    }

    // Spring boot REST API with path variable
    // {id} - URI template variable
    // http://localhost:8080/students/1

    // @PathVariable annotation used on a method argument to bind it
    // to a value of a URI template variable
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable int id,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName) {
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    //REST API with request param
    //http://localhost:8080/students/query?id=1
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        Student student =  new Student(id, "request-param " + firstName, "request-param " +lastName);
        return ResponseEntity.ok(student);
    }

    // REST API: POST - creating resource
    // @PostMapping and @RequestBody
    // The RequestBody annoation is responsible for retrieving the HTTP request body and
    // automatically converting it to the Java object


    @PostMapping("create")
    // By default if we dont have Response Status it will automatically return 200
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }

    // PUT request - update existing resource
    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student,@PathVariable("id") int studentId){
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());

        return ResponseEntity.ok(student);
    }

    // DELETE request
    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("Student deleted successfully!");
    }
}
