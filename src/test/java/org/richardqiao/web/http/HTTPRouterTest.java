package org.richardqiao.web.http;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

class HTTPRouterTest {

  @Test
  void get(){
    org.richardqiao.web.http.HTTPRouter router = org.richardqiao.web.http.HTTPRouter.getInstance();
    Function<HTTPRequest, HTTPResponse> func = router.getFunc("hi");
    assert(func.apply(null).getBody().equals("{status: 1}"));
    func = router.getFunc("richard");
    assert(func.apply(null).getBody().equals("{richard: 1}"));
    func = router.getFunc("comcast");
    assert(func.apply(null).getBody().equals("{ComCast: 250}"));
    func = router.getFunc("404");
    assert(func.apply(null).getBody().equals("{httpCode: 404}"));
    func = router.getFunc("else");
    assert(func.apply(null).getBody().equals("{httpCode: 404}"));
  }
}