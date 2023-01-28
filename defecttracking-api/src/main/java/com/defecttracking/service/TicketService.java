package com.defecttracking.service;

import com.defecttracking.exception.TicketNotFoundException;
import com.defecttracking.model.TicketRequest;
import com.defecttracking.model.TicketVO;

import java.util.List;

public interface TicketService {
    List<TicketVO> findAll();
    TicketVO TicketFindById(int id) throws TicketNotFoundException;

    TicketVO TicketFindByStatus(String status) throws TicketNotFoundException,InterruptedException;

    TicketVO save(TicketRequest ticketRequest) throws TicketNotFoundException;

    String deleteTicket(int id) throws TicketNotFoundException;
}
