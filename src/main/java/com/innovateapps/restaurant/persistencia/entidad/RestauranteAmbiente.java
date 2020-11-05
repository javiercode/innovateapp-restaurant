package com.innovateapps.restaurant.persistencia.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "res_restaurante_ambientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteAmbiente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ID_RESTAURANTE")
    private Integer idRestaurante;

    @Column(name = "ID_TIPO_AMBIENTE")
    private Integer idTipoAmbiente;

    @Column(name = "FECHA_ALTA")
    private LocalDateTime fechaAlta;

    @Column(name = "ID_USUARIO_ALTA")
    private Integer idUsuarioAlta;

    @Column(name = "FECHA_BAJA")
    private LocalDateTime fechaBaja;

    @Column(name = "ID_USUARIO_BAJA")
    private Integer idUsuarioBaja;

    @Column(name = "FECHA_DESDE")
    private LocalDateTime fechaDesde;

    @Column(name = "ID_USUARIO_DESDE")
    private Integer idUsuarioDesde;

    @Column(name = "FECHA_HASTA")
    private LocalDateTime fechaHasta;

    @Column(name = "ID_USUARIO_HASTA")
    private Integer idUsuarioHasta;


    @ManyToOne
    @JoinColumn(name = "ID_TIPO_AMBIENTE", insertable = false, updatable = false)
    private TipoAmbiente tipoAmbiente;

    @ManyToOne
    @JoinColumn(name = "ID_RESTAURANTE", insertable = false, updatable = false)
    private Restaurante restaurante;
}