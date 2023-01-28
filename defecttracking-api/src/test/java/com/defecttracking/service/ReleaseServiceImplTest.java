package com.defecttracking.service;

import com.defecttracking.entity.Release;
import com.defecttracking.model.ReleaseVO;
import com.defecttracking.repository.ReleaseRepository;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReleaseServiceImplTest {

    @Mock
    ReleaseRepository releaseRepository;

    @InjectMocks
    ReleaseServiceImpl releaseService;

    @Test
    public void testFindAll(){
        List<Release> releases = new ArrayList<>();
        Release release = new Release();
        release.setReleaseId(1);
        release.setDescription("Release APp");
        releases.add(release);

        when(releaseRepository.findAll()).thenReturn(releases);
        List<ReleaseVO> releaseVOs = releaseService.findAll();
        assertEquals(1,releases.size());
    }


}
