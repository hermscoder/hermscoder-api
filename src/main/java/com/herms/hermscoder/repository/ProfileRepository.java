package com.herms.hermscoder.repository;

import com.herms.hermscoder.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query(value = "SELECT (p) FROM Profile p WHERE p.active = 'Y'")
    public List<Profile> findAll();

}
