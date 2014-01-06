import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class ServerThread extends Thread{
	
	private Socket socket;
	
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run(){
		
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			// request의 header에서 요청된 파일 이름을 찾아낸다.
			RequestParser rp = new RequestParser(br.readLine());
			String fileName = rp.parseFileName();
			
			File file = new File(fileName);
			
			// 파일이 존재하는 경우
			if( file.exists() ){
				// 파일 이름을 활용해 파일의 확장자명과 파일 크기를 찾아낸다.
				FileNameHandler fileNameHandler = new FileNameHandler(fileName);
				String fileType = fileNameHandler.findFileType();
				int fileSize = (int) file.length();
				
				// 요청받은 파일을 읽어들인다.
				FileInputStream fileStream = new FileInputStream(fileName);
				byte[] fileInBytes = new byte[fileSize];
				fileStream.read(fileInBytes);
				
				// 파일을 출력한다.
				dos.writeBytes("HTTP/1.0 200 Document Follows \r\n");
				dos.writeBytes("Content-Type: " + fileType + "\r\n");
				dos.writeBytes("Content-Length: " + fileSize + "\r\n");
				dos.writeBytes("\r\n");
				dos.write(fileInBytes, 0, fileSize);

			}
			else
			{
				// 파일이 존재하지 않는다는 에러인 404 에러를 출력하고 접속을 종료한다.
				System.out.println("Requested File Not Found : " + fileName);
				
				dos.writeBytes("HTTP/1.0 404 Not Found \r\n");
				dos.writeBytes("Connection: close\r\n");
				dos.writeBytes("\r\n");
			}
			
		}catch(IOException ioe){
			
		}
	}

}
