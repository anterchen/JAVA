package java练习;

import java.awt.Button;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class IOtest extends Frame implements ActionListener{
	private static final String host_file_path = "%SystemRoot%/system32/drivers/etc/hosts";
	private Frame f;
	private TextArea ta;   //ta用于显示打开的内容
	private Button btn;
	private FileDialog fd;
	private File file1 = null;
	//构造函数开始
	public IOtest(){
		btn = new Button("打开");
		ta = new TextArea(46,78);
		btn.addActionListener(this);//给按钮添加事件监听器
		try {   //以缓冲区方式读取文件内容

			file1 = new File(host_file_path);
			FileReader fr = new FileReader(file1);
			BufferedReader br = new BufferedReader(fr);
			String aline;
			while ((aline=br.readLine()) != null)//按行读取文本
				ta.append(aline+"\r\n");
			fr.close();
			br.close();
		}
		catch (IOException ioe){

			System.out.println(ioe);
		}
	}
	//给按钮添加行为
	public void actionPerformed(ActionEvent e){
		if (e.getSource()==btn) { //单击打开按钮时   
			fd = new FileDialog(f,"Open",FileDialog.LOAD);
			fd.setVisible(true);   //创建并显示打开文件对话框

			//if ((fd.getDirectory()!=null) && (fd.getFile()!=null)) {

			try {   //以缓冲区方式读取文件内容

				file1 = new File(fd.getDirectory(),fd.getFile());
				FileReader fr = new FileReader(file1);
				BufferedReader br = new BufferedReader(fr);
				String aline;
				while ((aline=br.readLine()) != null)//按行读取文本
					ta.append(aline+"\r\n");
				fr.close();
				br.close();
			}
			catch (IOException ioe){

				System.out.println(ioe);
			}
		}
		//}

	}
	public void show()
	{
		f = new Frame("hosts");         //初始化对象f
		f.setSize(600,800);                //设置窗口f的大小
		//设置布局管理器为FlowLayout
		f.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		f.add(ta);   		
		f.add(btn);   
		//为窗口f添加WindowListener监听器
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent evt){ //实现windowClosing方法
				f.setVisible(false);         //设置窗口f不可见
				f.dispose();            //释放窗口及其子组件的屏幕资源
				System.exit(0);            //退出程序
			}
		});
		f.setLocation(100,10);
		f.setVisible(true);                //设置窗口f可视
	}
}