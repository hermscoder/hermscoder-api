package com.herms.hermscoder.repository;

import com.herms.hermscoder.model.entity.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    @Query(value = "SELECT (e) FROM Experience e WHERE e.profile.id = :profileId ORDER BY e.endDate desc")
    List<Experience> findByProfileId(Long profileId);

}
