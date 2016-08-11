package cn.slimsmart.ip.line.analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Test {
	
	static String ipv4="http://www.cnisp.org/Home/Ipv4?ip=";
	static String ipas = "http://www.cnisp.org/Home/IpAs?searchAction=ip&keyword=";
	static String ip138 = "http://www.cnisp.org/Home/Ip138?ip=";
	
	public static void main(String[] args) throws Exception {
		File file = new File("src/main/resources/ip-line-unknown");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = null;
		while ((line = br.readLine()) != null) {
			line = line.trim();
			if (!line.equals("")) {
				 String[] aa =line.split("\\s+");
				 String ipaddr = getUrlContent(ipv4+aa[0]);
				 if(ipaddr.indexOf("该段地址不属于中国使用")!=-1 && getUrlContent(ipas+aa[0]).indexOf("<tr>            <td>            </td>            <td>            </td>            <td>            </td>            <td>            </td>            <td>            </td>            <td>            </td>        </tr>")!=-1){
					 System.out.println(aa[2]+"======");
				 }else{
					 ipaddr = ipaddr.substring(ipaddr.indexOf("<tr>")+4);
					 ipaddr = ipaddr.substring(ipaddr.indexOf("<tr>")+4);
					 
					 ipaddr = ipaddr.substring(ipaddr.indexOf("<td>")+4);
					 ipaddr = ipaddr.substring(ipaddr.indexOf("<td>")+4);
					 ipaddr = ipaddr.substring(ipaddr.indexOf("<td>")+4);
					 ipaddr = ipaddr.substring(0, ipaddr.indexOf("</td>"));
					 if(ipaddr.indexOf("td colspan=\"4\">")!=-1){
						 ipaddr = "";
					 }
					 
					 String ip138Addr = getUrlContent(ip138+aa[0]);
					 ip138Addr = ip138Addr.substring(ip138Addr.indexOf("<td width=\"305px;\">")+"<td width=\"305px;\">".length());
					 ip138Addr = ip138Addr.substring(0, ip138Addr.indexOf("</td>        </tr> "));
					 ipaddr += "	"+ip138Addr.trim();
					 System.out.println(aa[2]+" --------- "+ipaddr);
				 }
			}
		}
		br.close();
	}
	static String getUrlContent(String urlIP) throws Exception{
		URL url = new URL(urlIP);
		URLConnection conn = url.openConnection();
		InputStream inStream = conn.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		return sb.toString();
	}
}
