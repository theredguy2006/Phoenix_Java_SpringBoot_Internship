package com.red.jounrnalpostgre.controller;

import com.red.journalingapp.entity.journalEntry;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class journalEntryController {

    public Map<Long, journalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<journalEntry> getAll() {
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public void createEntry(@Valid @RequestBody journalEntry myEntry) {
        journalEntries.put(myEntry.getId(), myEntry);
    }

    @GetMapping("/id/{myId}")
    public journalEntry getEntryById(@PathVariable long myId) {
        return journalEntries.get(myId);
    }

    @DeleteMapping("/id/{myId}")
    public journalEntry deleteEntryById(@PathVariable long myId) {
        return journalEntries.remove(myId);
    }

    @PutMapping("/id/{myId}")
    public journalEntry updateEntry(@PathVariable long myId, @Valid @RequestBody journalEntry myEntry) {

        return journalEntries.put(myId, myEntry);
    }

    // Update only email
    @PutMapping("/email/{id}")
    public journalEntry updateEmail(@PathVariable long id, @RequestBody journalEntry request) {

        journalEntry entry = journalEntries.get(id);

        if (entry != null) {
            entry.setEmailId(request.getEmailId());
        }

        return entry;
    }
}
