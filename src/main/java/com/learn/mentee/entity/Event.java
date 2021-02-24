package com.learn.mentee.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String title;
    private Date date;
}
