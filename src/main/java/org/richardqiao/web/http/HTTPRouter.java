package org.richardqiao.web.http;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class HTTPRouter {
  private Map<String, Class<?>> map;
  private Map<String, Function<HTTPRequest, HTTPResponse>> router;

  private static HTTPRouter instance;
  private HTTPRouter(){
    router = new HashMap<>();
    router.put("404", funcResponse404);
    router.put("hi", funcResponseHi);
    router.put("richard", funcResponseRichard);
    router.put("comcast", funcResponseComcast);
  }

  public static HTTPRouter getInstance(){
    if(instance == null){
      synchronized(HTTPRouter.class){
        if(instance == null){
          instance = new HTTPRouter();
        }
      }
    }
    return instance;
  }

  public Function<HTTPRequest, HTTPResponse> getFunc(String path){
    return router.getOrDefault(path, router.get("404"));
  }

  private Function<HTTPRequest, HTTPResponse> funcResponse404 = request -> new HTTPResponse(404);

  private Function<HTTPRequest, HTTPResponse> funcResponseHi = request -> {
    String body = "{status: 1}";
    return new HTTPResponse(200, body);
  };

  private Function<HTTPRequest, HTTPResponse> funcResponseRichard = request -> {
    String body = "{richard: 1}";
    return new HTTPResponse(200, body);
  };

  private Function<HTTPRequest, HTTPResponse> funcResponseComcast = request -> {
    String body = "{ComCast: 250}";
    return new HTTPResponse(200, body);
  };
}
