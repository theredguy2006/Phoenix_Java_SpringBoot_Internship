package com.red.jounrnalpostgre.controller;

import com.red.jounrnalpostgre.entity.JournalEntry;
import com.red.jounrnalpostgre.service.JournalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    public Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @Autowired
    private JournalService journalService;

    @GetMapping
    public List<JournalEntry> getAll() {
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public boolean createEntry(@Valid @RequestBody JournalEntry myEntry) {
        myEntry.setDate(LocalDateTime.now());
        journalService.saveJournal(myEntry);
        return true;
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getEntryById(@PathVariable long myId) {

        return journalService.getJournalById(myId).orElse(null);
    }

    @DeleteMapping("/id/{myId}")
    public boolean deleteEntryById(@PathVariable long myId) {
        journalService.deleteByID(myId);
        return true;
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
