package com.bt06.bt06.repository;

import com.bt06.bt06.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAllBy();
    User findById(long id);
    User findByUsername(String name);
    List<User>  findByUsernameContainingIgnoreCase(String name);
}
