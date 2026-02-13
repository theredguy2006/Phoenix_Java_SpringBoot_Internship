package com.red.responseentity.repository;

import com.red.responseentity.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepo extends JpaRepository<JournalEntry, Long> {
}
