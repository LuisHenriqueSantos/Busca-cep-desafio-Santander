package com.desafiosantander.buscacep.entities;


import jakarta.persistence.*;
import lombok.*;

import javax.annotation.processing.Generated;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "tb_historico_logs")
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    @Column(name = "cep", length = 10) 
    private String cep;
    
    @Column(name = "data_hora")
    private LocalDateTime dataHora;
    
    @Column(length = 120)
    private String rua;
    
    @Column(length = 120)
    private String cidade ;
    
    @Column(length = 2)
    private String uf;
    
}
