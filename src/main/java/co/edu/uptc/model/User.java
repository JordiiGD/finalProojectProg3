package co.edu.uptc.model;

import co.edu.uptc.utils.EUserType;

import java.time.LocalDate;

public class User {

    private EUserType userType;
    private String name;
    private String surname;
    private String email;
    private String password;
    private LocalDate dob;
    private int idCard;

    public User(String name, String surname, String email, String password, LocalDate dob, int idCard) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.idCard = idCard;
    }

    public User(){
        name = "";
        surname = "";
        email = "";
        password = "";
        dob = LocalDate.now();
        idCard = 0;
    }

    public EUserType getUserType() {
        return userType;
    }

    public void setUserType(EUserType userType) {
        this.userType = userType;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }
}
