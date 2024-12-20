package com.example.Thu_hoc_phi.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
     String id;
     String username;
     String password;
     String firstName;
     String lastName;
     LocalDate dob;
    @ElementCollection
     Set<String> roles;
    @ManyToMany
    @JoinTable( name = "user_subjects", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "subject_id") )
    Set<Subject> subjects;
}
