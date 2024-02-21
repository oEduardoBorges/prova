package com.oEduardoBorges.dto.response.tela;

import com.oEduardoBorges.model.Tela;
import lombok.Builder;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelaResponse {

    private Long id;
    private String name;
    private String description;

    public TelaResponse(Tela tela) {
        id = tela.getId();
        name = tela.getName();
        description = tela.getDescription();
    }
}
