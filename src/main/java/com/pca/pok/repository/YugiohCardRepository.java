package com.pca.pok.repository;

import com.pca.pok.entity.YugiohCard;
import org.springframework.data.jpa.repository.JpaRepository;


public interface YugiohCardRepository extends JpaRepository<YugiohCard, Long> {
}
