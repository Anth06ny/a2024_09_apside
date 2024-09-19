package org.example.a2024_09_apside.controllers;

import org.example.a2024_09_apside.model.beans.StudentBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

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
