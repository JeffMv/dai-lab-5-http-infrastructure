package ch.heig.dai.framwork;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StorageBase<T> implements StorageInterface<T> {

    protected ConcurrentHashMap<Integer, T> data = new java.util.concurrent.ConcurrentHashMap<>();
    protected int lastId = 0;

    public StorageBase() {}

    public Collection<T> getAll() {
        return data.values();
    }

    public T find(int id) {
        return data.get(id);
    }

    public Collection<T> filter(Predicate<T> p) {
        Collection<T> res =
            data.values().stream()
                .filter(p)
                .collect(Collectors.toList());

        return  res;
    }

    public T add(T data) {
        this.data.put(id(),data);
        return data;
    }

    public T update(int id, T data) {
        this.data.put(id,data);
        return data;
    }

    public void remove(int id) {
        data.remove(id);
    }

    protected int id() {
        return ++lastId;
    }

}
