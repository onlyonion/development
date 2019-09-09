package com.onion.test.common.mock;

import com.github.tomakehurst.wiremock.client.WireMock;
import java.io.IOException;
import org.aspectj.util.FileUtil;
import org.springframework.core.io.ClassPathResource;

public class WireMockTest {

    public static void main(String[] args) throws IOException {
        WireMock.configureFor(9000);
        // WireMock.removeAllMappings();
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/user/1"))
            .willReturn(WireMock.aResponse()
                .withBody("{\"username\":FantJ}")
                .withStatus(200)));

        mock("/user/2", "user");

    }

    private static void mock(String url, String filename) throws IOException {
        ClassPathResource resource = new ClassPathResource("/wiremock/" + filename + ".txt");
        String content = FileUtil.readAsString(resource.getFile());

        //get请求
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo(url))
            .willReturn(WireMock.aResponse()
                .withBody(content)
                .withStatus(200)));
    }
}
