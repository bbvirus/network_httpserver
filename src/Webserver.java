import java.net.ServerSocket;
import java.net.Socket;


public class Webserver {

	public static void main(String[] args) throws Exception{
		// ����� ���� ������ ����
		ServerSocket serverSocket = new ServerSocket(80);
		Socket socket;
		ServerThread serverThread;
		System.out.println("socket OK");
		
		// Ŭ���̾�Ʈ ������ �ް� ���������带 ����� ����
		while( (socket = serverSocket.accept()) != null ){
			serverThread = new ServerThread(socket);
			serverThread.start();
		}

	}

}
