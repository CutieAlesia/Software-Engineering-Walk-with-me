package com.acmseproject.WebService.Report;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class Report {

    @Id private int id;
    private int id_reporter;
    private int id_reported;
    private String reason;
    private Date date;
}
