package server;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPserver_file {
	//�������ļ�
	public static File createFile(String pathName){
	       File file = new File(pathName);
	       if(file.exists()&&file.isFile()){
	          System.out.println("ʹ���Ѵ��ڵ��ļ�");
	       }
	       else{
	          try {
	       file.createNewFile();
	       } catch (IOException e) {
	           e.printStackTrace();
	         }
	     }
	      return file;
	}
    /**
     * Socket�����
     */
	public static void main(String[] args) {
        try {
            ServerSocket serverSocket=new ServerSocket(8888);
            System.out.println("��������������ȴ��ͻ�������..");
            Socket socket=serverSocket.accept();//���������ܵ����׽��ֵ�����,����һ��Socket����
            String pathName = "F:\\newfile.mp4"; //���ı��������������ݡ�
            File file = createFile(pathName);
            
            //��������������Ϳͻ�������
            InputStream inputStream=socket.getInputStream();//�õ�һ�������������տͻ��˴��ݵ���Ϣ
            FileOutputStream fos = new FileOutputStream(file);
            System.out.println("��ʼ������...");
            byte[] buffer = new byte[1024];
            int len = 0;
            int dataCounter = 0;
            while((len=inputStream.read(buffer))!=-1){
               fos.write(buffer, 0, len);
               dataCounter+=len;
            }
            fos.close();
            inputStream.close();
            socket.close();
            serverSocket.close();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
            System.out.println("�������ʱ��Ϊ��"+df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
            System.out.println("���ݴ�����ϣ���СΪ" + dataCounter + "�ֽ�");
         } catch (IOException e) {
            e.printStackTrace();
         }
       }
  }

