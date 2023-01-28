package com.defecttracking.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="TICKET_TBL")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ticketIdGenerator")
    @SequenceGenerator(name = "ticketIdGenerator",sequenceName = "ticket_id_sequence",allocationSize = 1)
    @Column(name = "ticket_id")
    private int ticketId;
    private String description;

    @ManyToOne(cascade= CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "application_id")
    private Application application;

    @ManyToOne(cascade= CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "release_id")
    private Release release;

    private String status;
    private String title;




}
