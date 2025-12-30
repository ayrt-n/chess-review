package com.chess.api.model;

import java.io.Serializable;
import java.util.List;

public class MoveAnalysis implements Serializable {
  private String san;
  private String uci;

  private String bestUci;
  private List<String> pvUci;

  private Integer evalCp;
  private Integer evalMate;

  private String commentary;
  private MoveClassification classification;

  public MoveAnalysis() {}

  public MoveAnalysis(String san, String uci) {
    this.san = san;
    this.uci = uci;
  }

  public String getSan() {
    return san;
  }

  public void setSan(String san) {
    this.san = san;
  }

  public String getUci() {
    return uci;
  }

  public void setUci(String uci) {
    this.uci = uci;
  }

  public String getBestUci() {
    return bestUci;
  }

  public void setBestUci(String bestUci) {
    this.bestUci = bestUci;
  }

  public List<String> getPvUci() {
    return pvUci;
  }

  public void setPvUci(List<String> pvUci) {
    this.pvUci = pvUci;
  }

  public Integer getEvalCp() {
    return evalCp;
  }

  public void setEvalCp(Integer evalCp) {
    this.evalCp = evalCp;
  }

  public Integer getEvalMate() {
    return evalMate;
  }

  public void setEvalMate(Integer evalMate) {
    this.evalMate = evalMate;
  }

  public String getCommentary() {
    return commentary;
  }

  public void setCommentary(String commentary) {
    this.commentary = commentary;
  }

  public MoveClassification getClassification() {
    return classification;
  }

  public void setClassification(MoveClassification classification) {
    this.classification = classification;
  }
}
