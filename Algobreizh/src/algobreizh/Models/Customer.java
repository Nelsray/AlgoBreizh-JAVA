/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.Models;

import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author paul
 */
public class Customer {
    
    int id;
    String firstname;
    String lastname;
    String email;
    City city;
    LocalDateTime lastDate;
    
    public Customer(int _id, String _firstname, String _lastname, String _email, City _city, LocalDateTime _date){
        this.id = _id;
        this.firstname = _firstname;
        this.lastname = _lastname;
        this.email = _email;
        this.city = _city;
        this.lastDate = _date;
    }
    
    public Customer(){
        
    }
    
    public LocalDateTime getLastDate() {
        return lastDate;
    }
    
    public void setLastDate(LocalDateTime date) {
        lastDate = date;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    
}