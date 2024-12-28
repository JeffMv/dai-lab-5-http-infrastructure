package ch.heig.dai.framwork;

import java.util.Collection;

public interface StorageInterface<T> {

    public Collection<T> getAll();

    public T find(int id);

    public T add(T data);

    public T update(int id, T data);

    public void remove(int id);
}
