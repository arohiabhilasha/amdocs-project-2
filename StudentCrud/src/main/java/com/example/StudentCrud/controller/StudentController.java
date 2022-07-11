package com.example.StudentCrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
import com.example.StudentCrud.domain.Student;
import com.example.StudentCrud.service.StudentService;

@Controller  // annotation for controller. Marks the class as a web controller, capable of handling the HTTP requests.
public class StudentController {
	@Autowired
	/*
	 * To wire the application parts together, use the @Autowired on the fields,
	 * constructors, or methods in a component. Spring's dependency injection
	 * mechanism wires appropriate beans into the class members marked
	 * with @Autowired.
	 */    
	private StudentService service;
 
    @GetMapping("/")   // Annotation for mapping HTTP GET requests onto specific handler methods.
    public String viewHomePage(Model model) {
        List<Student> liststudent = service.listAll();
        model.addAttribute("liststudent", liststudent);
        System.out.print("Get / ");
        return "index";
    }
 
    @GetMapping("/new")
    public String add(Model model) {
        model.addAttribute("student", new Student());
        return "new";
    }
 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveStudent(@ModelAttribute("student") Student std) {
        service.save(std);
        return "redirect:/";
    }
 
	/*
	 * The @RequestMapping(method = RequestMethod.GET, value = "/path") annotation
	 * specifies a method in the controller that should be responsible for serving
	 * the HTTP request to the given path.
	 */   
    @RequestMapping("/edit/{id}")
    public ModelAndView showEditStudentPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("new");
        Student std = service.get(id);
        mav.addObject("student", std);
        return mav;
        
    }
    @RequestMapping("/delete/{id}")
    public String deletestudent(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }
}
