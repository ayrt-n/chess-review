package com.chess.api.dto;

import jakarta.validation.constraints.NotBlank;

public record GameRequest(
  @NotBlank(message = "PGN cannot be empty")
  String pgn
) {}
