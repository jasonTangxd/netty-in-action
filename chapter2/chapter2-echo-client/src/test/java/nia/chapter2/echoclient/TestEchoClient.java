package nia.chapter2.echoclient;

import org.junit.Test;

/**
 * @ Author     ：XuanDongTang
 * @ Date       ：Created in 15:15 2019-07-31
 * @ Description：EchoClient
 * @ Modified By：
 */
public class TestEchoClient {

    /**
     * (1) 一旦客户端建立连接，它就发送它的消息——Netty rocks!;
     * (2) 服务器报告接收到的消息，并将其回送给客户端;
     * (3) 客户端报告返回的消息并退出。
     *
     * @throws Exception
     */
    @Test
    public void testEchoClient() throws Exception {

        final String host = "localhost";
        final int port = 8080;
        new EchoClient(host, port).start();

    }
}
