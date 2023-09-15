package org.plan.halcon.security.service.securityservice.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "exchange_rate")
@Getter
@Setter
public class ExchangeRate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String currencyFrom;
    private String currencyTo;
    private BigDecimal rate;

}
