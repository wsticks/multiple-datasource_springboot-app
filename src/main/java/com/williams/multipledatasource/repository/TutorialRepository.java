package com.williams.multipledatasource.repository;

import com.williams.multipledatasource.model.entity.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {


}
