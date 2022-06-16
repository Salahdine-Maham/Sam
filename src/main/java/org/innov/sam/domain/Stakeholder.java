package org.innov.sam.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stakeholder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String name;
    private String Description ;
    private String Miscellaneous ;
    private String priority;
    private String influence ;
    private String Attitude ;
    private String size ;
    private String usage ;

    @ManyToOne
    @JoinColumn(name = "project")
    private Project project;
}
