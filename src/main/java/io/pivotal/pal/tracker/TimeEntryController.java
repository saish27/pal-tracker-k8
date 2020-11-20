    package io.pivotal.pal.tracker;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/time-entries")
    public class TimeEntryController {

        private TimeEntryRepository timeEntryRepository;

        @Autowired
        public TimeEntryController(  TimeEntryRepository timeEntryRepository) {
            this.timeEntryRepository = timeEntryRepository;
        }

        @PostMapping
        public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
            return new ResponseEntity<>(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);
        }

        @GetMapping("{TIME_ENTRY_ID}")
        public ResponseEntity<TimeEntry> read(@PathVariable(name = "TIME_ENTRY_ID")long timeEntryId) {
            TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
            return timeEntry != null ? new ResponseEntity<>(timeEntry,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        @GetMapping
        public ResponseEntity<List<TimeEntry>> list() {
            return new ResponseEntity<>(timeEntryRepository.list(),HttpStatus.OK);
        }
        @PutMapping("{TIME_ENTRY_ID}")
        public ResponseEntity<TimeEntry> update(@PathVariable(name = "TIME_ENTRY_ID") long timeEntryId, @RequestBody TimeEntry expected) {
            TimeEntry timeEntry = timeEntryRepository.update(timeEntryId,expected);
            return timeEntry != null ? new ResponseEntity<>(timeEntry,HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        @DeleteMapping("{TIME_ENTRY_ID}")
        public ResponseEntity<TimeEntry> delete(@PathVariable(name = "TIME_ENTRY_ID")long timeEntryId) {
            timeEntryRepository.delete(timeEntryId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
