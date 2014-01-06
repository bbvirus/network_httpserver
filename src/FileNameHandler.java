import java.util.StringTokenizer;


public class FileNameHandler {
	
	String fileName;
	StringTokenizer token;
	String buffer;
	String fileType;
	
	// 파일 이름을 저장
	public FileNameHandler(String fileName) {
		this.fileName = fileName;
	}

	// 저장해둔 파일 이름에서 확장자명을 분리해내는 메서드
	public String findFileType() {
		token = new StringTokenizer(fileName, ".");
		if(token.hasMoreTokens()){
			buffer = (String)token.nextElement();
		}
		
		if(buffer == "html") fileType = "text/html";
		if(buffer == "js") fileType = "text/javascript";
		if(buffer == "css") fileType = "text/css";
		if(buffer == "json") fileType = "text/json";
		if(buffer == "jpg") fileType = "image/jpg";
		if(buffer == "bmp") fileType = "image/bmp";
		if(buffer == "png") fileType = "image/png";
		
		return fileType;
	}

}
