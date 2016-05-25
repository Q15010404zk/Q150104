import java.awt.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;

public class Main{
	public static void main(String[] args) {
		MainFrame f = new MainFrame();
	}
}

class MainFrame extends JFrame {
	Vector stuList=new Vector();
	
	MainFrame(){
		super("专柜人员销售情况查询系统");
		
		this.setTitle("专柜人员销售情况查询系统");
		this.setSize(700,320);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.readStuFile();
		this.showStuPanel();
		this.setVisible(true);

		this.setResizable(false);
		this.setSize(700,320);
		this.setLocationRelativeTo(this.getOwner());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	void readStuFile(){
		try {
			FileInputStream fis;
			fis = new FileInputStream("Staff.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Student stu=new Student();
				String[] temp=s.split(" ");
				stu.setStuNo(temp[0]);
				stu.setStuName(temp[1]);
				stu.setStuSex(temp[2]);
				stu.setStuBirthday(temp[3]);
				stuList.add(stu);
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	
	void showStuPanel(){
		StuPanel stuPanel=new StuPanel();
		stuPanel.stuList=this.stuList;
		this.add(stuPanel,BorderLayout.CENTER);
		stuPanel.showStudent(0);
		this.setVisible(true);
	}
}

class StuPanel extends JPanel {
	private JTextField stuNo=new JTextField();											//学号
	private JTextField stuName=new JTextField();										//姓名
	private JTextField stuSex=new JTextField();											//性别
	private JTextField stuBirthday=new JTextField();									//出生日期
	Vector stuList=new Vector();
	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];
	
	StuPanel(){
		this.setLayout(null);
		//显示学号
		JLabel lb1=new JLabel("学号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		stuNo.setBounds(100,10, 100, 20);
		this.add(stuNo);
		//显示姓名
		JLabel lb2=new JLabel("姓名：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		stuName.setBounds(100,60, 100, 20);
		this.add(stuName);
		//显示性别
		JLabel lb3=new JLabel("性别：");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		stuSex.setBounds(100,110, 100, 20);
		this.add(stuSex);
		//显示出生日期
		JLabel lb4=new JLabel("生日：");
		lb4.setBounds(30, 160, 100, 20);
		this.add(lb4);
		stuBirthday.setBounds(100,160, 100, 20);
		this.add(stuBirthday);
		//显示控制按钮
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			this.add(btn[i]);
		}
	}
	
	void showStudent(int offset){
		Student stu=(Student) stuList.get(offset);
		this.stuNo.setText(stu.getStuNo());
		this.stuName.setText(stu.getStuName());
		this.stuSex.setText(stu.getStuSex());
		this.stuBirthday.setText(stu.getStuBirthday());
	}
}

class Student {
	private String stuNo;											//学号
	private String stuName;										//姓名
	private String stuSex;											//性别
	private String stuBirthday;									//出生日期
	
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuSex() {
		return stuSex;
	}
	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}
	public String getStuBirthday() {
		return stuBirthday;
	}
	public void setStuBirthday(String stuBirthday) {
		this.stuBirthday = stuBirthday;
	}
}
