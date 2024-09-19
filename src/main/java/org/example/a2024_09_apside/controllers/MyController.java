package org.example.a2024_09_apside.controllers;

import org.example.a2024_09_apside.model.beans.StudentBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class MyController {

    //http://localhost:8080/hello
    @GetMapping("/hello")
    public String testHello(Model model){
        System.out.println("/hello");
        //Nom du fichier HTML que l'on souhaite afficher

        model.addAttribute("text", "Bonjour");
        //model.addAttribute("studentBean", new StudentBean("Toto", 18));
        model.addAttribute("studentList", Arrays.asList(
                new StudentBean("Toto", 18),
                new StudentBean("Tata", 16),
        new StudentBean("Alfred", 14)
                )

        );

        return "welcome";
    }

}
