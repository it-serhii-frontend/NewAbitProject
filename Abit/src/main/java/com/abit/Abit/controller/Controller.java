package com.abit.Abit.controller;

import com.abit.Abit.entety.Abit;
import com.abit.Abit.entety.FileModel;
import com.abit.Abit.entety.View;
import com.abit.Abit.repo.FileRepository;
import com.abit.Abit.service.AbitService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Controller("/book")
@RequestMapping("/students")
public class Controller {

    @Autowired
    private AbitService abitServ;

    @Autowired
    FileRepository fileRepository;

    @GetMapping("/")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/up")
    public String index() {
        return "uploadfile";
    }

    @PostMapping("/file/upload")
    public String uploadMultipartFile(@RequestParam("uploadfile") MultipartFile file) {
        try {

            FileModel filemode = new FileModel(file.getOriginalFilename(), file.getContentType(), file.getBytes());
            fileRepository.save(filemode);
            return "File uploaded successfully! -> filename = " + file.getOriginalFilename();
        } catch (Exception e) {
            return "FAIL! Maybe You had uploaded the file before or the file's size > 500KB";
        }
    }

    @JsonView(View.FileInfo.class)
    @GetMapping("/file/all")
    public List<FileModel> getListFiles() {
        return fileRepository.findAll();
    }


    @GetMapping("/file/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        Optional<FileModel> fileOptional = fileRepository.findById(id);

        if (fileOptional.isPresent()) {
            FileModel file = fileOptional.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                    .body(file.getPic());
        }

        return ResponseEntity.status(404).body(null);
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


}
