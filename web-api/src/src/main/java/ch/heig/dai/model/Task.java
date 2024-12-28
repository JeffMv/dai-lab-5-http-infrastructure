package ch.heig.dai.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {

    protected Integer id = null;
    protected String title = "";
    protected String description = "";
    protected Priority priority = null;
    protected LocalDate dueDate = null;
    protected DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public Task() {}

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.priority = Priority.MEDIUM;
        this.dueDate = LocalDate.now();
    }
    public Task(String title, String description, String dueDate) {
        this(title,description);
        this.dueDate = LocalDate.parse(dueDate,dateFormat);
    }


    public Task(String title, String description, Priority priority) {
        this(title,description);
        this.priority = priority;
    }

    public Task(String title, String description, String date, Priority priority) {
        this(title,description,date);
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDueDate(String date) {
        this.dueDate = LocalDate.parse(date,dateFormat);
    }

    public String getDueDate() {
        if(this.dueDate == null){
            return "";
        }
        return this.dateFormat.format(dueDate);
    }

    public Priority getPriority() {
        return this.priority;
    }

    @Override
    public String toString() {
        return "id : " + this.id + "\n"
                + "title : " + this.title + "\n"
                + "description : " + description + "\n"
                + "dueDate : " + dueDate + "\n"
                + "Priority : " + priority + "\n";
    }
}
