package com.defecttracking.service;

import com.defecttracking.exception.ApplicationNotFoundException;
import com.defecttracking.model.ApplicationRequest;
import com.defecttracking.model.ApplicationVO;
import java.util.List;

public interface ApplicationService {
    List<ApplicationVO> findAll();

    ApplicationVO findById(long id) throws ApplicationNotFoundException;
    ApplicationVO findByName(String name) throws ApplicationNotFoundException, InterruptedException;

    ApplicationVO save(ApplicationRequest applicationRequest) throws ApplicationNotFoundException;

    String delete(long id) throws ApplicationNotFoundException;
}
