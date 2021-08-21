package com.abit.Abit.controller;

import com.abit.Abit.entety.Abit;
import com.abit.Abit.service.AbitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;

@org.springframework.stereotype.Controller("/book")
@RequestMapping("/students")
public class Controller {

    @Autowired
    private AbitService abitServ;

    @GetMapping("/")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/main")
    public String main(Model model) {
        return "main";
    }




    @GetMapping("/about")
    public String about(Model model) {
        return "aboutUs";
    }

    @GetMapping("/all")
    public String findAll(Model model) {

        model.addAttribute("students", abitServ.findAll());

        return "allStudents";

    }

    @GetMapping("/studentOne/{id}")
    public String findOne(Model model, @PathVariable("id") Long id) {
        Abit student = abitServ.findById(id);

        model.addAttribute("student", student);
        return "oneStudent";

    }


    @GetMapping("/student")
    public String createStudent(@ModelAttribute("student") Abit student) {


        return "student";
    }


    @PostMapping("/student")
    public String creStud(@ModelAttribute("student") @Valid Abit student, BindingResult bR) {

        if (bR.hasErrors())
            return "/student";

        Iterable<Abit> students = abitServ.findAll();

        for (Abit el : students)
            if (el.getEmail().equals(student.getEmail()))

                return "studentFail";


        abitServ.save(student);
        return "redirect:/students/all";

    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {

        Abit student = abitServ.findById(id);

        model.addAttribute("student", student);
        return "studentUpdate";
    }

    @PostMapping("/{id}/edit")
    public String update(@ModelAttribute("student") @Valid Abit student, BindingResult bR, @PathVariable("id") Long id) {

        if (bR.hasErrors()) {
            return "studentUpdate";
        }
        abitServ.update(id, student);

        return "redirect:/students/all";
    }

    @GetMapping("/student/{id}")
    public String deletePost(@PathVariable("id") Long id) {

        abitServ.deleteById(id);

        return "redirect:/students/all";
    }

    @PostMapping("/login")
    public String update(Model model) {

        return "main";
    }

    @PostMapping("/upload")
    public String submit(
            @RequestParam MultipartFile file, @RequestParam String name,
            @RequestParam String email, ModelMap modelMap) {

        modelMap.addAttribute("name", name);
        modelMap.addAttribute("email", email);
        modelMap.addAttribute("file", file);
        return "fileUploadView";
    }


}
