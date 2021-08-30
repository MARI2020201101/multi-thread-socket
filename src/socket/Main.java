package socket;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        EchoMultiSocket socket = new EchoMultiSocket();
        socket.start(9999);
    }
}
