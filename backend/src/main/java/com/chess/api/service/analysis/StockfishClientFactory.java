package com.chess.api.service.analysis;

import java.io.IOException;
import java.net.Socket;

import org.springframework.stereotype.Service;

@Service
public class StockfishClientFactory {
  public StockfishClient createClient() throws IOException {
    Socket socket = new Socket("stockfish-engine", 5000);
    socket.setSoTimeout(5000);
    
    StockfishClient client = new StockfishClient(socket);
    client.initializeUci();
    client.isReady();

    return client;
  }
}
