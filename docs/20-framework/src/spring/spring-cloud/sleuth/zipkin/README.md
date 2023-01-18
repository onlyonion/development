zipkin


https://search.maven.org/remote_content?g=io.zipkin.java&a=zipkinserver&v=LATEST&c=exec
java -jar zipkin-server-2.12.9-exec.jar
http://localhost:9411

docker run -d -p 9411:9411 openzipkin/zipkin