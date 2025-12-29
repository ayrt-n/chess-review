FROM python:3.11-slim
RUN apt-get update && apt-get install -y stockfish && rm -rf /var/lib/apt/lists/*
ENV PATH="/usr/games:${PATH}"
WORKDIR /app
COPY bridge.py .
EXPOSE 5000

CMD ["python", "bridge.py"]