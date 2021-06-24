package com.herms.hermscoder.repository;

import com.herms.hermscoder.model.entity.Profile;
import com.herms.hermscoder.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
