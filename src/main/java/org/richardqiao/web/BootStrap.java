package org.richardqiao.web;

/**
 * TODO: Exception responses
 * Logging
 * NGinx Load Balancer
 */

import org.richardqiao.web.http.HTTPServer;

public class BootStrap {
  /**
   * start command
   * java -cp quick-web-0.01.jar org.richardqiao.web.BootStrap 5678 5
   * @param args args[0]: portNumber, args[1]: threadCount
   * @throws Exception
   */
  public static void main(String[] args) {
    try {
      HTTPServer server = HTTPServer.getInstance(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
      server.start();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
