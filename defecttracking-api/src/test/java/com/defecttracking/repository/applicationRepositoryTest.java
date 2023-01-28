package com.defecttracking.repository;

import com.defecttracking.entity.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;



import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class applicationRepositoryTest {
    @Autowired
    public ApplicationRepository applicationRepository;

    public void saveApplication(){
        Application application = new Application();
        application.setApplicationId(1l);
        application.setApplicationName("BugTracking");
        application.setDescription("Bug");
        application.setOwner("Ritesh");
        applicationRepository.save(application);
    }

    @Test
    public void testFindAll(){
        saveApplication();
        List<Application> applicationList = applicationRepository.findAll();
        assertEquals(1, applicationList.size());
    }

    @Test
    public void testFindByApplicationName(){
        saveApplication();
        Application application = applicationRepository.findApplicationByName("BugTracking").get();
        assertEquals("BugTracking", application.getApplicationName());
    }

    @Test
    public void testFindByID(){
        saveApplication();
        Application application = applicationRepository.findById(1L).get();
        assertEquals(1l,application.getApplicationId());
    }

    @Test
    public void testDeleteById(){
        saveApplication();
        applicationRepository.deleteById(1L);
        assertEquals(null,applicationRepository.findById(1L).get());
    }
}
