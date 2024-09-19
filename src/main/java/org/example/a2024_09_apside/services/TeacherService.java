package org.example.a2024_09_apside.services;

import jakarta.transaction.Transactional;
import org.example.a2024_09_apside.model.beans.TeacherBean;
import org.example.a2024_09_apside.model.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;



    public TeacherBean createTeacher(String name, int code, String sessionId) throws Exception {
        if(name == null || name.isBlank()){
            throw new Exception("Name missing");
        }
        else if(code < 1 || code > 10){
            throw new Exception("Code incorrecte");
        }
        TeacherBean teacher = new TeacherBean(0, name, code, sessionId);
        teacherRepository.save(teacher);
        return teacher;
    }

    public List<TeacherBean> getAll() {
        return teacherRepository.findAll();
    }
}
