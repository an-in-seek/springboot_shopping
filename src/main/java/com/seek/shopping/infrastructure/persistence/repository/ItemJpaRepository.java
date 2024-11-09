package com.seek.shopping.infrastructure.persistence.repository;

import com.seek.shopping.infrastructure.persistence.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemJpaRepository extends JpaRepository<ItemEntity, Long> {

}
