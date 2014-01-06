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
			
			// request�� header���� ��û�� ���� �̸��� ã�Ƴ���.
			RequestParser rp = new RequestParser(br.readLine());
			String fileName = rp.parseFileName();
			
			File file = new File(fileName);
			
			// ������ �����ϴ� ���
			if( file.exists() ){
				// ���� �̸��� Ȱ���� ������ Ȯ���ڸ�� ���� ũ�⸦ ã�Ƴ���.
				FileNameHandler fileNameHandler = new FileNameHandler(fileName);
				String fileType = fileNameHandler.findFileType();
				int fileSize = (int) file.length();
				
				// ��û���� ������ �о���δ�.
				FileInputStream fileStream = new FileInputStream(fileName);
				byte[] fileInBytes = new byte[fileSize];
				fileStream.read(fileInBytes);
				
				// ������ ����Ѵ�.
				dos.writeBytes("HTTP/1.0 200 Document Follows \r\n");
				dos.writeBytes("Content-Type: " + fileType + "\r\n");
				dos.writeBytes("Content-Length: " + fileSize + "\r\n");
				dos.writeBytes("\r\n");
				dos.write(fileInBytes, 0, fileSize);

			}
			else
			{
				// ������ �������� �ʴ´ٴ� ������ 404 ������ ����ϰ� ������ �����Ѵ�.
				System.out.println("Requested File Not Found : " + fileName);
				
				dos.writeBytes("HTTP/1.0 404 Not Found \r\n");
				dos.writeBytes("Connection: close\r\n");
				dos.writeBytes("\r\n");
			}
			
		}catch(IOException ioe){
			
		}
	}

}
