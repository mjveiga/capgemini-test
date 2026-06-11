package com.capgemini.test.code.clients.infrastructure.adapter.out.persistence.jpa.repository;

import com.capgemini.test.code.clients.infrastructure.adapter.out.persistence.jpa.entity.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserManagementRepository extends JpaRepository <UserEntity, Integer>{
 Optional<UserEntity>  findByEmail(String email);
 @Query("SELECT u FROM UserEntity u WHERE u.id = :id AND u.room.id = 1")
  Optional<UserEntity> findByIdAndRoom(Integer id);
}
