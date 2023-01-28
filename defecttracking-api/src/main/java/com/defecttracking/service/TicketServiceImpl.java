package com.defecttracking.service;

import com.defecttracking.entity.Application;
import com.defecttracking.entity.Release;
import com.defecttracking.entity.Ticket;
import com.defecttracking.exception.TicketNotFoundException;
import com.defecttracking.model.ApplicationVO;
import com.defecttracking.model.ReleaseVO;
import com.defecttracking.model.TicketRequest;
import com.defecttracking.model.TicketVO;
import com.defecttracking.repository.TicketRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketServiceImpl implements TicketService{

    @Autowired
    TicketRepository ticketRepository;

    @Override
    public List<TicketVO> findAll() {
        log.info("Inside the ticket release findAll method");
        List<Ticket> tickets = ticketRepository.findAll();
        log.info("find all ticket response: {}",tickets);
        List<TicketVO> ticketVOS = tickets.parallelStream().map(ticket -> {
            TicketVO ticketVO = new TicketVO();
            ticketVO.setTicketId(ticket.getTicketId());
            ticketVO.setDescription(ticket.getDescription());
            ticketVO.setStatus(ticket.getStatus());
            ticketVO.setTitle(ticket.getTitle());

            ApplicationVO applicationVO = new ApplicationVO();
            applicationVO.setApplicationId(ticket.getApplication().getApplicationId());
            applicationVO.setApplicationName(ticket.getApplication().getApplicationName());
            applicationVO.setDescription(ticket.getApplication().getDescription());
            applicationVO.setOwner(ticket.getApplication().getOwner());
            ticketVO.setApplicationVO(applicationVO);

            ReleaseVO releaseVO = new ReleaseVO();
            releaseVO.setRelease_date(ticket.getRelease().getRelease_date());
            releaseVO.setReleaseId(ticket.getRelease().getReleaseId());
            releaseVO.setDescription(ticket.getRelease().getDescription());
            ticketVO.setReleaseVO(releaseVO);
            return ticketVO;
        }).collect(Collectors.toList());
        return ticketVOS;
    }

    @Override
    public TicketVO TicketFindById(int id) throws TicketNotFoundException {
        log.info("Inside the TicketServiceImpl.TicketFindById id:{}",id);
        if(id<0){
            log.info("invalid ticket id, id{}", id);
            throw new TicketNotFoundException("Id is not found");
        }
        Optional<Ticket> ticket = ticketRepository.findById(id);
        log.info("find ticket response:{}",ticket);

        if(!ticket.isPresent()){
            log.info("ticket is not present for id:{}",id);
            throw new TicketNotFoundException("Ticket  details are not found");}

            TicketVO ticketVO = new TicketVO();
            ticketVO.setTicketId(ticket.get().getTicketId());
            ticketVO.setDescription(ticket.get().getDescription());
            ticketVO.setStatus(ticket.get().getDescription());
            ticketVO.setTitle(ticket.get().getTitle());

            ApplicationVO applicationVO = new ApplicationVO();
            applicationVO.setApplicationId(ticket.get().getApplication().getApplicationId());
            applicationVO.setApplicationName(ticket.get().getApplication().getApplicationName());
            applicationVO.setDescription(ticket.get().getApplication().getDescription());
            applicationVO.setOwner(ticket.get().getApplication().getOwner());
            ticketVO.setApplicationVO(applicationVO);

            ReleaseVO releaseVO = new ReleaseVO();
            releaseVO.setReleaseId(ticket.get().getRelease().getReleaseId());
            releaseVO.setDescription(ticket.get().getDescription());
            releaseVO.setRelease_date(ticket.get().getRelease().getRelease_date());
            ticketVO.setReleaseVO(releaseVO);

        return ticketVO;
    }

    @Override
    public TicketVO TicketFindByStatus(String status) throws TicketNotFoundException, InterruptedException {
        log.info("Inside the TicketServiceImpl.TicketFindByStatus status:{}",status);
        if(status==null){
            log.info("Status can't be null");
            throw new TicketNotFoundException("Status code can't find");
        }
        Optional<Ticket> ticket = ticketRepository.findByStatus(status);
        log.info("find ticket details:{}",ticket);

        if(!ticket.isPresent()){
            log.info("ticket is not present for status:{}",status);
            throw new TicketNotFoundException("Ticket  details are not found");}

        TicketVO ticketVO = new TicketVO();
        ticketVO.setTicketId(ticket.get().getTicketId());
        ticketVO.setDescription(ticket.get().getDescription());
        ticketVO.setStatus(ticket.get().getDescription());
        ticketVO.setTitle(ticket.get().getTitle());

        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(ticket.get().getApplication().getApplicationId());
        applicationVO.setApplicationName(ticket.get().getApplication().getApplicationName());
        applicationVO.setDescription(ticket.get().getApplication().getDescription());
        applicationVO.setOwner(ticket.get().getApplication().getOwner());
        ticketVO.setApplicationVO(applicationVO);

        ReleaseVO releaseVO = new ReleaseVO();
        releaseVO.setReleaseId(ticket.get().getRelease().getReleaseId());
        releaseVO.setDescription(ticket.get().getDescription());
        releaseVO.setRelease_date(ticket.get().getRelease().getRelease_date());
        ticketVO.setReleaseVO(releaseVO);

        return ticketVO;
    }

    @Override
    public TicketVO save(TicketRequest ticketRequest) throws TicketNotFoundException {
        log.info("Inside the TicketServiceImpl.save ticketRequest:{}",ticketRequest);
        if(ticketRequest==null){
            log.info("No input details found");
            throw new TicketNotFoundException("ticketRequest is not valid");
        }
        Ticket ticket = new Ticket();
        if(ticketRequest.getTicketId()>0)
            ticket.setTicketId(ticketRequest.getTicketId());
        if(ticketRequest.getDescription()!=null)
            ticket.setDescription(ticketRequest.getDescription());
        if(ticketRequest.getTitle()!=null)
            ticket.setTitle(ticketRequest.getTitle());
        if(ticketRequest.getStatus()!=null)
            ticket.setStatus(ticketRequest.getStatus());


        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(ticketRequest.getApplicationVO().getApplicationId());
        applicationVO.setApplicationName(ticketRequest.getApplicationVO().getApplicationName());
        applicationVO.setDescription(ticketRequest.getApplicationVO().getDescription());
        applicationVO.setOwner(ticketRequest.getApplicationVO().getOwner());
        Application application = new Application();
        application.setApplicationId(applicationVO.getApplicationId());
        application.setApplicationName(applicationVO.getApplicationName());
        application.setDescription(applicationVO.getDescription());
        application.setOwner(applicationVO.getOwner());
        if(ticketRequest.getApplicationVO()!=null)
            ticket.setApplication(application);


        ReleaseVO releaseVO =new ReleaseVO();
        releaseVO.setReleaseId(ticketRequest.getReleaseVO().getReleaseId());
        releaseVO.setDescription(ticketRequest.getReleaseVO().getDescription());
        releaseVO.setRelease_date(ticketRequest.getReleaseVO().getRelease_date());

        Release release = new Release();
        release.setReleaseId(releaseVO.getReleaseId());
        release.setDescription(releaseVO.getDescription());
        release.setRelease_date(releaseVO.getRelease_date());
        if(ticketRequest.getReleaseVO()!=null)
            ticket.setRelease(release);

        Ticket ticketResponse = ticketRepository.save(ticket);
        TicketVO ticketVO = null;
        if(ticketResponse!=null) {
            log.info("ticketResponse:{}", ticketResponse);
            ticketVO  = new TicketVO();
            ticketVO.setTicketId(ticketResponse.getTicketId());
            ticketVO.setStatus(ticketResponse.getStatus());
            ticketVO.setDescription(ticketResponse.getDescription());
            ticketVO.setTitle(ticketResponse.getTitle());
            if(applicationVO.getApplicationId()>0){
            ticketVO.setApplicationVO(applicationVO);}
            if(releaseVO.getReleaseId()>0){
            ticketVO.setReleaseVO(releaseVO);}
        }
        return ticketVO;
    }

    @Override
    public String deleteTicket(int id) throws TicketNotFoundException {
        log.info("Inside the TicketServiceImpl.deleteTicket");
        if(id<0){
            log.info("Ticket id is not valid");
            throw new TicketNotFoundException("Ticket Id is not correct");
        }
        try {
            ticketRepository.deleteById(id);
        }
        catch(Exception e)
        {
            log.info("Exception,Id is not deleted");
            throw new TicketNotFoundException("Exception while deleting id");
        }
        return "Ticket has been deleted for given id";
    }
}
