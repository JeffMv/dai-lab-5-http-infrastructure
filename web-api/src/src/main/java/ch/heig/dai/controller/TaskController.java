package ch.heig.dai.controller;

import ch.heig.dai.model.Priority;
import ch.heig.dai.model.Task;
import ch.heig.dai.storage.TaskStorage;
import io.javalin.http.Context;

import java.util.*;

public class TaskController {

    protected TaskStorage tasks = new TaskStorage();

    public TaskController() {
    }

    //for test
    public TaskController(boolean b) {
        tasks.add(new Task("one", "it's task for test", Priority.LOW));
        tasks.add(new Task("two", "it's task for test"));
        tasks.add(new Task("three", "it's task for test", Priority.HIGH));
        tasks.add(new Task("four", "it's task for test"));
        tasks.add(new Task("five", "it's task for test","30-09-2024"));
        tasks.add(new Task("six", "it's task for test","02-07-2024"));
        tasks.add(new Task("seven", "it's task for test", "28-05-2024",Priority.HIGH));
        tasks.add(new Task("eight", "it's task for test","27-03-2024"));
    }

    public void getAll(Context ctx) {
        /*
        String hello = ctx.queryParam("hello");
        Map<String,List<String>> query = ctx.queryParamMap();
        if(query.isEmpty()){
            System.out.println("Query is empty");
        }
        */
        ctx.json(tasks.getAll());
    }


    public void getOne(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        ctx.json(tasks.find(id));
    }

    public void getInDate(Context ctx) {
        String date = ctx.pathParam("date");
        ctx.json(tasks.filter(task -> task.getDueDate().equals(date)));
    }

    public void create(Context ctx) {
        Task task = ctx.bodyAsClass(Task.class);
        if(task.getPriority() == null && task.getDueDate().isEmpty()){
            task = new Task(task.getTitle(),task.getDescription());
        }
        else if(task.getDueDate().isEmpty()) {
            task = new Task(task.getTitle(),task.getDescription(),task.getPriority());
        }
        else if(task.getPriority() == null){
            task = new Task(task.getTitle(), task.getDescription(),task.getDueDate());
        }
        tasks.add(task);
        ctx.status(201).json(task);
    }

    public void update(Context ctx) {
        int id = Integer.parseInt((ctx.pathParam("id")));
        Task taskRequest = ctx.bodyAsClass(Task.class);
        Task taskData = tasks.find(id);
        Task task = new Task(
            (!Objects.equals(taskRequest.getTitle(), ""))? taskRequest.getTitle() : taskData.getTitle(),
            (!Objects.equals(taskRequest.getDescription(), ""))? taskRequest.getDescription() : taskData.getDescription(),
            (!Objects.equals(taskRequest.getDueDate(), ""))? taskRequest.getDueDate() : taskData.getDueDate(),
            (!Objects.equals(taskRequest.getPriority(), null))? taskRequest.getPriority() : taskData.getPriority()
        );
        task.setId(id);
        tasks.update(id,task);
        ctx.status(200).json(task);
    }

    public void delete(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        tasks.remove(id);
        ctx.status(204);
    }
}
