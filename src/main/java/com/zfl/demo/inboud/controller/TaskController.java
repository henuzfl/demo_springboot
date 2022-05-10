package com.zfl.demo.inboud.controller;

import com.zfl.demo.domain.task.Task;
import com.zfl.demo.domain.task.TaskService;
import io.swagger.annotations.Api;
import org.quartz.SchedulerException;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "定时任务接口")
@RestController
@RequestMapping("/tasks")
@CacheConfig(cacheNames = "controller.task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping
    @Cacheable(key = "'ALL_TASKS'")
    public List<Task> list() {
        return taskService.getAllTasks();
    }

    @PostMapping
    @Caching(evict = {
            @CacheEvict(key = "#task.id"),
            @CacheEvict(key = "'ALL_TASKS'")
    })
    public Task create(@Valid Task task) throws SchedulerException {
        return taskService.create(task);
    }

    @PostMapping("/{id}:pause")
    @Caching(evict = {
            @CacheEvict(key = "#id"),
            @CacheEvict(key = "'ALL_TASKS'")
    })
    public void pause(@PathVariable("id") Long id) throws SchedulerException {
        taskService.pause(id);
    }

    @PostMapping("/{id}:resume")
    @Caching(evict = {
            @CacheEvict(key = "#id"),
            @CacheEvict(key = "'ALL_TASKS'")
    })
    public void resume(@PathVariable("id") Long id) throws SchedulerException {
        taskService.resume(id);
    }

    @DeleteMapping("/{id}")
    @Caching(evict = {
            @CacheEvict(key = "#id"),
            @CacheEvict(key = "'ALL_TASKS'")
    })
    public void delete(@PathVariable("id") Long id) throws SchedulerException {
        taskService.delete(id);
    }
}
