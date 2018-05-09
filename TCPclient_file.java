package client;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class TCPclient_file {
	public static boolean findFile(String pathName) {
        File file = new File(pathName);
        if (file.isFile())
            return true;
        else
            return false;
    }
	public static void main(String[] args) {
		String pathName = "F:\\data.mp4";
        File file = new File(pathName);
		 try {
	            //����Socket����
	            Socket socket=new Socket("localhost",8888);
	            OutputStream output = socket.getOutputStream();
	            FileInputStream inputstream = new FileInputStream(file);
	            System.out.println("���ӳɹ�����ʼ��������...");
	            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
	            System.out.println("���俪ʼʱ��Ϊ��"+df.format(new Date()));// new Date()Ϊ��ȡ��ǰϵͳʱ��
	            byte[] buffer = new byte[1024];
	            int len = 0;
	            int dataCounter = 0;
	            while ((len = inputstream.read(buffer)) != -1) {
	                output.write(buffer, 0, len);
	                dataCounter += len;
	            }
	            output.flush();
	            inputstream.close();
	            output.close();
	            socket.close();
	            System.out.println("���ݴ�����ϣ�������" + dataCounter + "�ֽڡ�");
	        } catch (UnknownHostException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
}