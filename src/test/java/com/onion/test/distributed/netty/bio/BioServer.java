package com.onion.test.distributed.netty.bio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class BioServer {
    private static final int PORT = 8090;

    private static final ExecutorService SOCKET_POOL = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100),
            new CustomizableThreadFactory("socket-pool-"), new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        log.info("服务端已启动");
        while (true) {
            Socket socket = serverSocket.accept();
            log.info("接收到客户端连接: {}", socket);
            SOCKET_POOL.execute(new ServerTask(socket));
        }
    }

    public static class ServerTask implements Runnable {

        private final Socket socket;

        public ServerTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
            ) {
                String clientContent = reader.readLine();
                log.info("线程: {}, 接收到客户端:{}, 信息: {} ", Thread.currentThread().getName(), socket, clientContent);
                String replyContent = new Date().toString();
                writer.write(replyContent + "\n");
                writer.flush();
                log.info("线程: {}, 返回客户端:{}, 响应: {} ", Thread.currentThread().getName(), socket, replyContent);
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
