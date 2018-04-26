package io.swingdev.swing_overflow.resources_management.domain;

import java.util.Date;

public class Message {
    private String text;
    private Date date;

    public String getText() {
        return text;
    }

    public Date getDate() {
        return date;
    }

    public void changeDate(Date date) {
        this.date = date;
    }
}
