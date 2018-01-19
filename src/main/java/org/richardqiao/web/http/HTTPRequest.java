package org.richardqiao.web.http;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

public class HTTPRequest {

  private String header;
  private String body;
  private String request;
  private Map<String, String> map;
  private String line0;

  public HTTPRequest() {
    header = "";
    body = "";
    map = new HashMap();
  }

  public HTTPRequest(String line0, BufferedReader bf) throws Exception {
    this();
    this.line0 = line0;
    StringBuilder sb = new StringBuilder();
    sb.append(line0 + "\n");
    while ((request = bf.readLine()) != null) {
      if (request.length() == 0) break;
      sb.append(request + "\n");
      String[] kv = request.split(":");
      if (kv.length > 1) map.put(kv[0], kv[1]);
    }
    request = sb.toString();
  }

  public String toString() {
    return request;
  }

  public String getPath() {
    String str = line0.split(" ")[1].trim().substring(1).toLowerCase();
    str = str.split("[\\?#]+")[0].trim();
    while (str.length() > 0 && str.charAt(str.length() - 1) == '/') {
      str = str.substring(0, str.length() - 1);
    }
    return str;
  }
}
