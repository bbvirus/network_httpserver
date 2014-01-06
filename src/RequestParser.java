

public class RequestParser {
	
	String request;
	String fileName;
	
	// ��û ������ �����Ѵ�.
	public RequestParser(String request) {
		this.request = request;
	}
	
	// �����ص� ��û ���뿡�� ���� �̸��� ã�Ƴ� �����Ѵ�.
	public String parseFileName(){
		String[] buffer = request.split(" ");

		if( buffer[0].equals("GET") ){
			
			if(buffer[1].length() > 1){
			
				if(buffer[1].startsWith("/")){
					fileName = buffer[1].substring(1);
				}
			}
		}
		
		else{
			System.out.println("bad request");
			fileName = "HTTP/1.0 400 Bad Request Message \r\n" + "Connection: close\r\n" + "\r\n";
		}

		return fileName;
	}

}