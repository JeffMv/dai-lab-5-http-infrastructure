package ch.heig.dai.storage;

import ch.heig.dai.framwork.StorageBase;
import ch.heig.dai.model.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;

public class TaskStorage extends StorageBase<Task> {

    protected DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public TaskStorage() {}

    @Override
    public Collection<Task> getAll() {
        List<Task> list = new LinkedList<>(data.values());
        orderByPriorities(list);
        orderByDateAscending(list);
        return list;
    }

    @Override
    public Collection<Task> filter(Predicate<Task> p){
        List<Task> list = new LinkedList<>(super.filter(p));
        orderByPriorities(list);
        orderByDateAscending(list);
        return list;
    }

    @Override
    public Task add(Task data) {
        super.add(data);
        data.setId(lastId);
        return data;
    }

    private void orderByDateAscending(List<Task> data){
        data.sort((t1, t2) -> LocalDate.parse(t1.getDueDate(), dateFormat)
                .compareTo(LocalDate.parse(t2.getDueDate(), dateFormat)));
    }

    public void orderByPriorities(List<Task> data) {
        data.sort(Comparator.comparing(Task::getPriority));
    }


}
