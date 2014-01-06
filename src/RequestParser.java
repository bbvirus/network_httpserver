

public class RequestParser {
	
	String request;
	String fileName;
	
	// 요청 내용을 저장한다.
	public RequestParser(String request) {
		this.request = request;
	}
	
	// 저장해둔 요청 내용에서 파일 이름을 찾아내 리턴한다.
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