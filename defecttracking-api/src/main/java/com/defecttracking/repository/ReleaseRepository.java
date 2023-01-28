package com.defecttracking.repository;

import com.defecttracking.entity.Release;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReleaseRepository extends JpaRepository<Release,Integer> {
}
