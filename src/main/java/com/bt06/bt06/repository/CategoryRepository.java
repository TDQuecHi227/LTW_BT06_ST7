package com.bt06.bt06.repository;

import com.bt06.bt06.entity.Category;
import com.bt06.bt06.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findAllBy();
}
