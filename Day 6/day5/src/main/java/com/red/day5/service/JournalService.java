package com.red.day5.service;

import com.red.day5.entity.JournalEntry;
import com.red.day5.entity.UserEntity;
import com.red.day5.repository.JournalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalService {

    @Autowired
    private JournalRepo journalRepo;
    @Autowired
    private UserService userService;

    //    TODO : Learn about this method and it's controller
    //     by hand writing it and going in detail with the flow , also focus on controller
    public void saveJournal(JournalEntry journalEntry, String userName) {
        UserEntity user = userService.findByUserName(userName);
//        Todo before adding line 26 there was an error so learn about that and try to understand it.
        journalEntry.setUserEntity(user);
        journalEntry.setJournalDate(LocalDateTime.now());
        JournalEntry saved = journalRepo.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
    }

//    Todo Learn about the flow of this program. As detailes as you can.
//     After learning about the transactional annotation.
    public void saveJournal(JournalEntry journalEntry) {
        journalRepo.save(journalEntry);
    }


    public List<JournalEntry> getAllJournal() {
        return journalRepo.findAll();

    }


    public Optional<JournalEntry> getJournalById(Long id) {
        return journalRepo.findById(id);
    }

    public void deleteByID(Long id, String userName) {
//        UserEntity user = userService.findByUserName(userName);
//        user.getJournalEntries().removeIf(x-> x.getJournalID().equals(id));
//        userService.saveUser(user);
        journalRepo.deleteById(id);
    }
}

// controller --> service --> repository