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


```
$ ./gradlew bootRun
...
2019-02-05 17:01:18.954  INFO 30260 --- [main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2019-02-05 17:01:18.956  INFO 30260 --- [main] com.hackorama.flags.FlagsApplication     : Started FlagsApplication in 2.93 seconds (JVM running for 3.486)
2019-02-05 17:01:18.957  INFO 30260 --- [main] com.hackorama.flags.data.Loader          : Loading data from /continents.txt ...
..
```

```
$ curl http://127.0.0.1:8080/flags/America
[{"name":"USA","flag":"\uD83C\uDDFA\uD83C\uDDF8"},{"name":"Brazil","flag":"\uD83C\uDDE7\uD83C\uDDF7"},{"name":"Mexico","flag":"\uD83C\uDDF2\uD83C\uDDFD"},{"name":"Colombia","flag":"\uD83C\uDDE8\uD83C\uDDF4"},{"name":"Argentina","flag":"\uD83C\uDDE6\uD83C\uDDF7"}]
```

Using Actuator

```
$ curl http://127.0.0.1:8080/actuator/health
{"status":"UP"}
```
