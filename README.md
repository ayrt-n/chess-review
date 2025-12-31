# Chess Review

A chess game analysis application that evaluates your games move-by-move using the Stockfish engine — similar to the game review features on Chess.com or Lichess.

## What It Does

Upload a chess game in PGN format and get detailed analysis including:

- **Position evaluation** — centipawn scores and mate-in-N detection for each move
- **Move classification** — identifies brilliant moves, great moves, good moves, inaccuracies, mistakes, and blunders
- **Best move suggestions** — shows the engine's recommended line at each position

## Architecture

```
┌──────────────┐     ┌──────────────┐     ┌──────────────┐
│   Frontend   │────▶│   Backend    │────▶│  PostgreSQL  │
│    (React)   │     │ (Spring Boot)│     │              │
└──────────────┘     └──────┬───────┘     └──────────────┘
                            │
                      ┌─────┴─────┐
                      ▼           ▼
                ┌──────────┐ ┌──────────────┐
                │ RabbitMQ │ │  Stockfish   │
                │          │ │   Service    │
                └──────────┘ └──────────────┘
```

| Service | Description |
|---------|-------------|
| **Frontend** | React/TypeScript web interface |
| **Backend** | Java/Spring Boot API — handles PGN parsing, game storage, and analysis orchestration |
| **PostgreSQL** | Stores games and analysis results |
| **RabbitMQ** | Message queue for async game analysis |
| **Stockfish Service** | Python TCP bridge exposing the Stockfish chess engine |

## Getting Started

The easiest way to get started is with the included start script:

```bash
./start.sh
```

This will check dependencies, create a `.env` file with sensible defaults if needed, build all containers, and start the application.

### Start Script Options

| Option | Description |
|--------|-------------|
| `--build-only` | Only build containers, don't start them |
| `--no-build` | Start without rebuilding containers |
| `-d, --detached` | Run containers in background |
| `-h, --help` | Show help message |

### Manual Setup

Alternatively, you can run Docker Compose directly:

1. Copy `.env.example` to `.env` and configure your environment variables
2. Run:

```bash
docker compose up --build
```

### Access Points

| Service | URL |
|---------|-----|
| Frontend | http://localhost:3000 |
| Backend API | http://localhost:8080 |
| RabbitMQ Console | http://localhost:15672 |
| PostgreSQL | localhost:5432 |

