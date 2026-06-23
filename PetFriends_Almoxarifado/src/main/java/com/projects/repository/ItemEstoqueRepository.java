package com.projects.repository;

import com.projects.domain.ItemEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemEstoqueRepository extends JpaRepository<ItemEstoque, Long>{
    Optional<ItemEstoque> findByProdutoid(Long produtoid);

}
