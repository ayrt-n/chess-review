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
  private Long id;

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

  @Column(nullable = false)
  private int whiteElo;

  @Column(nullable = false)
  private int blackElo;
 
  private String white;
  private String black;
  private String analysisVersion;
  private String engineVersion;

  public Game() {}

  public Game(String rawPgn, String pgnHash, String white, int whiteElo,  String black, int blackElo) {
    this.rawPgn = rawPgn;
    this.pgnHash = pgnHash;
    this.white = white;
    this.whiteElo = whiteElo;
    this.black = black;
    this.blackElo = blackElo;
  }

  public Long getId() {
    return id;
  }

  public String getRawPgn() {
    return rawPgn;
  }

  public void setRawPgn(String rawPgn) {
    this.rawPgn = rawPgn;
  }

  public String getPgnHash() {
    return pgnHash;
  }

  public void setPgnHash(String pgnHash) {
    this.pgnHash = pgnHash;
  }

  public AnalysisStatus getAnalysisStatus() {
    return analysisStatus;
  }

  public void setAnalysisStatus(AnalysisStatus analysisStatus) {
    this.analysisStatus = analysisStatus;
  }

  public List<MoveAnalysis> getAnalysis() {
    return analysis;
  }

  public void setAnalysis(List<MoveAnalysis> analysis) {
    this.analysis = analysis;
  }

  public int getWhiteElo() {
    return whiteElo;
  }

  public void setWhiteElo(int whiteElo) {
    this.whiteElo = whiteElo;
  }

  public int getBlackElo() {
    return blackElo;
  }

  public void setBlackElo(int blackElo) {
    this.blackElo = blackElo;
  }

  public String getWhite() {
    return white;
  }

  public void setWhite(String white) {
    this.white = white;
  }

  public String getBlack() {
    return black;
  }

  public void setBlack(String black) {
    this.black = black;
  }

  public String getAnalysisVersion() {
    return analysisVersion;
  }

  public void setAnalysisVersion(String analysisVersion) {
    this.analysisVersion = analysisVersion;
  }

  public String getEngineVersion() {
    return engineVersion;
  }

  public void setEngineVersion(String engineVersion) {
    this.engineVersion = engineVersion;
  }
}
