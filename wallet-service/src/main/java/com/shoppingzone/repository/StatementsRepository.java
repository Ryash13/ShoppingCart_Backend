package com.shoppingzone.repository;

import com.shoppingzone.pojo.Statement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementsRepository extends JpaRepository<Statement , Integer> {
}
