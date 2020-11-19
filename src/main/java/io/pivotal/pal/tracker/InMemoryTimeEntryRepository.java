package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class InMemoryTimeEntryRepository implements TimeEntryRepository{
    Map<Long,TimeEntry> timeEntries = new HashMap<>();
    private long count = 1;

    public TimeEntry create(TimeEntry timeEntry) {
        //if(!timeEntries.containsKey(timeEntry.getId())){
        long id = count++;
        timeEntry.setId(id);
            timeEntries.put(id,timeEntry);
        //}
        return timeEntry;
    }


    public TimeEntry find(long id) {
        return timeEntries.get(id);
    }


    public TimeEntry update(long l, TimeEntry timeEntry) {
        if(timeEntries.containsKey(l)){
            long id = l;
            timeEntry.setId(l);
            timeEntries.put(l,timeEntry);
            return timeEntry;
        }
        return null;
    }

    public void delete(long id) {
        timeEntries.remove(id);
    }


    public List<TimeEntry> list() {
        return (List<TimeEntry>) timeEntries.values().stream().collect(Collectors.toList());
    }
}
