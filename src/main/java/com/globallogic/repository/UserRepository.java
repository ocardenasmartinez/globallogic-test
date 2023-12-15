package com.globallogic.repository;

import com.globallogic.entities.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserData, UUID> {

    UserData findByName(String name);
    UserData findByToken(String token);

}




