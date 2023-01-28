package com.defecttracking.service;

import com.defecttracking.entity.Application;
import com.defecttracking.exception.ApplicationNotFoundException;
import com.defecttracking.model.ApplicationRequest;
import com.defecttracking.model.ApplicationVO;
import com.defecttracking.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ApplicationServiceImpl implements ApplicationService{

    @Autowired
    ApplicationRepository applicationRepository;

    @Override
    public List<ApplicationVO> findAll() {
        log.info("Inside ApplicationServiceImpl.findAll");
        List<Application> applications = applicationRepository.findAll();
        log.info("Find all application response: {}",applications);
        List<ApplicationVO> applicationVOS = applications.parallelStream().map(application -> {
            ApplicationVO  applicationVO = new ApplicationVO();
            applicationVO.setApplicationId(application.getApplicationId());
            applicationVO.setDescription(application.getDescription());
            applicationVO.setApplicationName(application.getApplicationName());
            applicationVO.setOwner(application.getOwner());
            return applicationVO;
        }).collect(Collectors.toList());
        return applicationVOS;
    }

    @Override
    public ApplicationVO findById(long id) throws ApplicationNotFoundException {
        log.info("Inside ApplicationServiceImpl.findById id:{}" , id);
        if(id<0){
            log.info("Invalid Application id:{}", id);
            throw new ApplicationNotFoundException("Application doesn't exists for id");
        }
        Optional<Application> application = applicationRepository.findById(id);
        log.info("Find application response: {}",application);

        if(!application.isPresent()) {
            log.info("Application is not present for id:{}", id);
            throw new ApplicationNotFoundException("Application does not exists");
        }
            ApplicationVO applicationVO = new ApplicationVO();
            applicationVO.setApplicationId(application.get().getApplicationId());
            applicationVO.setApplicationName(application.get().getApplicationName());
            applicationVO.setDescription(application.get().getDescription());
            applicationVO.setOwner(application.get().getOwner());

        return applicationVO;
    }

    @Override
    public ApplicationVO findByName(String name) throws ApplicationNotFoundException, InterruptedException {
        log.info("Inside ApplicationServiceImpl.findByName Name:{}" ,name);
        if(name==null){
            log.info("Name can't be null");
            throw new ApplicationNotFoundException("Application doesn't exists for name");
        }
        Optional<Application> application = applicationRepository.findApplicationByName(name);
        log.info("Find application details: {}",application);

        if(!application.isPresent()) {
            log.info("Application is not present for name:{}", name);
            throw new ApplicationNotFoundException("Application does not exists");
        }
        ApplicationVO applicationVO = new ApplicationVO();
        applicationVO.setApplicationId(application.get().getApplicationId());
        applicationVO.setApplicationName(application.get().getApplicationName());
        applicationVO.setDescription(application.get().getDescription());
        applicationVO.setOwner(application.get().getOwner());

        return applicationVO;
    }

    @Override
    public ApplicationVO save(ApplicationRequest applicationRequest) throws ApplicationNotFoundException {
        log.info("Inside ApplicationServiceImpl.save applicationRequest:{}" , applicationRequest);
       if(applicationRequest==null){
           log.info("applicationRequest does not have details");
           throw new ApplicationNotFoundException("ApplicationRequest does not contain details");
       }
       Application application = new Application();
       if(applicationRequest.getApplicationId()>0){
           application.setApplicationId(applicationRequest.getApplicationId());
       }
       if(applicationRequest.getApplicationName()!=null)
       application.setApplicationName(applicationRequest.getApplicationName());

       if(applicationRequest.getDescription()!=null)
       application.setDescription(applicationRequest.getDescription());

       if(applicationRequest.getOwner()!=null)
       application.setOwner(applicationRequest.getOwner());

       Application applicationDetails = applicationRepository.save(application);
       ApplicationVO applicationVO = null;
       if(applicationDetails!=null){
           log.info("applicationDetails are applicationDetails:{}",applicationDetails);
           applicationVO = new ApplicationVO();
           applicationVO.setApplicationId(applicationDetails.getApplicationId());
           applicationVO.setApplicationName(applicationDetails.getApplicationName());
           applicationVO.setDescription(applicationDetails.getDescription());
           applicationVO.setOwner(applicationDetails.getOwner());
       }
        return applicationVO;
    }

    @Override
    public String  delete(long id) throws ApplicationNotFoundException {
       log.info("Inside the ApplicationServiceImpl.delete id:{}",id);
       if(id<0){
           log.info("application id is not valid");
           throw new ApplicationNotFoundException("Application Id is not correct");
               }
       try {
           applicationRepository.deleteById(id);
       }
       catch(Exception e)
       {
           log.info("Exception,Id is not deleted");
           throw new ApplicationNotFoundException("Exception while deleting id");
       }
       return "Application details has been deleted for given id";
    }
}
