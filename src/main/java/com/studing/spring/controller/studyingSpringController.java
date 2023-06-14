package com.studing.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class studyingSpringController {

    @GetMapping("hello")
    public String hello (Model model) {
        model.addAttribute("data", "hello!");

        return "hello"; // return 문의 내용은 view단 html의 파일명으로 찾아가는 듯.
    }

    @GetMapping("hello-mvc")
    public String helloMvc (@RequestParam("name") String name, Model model) {

        model.addAttribute("name", name);

        return "hello-template";
    }


    @GetMapping("hello-string")
    @ResponseBody // ResponseBody는 return문 자체를 내려줌.
    public String helloString (@RequestParam("name") String name, Model model) {

        model.addAttribute("name", name);

        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody // ResponseBody는 return문 자체를 내려줌.
    public Name helloApi (@RequestParam("name") String name) {
        Name nameObj = new Name();

        nameObj.setName(name);


        return nameObj;
    }

    static class Name {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
