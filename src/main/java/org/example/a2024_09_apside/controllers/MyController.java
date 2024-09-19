package org.example.a2024_09_apside.controllers;

import jakarta.servlet.http.HttpSession;
import org.example.a2024_09_apside.model.beans.StudentBean;
import org.example.a2024_09_apside.model.beans.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

@Controller
public class MyController {


    //http://localhost:8080/login
    @GetMapping("/login") //Affiche le formulaire
    public String login(UserBean userBean, HttpSession session) {
        System.out.println("/login : session=" + session.getId());

        return "login";  //Lance login.html
    }

    @PostMapping("/loginSubmit") //traite le formulaire
    public String loginSubmit(UserBean userBean, RedirectAttributes redirectAttributes) {
        System.out.println("/loginSubmit : " + userBean.getLogin() + " " + userBean.getPassword());

        try {

            if(userBean.getLogin().isBlank()) {
                throw new Exception("Login vide");
            }

            if(userBean.getPassword().isBlank()) {
                throw new Exception("Password vide");
            }

            redirectAttributes.addFlashAttribute("userBean", userBean);
            //Traitement du formulaire
            //Afin d'éviter la duplication de code, redirige sur la page qui s'occupe de l'affichage
            return "redirect:userRegister";
        }
        catch(Exception e){
            e.printStackTrace();

            redirectAttributes.addFlashAttribute("userBean", userBean);
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

            //Traitement du formulaire
            //Afin d'éviter la duplication de code, redirige sur la page qui s'occupe de l'affichage
            return "redirect:login";
        }



    }

    @GetMapping("/userRegister") //Affiche la page résultat
    public String userRegister(Model model) {
        System.out.println("/userRegister");


        return "userRegister"; //Lance userRegister.html
    }

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
