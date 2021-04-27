package com.shoppingzone.repository;

import com.shoppingzone.pojo.Ewallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EwalletRepository extends JpaRepository<Ewallet , Integer> {
}
