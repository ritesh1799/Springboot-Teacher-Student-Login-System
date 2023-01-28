package com.defecttracking.service;

import com.defecttracking.entity.Release;
import com.defecttracking.exception.ReleaseNotFoundException;
import com.defecttracking.model.ReleaseRequest;
import com.defecttracking.model.ReleaseVO;
import com.defecttracking.repository.ReleaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReleaseServiceImpl implements ReleaseService{

    @Autowired
    ReleaseRepository releaseRepository;

    @Override
    public List<ReleaseVO> findAll() {
        log.info("Inside the release release.findAll");
        List<Release> releases =  releaseRepository.findAll();
        log.info("Find all release response: {}",releases);
        List<ReleaseVO> releaseVOS = releases.parallelStream().map(release -> {
                    ReleaseVO releaseVO = new ReleaseVO();
                    releaseVO.setReleaseId(release.getReleaseId());
                    releaseVO.setDescription(release.getDescription());
                    releaseVO.setRelease_date(release.getRelease_date());
                   // releaseVO.setApplicationVO(releaseVO.getApplicationVO());
                    return releaseVO;
                }).collect(Collectors.toList());
        return releaseVOS;
    }

    @Override
    public ReleaseVO ReleasefindById(Integer id) throws ReleaseNotFoundException {
        log.info("Inside the ReleaseServiceImpl.findById, id{}:",id);
        if(id<0){
            log.info("invalid release id, id{}", id);
            throw new ReleaseNotFoundException("Id is not found");
        }
        Optional<Release> release = releaseRepository.findById(id);
             if(!release.isPresent()) {
            log.info("No record found for the releaseId, id{}", id);
            throw new ReleaseNotFoundException("No record found for the releaseId");
                                     }
            ReleaseVO releaseVO = new ReleaseVO();
            releaseVO.setReleaseId(release.get().getReleaseId());
            releaseVO.setDescription(release.get().getDescription());
            releaseVO.setRelease_date(release.get().getRelease_date());

         return releaseVO;

    }

    @Override
    public ReleaseVO save(ReleaseRequest releaseRequest) throws ReleaseNotFoundException {
            log.info("Inside the ReleaseServiceImpl.save details:{}",releaseRequest);
            if(releaseRequest==null){
                log.info("Data is not found to save");
                throw new ReleaseNotFoundException("Data is not present");
            }
            Release release = new Release();
            if(releaseRequest.getReleaseId()>0) {
                release.setReleaseId(releaseRequest.getReleaseId());
            }
                release.setRelease_date(releaseRequest.getRelease_date());
                release.setDescription(releaseRequest.getDescription());


            Release releaseResponse = releaseRepository.save(release);
            ReleaseVO releaseVO =null;
            if(releaseResponse!=null){
                log.info("releaseResponse are releaseResponse:{}",releaseResponse);
                releaseVO = new ReleaseVO();
                releaseVO.setReleaseId(releaseResponse.getReleaseId());
                releaseVO.setDescription(releaseResponse.getDescription());
                releaseVO.setRelease_date(releaseResponse.getRelease_date());
            }
            return releaseVO;
    }

    @Override
    public String deleteRelease(int id) throws ReleaseNotFoundException {
        log.info("Inside the ReleaseServiceImpl.deleteRelease");
        if(id<0){
            log.info("Release id is not valid");
            throw new ReleaseNotFoundException("Release Id is not correct");
        }
        try {
            releaseRepository.deleteById(id);
        }
        catch(Exception e)
        {
            log.info("Exception,Id is not deleted");
            throw new ReleaseNotFoundException("Exception while deleting id");
        }
        return "Release details has been deleted for given id";
    }
}
