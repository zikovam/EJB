package ejb.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "student")
@NamedQuery(name = "Student.asList", query = "SELECT student FROM Student student")
public class Student {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private @Setter @Getter int studentId;

    @Column(name = "first_name")
    private @Setter @Getter String firstName;

    @Column(name = "sur_name")
    private @Setter @Getter String surName;

    @Column(name = "patronymic")
    private @Setter @Getter String patronymic;

    @Column(name = "date_of_birth")
    private @Setter @Getter Date dateOfBirth;

    @Column(name = "sex")
    private @Setter @Getter char sex;

    @Column(name = "group_id")
    private @Setter @Getter int groupId;

    @Column(name = "education_year")
    private @Setter @Getter int educationYear;
}