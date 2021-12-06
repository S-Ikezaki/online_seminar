package com.example.online_seminar.controller.user;

import com.example.online_seminar.entity.user.Certification;
import com.example.online_seminar.entity.user.User;
import com.example.online_seminar.repository.CertificationRepository;
import com.example.online_seminar.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("student")
public class StudentController {

    private final UserRepository userRepository;
    private final CertificationRepository certificationRepository

    public StudentController(
            UserRepository userRepository,
            CertificationRepository certificationRepository
    ) {
        this.userRepository = userRepository;
        this.certificationRepository = certificationRepository;
    }

    // ログインしている学生のパスワード、タグ変更
    @PostMapping("/student/editProfile")
    public String editProfile(@ModelAttribute Certification user, Model model, BindingResult result) {
        if(result.hasErrors()) {
            return "";
        }
        certificationRepository.save(user);

        return "";
    }

    //

}
