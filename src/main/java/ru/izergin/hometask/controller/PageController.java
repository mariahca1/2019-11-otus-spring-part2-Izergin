package ru.izergin.hometask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @GetMapping(path = {"/main","/"})
    public String mainPage() {
        return "mainPage";
    }

    @GetMapping(path = {"/update","/edit"})
    public String getUpdateBook(@RequestParam String id) {
        return "editPage";
    }

    @GetMapping("/create")
    public String createBookPage() {
        return "createPage";
    }

}