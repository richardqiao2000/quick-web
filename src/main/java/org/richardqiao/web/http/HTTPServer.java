package org.richardqiao.web.http;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {

  private final ExecutorService exec;
  private int port;

  private static HTTPServer instance;
  private HTTPServer(int port, int threadCount){
    this.port = port;
    exec = Executors.newFixedThreadPool(threadCount);
  }

  public static HTTPServer getInstance(int port, int threadCount){
    if(instance == null){
      synchronized (HTTPServer.class){
        if(instance == null){
          instance = new HTTPServer(port, threadCount);
        }
      }
    }
    return instance;
  }

  /**
   * start the http server
   * @throws Exception
   */
  public void start() throws Exception{
    final ServerSocket serverSocket = new ServerSocket(port);
    Runtime.getRuntime().addShutdownHook(new Thread(){
      public void run(){
        try{
          exec.shutdown();
          System.out.println("ExecutorServices shutdown.");
          serverSocket.close();
          System.out.println("ServerSocked closed.");
        }catch(Exception e){
          e.printStackTrace();
        }
      }
    });
    while (true) {
      Socket socket  = serverSocket.accept();
      exec.execute(new HTTPApplication(socket));
    }
  }

}
