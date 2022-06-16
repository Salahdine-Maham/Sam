package org.innov.sam.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Project {

@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private String name ;
    private String description ;
    private String miscellaneous ;
    private String priority ;
    private String status ;

    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;
}
