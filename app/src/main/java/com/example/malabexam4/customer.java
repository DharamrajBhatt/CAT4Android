package com.example.malabexam4;

import com.google.firebase.database.Exclude;

public class customer {

    String cust_name;
    String cust_email;
    String cust_phone;
    String cust_register;
    String cust_password;
    String cust_id;


    public customer(){

    }


    public customer(String cust_name, String cust_email, String cust_phone, String cust_address, String cust_password) {
        this.cust_name = cust_name;
        this.cust_email = cust_email;
        this.cust_phone = cust_phone;
        this.cust_register = cust_address;
        this.cust_password = cust_password;
    }

    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_email() {
        return cust_email;
    }

    public void setCust_email(String cust_email) {
        this.cust_email = cust_email;
    }

    public String getCust_phone() {
        return cust_phone;
    }

    public void setCust_phone(String cust_phone) {
        this.cust_phone = cust_phone;
    }

    public String getCust_register() {
        return cust_register;
    }

    public void setCust_register(String cust_address) {
        this.cust_register = cust_address;
    }

    public String getCust_password() {
        return cust_password;
    }

    public void setCust_password(String cust_password) {
        this.cust_password = cust_password;
    }

    @Exclude
    public String getCust_id() {
        return cust_id;
    }

    @Exclude
    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }
}
