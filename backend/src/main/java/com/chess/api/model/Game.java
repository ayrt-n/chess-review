package com.chess.api.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Type;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;

@Entity
@Table(name = "games")
public class Game extends BaseModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(columnDefinition = "TEXT", nullable = false)
  private String rawPgn;
  
  @Column(nullable = false)
  private String pgnHash;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private AnalysisStatus analysisStatus = AnalysisStatus.PENDING;

  @Type(JsonBinaryType.class)
  @Column(columnDefinition = "jsonb")
  private List<MoveAnalysis> analysis = new ArrayList<>();
 
  private String white;
  private String black;
  private int whiteElo;
  private int blackElo;
  private String analysisVersion;
  private String engineVersion;

  public Game() {}
}
