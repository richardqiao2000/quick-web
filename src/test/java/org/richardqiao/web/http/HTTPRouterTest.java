package org.richardqiao.web.http;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

class HTTPRouterTest {

  @Test
  void get() {
    org.richardqiao.web.http.HTTPRouter router = org.richardqiao.web.http.HTTPRouter.getInstance();
    Function<HTTPRequest, HTTPResponse> func = router.getFunc("hi");
    assert (func.apply(null).getBody().equals("{status: 1}"));
    func = router.getFunc("richard");
    assert (func.apply(null).getBody().equals("{richard: 1}"));
    func = router.getFunc("comcast");
    assert (func.apply(null).getBody().equals("{ComCast: 250}"));
    func = router.getFunc("404");
    assert (func.apply(null).getBody().equals("{httpCode: 404}"));
    func = router.getFunc("else");
    assert (func.apply(null).getBody().equals("{httpCode: 404}"));
    for (String str : permute("abc")) {
      System.out.println(str);
    }
    System.out.println(binary("8D28290AB9FF"));
  }

  private List<String> permute(String str) {
    List<String> res = new ArrayList<>();
    if (str.length() < 2) {
      res.add(str);
      return res;
    }
    for (int i = 0; i < str.length(); i++) {
      for (String sub : permute(str.substring(0, i) + str.substring(i + 1))) {
        res.add(str.substring(i, i + 1) + sub);
      }
    }
    return res;
  }

  private String binary(String base16) {
    StringBuilder sb = new StringBuilder();
    for (char ch : base16.toCharArray()) {
      int num = ch <= '9' ? ch - '0' : ch - 'A' + 10;
      System.out.println(num);
      StringBuilder sub = new StringBuilder();
      for (int j = 0; j < 4; j++) {
        sub.append(num & 1);
        num >>= 1;
      }
      sb.append(sub.reverse());
    }
    return sb.toString();
  }
}