/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algobreizh.Models;

import java.time.LocalDateTime;

public class Meeting {

    private int id;
    private Salesman Salesman;
    private Customer Customer;
    private LocalDateTime date;
    private String infos;
    private String contact;
    private String telephone;

    // Constructor with id
    public Meeting(int _id, Salesman _salesman, Customer _customer,
            LocalDateTime _date, String _infos, String _contact, String _telephone) {
        this.id = _id;
        this.Salesman = _salesman;
        this.Customer = _customer;
        this.date = _date;
        this.infos = _infos;
        this.contact = _contact;
        this.telephone = _telephone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Salesman getSalesman() {
        return Salesman;
    }

    public String getContact() {
        return contact;
    }

    public void setSalesman(Salesman Salesman) {
        this.Salesman = Salesman;
    }

    public Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(Customer Customer) {
        this.Customer = Customer;
    }

    public LocalDateTime getDateTime() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTelephone() {
        return telephone;
    }

    public String setTelephone(String telephone) {
        return telephone;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
