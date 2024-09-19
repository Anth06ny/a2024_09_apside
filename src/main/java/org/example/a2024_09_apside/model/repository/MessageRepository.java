package org.example.a2024_09_apside.model.repository;

import org.example.a2024_09_apside.model.beans.MessageBean;
import org.example.a2024_09_apside.model.beans.TeacherBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageBean, Long> {
    List<MessageBean> findFirst10ByOrderByIdDesc();
}
