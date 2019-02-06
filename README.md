# Spring Flags

Spring Style Flags Micro Service


> Work in progress ...


Implementation focused to leverage the best practices and conventions of Spring Boot framework.

* Use `Data Key Value` repositories 
  * Use the `Map` based implementation, can easily switch to `Spring Data Redis` or `Spring Data MongoDB` as needed.
* Use `Spring Boot Actuator` for audit, health and metrics.
  * Use `Micrometer` for metrics, monitoring.
* Use `Spring Boot Test` for unit/integration tests
* Configure using `application.properties`

## Run

```
$ ./gradlew bootRun
...
2019-02-05 17:01:18.954  INFO 30260 --- [main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2019-02-05 17:01:18.956  INFO 30260 --- [main] com.hackorama.flags.FlagsApplication     : Started FlagsApplication in 2.93 seconds (JVM running for 3.486)
2019-02-05 17:01:18.957  INFO 30260 --- [main] com.hackorama.flags.data.Loader          : Loading data from /continents.txt ...
..
```

## Test

Authentication added for auditing


```
$ curl   http://127.0.0.1:8080/flags/USA
HTTP Status 401 : Full authentication is required to access this resource
```

```
$ curl --user test:test  http://127.0.0.1:8080/flags/USA
[{"name":"USA","flag":"\uD83C\uDDFA\uD83C\uDDF8"}]
```


## Metrics Using Actuator


### Health 
```
$ curl http://127.0.0.1:8080/actuator/health
{"status":"UP"}
```

### System Metrics

```
$ curl http://127.0.0.1:8080/actuator/metrics/jvm.memory.used
{"name":"jvm.memory.used","description":"The amount of used memory","baseUnit":"bytes","measurements":[{"statistic":"VALUE","value":1.24928168E8}],"availableTags":[{"tag":"area","values":["heap","nonheap"]},{"tag":"id","values":["Compressed Class Space","PS Survivor Space","PS Old Gen","Metaspace","PS Eden Space","Code Cache"]}]}
```

```
$ curl http://127.0.0.1:8080/actuator/metrics/http.server.requests
{"name":"http.server.requests","description":null,"baseUnit":"seconds","measurements":[{"statistic":"COUNT","value":6.0},{"statistic":"TOTAL_TIME","value":0.127122619},{"statistic":"MAX","value":0.065799236}],"availableTags":[{"tag":"exception","values":["None"]},{"tag":"method","values":["GET"]},{"tag":"uri","values":["/actuator/metrics/{requiredMetricName}","/actuator/info","/actuator/metrics/","/actuator/metrics"]},{"tag":"outcome","values":["SUCCESS"]},{"tag":"status","values":["200"]}]}
```

### Application Metrics

Flags API call metrics for `/flags/{id}`
 
```
$ curl http://127.0.0.1:8080/actuator/metrics/flags.byid
{"name":"flags.byid","description":null,"baseUnit":"seconds","measurements":[{"statistic":"COUNT","value":4.0},{"statistic":"TOTAL_TIME","value":0.026370291},{"statistic":"MAX","value":0.003816216}],"availableTags":[{"tag":"exception","values":["None"]},{"tag":"method","values":["GET"]},{"tag":"uri","values":["/flags/{id}"]},{"tag":"outcome","values":["SUCCESS"]},{"tag":"status","values":["200"]}]}
```

### Auditing 

Authentication events are captured as audit events

```
$ curl http://127.0.0.1:8080/actuator/auditevents
{"events":[{"timestamp":"2019-02-06T22:12:20.239Z","principal":"anonymousUser","type":"AUTHORIZATION_FAILURE","data":{"details":{"remoteAddress":"127.0.0.1","sessionId":null},"type":"org.springframework.security.access.AccessDeniedException","message":"Access is denied"}},{"timestamp":"2019-02-06T22:12:30.838Z","principal":"test","type":"AUTHENTICATION_FAILURE","data":{"details":{"remoteAddress":"127.0.0.1","sessionId":null},"type":"org.springframework.security.authentication.BadCredentialsException","message":"Bad credentials"}},{"timestamp":"2019-02-06T22:12:50.914Z","principal":"test","type":"AUTHENTICATION_SUCCESS","data":{"details":{"remoteAddress":"127.0.0.1","sessionId":null}}}]}
```

