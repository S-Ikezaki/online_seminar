<<<<<<< HEAD
//package com.example.online_seminar.controller;
//
//import com.example.online_seminar.model.Certification;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
=======
package com.example.online_seminar.controller;

import com.example.online_seminar.model.user.Certification;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String login(@ModelAttribute Certification certification) {
        return "login";
    }


    @PostMapping("/")
    public String meinmenu(@Validated @ModelAttribute Certification certification, BindingResult result) {

        if (result.hasErrors()) {
            return "login";
        }
        return "mainmenu";
    }
}





>>>>>>> e0e7db5fa17b732b65ebd7219b17ebded4b549fb
//
//@Controller
//public class HomeController {
//
//    @GetMapping("/")
//    public String login(@ModelAttribute Certification certification) {
//        return "login";
//    }
//
//
//    @PostMapping("/")
//    public String meinmenu(@Validated @ModelAttribute Certification certification, BindingResult result) {
//
//        if (result.hasErrors()) {
//            return "login";
//        }
//        return "mainmenu";
//    }
//}


