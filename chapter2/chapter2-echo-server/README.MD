
## Echo 服务器

- 所有的 Netty 服务器都需要以下两部分。
    - 至少一个ChannelHandler— 该组件实现了服务器对从客户端接收的数据的处理，即它的业务逻辑。
    - 引导— 这是配置服务器的启动代码。至少，它会将服务器绑定到它要监听连接请求的端口上
    

- Echo 服务器会响应传入的消息，所以它需要实现 ChannelInboundHandler 接口，用 来定义响应入站事件的方法。
这个简单的应用程序只需要用到少量的这些方法，所以继承 Channel- InboundHandlerAdapter 类也就足够了，它提供了 ChannelInboundHandler 的默认实现    

- channelRead()— 对于每个传入的消息都要调用; 
- channelReadComplete()— 通知ChannelInboundHandler最后一次对channel-Read()的调用是当前批量读取中的最后一条消息;
-exceptionCaught()— 在读取操作期间，有异常抛出时会调用。
