package cn.slimsmart.ip.line.analysis;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 下载apnic亚太IP地址
 */
public class ObtainApnicIP {

	public static void main(String[] args) {
		File file = new File("src/main/resources/delegated-apnic-latest");
		String httpUrl="ftp://ftp.apnic.net/pub/stats/apnic/delegated-apnic-latest";
		downloadFile(httpUrl,file);
	}

	public static void downloadFile(String httpUrl, File file) {
		try {
			BufferedOutputStream br = new BufferedOutputStream(new FileOutputStream(file));
			URL url = new URL(httpUrl);
			URLConnection conn = url.openConnection();
			InputStream inStream = conn.getInputStream();

			byte[] buffer = new byte[1024];
			int byteread = 0;
			while ((byteread = inStream.read(buffer)) != -1) {
				br.write(buffer, 0, byteread);
			}

			br.flush();
			br.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
