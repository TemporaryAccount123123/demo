package com.example.demo.controllers;

import com.example.demo.model.Task;
import com.example.demo.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/task")
    public String taskMain(Model model){
        Iterable<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "task/taskMain";
    }

    @GetMapping("/task/add")
    public String taskAdd(Model model){
        return "task/taskAdd";
    }

    @PostMapping("/task/add")
    public String taskPostAdd(@RequestParam String name, @RequestParam String description, @RequestParam int priority, @RequestParam LocalDate date, Model model){
        Task task = new Task(name, description, priority, date);
        taskRepository.save(task);
        return "redirect:/task";
    }

    @GetMapping("/task/{id}")
    public String taskDetails(@PathVariable(value = "id") long id, Model model){
        if(!taskRepository.existsById(id)){
            return "redirect:/task";
        }
        Optional<Task> task = taskRepository.findById(id);
        ArrayList<Task> res = new ArrayList<>();
        task.ifPresent(res::add);
        model.addAttribute("task", res);
        return "task/taskDetails";
    }
    @GetMapping("/task/{id}/edit")
    public String taskEdit(@PathVariable(value = "id") long id, Model model){
        if(!taskRepository.existsById(id)){
            return "redirect:/task";
        }
        Optional<Task> task = taskRepository.findById(id);
        ArrayList<Task> res = new ArrayList<>();
        task.ifPresent(res::add);
        model.addAttribute("task", res);
        return "task/taskEdit";
    }
    @PostMapping("/task/{id}/edit")
    public String taskPostUpdate(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String description, @RequestParam int priority, @RequestParam LocalDate date, Model model){
        Task task = taskRepository.findById(id).orElseThrow();
        task.setName(name);
        task.setDescription(description);
        task.setPriority(priority);
        task.setDate(date);
        taskRepository.save(task);
        return "redirect:/task";
    }
    @PostMapping("/task/{id}/remove")
    public String taskPostDelete(@PathVariable(value = "id") long id, Model model){
        Task task = taskRepository.findById(id).orElseThrow();
        taskRepository.delete(task);
        return "redirect:/task";
    }
}