package com.onion.test.java.features;

import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java9Test {

    @Test
    public void testModule() {
        /*
            module java.base {
                exports java.io;
                uses sun.util.spi.CalendarProvider;
                provides java.nio.file.spi.FileSystemProvider with jdk.internal.jrtfs.JrtFileSystemProvider;
            }

         */

        // jshell
        // G1 default gc
    }

    @Test
    public void testCollections() {
        Set<Integer> ints = Set.of(1,2,3);
        List<String> strings = List.of("first","second");
    }

    @Test
    public void testStream() {
        List<String> strings = Stream.of("1", "2").takeWhile("1"::equals).toList();
        List<String> string2 = Stream.of("1", "2").takeWhile("1"::equals).collect(Collectors.toList());
    }

    @Test
    public void testOptional() {
        Optional.ofNullable(null).ifPresentOrElse(System.out::println,
                () -> System.out.println("null"));
        Optional.ofNullable(null).or(() -> Optional.of(List.of("1")));
    }

    @Test
    public void testHttp2() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest
                .newBuilder(URI.create("http://www.baidu.com"))
                .header("User-Agent","Java")
                .GET()
                .build();
        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
    }

}
