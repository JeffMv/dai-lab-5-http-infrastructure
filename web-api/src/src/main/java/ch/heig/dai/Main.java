package ch.heig.dai;

import ch.heig.dai.controller.TaskController;
import io.javalin.Javalin;

public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7070);

        // for data for test add true in TaskController() contructor ( ... = new TaskController(true))
        // for without dataTest, juste leave empty (... = new TaskController(); )
        TaskController taskController = new TaskController(true);

        app.get("/api/task/",taskController::getAll);
        app.get("/api/task/{id}",taskController::getOne);
        app.get("/api/task/date/{date}",taskController::getInDate);
        app.post("/api/task/",taskController::create);
        app.put("/api/task/{id}",taskController::update);
        app.delete("/api/task/{id}",taskController::delete);
    }
}