package com.defecttracking.controller;

import com.defecttracking.model.ApplicationRequest;
import com.defecttracking.model.ApplicationVO;
import com.defecttracking.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/applications")
@Slf4j
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @GetMapping
    public ResponseEntity<List<ApplicationVO>> getApplication(){
        log.info("Inside the ApplicationController.getApplications");
        List<ApplicationVO> applicationVOS = null;
        try{
            applicationVOS = applicationService.findAll();
            log.info("Application details are not found");
            if (CollectionUtils.isEmpty(applicationVOS)){
                log.info("Application details are not found");

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }}
        catch (Exception exception){
            log.error("Exception while calling getApplications", exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<List<ApplicationVO>>(applicationVOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationVO> getApplicationById(@PathVariable("id") long id){
        log.info("Inside the ApplicationController.getApplicationById id:{}",id);
             ApplicationVO applicationVO = null;
        try{
           applicationVO = applicationService.findById(id);
            log.info("Application detail found for id:{}", id);
            if(applicationVO == null){
                log.info("No Application Data Found for id:{}, details:{}",id, applicationVO);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception exception){
            log.error("Exception while calling getApplicationsById", exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ApplicationVO>(applicationVO,HttpStatus.OK);

    }

    @GetMapping("/name")
    public ResponseEntity<ApplicationVO> getApplicationByName(@RequestParam("name") String name){
        log.info("Inside the ApplicationController.getApplicationById");
        ApplicationVO applicationVO = null;
        try{
            applicationVO = applicationService.findByName(name);
            log.info("Application detail found for details:{}", applicationVO);
            if(applicationVO == null){
                log.info("No Application Data Found for name");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception exception){
            log.error("Exception while calling getApplicationByName", exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<ApplicationVO>(applicationVO,HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<ApplicationVO> save(@RequestBody ApplicationRequest applicationRequest){
        log.info("Inside ApplicationController.save applicationVO;{}", applicationRequest);
        if(applicationRequest == null){
            log.info("Please check applicationRequest it's invalid");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ApplicationVO applicationVO = null;
        try{
            applicationVO = applicationService.save(applicationRequest);
            if(applicationVO==null){
                log.info("Application details can not saved");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception exception){
            log.info("Exception while save method");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<ApplicationVO>(applicationVO,HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<ApplicationVO> updateApplication(@RequestBody ApplicationRequest applicationRequest){
        log.info("Inside ApplicationController.updateApplication and applicationVO;{}", applicationRequest);
        if(applicationRequest == null){
            log.info("Invalid Application request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ApplicationVO applicationVO = null;
        try {
            applicationVO = applicationService.save(applicationRequest);
            if(applicationVO == null){
                log.info("Application details are not saved");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex){
            log.error("Exception while saving application");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ApplicationVO>(applicationVO, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteApplication(@RequestParam("id") long id){
        log.info("Inside the ApplicationController.deleteApplication, id:{}", id);
        String response = null;
        try{
            response = applicationService.delete(id);
        }catch (Exception ex){
            log.error("Exception while delete application");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

}
