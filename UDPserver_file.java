package server;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class UDPserver_file {
	public static void main(String []args) throws Exception {
	      DatagramSocket serverSocket = new DatagramSocket(8888);
	      System.out.println("服务端已启动，等待客户端连接..");
	      byte[] buffer = new byte[1024]; 
	      while(true) {
	    	  //接收文件长度（字节数）
	    	  byte[] fileLenBuf = new byte[12];// 可以传输1T的文件???
	    	  DatagramPacket packet = new DatagramPacket(fileLenBuf,fileLenBuf.length);//占位符???
	    	  serverSocket.receive(packet);
	    	  System.out.println(new String(packet.getData()));
	    	  String str_fileLen = new String(fileLenBuf, 0,packet.getLength());
	    	  int fileLen = Integer.parseInt(str_fileLen);
	    	  //接收文件内容
	    	  DatagramPacket packet_file = new DatagramPacket(buffer, 0,buffer.length);
	    	  int packetNum = fileLen/buffer.length;//packet数
	  		  int lastSize = fileLen%buffer.length;//最后一个packet的长度
	  		  File file = new File("F:\\newfilebyUDP.mp4");
	  		  FileOutputStream fos = new FileOutputStream(file);// 从内存取出存入文件
	  		  for(int i=0;i<packetNum;i++) {
	  			packet_file = new DatagramPacket(buffer,0,buffer.length);
	  			serverSocket.receive(packet_file);// 通过serverSocket接收数据
	  			fos.write(buffer, 0, 1024);// 写入文件
	  		  }
	  		  	// 接收最后一点点零头
		  		packet_file = new DatagramPacket(buffer, 0, lastSize);
		  		serverSocket.receive(packet_file);// 通过套接字接收数据
		  		fos.write(buffer, 0, lastSize);// 写入文件
		  		fos.close();
		  		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	            System.out.println("传输结束时间为："+df.format(new Date()));// new Date()为获取当前系统时间
	            System.out.println("数据传输完毕，大小为" + fileLen + "字节");
	      } 
	}
}
	
	
