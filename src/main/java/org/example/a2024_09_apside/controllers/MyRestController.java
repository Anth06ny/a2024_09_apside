package org.example.a2024_09_apside.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.a2024_09_apside.model.beans.StudentBean;
import org.example.a2024_09_apside.model.beans.TeacherBean;
import org.example.a2024_09_apside.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyRestController {

    @Autowired
    private TeacherService teacherService;


    //http://localhost:8080/addTeacher?name=toto&code=5
    @GetMapping("/addTeacher")
    public ResponseEntity<?> addTeacher(String name, int code, HttpSession session)
    {
        System.out.println("/addTeacher");

        try {
            teacherService.createTeacher(name, code, session.getId());
            return ResponseEntity.ok(teacherService.getAll());
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity( "{\"message\" : \"Une erreur est survenue " + e.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
        }
    }

    //http://localhost:8080/receiveStudent
//Json Attendu : {"name": "toto", "note": 12}
    @PostMapping("/receiveStudent")
    public void receiveStudent(@RequestBody StudentBean student) {
        System.out.println("/receiveStudent : " + student.getName() + " : " + student.getNote());

        //traitement, mettre en base…
        //Retourner d'autres données
    }


    //http://localhost:8080/max?p1=5&p2=6
    @GetMapping("/max")
    public Integer max(Integer p1,  Integer p2) {
        System.out.println("/max p1=" + p1 + " p2=" + p2);

        if(p1 == null) {
            return p2;
        }
        else if(p2 == null){
            return p1 ;
        }

        return Math.max(p1, p2);
    }

    //http://localhost:8080/test
    @GetMapping("/test")
    public String testMethode() {
        System.out.println("/test");

        return "helloWorld";
    }

    //http://localhost:8080/getStudent
    @GetMapping("/getStudent")
    public StudentBean getStudent() {
        System.out.println("/getStudent");

        StudentBean studentBean = new StudentBean("toto", 12);
        return studentBean;
    }



}
