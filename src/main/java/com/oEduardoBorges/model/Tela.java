package com.oEduardoBorges.model;

import com.oEduardoBorges.dto.request.tela.TelaRequest;
import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_tela")
public class Tela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    public Tela(TelaRequest telaRequest) {
        this.name = telaRequest.getName();
        this.description = telaRequest.getDescription();
    }
}
