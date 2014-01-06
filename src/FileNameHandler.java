import java.util.StringTokenizer;


public class FileNameHandler {
	
	String fileName;
	StringTokenizer token;
	String buffer;
	String fileType;
	
	// ���� �̸��� ����
	public FileNameHandler(String fileName) {
		this.fileName = fileName;
	}

	// �����ص� ���� �̸����� Ȯ���ڸ��� �и��س��� �޼���
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
