package com.red.day5.controller;

import com.red.day5.entity.JournalEntry;
import com.red.day5.entity.UserEntity;
import com.red.day5.service.JournalService;
import com.red.day5.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    public Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @Autowired
    private JournalService journalService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<>(journalService.getAllJournal());
    }

    @GetMapping("{userName}")
    public ResponseEntity<?> getUserJournal(@Valid @PathVariable String userName) {

        UserEntity user = userService.findByUserName(userName);
        userService.findByUserName(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@Valid @RequestBody JournalEntry myEntry, @PathVariable String userName) {
        try {
            journalService.saveJournal(myEntry, userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.print(e + "Error and Exception ");
            System.out.print("Error in the Create Entry method which is in journal controller ");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
//        try {
//            myEntry.setJournalDate(LocalDateTime.now());
//            journalService.saveJournal(myEntry);
//            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
//        } catch (Exception e) {
//            System.out.print(e + "Error and Exception ");
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable long myId) {
        Optional<JournalEntry> journalEntry = journalService.getJournalById(myId);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{userName}/{myId}")
    public ResponseEntity<JournalEntry> deleteEntryById(@PathVariable long myId, @PathVariable String userName) {
        journalService.deleteByID(myId, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{userName}/{myId}")
    public JournalEntry updateEntry(@PathVariable long myId, @Valid @RequestBody JournalEntry myEntry, @PathVariable String userName) {

        JournalEntry old = journalService.getJournalById(myId).orElse(null);
        if (old != null) {
            old.setJournalTitle(myEntry.getJournalTitle() != null & "".equals(myEntry.getJournalTitle()) ? myEntry.getJournalTitle() : old.getJournalTitle());
            old.setJournalContent(myEntry.getJournalContent() != null & "".equals(myEntry.getJournalContent()) ? myEntry.getJournalContent() : old.getJournalContent());
            old.setJournalDate(LocalDateTime.now());
            journalService.saveJournal(myEntry);
        }
        return journalEntries.put(myId, myEntry);
    }

}
