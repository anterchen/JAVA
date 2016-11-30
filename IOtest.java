package java��ϰ;

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
	private TextArea ta;   //ta������ʾ�򿪵�����
	private Button btn;
	private FileDialog fd;
	private File file1 = null;
	//���캯����ʼ
	public IOtest(){
		btn = new Button("��");
		ta = new TextArea(46,78);
		btn.addActionListener(this);//����ť����¼�������
		try {   //�Ի�������ʽ��ȡ�ļ�����

			file1 = new File(host_file_path);
			FileReader fr = new FileReader(file1);
			BufferedReader br = new BufferedReader(fr);
			String aline;
			while ((aline=br.readLine()) != null)//���ж�ȡ�ı�
				ta.append(aline+"\r\n");
			fr.close();
			br.close();
		}
		catch (IOException ioe){

			System.out.println(ioe);
		}
	}
	//����ť�����Ϊ
	public void actionPerformed(ActionEvent e){
		if (e.getSource()==btn) { //�����򿪰�ťʱ   
			fd = new FileDialog(f,"Open",FileDialog.LOAD);
			fd.setVisible(true);   //��������ʾ���ļ��Ի���

			//if ((fd.getDirectory()!=null) && (fd.getFile()!=null)) {

			try {   //�Ի�������ʽ��ȡ�ļ�����

				file1 = new File(fd.getDirectory(),fd.getFile());
				FileReader fr = new FileReader(file1);
				BufferedReader br = new BufferedReader(fr);
				String aline;
				while ((aline=br.readLine()) != null)//���ж�ȡ�ı�
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
		f = new Frame("hosts");         //��ʼ������f
		f.setSize(600,800);                //���ô���f�Ĵ�С
		//���ò��ֹ�����ΪFlowLayout
		f.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		f.add(ta);   		
		f.add(btn);   
		//Ϊ����f���WindowListener������
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent evt){ //ʵ��windowClosing����
				f.setVisible(false);         //���ô���f���ɼ�
				f.dispose();            //�ͷŴ��ڼ������������Ļ��Դ
				System.exit(0);            //�˳�����
			}
		});
		f.setLocation(100,10);
		f.setVisible(true);                //���ô���f����
	}
}