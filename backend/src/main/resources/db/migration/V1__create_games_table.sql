CREATE TABLE games (
  id BIGSERIAL PRIMARY KEY,
  raw_pgn TEXT NOT NULL,
  pgn_hash VARCHAR(255) NOT NULL,
  analysis_status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
  analysis JSONB,
  white VARCHAR(255),
  black VARCHAR(255),
  white_elo INT,
  black_elo INT,
  analysis_version VARCHAR(50),
  engine_version VARCHAR(50),
  
  created_at TIMESTAMP WITH TIME ZONE NOT NULL,
  updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);