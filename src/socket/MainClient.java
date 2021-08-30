package socket;

public class MainClient {
    public static void main(String[] args) throws InterruptedException {
        for(int i = 0 ; i<10 ; i++){
            EchoClient echoClient = new EchoClient();
            echoClient.startConnection("127.0.0.1",9999);
            echoClient.sendMessage("Hi~~~"+i);
            Thread.sleep(500);
            echoClient.sendMessage("I am "+i+"th Client");
            Thread.sleep(500);
            // echoClient.stopConnection();
            echoClient.sendMessage("EXIT");
        }
    }
}
