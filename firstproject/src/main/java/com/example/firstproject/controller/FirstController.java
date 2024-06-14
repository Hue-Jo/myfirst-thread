package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi") // 클라이언트의 url 요청을 받아서 특정 컨트롤러의 메서드가 처리하게 함
    public String niceToMeetYou(Model model) { // Model 타입의 model 매개변수

        // model 객체가 "휴조"값을 "username"에 연결해서 브라우저로 보냄
        model.addAttribute("username", "Hue-jo");

        return "greetings"; // greetings.mustache 반환
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {

        model.addAttribute("nickname", "휴조");
        return "goodbye";
    }
}
