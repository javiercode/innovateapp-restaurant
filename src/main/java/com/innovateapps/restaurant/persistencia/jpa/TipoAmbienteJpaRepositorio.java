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
public interface TipoAmbienteJpaRepositorio extends JpaRepository<TipoAmbiente,Integer> {

    @Query("SELECT r FROM TipoAmbiente r ")
    Optional<List<TipoAmbiente>> recuparLista();

    public Page<TipoAmbiente> findAll(Pageable pageable);
}