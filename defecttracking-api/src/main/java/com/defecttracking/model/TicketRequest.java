package com.defecttracking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@Setter
public class TicketRequest {
    private int ticketId;
    private String description;

    private ApplicationVO applicationVO;

    private ReleaseVO releaseVO;

    private String status;
    private String title;
}
