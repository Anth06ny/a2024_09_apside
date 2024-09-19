package org.example.a2024_09_apside.model.beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teacher") //Nom de la table que représente ce bean
public class TeacherBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //c'est l'id est il est auto incrément
    private String name;
    private Integer code;
    private String sessionId; //Ira chercher session_id dans la table
}
