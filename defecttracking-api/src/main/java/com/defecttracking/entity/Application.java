package com.defecttracking.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name="APPLICATION_TBL")
public class Application implements Serializable {
    public static final long serialVersionUID = 342343l;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "application_gen")
    @SequenceGenerator(name = "application_gen", sequenceName = "application_id_sequence", allocationSize = 1)
    @Column(name = "application_id")
    private long applicationId;

    private String description;

    @Column(name = "application_name")
    private String applicationName;

    private String owner;




}
