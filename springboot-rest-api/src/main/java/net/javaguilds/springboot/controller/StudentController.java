package net.javaguilds.springboot.controller;

import net.javaguilds.springboot.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Struct;
import java.util.ArrayList;

@RestController
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public Student getStudent() {
        return new Student(1, "firstName", "lastName");
    }

    @GetMapping("students")
    public ArrayList<Student> getStudents() {
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student(2, "Johnson", "Chen"));
        students.add(new Student(3, "Test", "2"));
        students.add(new Student(4, "Test", "3"));
        students.add(new Student(5, "Test", "4"));
        return students;
    }

    // Spring boot REST API with path variable
    // {id} - URI template variable
    // http://localhost:8080/students/1

    // @PathVariable annotation used on a method argument to bind it
    // to a value of a URI template variable
    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentPathVariable(@PathVariable int id,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName) {
        return new Student(id, firstName, lastName);
    }

    //REST API with request param
    //http://localhost:8080/students/query?id=1
    @GetMapping("/students/query")
    public Student studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        return new Student(id, "request-param " + firstName, "request-param " +lastName);
    }

    // REST API: POST
    // @PostMapping and @RequestBody
    // The RequestBody annoation is responsible for retrieving the HTTP request body and
    // automatically converting it to the Java object


    @PostMapping("student/create")
    // By default if we dont have Response Status it will automatically return 200
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }
}
