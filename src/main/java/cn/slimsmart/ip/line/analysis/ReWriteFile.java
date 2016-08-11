package cn.slimsmart.ip.line.analysis;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/9.
 */
public class ReWriteFile {
	public static void main(String[] args) {
		File srcFile = new File("src/main/resources/netname-line.conf");
		File desFile = new File("src/main/resources/netname-line-new.conf");
		reWrite(srcFile, desFile);
	}

	public static void reWrite(File srcFile, File desFile) {
		Map<String, String> netNameLineMap = new HashMap<String, String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(srcFile));
			BufferedWriter wr = new BufferedWriter(new FileWriter(desFile));
			String line = null;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (!line.equals("")) {
					String[] arr = line.split("\\s+");
					netNameLineMap.put(arr[0].trim(), arr[1].trim());
				}
			}
			br.close();

			for (Map.Entry<String, String> en : netNameLineMap.entrySet()) {
				wr.write(en.getKey() + "    " + en.getValue());
				wr.newLine();
			}

			wr.flush();
			wr.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
