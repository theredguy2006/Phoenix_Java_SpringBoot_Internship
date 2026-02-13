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
    @NotNull(message = "Id cannot be null ")
    @Id
    private Long JournalID;
    @NotNull(message = "Title cannot be null ")
    private String JournalTitle;
    @NotNull(message = "content cannot be null ")
    private String JournalContent;
    private LocalDateTime JournalDate;
    @ManyToOne
    @JoinColumn(name = "UserId")
    private UserEntity userEntity;


}
