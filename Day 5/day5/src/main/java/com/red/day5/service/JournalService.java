package com.red.day5.service;

import com.red.day5.entity.JournalEntry;
import com.red.day5.repository.JournalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalService {

    @Autowired
    private JournalRepo journalRepo;

    public void saveJournal(JournalEntry journalEntry) {
        journalRepo.save(journalEntry);
    }

    public List<JournalEntry> getAllJournal() {
        return journalRepo.findAll();

    }


    public Optional<JournalEntry> getJournalById(Long id) {
        return journalRepo.findById(id);
    }

    public void deleteByID(Long id) {
        journalRepo.deleteById(id);
    }
}

// controller --> service --> repository