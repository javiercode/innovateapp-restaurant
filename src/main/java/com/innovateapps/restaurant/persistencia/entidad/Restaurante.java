package com.innovateapps.restaurant.persistencia.entidad;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "res_restaurantes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "TELEFONO")
    private Integer telefono;

    @Column(name = "ESTADO")
    private String estado;

    @Column(name = "LOGO")
    private String logo;

    @Column(name = "HORARIO")
    private String horario;

    @Column(name = "LATITUD")
    private String latitud;

    @Column(name = "LONGITUD")
    private String longitud;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "ID_TIPO_COMIDA")
    private Integer idTipoComida;

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
    @JoinColumn(name = "ID_TIPO_COMIDA", insertable = false, updatable = false)
    private TipoComida tipoComida;

    @OneToMany(mappedBy = "restaurante")
    private List<RestauranteAmbiente> restauranteAmbienteList;
}