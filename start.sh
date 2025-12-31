#!/bin/bash

# Chess Review - Local Development Startup Script
# This script builds all Docker containers and starts the application

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

echo -e "${BLUE}╔════════════════════════════════════════╗${NC}"
echo -e "${BLUE}║     Chess Review - Local Setup         ║${NC}"
echo -e "${BLUE}╚════════════════════════════════════════╝${NC}"
echo ""

# Check if Docker is installed
if ! command -v docker &> /dev/null; then
    echo -e "${RED}Error: Docker is not installed. Please install Docker first.${NC}"
    exit 1
fi

# Check if Docker daemon is running
if ! docker info &> /dev/null; then
    echo -e "${RED}Error: Docker daemon is not running. Please start Docker.${NC}"
    exit 1
fi

echo -e "${GREEN}✓ Docker is running${NC}"

# Check if docker-compose is available (either standalone or as docker compose)
if command -v docker-compose &> /dev/null; then
    COMPOSE_CMD="docker-compose"
elif docker compose version &> /dev/null; then
    COMPOSE_CMD="docker compose"
else
    echo -e "${RED}Error: docker-compose is not installed.${NC}"
    exit 1
fi

echo -e "${GREEN}✓ Docker Compose is available${NC}"

# Navigate to project root
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

# Check for .env file and create if it doesn't exist
if [ ! -f .env ]; then
    echo -e "${YELLOW}No .env file found. Creating one with default values...${NC}"
    cat > .env << 'EOF'
# Database Configuration
DB_HOST=postgres
DB_USER=chess_user
DB_PASSWORD=chess_password
DB_NAME=chess_review

# RabbitMQ Configuration
RABBITMQ_USER=guest
RABBITMQ_PASS=guest
EOF
    echo -e "${GREEN}✓ Created .env file with default values${NC}"
else
    echo -e "${GREEN}✓ .env file exists${NC}"
fi

# Parse command line arguments
BUILD_ONLY=false
NO_BUILD=false
DETACHED=false

while [[ $# -gt 0 ]]; do
    case $1 in
        --build-only)
            BUILD_ONLY=true
            shift
            ;;
        --no-build)
            NO_BUILD=true
            shift
            ;;
        -d|--detached)
            DETACHED=true
            shift
            ;;
        -h|--help)
            echo "Usage: ./start.sh [OPTIONS]"
            echo ""
            echo "Options:"
            echo "  --build-only    Only build containers, don't start them"
            echo "  --no-build      Start without rebuilding containers"
            echo "  -d, --detached  Run containers in detached mode (background)"
            echo "  -h, --help      Show this help message"
            exit 0
            ;;
        *)
            echo -e "${RED}Unknown option: $1${NC}"
            echo "Use --help for usage information"
            exit 1
            ;;
    esac
done

echo ""

# Build containers
if [ "$NO_BUILD" = false ]; then
    echo -e "${BLUE}Building Docker containers...${NC}"
    echo ""
    $COMPOSE_CMD build
    echo ""
    echo -e "${GREEN}✓ All containers built successfully${NC}"
fi

if [ "$BUILD_ONLY" = true ]; then
    echo ""
    echo -e "${GREEN}Build complete. Use './start.sh --no-build' to start the containers.${NC}"
    exit 0
fi

# Start containers
echo ""
echo -e "${BLUE}Starting all services...${NC}"
echo ""

if [ "$DETACHED" = true ]; then
    $COMPOSE_CMD up -d
    echo ""
    echo -e "${GREEN}✓ All services started in background${NC}"
    echo ""
    echo -e "${BLUE}Services available at:${NC}"
    echo -e "  • Frontend:         ${GREEN}http://localhost:3000${NC}"
    echo -e "  • Backend API:      ${GREEN}http://localhost:8080${NC}"
    echo -e "  • RabbitMQ Console: ${GREEN}http://localhost:15672${NC}"
    echo -e "  • PostgreSQL:       ${GREEN}localhost:5432${NC}"
    echo ""
    echo -e "${YELLOW}Tip: Use '$COMPOSE_CMD logs -f' to view logs${NC}"
    echo -e "${YELLOW}Tip: Use '$COMPOSE_CMD down' to stop all services${NC}"
else
    echo -e "${YELLOW}Starting in foreground mode. Press Ctrl+C to stop.${NC}"
    echo ""
    echo -e "${BLUE}Services will be available at:${NC}"
    echo -e "  • Frontend:         ${GREEN}http://localhost:3000${NC}"
    echo -e "  • Backend API:      ${GREEN}http://localhost:8080${NC}"
    echo -e "  • RabbitMQ Console: ${GREEN}http://localhost:15672${NC}"
    echo -e "  • PostgreSQL:       ${GREEN}localhost:5432${NC}"
    echo ""
    $COMPOSE_CMD up
fi

