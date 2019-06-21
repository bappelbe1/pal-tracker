/**
 * Copyright 2019 Dell Inc. or its subsidiaries. All Rights Reserved.
 */
package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class InMemoryTimeEntryRepository implements TimeEntryRepository{
    private HashMap<Long, TimeEntry> timeEntries = new HashMap();
    private long currentId =1L;

//    public InMemoryTimeEntryRepository() {
//    }

    @Override
    public TimeEntry create(TimeEntry timeEntry){
        Long id = currentId++;
        TimeEntry te = new TimeEntry(id, timeEntry.getProjectId(),timeEntry.getUserId(),timeEntry.getDate(),timeEntry.getHours());
        timeEntries.put(id,te);
        return te;
    }

    public TimeEntry find(long id){
        return timeEntries.get(id);
    }

    public List<TimeEntry> list(){
        return new ArrayList<>(timeEntries.values());
    }

    public TimeEntry update(long id, TimeEntry timeEntry){
        if (find(id) == null) return null;

        TimeEntry updatedEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntries.replace(id, updatedEntry);
        return updatedEntry;
    }

    @Override
    public void delete(long id){
        this.timeEntries.remove(id);
    }
}