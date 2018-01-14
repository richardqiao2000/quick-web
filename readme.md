# Quick-Web
### A quick multi-threaded web server implementation by using only java libs
### A good academic learning material, but to be improved in term of lots of features.

### TODO
- Logging
- NGinx Load Balancer
- detect and enhance method types
- http headers manipulating keep-alive/cookie/auth etc
- cluster high availability (may introduce gossip or zookeeper or blockchain)
- CI/CD setup on Jenkins
- Add handler pool. 
- Design distribution deployment strategy. Consider dockerize it.
- Add statsd metrics
- Unit Test --  Done
- router configurable   --  Done
- Perf testing  --  Done
- Memory monitoring at long duration    --  Done
- Convert handler to functional call instead. --  Done

### How to Run
```shell
mvn clean install
java -cp quick-web-0.01.jar org.richardqiao.web.BootStrap 5678 5
-- above 2 parameters: 5678 is port number, 5 is thread count
```
Put below URLs in browser to for testing (or use any rest client too)
- http://localhost:5678/hi
- http://localhost:5678/hi?you=9
- http://localhost:5678/richard
- http://localhost:5678/richard?sex=m
- http://localhost:5678/comcast
- http://localhost:5678/comcast?stock=100

### How to enhance your URL paths and handlers?
- Add router mapping in org.richardqiao.web.http.HTTPRouter

### Performance Monitor at 50 QPS
```shell
java -cp quick-web-0.01.jar org.richardqiao.web.http.HTTPClient
```

![Alt text](visualVM.png?raw=true "VisualVM")
