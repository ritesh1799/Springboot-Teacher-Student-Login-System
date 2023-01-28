package com.defecttracking.repository;


import com.defecttracking.entity.Release;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class releaseRepositoryTest {

    @Autowired
    ReleaseRepository releaseRepository;

    public void saveRelease(){
        Release release = new Release();
        release.setReleaseId(1);
        release.setDescription("Release");

        releaseRepository.save(release);
    }

    @Test
    public void testFindAll(){
        saveRelease();
        List<Release> releases=releaseRepository.findAll();
        assertEquals(1,releases.size());
    }

    @Test
    public void testFindByID(){
        saveRelease();
        Release  release = releaseRepository.findById(1).get();
        assertEquals(1,release.getReleaseId());
    }

    @Test
    public void testDelete(){
        saveRelease();;
        releaseRepository.deleteById(1);
        assertEquals(null,releaseRepository.findById(1).get());

    }
}
