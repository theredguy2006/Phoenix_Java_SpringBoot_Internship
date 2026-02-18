package com.red.day5.repository;

import com.red.day5.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepo extends JpaRepository<JournalEntry, Long> {
}
