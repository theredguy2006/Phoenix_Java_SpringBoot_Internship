package com.red.jounrnalpostgre.repository;

import com.red.jounrnalpostgre.entity.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalRepo extends JpaRepository<JournalEntry, Long> {
}
