package com.red.day5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter

public class UserEntity {

    //    NOTE TO SELF THE NAMING CONVENTION IS WEIRD FOR ENTITY AND DON'T USE _ , - Or any of that. Idk man.
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // @ID does not mean Auto Generate !!!!!!!
//    private Long UserId;
////    If possible
//    @NotNull
//    @Column(unique = true)
//    private String UserName;
//    @Email(message = "Please Enter a Valid Email Id ")
    /// /    Somehow adding a ';' will raise/create an error.
//    @NotNull
//    private String EmailID;
//
//    @NotNull
//    private Long UserPWD;


//        This shit gives error the null one in data base and exception. Because it conflicts with the database and all.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserId;

    @Column(name = "u_name", unique = true, nullable = false)
    private String userName;

    @Column(name = "emailid", nullable = false)
    private String emailID;

    @Column(name = "u_password", nullable = false)

    private Long userPwd;

    @Column(name = "date")
    private LocalDateTime date;


    //    The Mapped By is CASE SENSITIVE !!!!!!!!!!!!!!!!!!!!!!!
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL)
//    TODO learn about this keyword.
    @JsonIgnore
    private List<JournalEntry> journalEntries = new ArrayList<>();
}
