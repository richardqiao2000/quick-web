package org.richardqiao.web.http;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.function.Function;

public class HTTPHandler {
  protected HTTPRequest httpRequest;
  protected Socket socket;
  protected HTTPRouter router;

  public HTTPHandler(){
    router = HTTPRouter.getInstance();
  }

  public HTTPResponse response(HTTPRequest request, Socket socket) throws Exception{
    httpRequest = request;
    this.socket = socket;
    DataOutputStream output = new DataOutputStream(socket.getOutputStream());
    Function<HTTPRequest, HTTPResponse> func = router.getFunc(request.getPath());
    HTTPResponse response = func.apply(request);
    output.writeBytes(response.toString());
    output.close();
    return response;
  }

}
