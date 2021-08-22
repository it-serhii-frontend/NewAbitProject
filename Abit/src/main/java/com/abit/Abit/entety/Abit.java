package com.abit.Abit.entety;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.File;
import java.util.Set;

@Entity(name = "abit")
@Table(name = "abit")
public class Abit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name")
    @NotEmpty(message = "Between 2 and 50 chars")
    @Size(min = 2, max = 50)
    private String name;

    @Column(name = "surname")
    @NotEmpty(message = "Between 2 and 50 chars")
    @Size(min = 2, max = 50)
    private String surname;

    @Column(name = "age")
    @Range(min = 18, max = 90, message = "Between 18 and 90")
    private int age;

    @Column(name = "email")
    @Email(message = "Email should be valid, Between 2 and 100 chars")
    @Size(min = 2, max = 100)
    private String email;

    @Column(name = "password")
    @NotEmpty(message = "Between 4 and 100 chars")
    @Size(min = 4, max = 100)
    private String password;

    private String passwordCheck;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Column(name = "history")
    @Range(min = 1, max = 5, message = "Between 1 and 5")
    private int mark1;
    @Column(name = "language")
    @Range(min = 1, max = 5, message = "Between 1 and 5")
    private int mark2;
    @Column(name = "mathematic")
    @Range(min = 1, max = 5, message = "Between 1 and 5")
    private int mark3;

    @Lob
    @Column(name = "photo", columnDefinition="BLOB")
    private byte[] photo;

    private String averageMark;

    public String getAverageMark() {


        if (((mark1 + mark2 + mark3) / 3) >= 4) {
            averageMark = "Passed";
            return averageMark;
        }
        averageMark = "Not passed";
        return averageMark;
    }

    public void setAverageMark(String averageMark) {

        this.averageMark = averageMark;
    }


    public int getMark1() {
        return mark1;
    }

    public void setMark1(int mark1) {
        this.mark1 = mark1;
    }

    public int getMark2() {
        return mark2;
    }

    public void setMark2(int mark2) {
        this.mark2 = mark2;
    }

    public int getMark3() {
        return mark3;
    }

    public void setMark3(int mark3) {
        this.mark3 = mark3;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }
}
