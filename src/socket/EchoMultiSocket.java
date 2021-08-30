package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoMultiSocket {
    private ServerSocket serverSocket;

    public void start(int port){
        try {
            serverSocket = new ServerSocket(port);
            while (true){
                new EchoClientHandler(serverSocket.accept()).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            stop();
        }
    }
    public void stop(){
        try {
            serverSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class EchoClientHandler extends Thread{
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private StringBuffer stringBuffer;

        public EchoClientHandler(Socket accept) {
            this.clientSocket = accept;

        }

        @Override
        public void run() {
            try{
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                System.out.println("server running...........................");
                String inputLine;

                while((inputLine = in.readLine())!= null){
                        System.out.println(inputLine);
                        if ("EXIT".equals(inputLine)) {
                            out.println("close connection....");
                            break;
                        }
                       out.println(inputLine);
                    }
                in.close();
                out.close();
                clientSocket.close();

            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
