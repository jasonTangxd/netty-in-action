package nia.chapter2.echoserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Listing 2.2 EchoServer class
 *
 * @author <a href="mailto:norman.maurer@gmail.com">Norman Maurer</a>
 */
public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }


    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();

        // 创建 Event- LoopGroup
        EventLoopGroup group = new NioEventLoopGroup();

        try {

            // 你创建了一个 ServerBootstrap 实例。
            // 因为你正在使用的是 NIO 传输，所以你指定了NioEventLoopGroup来接受和处理新的连接，并且将Channel的类型指定为NioServerSocketChannel
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)

                    // 使用指定的 端口设置套 接字地址
                    .localAddress(new InetSocketAddress(port))

                    // 添加一个 EchoServerHandler 到子 Channel 的 ChannelPipeline
                    // 使用了一个特殊的类——ChannelInitializer。这是关键。
                    // 当一个新的连接 被接受时，一个新的子 Channel 将会被创建，
                    // 而 ChannelInitializer 将会把一个你的 EchoServerHandler 的实例添加到该 Channel 的 ChannelPipeline 中。
                    // 正如我们之前所 解释的，这个 ChannelHandler 将会收到有关入站消息的通知
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(serverHandler);
                        }
                    });

            ChannelFuture f = b.bind().sync();
            System.out.println(EchoServer.class.getName() +
                    " started and listening for connections on " + f.channel().localAddress());
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }
}
