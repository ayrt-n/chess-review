package com.chess.api.dto;

import java.io.Serializable;

public record GameAnalysisMessage(Long gameId) implements Serializable {}
