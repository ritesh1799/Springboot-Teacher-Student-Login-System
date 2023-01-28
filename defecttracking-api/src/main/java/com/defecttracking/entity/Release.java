package com.defecttracking.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "RELEASE_TBL")
public class Release
{
@Id
@Column(name = "RELEASE_ID")
@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "releaseIdGenerator")
@SequenceGenerator(name = "releaseIdGenerator",sequenceName = "release_id_sequence",allocationSize = 1)
private int releaseId;
private String description;

@Temporal(TemporalType.DATE)
private Date release_date;

//    @ManyToOne
//    @JoinColumn(name = "application_id")
//    private Application application;


}
