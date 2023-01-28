package com.defecttracking.controller;

import com.defecttracking.model.ReleaseRequest;
import com.defecttracking.model.ReleaseVO;
import com.defecttracking.service.ReleaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/release")
@Slf4j
public class ReleaseController {
    @Autowired
    ReleaseService releaseService;

    @GetMapping
    public ResponseEntity<List<ReleaseVO>> getRelease(){
        log.info("Inside the ReleaseController.getRelease");
        List<ReleaseVO> releaseVOS = null;
        try{
            releaseVOS = releaseService.findAll();
            log.info("Release details are not found");
            if(CollectionUtils.isEmpty(releaseVOS)){
                log.info("Release details not found");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }}
        catch (Exception exception){
            log.error("Exception while calling getRelease", exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<List<ReleaseVO>>(releaseVOS, HttpStatus.OK);
        }

    @GetMapping("/{id}")
    public ResponseEntity<ReleaseVO> getReleaseById(@PathVariable("id") Integer id){
        log.info("Input to ReleaseController.getReleaseByID, id:{}, id");
        ReleaseVO releaseVO = null;
        try{
            releaseVO = releaseService.ReleasefindById(id);
            log.info("Release data for the releaseId id:{}, details:{}",id,releaseVO);
            if(releaseVO==null){
                log.info("Release Id does not have data");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception exception){
            log.error("Exception while calling getReleaseByID", exception);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ReleaseVO>(releaseVO,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReleaseVO> save(@RequestBody ReleaseRequest releaseRequest){
        log.info("Inside ReleaseController.save releaseRequest:{}", releaseRequest);
        if(releaseRequest == null){
            log.info("Please check releaseRequest it's invalid");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ReleaseVO releaseVO = null;
        try{
            releaseVO = releaseService.save(releaseRequest);
            if(releaseVO==null){
                log.info("Release details can not saved");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception exception){
            log.info("Exception while save method");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<ReleaseVO>(releaseVO,HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<ReleaseVO> updateRelease(@RequestBody ReleaseRequest releaseRequest){
        log.info("Inside ReleaseController.updateRelease and releaseVO;{}", releaseRequest);
        if(releaseRequest == null){
            log.info("Invalid Release update request");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        ReleaseVO releaseVO = null;
        try {
            releaseVO = releaseService.save(releaseRequest);
            if(releaseVO == null){
                log.info("Release details are not saved");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception ex){
            log.error("Exception while saving application");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ReleaseVO>(releaseVO, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteRelease(@RequestParam("id") int id){
        log.info("Inside the ReleaseController.deleteRelease, id:{}", id);
        String response = null;
        try{
            response = releaseService.deleteRelease(id);
        }catch (Exception ex){
            log.error("Exception while delete Release Details");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

}
