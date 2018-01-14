package org.richardqiao.web.http;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPClient {
  public static void main(String[] args) throws Exception{
    ExecutorService exec = Executors.newFixedThreadPool(10);
    int count = 100000;
    while(count-- > 0){
      exec.execute(new Runnable(){
        private String getHeader(String path){
          return "GET /" + path + " HTTP/1.1\n" +
              "Host: localhost:5678\n" +
              "Connection: close\n" +
              "User-Agent: Paw/3.1.5 (Macintosh; OS X/10.13.1) GCDHTTPRequest\n" +
              "Content-Length: 0\r\n\r\n";
        }
        private void webCall(String header, String body, String address, int port) throws Exception{
          Socket socket = new Socket(address, port);
          DataOutputStream output = new DataOutputStream(socket.getOutputStream());
          output.writeBytes(header + body);
//          output.close();
//          socket.close();
        }
        public void run(){
          try {
            webCall(getHeader("richard"), "", "127.0.0.1", 5678);
          }catch(Exception e){
            e.printStackTrace();
          }
        }
      });
      Thread.sleep(20);
      System.out.println(count);
    }
    exec.shutdown();
  }

}
