package server;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class UDPserver_file {
	public static void main(String []args) throws Exception {
	      DatagramSocket serverSocket = new DatagramSocket(8888);
	      System.out.println("��������������ȴ��ͻ�������..");
	      byte[] buffer = new byte[1024]; 
	      while(true) {
	    	  //�����ļ����ȣ��ֽ�����
	    	  byte[] fileLenBuf = new byte[12];// ���Դ���1T���ļ�???
	    	  DatagramPacket packet = new DatagramPacket(fileLenBuf,fileLenBuf.length);//ռλ��???
	    	  serverSocket.receive(packet);
	    	  System.out.println(new String(packet.getData()));
	    	  String str_fileLen = new String(fileLenBuf, 0,packet.getLength());
	    	  int fileLen = Integer.parseInt(str_fileLen);
	    	  //�����ļ�����
	    	  DatagramPacket packet_file = new DatagramPacket(buffer, 0,buffer.length);
	    	  int packetNum = fileLen/buffer.length;//packet��
	  		  int lastSize = fileLen%buffer.length;//���һ��packet�ĳ���
	  		  File file = new File("F:\\newfilebyUDP.mp4");
	  		  FileOutputStream fos = new FileOutputStream(file);// ���ڴ�ȡ�������ļ�
	  		  for(int i=0;i<packetNum;i++) {
	  			packet_file = new DatagramPacket(buffer,0,buffer.length);
	  			serverSocket.receive(packet_file);// ͨ��serverSocket��������
	  			fos.write(buffer, 0, 1024);// д���ļ�
	  		  }
	  		  	// �������һ�����ͷ
		  		packet_file = new DatagramPacket(buffer, 0, lastSize);
		  		serverSocket.receive(packet_file);// ͨ���׽��ֽ�������
		  		fos.write(buffer, 0, lastSize);// д���ļ�
		  		fos.close();
		  		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	            System.out.println("�������ʱ��Ϊ��"+df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
	            System.out.println("���ݴ�����ϣ���СΪ" + fileLen + "�ֽ�");
	      } 
	}
}
	
	
