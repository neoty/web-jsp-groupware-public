package groupware.commons;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadController {
	public static void fStream(String encName, OutputStream os, String uploadPath, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		FileInputStream is = null;
		try {
			is = new FileInputStream(uploadPath + "\\" + encName);
			byte[] data = new byte[1024];
			int len = -1;
			while ((len = is.read(data)) != -1) {
				os.write(data, 0, len);
			}
			
		} catch(Exception e) {
			JavaScript.getIntance().HistoryBack(request, response, "지정된 파일이 없습니다. ");
		}
		finally {
			
			if (is != null)
				
				try {
					is.close();
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
				}
		}
	}
}
