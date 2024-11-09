package com.seek.shopping.infrastructure.persistence.repository;

import com.seek.shopping.infrastructure.persistence.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {

}
