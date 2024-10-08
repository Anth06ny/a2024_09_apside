package org.example.a2024_09_apside.model.beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Génère à la compilation
//-get/set
//-equals et toString
@Data
@AllArgsConstructor //-constructeurs plein
@NoArgsConstructor  //-constructeurs vide
@Entity
@Table(name = "message")
public class MessageBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //c'est l'id est il est auto incrément
    private String pseudo, message;
}
