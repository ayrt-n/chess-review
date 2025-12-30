package com.chess.api.model.analysis;

import java.util.List;

public class StockfishEvaluation {
  private int depth;
  private Integer cp;
  private Integer mate;
  private String bestUci;
  private List<String> pvUci;

  public StockfishEvaluation(int depth) {
    this.depth = depth;
  }

  public int getDepth() {
    return depth;
  }

  public void setDepth(int depth) {
    this.depth = depth;
  }

  public Integer getCp() {
    return cp;
  }

  public void setCp(Integer cp) {
    this.cp = cp;
  }

  public Integer getMate() {
    return mate;
  }

  public void setMate(Integer mate) {
    this.mate = mate;
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
}
