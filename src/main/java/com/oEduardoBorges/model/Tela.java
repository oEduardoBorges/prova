package com.oEduardoBorges.model;

import com.oEduardoBorges.dto.request.tela.TelaRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    public Tela(TelaRequest telaRequest) {
        this.name = telaRequest.getName();
        this.description = telaRequest.getDescription();
    }
}
