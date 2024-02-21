package com.oEduardoBorges.dto.request.tela;

import com.oEduardoBorges.dto.response.tela.TelaResponse;
import com.oEduardoBorges.model.Tela;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelaRequest {

    private String name;
    private String description;

    public TelaRequest(Tela tela) {
        name = tela.getName();
        description = tela.getDescription();
    }

    public TelaRequest(TelaResponse telaResponse) {
        name = telaResponse.getName();
        description = telaResponse.getDescription();
    }
}
