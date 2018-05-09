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
	            //创建Socket对象
	            Socket socket=new Socket("localhost",8888);
	            OutputStream output = socket.getOutputStream();
	            FileInputStream inputstream = new FileInputStream(file);
	            System.out.println("连接成功，开始传输数据...");
	            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	            System.out.println("传输开始时间为："+df.format(new Date()));// new Date()为获取当前系统时间
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
	            System.out.println("数据传送完毕，共传送" + dataCounter + "字节。");
	        } catch (UnknownHostException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
}