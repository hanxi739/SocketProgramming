package server;
import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPserver_file {
	//创建新文件
	public static File createFile(String pathName){
	       File file = new File(pathName);
	       if(file.exists()&&file.isFile()){
	          System.out.println("使用已存在的文件");
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
     * Socket服务端
     */
	public static void main(String[] args) {
        try {
            ServerSocket serverSocket=new ServerSocket(8888);
            System.out.println("服务端已启动，等待客户端连接..");
            Socket socket=serverSocket.accept();//侦听并接受到此套接字的连接,返回一个Socket对象
            String pathName = "F:\\newfile.mp4"; //空文本，用来接收数据。
            File file = createFile(pathName);
            
            //根据输入输出流和客户端连接
            InputStream inputStream=socket.getInputStream();//得到一个输入流，接收客户端传递的信息
            FileOutputStream fos = new FileOutputStream(file);
            System.out.println("开始接数据...");
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
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            System.out.println("传输结束时间为："+df.format(new Date()));// new Date()为获取当前系统时间
            System.out.println("数据传输完毕，大小为" + dataCounter + "字节");
         } catch (IOException e) {
            e.printStackTrace();
         }
       }
  }

