package com.red.day5.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


//@Document(collection="journal_entry")
@Entity
@Table(name = "journals")
@Getter
@Setter

public class JournalEntry {
    //    @NotEmpty(message = "You cannot create a journal without this")
//    This only works on String , Arrays , Collections and etc not for other values.
//    @NotNull(message = "Id cannot be null ")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long journalID;
    @NotNull(message = "Title cannot be null ")
    private String journalTitle;
    @NotNull(message = "content cannot be null ")
    private String journalContent;
    private LocalDateTime journalDate;
    @ManyToOne
//    Todo Learn about how does the schema works and hand write it and then learn it.
    @JoinColumn(name = "UserId", nullable = false)
    private UserEntity userEntity;


}
