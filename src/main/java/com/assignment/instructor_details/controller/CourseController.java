package com.assignment.instructor_details.controller;

import com.assignment.instructor_details.entity.Course;
import com.assignment.instructor_details.entity.Instructor;
import com.assignment.instructor_details.service.CourseService;
import com.assignment.instructor_details.service.InstructorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    InstructorService instructorService;

    @Autowired
    CourseService courseService;

    @PostMapping("/save")
    public String saveCourse(@ModelAttribute("course") @Valid Course theCourse, BindingResult bindingResult, @RequestParam("instructorId") int theId){
        if (bindingResult.hasErrors()) {
            return "redirect:/courses/add?instructorId="+theId;
        }
        else {
            Instructor theInstructor = instructorService.findById(theId);
            theInstructor.add(theCourse);
            courseService.save(theCourse);
            return "redirect:/courses/allCourses";
        }
    }

    @GetMapping("/add")
    public String addCourse(Model theModel, @RequestParam("instructorId") int theId){
        Course theCourse = new Course();
        theModel.addAttribute("course",theCourse);
        theModel.addAttribute("instructorId",theId);
        return "course/course-form";
    }

    @GetMapping("/list")
    public String instructorCourseList(Model theModel,@RequestParam("instructorId") int theId){
        Instructor instructor = instructorService.findById(theId);
        List<Course> theCourses = instructor.getCourses();
        theModel.addAttribute("courses",theCourses);
        theModel.addAttribute("name",instructor.getFirstName()+" "+instructor.getLastName());
        return "course/instructor-course-list";
    }

    @GetMapping("/allCourses")
    public String getAllCourses(Model theModel){
        List<Course> theCourses = courseService.findAll();
        theModel.addAttribute("allCourses",theCourses);
        return "course/all-courses";
    }


}
