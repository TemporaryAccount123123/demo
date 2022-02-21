package com.example.demo.repo;

import com.example.demo.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public interface TaskRepository extends CrudRepository <Task, Long> {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping('/Task')
    public String taskMain(Model model){
        Iterable<Task> tasks = taskRepository.findAll();
        model.addAttribute("tasks", tasks);
        return "task-main";
    }
}
