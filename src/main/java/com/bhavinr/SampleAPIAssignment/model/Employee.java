package com.bhavinr.SampleAPIAssignment.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @NotNull
    private Long id;
    private String firstname;
    private String lastname;
    private String contactnumber;

    public Employee(Long id,String firstname, String lastname, String contactNumber)
    {
        this.id=id;
       this.firstname =firstname;
       this.lastname =lastname;
       this.contactnumber =contactNumber;
    }

    public Employee() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getcontactnumber() {
        return contactnumber;
    }

    public void setcontactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }
}
