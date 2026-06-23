package com.projects.repository;

import com.projects.domain.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    Optional<Entrega> findByPedidoid(String pedidoid);
    boolean existsByPedidoid(String pedidoid);
}
