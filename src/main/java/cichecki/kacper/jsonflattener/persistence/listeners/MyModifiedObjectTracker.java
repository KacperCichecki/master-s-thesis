package cichecki.kacper.jsonflattener.persistence.listeners;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;


public class MyModifiedObjectTracker implements ModifiedObjectTracker{

    private final AtomicInteger counter = new AtomicInteger(0);

    public void notifyModified() {
        counter.incrementAndGet();
    }

    public int getCounter() {
        return counter.intValue();
    }

}
