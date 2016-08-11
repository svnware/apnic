package cn.slimsmart.ip.line.analysis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 从下载的apnic IP 文件过滤出CN，并且是IPV4的地址
 *
 */
public class ObtainCNAndIpv4 {

	public static void main(String[] args) throws IOException {
		File srcFile = new File("src/main/resources/delegated-apnic-latest");
		File desFile = new File("src/main/resources/cn-ipv4");
		analysis(srcFile, desFile);
	}

	public static void analysis(File srcFile, File desFile) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(srcFile));
			BufferedWriter wr = new BufferedWriter(new FileWriter(desFile));
			String line = null;
			while ((line = br.readLine()) != null) {
				if(line.indexOf("CN|ipv4")!=-1){
					wr.write(line);
					wr.newLine();
				}
			}
			wr.flush();
			wr.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
