package com.chess.api.respository;

import com.chess.api.model.AnalysisStatus;
import com.chess.api.model.Game;
import com.chess.api.model.MoveAnalysis;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GameRepository extends JpaRepository<Game, Long> {
  @Modifying
  @Transactional
  @Query("UPDATE Game g SET g.analysisStatus = :status WHERE g.id = :id")
  void updateStatus(Long id, AnalysisStatus status);

  @Modifying
  @Transactional
  @Query("UPDATE Game g SET g.analysis = :analysis WHERE g.id = :id")
  void updateAnalysis(Long id, List<MoveAnalysis> analysis);
}
