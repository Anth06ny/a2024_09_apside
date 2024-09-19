package org.example.a2024_09_apside.model.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Génère à la compilation
//-get/set
//-equals et toString
@Data
@AllArgsConstructor //-constructeurs plein
@NoArgsConstructor  //-constructeurs vide
public class StudentBean {

    private String name;
    private int note;


}
