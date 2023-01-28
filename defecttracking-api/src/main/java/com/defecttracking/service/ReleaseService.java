package com.defecttracking.service;

import com.defecttracking.exception.ReleaseNotFoundException;
import com.defecttracking.model.ReleaseRequest;
import com.defecttracking.model.ReleaseVO;

import java.util.List;

public interface ReleaseService {
    List<ReleaseVO> findAll();

    ReleaseVO ReleasefindById(Integer id) throws ReleaseNotFoundException;

    ReleaseVO save(ReleaseRequest releaseRequest) throws ReleaseNotFoundException;

    String deleteRelease(int id) throws ReleaseNotFoundException;
}
