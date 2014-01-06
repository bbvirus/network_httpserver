import java.net.ServerSocket;
import java.net.Socket;


public class Webserver {

	public static void main(String[] args) throws Exception{
		// 통신을 위한 소켓을 생성
		ServerSocket serverSocket = new ServerSocket(80);
		Socket socket;
		ServerThread serverThread;
		System.out.println("socket OK");
		
		// 클라이언트 접속을 받고 서버스레드를 만들어 실행
		while( (socket = serverSocket.accept()) != null ){
			serverThread = new ServerThread(socket);
			serverThread.start();
		}

	}

}
