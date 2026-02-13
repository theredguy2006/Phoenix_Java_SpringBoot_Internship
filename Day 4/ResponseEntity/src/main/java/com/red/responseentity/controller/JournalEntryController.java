package com.red.responseentity.controller;

import com.red.responseentity.entity.JournalEntry;
import com.red.responseentity.service.JournalService;
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

    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<>(journalService.getAllJournal());
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@Valid @RequestBody JournalEntry myEntry) {
        try {
            myEntry.setDate(LocalDateTime.now());
            journalService.saveJournal(myEntry);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.print(e + "Error and Exception ");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> getEntryById(@PathVariable long myId) {
        Optional<JournalEntry> journalEntry = journalService.getJournalById(myId);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> deleteEntryById(@PathVariable long myId) {
        journalService.deleteByID(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{myId}")
    public JournalEntry updateEntry(@PathVariable long myId, @Valid @RequestBody JournalEntry myEntry) {

        JournalEntry old = journalService.getJournalById(myId).orElse(null);
        if (old != null) {
            old.setTitle(myEntry.getTitle() != null & "".equals(myEntry.getTitle()) ? myEntry.getTitle() : old.getTitle());
            old.setContent(myEntry.getContent() != null & "".equals(myEntry.getContent()) ? myEntry.getContent() : old.getContent());
            old.setEmailId(myEntry.getEmailId() != null & "".equals(myEntry.getEmailId()) ? myEntry.getEmailId() : old.getEmailId());
            old.setDate(LocalDateTime.now());
        }
        return journalEntries.put(myId, myEntry);
    }

    // Update only email
    @PutMapping("/email/{id}")
    public JournalEntry updateEmail(@PathVariable long id, @RequestBody JournalEntry request) {

        JournalEntry entry = journalEntries.get(id);

        if (entry != null) {
            entry.setEmailId(request.getEmailId());
        }

        return entry;
    }
}
