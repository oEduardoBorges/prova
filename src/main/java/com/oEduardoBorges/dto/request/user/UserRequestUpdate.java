package com.oEduardoBorges.dto.request.user;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class userRequest {

    private String nome;
    private String email;
    private String username;
}
