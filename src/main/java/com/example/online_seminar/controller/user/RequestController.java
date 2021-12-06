package com.example.online_seminar.controller.user;

import com.example.online_seminar.entity.user.Request;
import com.example.online_seminar.repository.RequestRepository;
import com.example.online_seminar.repository.TagRepository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("request")
public class RequestController {

    private RequestRepository requestRepository;
    private TagRepository tagRepository;

    public RequestController(
            RequestRepository requestRepository,
            TagRepository tagRepository
    ) {
        this.requestRepository = requestRepository;
        this.tagRepository = tagRepository;
    }

    // リクエストを一覧表示
    @GetMapping("/showRequest")
    public String searchRequest(Model model) {
        model.addAttribute("requests", requestRepository.findAll());
        return "";
    }

    // リクエストをタグ検索
    @GetMapping("showRequest/{tag}")
    public String showRequestByTag(Model model, @PathVariable("tag") String tag) {
        model.addAttribute("requests", tagRepository.findByName(tag));
        return "";
    }

    // リクエストを受けゼミ作成画面へ
    @GetMapping("/addGroupByRequest")
    public String addGroupByRequest(@ModelAttribute Model model, Request request, BindingResult result) {
        model.addAttribute("request", request);
        if (result.hasErrors()) {
            return "";
        }
        return "";
    }
}
