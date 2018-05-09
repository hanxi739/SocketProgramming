package client;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
public class UDPclient_file {
	public static void main(String args[]) throws Exception {
		DatagramSocket clientSocket = new DatagramSocket(); //�������ݱ��׿�
		InetAddress IPAddress = InetAddress.getByName("localhost"); //ȷ������������˿�
		String pathName = "F:\\data.mp4";
        File file = new File(pathName);
		FileInputStream fileStream = new FileInputStream(file);//���ļ������ڴ�
		byte[] buffer = new byte[1024];
		//�����ļ�����
		int fileLen = fileStream.available();
		String filelenStr=""+fileLen;
		byte[] fileLenBuf=filelenStr.getBytes();
		DatagramPacket packet = new DatagramPacket(fileLenBuf,fileLenBuf.length,IPAddress,8888);
		clientSocket.send(packet);
		//�����ļ�����
		int packetNum = fileLen/buffer.length;//packet��
		int lastSize = fileLen%buffer.length;//���һ��packet�ĳ���
		System.out.println("���ӳɹ�����ʼ��������...");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
        System.out.println("���俪ʼʱ��Ϊ��"+df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
		for(int i=0;i<packetNum;i++) {
			fileStream.read(buffer);
			DatagramPacket sendPacket = new DatagramPacket(buffer, buffer.length, IPAddress, 8888);
			clientSocket.send(sendPacket);
			//Thread.sleep(1);
		}
		//�������һ��packet������
		fileStream.read(buffer,0,lastSize);
		packet = new DatagramPacket(buffer, buffer.length, IPAddress, 8888);
		clientSocket.send(packet);
		//Thread.sleep(1);
		//�ر�fileStream��clientSocket
		fileStream.close();
		clientSocket.close();
	} 
}
