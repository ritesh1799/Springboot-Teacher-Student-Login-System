package com.defecttracking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Getter
@Setter
@ToString
public class TicketVO {
    private int ticketId;
    private String description;

    private ApplicationVO applicationVO;

    private ReleaseVO releaseVO;

    private String status;
    private String title;
}
