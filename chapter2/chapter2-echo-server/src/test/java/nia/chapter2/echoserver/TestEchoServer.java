package nia.chapter2.echoserver;

import org.junit.Test;

/**
 * @ Author     ：XuanDongTang
 * @ Date       ：Created in 15:19 2019-07-31
 * @ Description：EchoServer
 * @ Modified By：
 */
public class TestEchoServer {


    @Test
    public void testEchoServer() throws Exception {
        int port = 8080;
        new EchoServer(port).start();
    }
}
