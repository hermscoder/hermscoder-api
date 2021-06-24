package com.herms.hermscoder.repository;

import com.herms.hermscoder.model.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query(value = "SELECT (p) FROM Profile p WHERE p.active = 'Y'")
    public List<Profile> findAll();

    @Query(value = "SELECT (p) FROM Profile p WHERE p.user.id = :userId")
    Profile findByUser_Id(Long userId);
}
