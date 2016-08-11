package cn.slimsmart.ip.line.analysis;

import java.io.File;

/**
 * 主控制一键生成各线路ACL文
 *
 */
public class Main {
	
	public static void main(String[] args) {
		
		//从apnic下载亚太地区的IP地址
		File file = new File("src/main/resources/delegated-apnic-latest");
		String httpUrl="ftp://ftp.apnic.net/pub/stats/apnic/delegated-apnic-latest";
		ObtainApnicIP.downloadFile(httpUrl,file);
		
		//从下载的apnic IP 文件过滤出CN，并且是IPV4的地址
		File srcFile = new File("src/main/resources/delegated-apnic-latest");
		File desFile = new File("src/main/resources/cn-ipv4");
		ObtainCNAndIpv4.analysis(srcFile, desFile);
		
		//通过apnic whois解析IP的netname 和mnt
		srcFile = new File("src/main/resources/cn-ipv4");
		desFile = new File("src/main/resources/ip-line");
		ObtainIPLine.analysis(srcFile, desFile);
		
		//根据netname和Line的映射解析出IP地址，并生成各线路的ACL文件
		//IP netname文件
		File ipLineFile = new File("src/main/resources/ip-line");
		//保存未知IP的地址
		File unknownFile = new File("src/main/resources/ip-line-unknown");
		//输出各线路的acl文件到src/main/resources/data/目录下
		ObtainResult.getSaveIpInfo(ipLineFile,unknownFile,"src/main/resources/data/");
	}
}
