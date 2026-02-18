package com.red.day5.controller;

import com.red.day5.entity.JournalEntry;
import com.red.day5.service.JournalService;
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
            myEntry.setJournalDate(LocalDateTime.now());
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
            old.setJournalTitle(myEntry.getJournalTitle() != null & "".equals(myEntry.getJournalTitle()) ? myEntry.getJournalTitle() : old.getJournalTitle());
            old.setJournalContent(myEntry.getJournalContent() != null & "".equals(myEntry.getJournalContent()) ? myEntry.getJournalContent() : old.getJournalContent());
//            old.setEmailId(myEntry.getEmailId() != null & "".equals(myEntry.getEmailId()) ? myEntry.getEmailId() : old.getEmailId());
            old.setJournalDate(LocalDateTime.now());
        }
        return journalEntries.put(myId, myEntry);
    }

}
