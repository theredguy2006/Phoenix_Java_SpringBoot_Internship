package com.red.jounrnalpostgre.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Date;


//@Document(collection="journal_entry")
@Entity
@Table(name = "journals")
public class JournalEntry {
    //    @NotEmpty(message = "You cannot create a journal without this")
//    This only works on String , Arrays , Collections and etc not for other values.
    @NotNull(message = "Id cannot be null ")
    @Id
    private long id;
    @NotNull(message = "Title cannot be null ")
    private String title;
    @NotNull(message = "content cannot be null ")
    private String content;
    @Email(message = "Please Enter a Valid Email Id ")
//    Somehow adding a ';' will raise/create an error.
    private String emailId;

    private LocalDateTime date;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date =date ;
    }
}
