package com.innovateapps.restaurant.persistencia.jpa;

import com.innovateapps.restaurant.persistencia.entidad.Restaurante;
import com.innovateapps.restaurant.persistencia.entidad.RestauranteAmbiente;
import com.innovateapps.restaurant.persistencia.entidad.TipoAmbiente;
import com.innovateapps.restaurant.persistencia.entidad.TipoComida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoComidaJpaRepositorio extends JpaRepository<TipoComida,Integer> {

    @Query("SELECT r FROM TipoComida r where r.idUsuarioBaja is null and r.fechaBaja is null")
    Optional<List<TipoComida>> recuparLista();

    @Query("SELECT r FROM TipoComida r where r.idUsuarioBaja is null and r.fechaBaja is null")
    public Page<TipoComida> recuparPaginado(Pageable pageable);

}