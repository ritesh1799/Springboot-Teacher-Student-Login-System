package com.defecttracking.service;

import com.defecttracking.entity.Application;
import com.defecttracking.exception.ApplicationNotFoundException;
import com.defecttracking.model.ApplicationVO;
import com.defecttracking.repository.ApplicationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationServiceImplTest {
    @Mock
    ApplicationRepository applicationRepository;

    @InjectMocks
    ApplicationServiceImpl applicationService;

    @Test
    public void testFindAll(){
        List<Application> applications = new ArrayList<>();
        Application application = new Application();
        application.setApplicationId(1);
        application.setApplicationName("Spring Boot Testing");
        application.setOwner("Emexo");
        application.setDescription("Testing");
        applications.add(application);

        when(applicationRepository.findAll()).thenReturn(applications);
        List<ApplicationVO> applicationVOS= applicationService.findAll();
        assertEquals(1, applicationVOS.size());

    }

    @Test
    public void testFindById() throws ApplicationNotFoundException {
        Application application = new Application();
        application.setApplicationId(2);
        application.setApplicationName("FB");
        application.setOwner("MJ");
        application.setDescription("Social Media App");

        when(applicationRepository.findById(anyLong())).thenReturn(Optional.of(application));
        ApplicationVO applicationVO = applicationService.findById(2);
        assertEquals(2,applicationVO.getApplicationId());
    }

    @Test
    public void testFindByName() throws ApplicationNotFoundException, InterruptedException {
        Application application = new Application();
        application.setApplicationId(2);
        application.setApplicationName("FB");
        application.setOwner("MJ");
        application.setDescription("Social Media App");
        when(applicationRepository.findApplicationByName(anyString())).thenReturn(Optional.of(application));
        ApplicationVO applicationVO = applicationService.findByName("FB");
        assertSame("FB",applicationVO.getApplicationName());
    }

    @Test
    public void testDelete() throws ApplicationNotFoundException{
        List<Application> applications = new ArrayList<>();
        Application application = new Application();
        application.setApplicationId(1);
        application.setApplicationName("Spring Boot Testing");
        application.setOwner("Emexo");
        application.setDescription("Testing");
        applications.add(application);
        application.setApplicationId(2);
        application.setApplicationName("Spring Boot");
        application.setOwner("Emexo");
        application.setDescription("Test");
        applications.add(application);
        String str  = applicationService.delete(1);

        assertEquals(2,application.getApplicationId());
    }


}
