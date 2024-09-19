package org.example.a2024_09_apside.model.repository;

import org.example.a2024_09_apside.model.beans.TeacherBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<TeacherBean, Long> {
}
