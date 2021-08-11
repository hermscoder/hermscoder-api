package com.herms.hermscoder.repository;

import com.herms.hermscoder.model.entity.Post;
import com.herms.hermscoder.model.entity.Profile;
import com.herms.hermscoder.model.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(value = "SELECT (p) FROM Project p WHERE p.profile.id = :profileId ORDER BY p.id")
    List<Project> findByProfileId(Long profileId);

}
