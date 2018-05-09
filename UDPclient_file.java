package client;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class UDPclient_file {
	public static void main(String args[]) throws Exception {
		DatagramSocket clientSocket = new DatagramSocket(); //建立数据报套口
		InetAddress IPAddress = InetAddress.getByName("localhost"); //确定服务器传输端口
		String pathName = "F:\\data.mp4";
        File file = new File(pathName);
		FileInputStream fileStream = new FileInputStream(file);//将文件读入内存
		byte[] buffer = new byte[1024];
		//发送文件长度
		int fileLen = fileStream.available();
		String filelenStr=""+fileLen;
		byte[] fileLenBuf=filelenStr.getBytes();
		DatagramPacket packet = new DatagramPacket(fileLenBuf,fileLenBuf.length,IPAddress,8888);
		clientSocket.send(packet);
		//发送文件主体
		int packetNum = fileLen/buffer.length;//packet数
		int lastSize = fileLen%buffer.length;//最后一个packet的长度
		System.out.println("连接成功，开始传输数据...");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("传输开始时间为："+df.format(new Date()));// new Date()为获取当前系统时间
		for(int i=0;i<packetNum;i++) {
			fileStream.read(buffer);
			DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, IPAddress, 8888);
			clientSocket.send(sendPacket);
			//Thread.sleep(1);
		}
		//发送最后一个packet的内容
		fileStream.read(buffer,0,lastSize);
		packet = new DatagramPacket(buffer, buffer.length, IPAddress, 8888);
		clientSocket.send(packet);
		//Thread.sleep(1);
		//关闭fileStream和clientSocket
		fileStream.close();
		clientSocket.close();
	} 
}
