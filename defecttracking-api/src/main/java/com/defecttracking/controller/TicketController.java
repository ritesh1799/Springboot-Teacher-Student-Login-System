package com.defecttracking.controller;

import com.defecttracking.model.TicketRequest;
import com.defecttracking.model.TicketVO;
import com.defecttracking.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/v1/ticket")
@Slf4j
public class TicketController {
    @Autowired
    TicketService ticketService;


    @GetMapping
    public ResponseEntity<List<TicketVO>> getTicket() {
        log.info("Inside the ticketController getTicket Method");
        List<TicketVO> ticketVOS = null;
        try {
            ticketVOS = ticketService.findAll();
            log.info("ticket are  not found");
            if (CollectionUtils.isEmpty(ticketVOS)) {
                log.info("Ticket details not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            log.error("Exception while calling getTicket", exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<TicketVO>>(ticketVOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketVO> getTicketById(@PathVariable("id") int id) {
        log.info("Inside the Controller.getTicketById id:{}", id);
        TicketVO ticketVO = null;
        try {
            ticketVO = ticketService.TicketFindById(id);
            log.info("Ticket details are for id:{},details:{}", id, ticketVO);
            if (ticketVO == null) {
                log.info("Data is not present for particular ticket id:{}", id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            log.error("Inside Exception catch block", exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<TicketVO>(ticketVO, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<TicketVO> getTicketByStatus(@RequestParam("status") String status) {
        log.info("Inside getTicketByStatus status:{}", status);
        TicketVO ticketVO = null;
        try {
            ticketVO = ticketService.TicketFindByStatus(status);
            log.info("Ticket Details are for status:{},details{}", status, ticketVO);
            if (ticketVO == null) {
                log.info("Details not present for status:{}", status);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception exception) {
            log.error("Exception while calling getTicket", exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<TicketVO>(ticketVO, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<TicketVO> save(@RequestBody TicketRequest ticketRequest){
        log.info("Inside TicketController.save ticketVO;{}", ticketRequest);
        if(ticketRequest == null){
            log.info("Please check ticketRequest it's invalid");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        TicketVO ticketVO = null;
        try{
            ticketVO = ticketService.save(ticketRequest);
           if(ticketVO==null){
                log.info("Ticket details can not saved");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception exception){
            log.info("Exception while save method");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<TicketVO>(ticketVO,HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<TicketVO> update(@RequestBody TicketRequest ticketRequest){
        log.info("Inside theTicketController.update ticketVO:{}",ticketRequest);
        if(ticketRequest==null){
            log.info("Please check ticketRequest it's invalid");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        TicketVO ticketVO = null;
        try{
            ticketVO = ticketService.save(ticketRequest);
            if(ticketVO==null){
                log.info("Ticket details can not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception exception){
            log.info("Exception while updating method");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<TicketVO>(ticketVO,HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteTicket(@RequestParam("id") int id){
        log.info("Inside the TicketController.deleteTicket, id:{}", id);
        String response = null;
        try{
            response = ticketService.deleteTicket(id);
        }catch (Exception ex){
            log.error("Exception while deleting Ticket");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}

