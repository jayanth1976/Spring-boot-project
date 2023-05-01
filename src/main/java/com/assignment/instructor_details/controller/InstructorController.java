package com.assignment.instructor_details.controller;

import com.assignment.instructor_details.entity.Instructor;
import com.assignment.instructor_details.service.InstructorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    InstructorService instructorService;

    @GetMapping("/list")
    public String findAll(Model theModel) {
        List<Instructor> theInstructors = instructorService.findAll();
        theModel.addAttribute("instructors", theInstructors);
        return "instructor/instructor-list";
    }


    @PostMapping("/save")
    public String saveInstructor(@ModelAttribute("instructor") @Valid Instructor theInstructor, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "instructor/instructor-form";
        } else {
            instructorService.save(theInstructor);
            return "redirect:/instructors/list";
        }
    }

    @GetMapping("/showFormForUpdate")
    public String update(@RequestParam("instructorId") int theId, Model theModel) {
        Instructor theInstructor = instructorService.findById(theId);
        theModel.addAttribute("instructor", theInstructor);
        return "instructor/instructor-form";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        Instructor theInstructor = new Instructor();

        theModel.addAttribute("instructor", theInstructor);

        return "instructor/instructor-form";
    }
    @GetMapping("/delete")
    public String deleteInstructor(@RequestParam("instructorId") int theId) {
        instructorService.deleteById(theId);
        return "redirect:/instructors/list";
    }
}
