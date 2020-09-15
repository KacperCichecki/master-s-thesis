package cichecki.kacper.jsonflattener.persistence.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

@Component
public class DaoListener {

    private ModifiedObjectTracker tracker;

    public DaoListener() {
    }

    @Autowired
    public DaoListener(ModifiedObjectTracker tracker) {
        this.tracker = tracker;
    }

    @PrePersist
    @PreUpdate
    @PreRemove
    private void beforeAnyOperation(Object object) {
        tracker.notifyModified();
    }

}