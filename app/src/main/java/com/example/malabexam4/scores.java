package com.example.malabexam4;

import com.google.firebase.database.Exclude;

public class scores {

    String cust_id;
    String date;
    String time;
    String marks;
    String subject;


    public scores(String cust_id, String date, String time, String marks,String subject) {

        this.cust_id = cust_id;
        this.date = date;
        this.time = time;
        this.marks = marks;
        this.subject = subject;

    }

    public scores() {
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMarks() { return marks; }

    public void setMarks(String marks) {
        this.marks = marks;
    }


    public String getSubject() { return subject; }

    public void setSubject(String subject) {
        this.subject = subject;
    }



}
