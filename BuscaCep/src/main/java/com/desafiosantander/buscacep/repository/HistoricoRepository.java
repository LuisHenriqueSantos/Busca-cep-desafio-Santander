package com.desafiosantander.buscacep.repository;

import com.desafiosantander.buscacep.entities.HistoricoLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoRepository extends JpaRepository<HistoricoLog, Long> {}

