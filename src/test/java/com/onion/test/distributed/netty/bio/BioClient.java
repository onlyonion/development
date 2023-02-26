package com.onion.test.distributed.netty.bio;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;

@Slf4j
public class BioClient {
    private static final int PORT = 8090;
    private static final int COUNT = 10;
    private static final String HOST = "127.0.0.1";

    public static void main(String[] args) {
        for (int i = 1; i <= COUNT; i++) {
            int finalI = i;
            new Thread(() -> {
                try (
                        Socket socket = new Socket(HOST, PORT);
                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
                ) {
                    log.info("客户端:{}_{}, 已连接", socket, finalI);
                    writer.write(finalI + "\n");
                    writer.flush();
                    log.info("客户端:{}_{}, 发送信息:{}", socket, finalI, finalI);
                    String replyContent = reader.readLine();
                    log.info("客户端:{}_{}, 接收响应:{}", socket, finalI, replyContent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
