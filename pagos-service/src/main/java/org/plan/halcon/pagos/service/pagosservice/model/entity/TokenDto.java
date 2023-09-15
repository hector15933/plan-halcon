package org.plan.halcon.pagos.service.pagosservice.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDto {

    @JsonProperty("access_token")
    private String accessToken;
    private String tokenType;
    private String refreshToken;


}
