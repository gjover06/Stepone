package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    TwitterRepository twitterRepository;
    private Object BindingResult;

    @RequestMapping("/")
    public String listTwitters(Model model){
        model.addAttribute("twitters", twitterRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String twitterForm(Model model) {
        model.addAttribute("twitter", new Twitter());
        return "twitterform";
    }
    @PostMapping("/process")
    public String processForm(@Valid Twitter twitter, BindingResult result){
        if (result.hasErrors()){
            return "redirect:/";
        }
        twitterRepository.save(twitter);
        return "redirect:/";
    }
    @RequestMapping("/detail/{id}")
    public String showTwitter(@PathVariable("id") long id, Model model){

        model.addAttribute("twitter", twitterRepository.findById(id).get());
        return "show";
    }
    @RequestMapping("/update/{id}")
    public String updateTwitter(@PathVariable("id") long id, Model model){
        model.addAttribute("twitter", twitterRepository.findById(id).get());
        return "twitterform";
    }

    @RequestMapping("/delete/{id}")
    public String delTwitter(@PathVariable("id") long id){
        twitterRepository.deleteById(id);
        return "redirect:/";
    }
}
