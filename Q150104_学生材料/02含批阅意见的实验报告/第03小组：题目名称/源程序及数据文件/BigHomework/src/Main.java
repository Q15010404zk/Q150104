import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import java.text.DecimalFormat;

import javax.swing.*;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener {
	Vector<Student> stuList=new Vector<Student>();
	Vector<Course> courseList=new Vector<Course>();
	Vector<Score> scoreList=new Vector<Score>();
	JMenu Menu0=new JMenu("文件");
	JMenuItem menuOpen=new JMenuItem("打开数据文件");
	JMenuItem menuSave=new JMenuItem("保存数据文件");
	JMenuItem menuChangePwd=new JMenuItem("更改管理密码");
	JMenuItem menuExit=new JMenuItem("退出系统");
	JMenu Menu1=new JMenu("信息维护");
	JMenuItem stuMenu=new JMenuItem("职员信息维护");
	JMenuItem courseMenu=new JMenuItem("商品信息维护");
	JMenuItem scoreMenu=new JMenuItem("销售信息维护");
	JMenu Menu2=new JMenu("数据查询");
	JMenuItem stuQueryMenu=new JMenuItem("职员信息查询");
	JMenuItem scoreQueryMenu=new JMenuItem("销售信息查询");
	
	public static void main(String[] args) {
		LoginFrame f=new LoginFrame();
		f.setVisible(true);
	}
	
	Main(){
		super();
		JMenuBar menuBar=new JMenuBar();
		this.setJMenuBar(menuBar);
		stuMenu.addActionListener(this);
		stuMenu.setEnabled(false);
		Menu1.add(stuMenu);
		courseMenu.addActionListener(this);
		courseMenu.setEnabled(false);
		Menu1.add(courseMenu);
		scoreMenu.addActionListener(this);
		scoreMenu.setEnabled(false);
		Menu1.add(scoreMenu);
		menuOpen.addActionListener(this);
		Menu0.add(menuOpen);
		menuSave.addActionListener(this);
		menuSave.setEnabled(false);
		Menu0.add(menuSave);
		Menu0.addSeparator();
		Menu0.add(menuChangePwd);
		menuChangePwd.addActionListener(this);
		Menu0.addSeparator();
		menuExit.addActionListener(this);
		Menu0.add(menuExit);
		stuQueryMenu.addActionListener(this);
		stuQueryMenu.setEnabled(false);
		Menu2.add(stuQueryMenu);
		scoreQueryMenu.addActionListener(this);
		scoreQueryMenu.setEnabled(false);
		Menu2.add(scoreQueryMenu);
		menuBar.add(Menu0);
		menuBar.add(Menu1);
		menuBar.add(Menu2);
		
		this.setTitle("专柜人员销售情况查询系统");
		this.setResizable(false);
		this.setSize(700,320);
		this.setLocationRelativeTo(this.getOwner());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void readStuFile(){
		try {
			FileInputStream fis = new FileInputStream("Staff.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Student stu=new Student();
				String[] temp=s.split(",");
				stu.setStuNo(temp[0]);
				stu.setStuName(temp[1]);
				stu.setStuSex(temp[2]);
				stu.setStuBirthday(temp[3]);
				stuList.add(stu);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void writeStuFile(){
		try {
			FileOutputStream fos = new FileOutputStream("Staff.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<stuList.size();i++){
				Student stu=(Student)stuList.get(i);
				writer.write(stu.getStuNo()+" "+stu.getStuName()+" "+stu.getStuSex()+" "+stu.getStuBirthday()+"\n");
			}
			writer.close();
			dos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void readCourseFile(){
		try {
			FileInputStream fis = new FileInputStream("goods.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Course course=new Course();
				String[] temp=s.split(",");
				course.setCourseNo(temp[0]);
				course.setCourseName(temp[1]);
				course.setCourseScore(Double.parseDouble(temp[2]));
				course.setTeacher(temp[3]);
				courseList.add(course);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void writeCourseFile(){
		try {
			FileOutputStream fos = new FileOutputStream("goods.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<courseList.size();i++){
				Course course=(Course)courseList.get(i);
				writer.write(course.getCourseNo()+" "+course.getCourseName()+" "+course.getCourseScore()+" "+course.getTeacher()+"\n");
			}
			writer.close();
			dos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void readScoreFile(){
		try{
			FileInputStream fis;
			fis = new FileInputStream("perform.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			while((s=reader.readLine())!=null)
			{
				Score score=new Score();
				String[] temp=s.split(",");
				score.setStuNo(temp[0]);
				score.setCourseNo(temp[1]);
				score.setScore(Double.parseDouble(temp[2]));
				scoreList.add(score);
			}
			reader.close();
			dis.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	void writeScoreFile(){
		try {
			FileOutputStream fos = new FileOutputStream("perform.txt");
			OutputStreamWriter dos=new OutputStreamWriter(fos);
			BufferedWriter writer=new BufferedWriter(dos);
			for(int i=0;i<scoreList.size();i++){
				Score score=(Score)scoreList.get(i);
				writer.write(score.getStuNo()+" "+score.getCourseNo()+" "+String.format("%.1f", score.getScore())+"\n");
			}
			writer.close();
			dos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
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
	
	void showCoursePanel(){
		CoursePanel coursePanel=new CoursePanel();
		coursePanel.courseList=this.courseList;
		this.add(coursePanel,BorderLayout.CENTER);
		coursePanel.showCourse(0);
		this.setVisible(true);
	}
	
	void showScorePanel(){
		ScorePanel scorePanel=new ScorePanel();
		scorePanel.scoreList=this.scoreList;
		this.add(scorePanel,BorderLayout.CENTER);
		scorePanel.showScore(0);
		this.setVisible(true);
	}
	
	void showStuQueryPanel(){
		StuQueryPanel stuQPanel=new StuQueryPanel();
		stuQPanel.stuList=this.stuList;
		stuQPanel.scoreList=this.scoreList;
		stuQPanel.courseList=this.courseList;
		this.add(stuQPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}

	void showScoreQueryPanel(){
		ScoreQueryPanel ScoreQPanel=new ScoreQueryPanel();
		ScoreQPanel.stuList=this.stuList;
		ScoreQPanel.scoreList=this.scoreList;
		ScoreQPanel.courseList=this.courseList;
		this.add(ScoreQPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==stuMenu){
			this.getContentPane().removeAll();
			this.showStuPanel();
		}
		if(e.getSource()==courseMenu){
			this.getContentPane().removeAll();
			this.showCoursePanel();
		}
		if(e.getSource()==scoreMenu){
			this.getContentPane().removeAll();
			this.showScorePanel();
		}
		if(e.getSource()==stuQueryMenu){
			this.getContentPane().removeAll();
			this.showStuQueryPanel();
		}
		if(e.getSource()==scoreQueryMenu){
			this.getContentPane().removeAll();
			this.showScoreQueryPanel();
		}
		if(e.getSource()==menuOpen){
			this.stuList.removeAllElements();
			this.scoreList.removeAllElements();
			this.courseList.removeAllElements();
			this.readStuFile();
			stuMenu.setEnabled(true);
			this.readCourseFile();
			courseMenu.setEnabled(true);
			this.readScoreFile();
			scoreMenu.setEnabled(true);
			menuSave.setEnabled(true);
			stuQueryMenu.setEnabled(true);
			scoreQueryMenu.setEnabled(true);
			JOptionPane.showMessageDialog(null, "您已经成功打开数据：\n职员信息"+stuList.size()+"条\n商品信息"+courseList.size()+"条\n成绩信息"+scoreList.size()+"条", "打开", JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==menuSave){
			this.writeStuFile();
			this.writeScoreFile();
			menuSave.setEnabled(true);
			scoreMenu.setEnabled(true);
			JOptionPane.showMessageDialog(null, "您已经成功保存数据：\n职员信息"+stuList.size()+"条\n商品信息"+courseList.size()+"条\n成绩信息"+scoreList.size()+"条", "保存", JOptionPane.INFORMATION_MESSAGE);
		}
		if(e.getSource()==menuChangePwd){
			String s=JOptionPane.showInputDialog(null, "请输入新密码", "管理员密码修改", JOptionPane.PLAIN_MESSAGE);
			if(s==null)return;
			s=s.trim();
			if(s.length()==0){
				JOptionPane.showMessageDialog(null, "密码不能为空！", "专柜人员销售情况查询系统", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String clearText = 	"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String cipherText=	"UADKIy3FxgVkl5iZzWuGd1HNhOCtvjJ2pEn6Yw7PqrcQReB8Mfm0STsLX9a4ob";
			String resultText=		"";
			for(int i=0;i<s.length();i++){
				char c=s.charAt(i);
				if(clearText.indexOf(""+c)==-1){
					JOptionPane.showMessageDialog(null, "密码中包含非法字符", "专柜人员销售情况查询系统", JOptionPane.ERROR_MESSAGE);
					return;
				}else{
					resultText+=""+cipherText.charAt(clearText.indexOf(""+c));
				}
			}
			try {
				FileOutputStream fos = new FileOutputStream("Password.txt");
				OutputStreamWriter dos=new OutputStreamWriter(fos);
				BufferedWriter writer=new BufferedWriter(dos);
				writer.write(resultText);
				writer.close();dos.close();fos.close();
				JOptionPane.showMessageDialog(null, "密码修改成功！", "专柜人员销售情况查询系统", JOptionPane.INFORMATION_MESSAGE);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}			
		if(e.getSource()==menuExit){
			System.exit(0);
		}
	}
}

@SuppressWarnings("serial")
class ScorePanel extends JPanel implements ActionListener{
	private JTextField stuNo=new JTextField();							//编号
	private JTextField courseNo=new JTextField();						///商品代码
	private JTextField Score=new JTextField();							//销售情况
	Vector<Score> scoreList=new Vector<Score>();
	int count=0,current=0,inserting=0;
	
	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];
	
	ScorePanel(){
		this.setLayout(null);
		//显示学号
		JLabel lb1=new JLabel("编号：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		stuNo.setBounds(100,10, 100, 20);
		this.add(stuNo);
		//显示课程代号
		JLabel lb2=new JLabel("商品代码：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		courseNo.setBounds(100,60, 100, 20);
		this.add(courseNo);
		//显示成绩
		JLabel lb3=new JLabel("销售情况：");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		Score.setBounds(100,110, 100, 20);
		this.add(Score);
		
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
		
	}
	
	void showScore(int offset){
		Score score=(Score)scoreList.get(offset);
		this.stuNo.setText(score.getStuNo());
		this.courseNo.setText(""+score.getCourseNo());
		this.Score.setText(""+score.getScore());
	}

	public void actionPerformed(ActionEvent e) {
		count=this.scoreList.size();
		if(e.getSource()==this.btn[0]){
			this.showScore(0);
			current=0;
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showScore(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showScore(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showScore(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.stuNo.setText("");
				this.courseNo.setText("");
				this.Score.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}
			else{
				Score score=new Score();
				score.setStuNo(this.stuNo.getText().trim());
				score.setCourseNo(this.courseNo.getText().trim());
				score.setScore(Double.parseDouble(this.Score.getText().trim()));
				scoreList.add(score);
				count++;
				current=count-1;
				btn[4].setText("添加");
				this.inserting=0;
			}
			for(int i=0;i<btn.length;i++){
				if(i==4||i==5) continue;
				btn[i].setEnabled(!btn[i].isEnabled());
			}			
		}
		if(e.getSource()==this.btn[5]){
			if(this.inserting==0){
				Score score=(Score)scoreList.get(current);
				score.setStuNo(this.stuNo.getText().trim());
				score.setCourseNo(this.courseNo.getText().trim());
				score.setScore(Double.parseDouble(this.Score.getText().trim()));
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}	
				this.inserting=0;
				this.showScore(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			scoreList.remove(current);
			count--;
			if(count==0){
				this.stuNo.setText("");
				this.courseNo.setText("");
				this.Score.setText("");
			}else{
				if(current>count-1){
					this.showScore(current-1);
					current=current-1;
				}
				else
					this.showScore(current);
			}
		}
		this.repaint();
	}
}

@SuppressWarnings("serial")
class StuPanel extends JPanel implements ActionListener {
	private JTextField stuNo=new JTextField();											//编号
	private JTextField stuName=new JTextField();										//姓名
	private JTextField stuSex=new JTextField();											//性别
	private JTextField stuBirthday=new JTextField();								//出生日期
	Vector<Student> stuList=new Vector<Student>();
	int count=0,current=0,inserting=0;
	
	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];
	
	StuPanel(){
		this.setLayout(null);
		//显示学号
		JLabel lb1=new JLabel("编号：");
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
		
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
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

	public void actionPerformed(ActionEvent e) {
		count=this.stuList.size();
		if(e.getSource()==this.btn[0]){
			this.showStudent(0);
			current=0;
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showStudent(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showStudent(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showStudent(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.stuNo.setText("");
				this.stuName.setText("");
				this.stuSex.setText("");
				this.stuBirthday.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}else{
				Student stu=new Student();
				stu.setStuNo(this.stuNo.getText().trim());
				stu.setStuName(this.stuName.getText().trim());
				stu.setStuSex(this.stuSex.getText().trim());
				stu.setStuBirthday(this.stuBirthday.getText().trim());
				stuList.add(stu);
				count++;
				current=count-1;
				btn[4].setText("添加");
				this.inserting=0;
			}
			for(int i=0;i<btn.length;i++){
				if(i==4||i==5) continue;
				btn[i].setEnabled(!btn[i].isEnabled());
			}	
		}

		if(e.getSource()==this.btn[5]){
			if(this.inserting==0){
				Student stu=(Student)stuList.get(current);
				stu.setStuNo(this.stuNo.getText().trim());
				stu.setStuName(this.stuName.getText().trim());
				stu.setStuSex(this.stuSex.getText().trim());
				stu.setStuBirthday(this.stuBirthday.getText().trim());
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}
				this.inserting=0;
				this.showStudent(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			stuList.remove(current);
			count--;
			if(count==0){
				this.stuNo.setText("");
				this.stuName.setText("");
				this.stuSex.setText("");
				this.stuBirthday.setText("");
			}else{
				if(current>count-1){
					this.showStudent(current-1);
					current=current-1;
				}
				else
					this.showStudent(current);
			}
		}
		this.repaint();
	}
}

@SuppressWarnings("serial")
class CoursePanel extends JPanel implements ActionListener {
	private JTextField courseNo=new JTextField();											///商品代码
	private JTextField courseName=new JTextField();										//商品名称
	private JTextField courseScore=new JTextField();										//商品类别
	private JTextField teacher=new JTextField();												//商品品牌
	Vector<Course> courseList=new Vector<Course>();
	int count=0,current=0,inserting=0;
	
	private String[] btnStr={"第一个","上一个","下一个","最后一个","添加","修改","删除"};
	private JButton[] btn= new JButton[btnStr.length];
	
	CoursePanel(){
		this.setLayout(null);
		//显示课程代号
		JLabel lb1=new JLabel("商品代码：");
		lb1.setBounds(30, 10, 100, 20);
		this.add(lb1);
		courseNo.setBounds(100,10, 100, 20);
		this.add(courseNo);
		//显示课程名
		JLabel lb2=new JLabel("商品名：");
		lb2.setBounds(30, 60, 100, 20);
		this.add(lb2);
		courseName.setBounds(100,60, 100, 20);
		this.add(courseName);
		//显示学分
		JLabel lb3=new JLabel("商品类别：");
		lb3.setBounds(30, 110, 100, 20);
		this.add(lb3);
		courseScore.setBounds(100,110, 100, 20);
		this.add(courseScore);
		//显示任课教师
		JLabel lb4=new JLabel("商品品牌：");
		lb4.setBounds(30, 160, 100, 20);
		this.add(lb4);
		teacher.setBounds(100,160, 100, 20);
		this.add(teacher);
		
		for(int i=0;i<btn.length;i++){
			btn[i]=new JButton(btnStr[i]);
			btn[i].setBounds(30+i*90, 210, 90, 30);
			btn[i].addActionListener(this);
			this.add(btn[i]);
		}
	}
	
	void showCourse(int offset){
		Course course=(Course) courseList.get(offset);
		this.courseNo.setText(course.getCourseNo());
		this.courseName.setText(course.getCourseName());
		this.courseScore.setText(""+course.getCourseScore());
		this.teacher.setText(course.getTeacher());
	}

	public void actionPerformed(ActionEvent e) {
		count=this.courseList.size();
		if(e.getSource()==this.btn[0]){
			this.showCourse(0);
			current=0;
		}
		if(e.getSource()==this.btn[1]&&current>0){
			this.showCourse(current-1);
			current=current-1;
		}
		if(e.getSource()==this.btn[2]&&current<count-1){
			this.showCourse(current+1);
			current=current+1;
		}
		if(e.getSource()==this.btn[3]){
			this.showCourse(count-1);
			current=count-1;
		}
		if(e.getSource()==this.btn[4]){
			if(this.inserting==0){
				this.courseNo.setText("");
				this.courseName.setText("");
				this.courseScore.setText("");
				this.teacher.setText("");
				btn[4].setText("保存");
				btn[5].setText("取消");
				this.inserting=1;
			}else{
				Course course=new Course();
				course.setCourseNo(this.courseNo.getText().trim());
				course.setCourseName(this.courseName.getText().trim());
				course.setCourseScore(Double.parseDouble(this.courseScore.getText().trim()));
				course.setTeacher(this.teacher.getText().trim());
				courseList.add(course);
				count++;
				current=count-1;
				btn[4].setText("添加");
				this.inserting=0;
			}
			for(int i=0;i<btn.length;i++){
				if(i==4||i==5) continue;
				btn[i].setEnabled(!btn[i].isEnabled());
			}	
		}

		if(e.getSource()==this.btn[5]){
			if(this.inserting==0){
				Course course=(Course)courseList.get(current);
				course.setCourseNo(this.courseNo.getText().trim());
				course.setCourseName(this.courseName.getText().trim());
				course.setCourseScore(Double.parseDouble(this.courseScore.getText().trim()));
				course.setTeacher(this.teacher.getText().trim());
			}else{
				btn[4].setText("添加");
				btn[5].setText("修改");
				for(int i=0;i<btn.length;i++){
					if(i==4||i==5) continue;
					btn[i].setEnabled(!btn[i].isEnabled());
				}
				this.inserting=0;
				this.showCourse(current);
			}
		}
		if(e.getSource()==this.btn[6]){
			if(count==0)
				return;
			courseList.remove(current);
			count--;
			if(count==0){
				this.courseNo.setText("");
				this.courseName.setText("");
				this.courseScore.setText("");
				this.teacher.setText("");
			}else{
				if(current>count-1){
					this.showCourse(current-1);
					current=current-1;
				}
				else
					this.showCourse(current);
			}
		}
		this.repaint();
	}
}

@SuppressWarnings("serial")
class StuQueryPanel extends JPanel implements ActionListener{
	JComboBox searchBy=new JComboBox();
	JTextField keyword=new JTextField(10);
	JTextArea result=new JTextArea();
	Vector<Student> stuList=new Vector<Student>();
	Vector<Score> scoreList=new Vector<Score>();
	Vector<Course> courseList=new Vector<Course>();
	StuQueryPanel(){
		this.setLayout(new BorderLayout());
		JPanel subPanel=new JPanel();
		subPanel.setLayout(new FlowLayout());
		searchBy.addItem("按编号查询");
		searchBy.addItem("按姓名查询");		
		subPanel.add(searchBy);
		subPanel.add(keyword);
		JButton btn=new JButton("查询");
		btn.addActionListener(this);
		subPanel.add(btn);
		this.add(subPanel, BorderLayout.NORTH);
		JScrollPane scrollPane=new JScrollPane(result);
		this.add(scrollPane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent arg0) {
		String str="编号\t姓名\t性别\t商品名称：销售额\n";
		for(int i=0;i<stuList.size();i++){
			Student stu=(Student)this.stuList.get(i);
			if(this.searchBy.getSelectedIndex()==0){
				if(stu.getStuNo().startsWith(this.keyword.getText().trim())||this.keyword.getText().trim().equals("")){
					str=str+stu.getStuNo()+"\t"+stu.getStuName()+"\t"+stu.getStuSex()+"\t";
				}else
					continue;
			}if(this.searchBy.getSelectedIndex()==1){
				if(stu.getStuName().startsWith(this.keyword.getText().trim())||this.keyword.getText().trim().equals("")){
					str=str+stu.getStuNo()+"\t"+stu.getStuName()+"\t"+stu.getStuSex()+"\t";
				}else
					continue;
			}
			for(int j=0;j<scoreList.size();j++){
				Score score=(Score)scoreList.get(j);
				if(score.getStuNo().equals(stu.getStuNo())){
					str=str+this.getCourseName(score.getCourseNo())+"："+score.getScore()+"  ";
				}
			}
			str+="\n";
		}
		this.result.setText(str);
	}
	
	String getCourseName(String courseNo){
		for(int i=0;i<courseList.size();i++){
			Course course=(Course)this.courseList.get(i);
			if(courseNo.equals(course.getCourseNo())){
				return course.getCourseName();
			}
		}
		return null;
	}
}

@SuppressWarnings("serial")
class ScoreQueryPanel extends JPanel implements ActionListener{
	JTextField keyword=new JTextField(10);
	JTextArea result=new JTextArea();
	Vector<Student> stuList=new Vector<Student>();
	Vector<Score> scoreList=new Vector<Score>();
	Vector<Course> courseList=new Vector<Course>();
	ScoreQueryPanel(){
		this.setLayout(new BorderLayout());
		JPanel subPanel=new JPanel();
		subPanel.setLayout(new FlowLayout());
		subPanel.add(new JLabel("组号："));
		subPanel.add(keyword);
		JButton btn=new JButton("查询");
		btn.addActionListener(this);
		subPanel.add(btn);
		this.add(subPanel, BorderLayout.NORTH);
		JScrollPane scrollPane=new JScrollPane(result);
		this.add(scrollPane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent arg0) {
		if(keyword.getText().length()!=7){
			JOptionPane.showMessageDialog(null, "请输入正确的组号", "专柜人员销售情况查询系统", JOptionPane.ERROR_MESSAGE);
			return;
		}
		double max,min,sum,average;
		int count;
		String str="商品代码\t商品名称\t最高分\t最低分\t平均分\n";
		Course course=null;
		Score score=null;
		for(int i=0;i<courseList.size();i++){
			course=courseList.get(i);
			max=0;min=100;sum=0;average=0;count=0;
			for(int j=0;j<scoreList.size();j++){
				score=scoreList.get(j);
				if(score.getStuNo().startsWith(this.keyword.getText().trim())&&score.getCourseNo().equals(course.getCourseNo())){
					if(score.getScore()>max)
						max=score.getScore();
					if(score.getScore()<min)
						min=score.getScore();
					sum+=score.getScore();
					count++;
				}
			}
			if(count!=0){
				DecimalFormat df=new DecimalFormat("#.0");
				average=sum/count;
				str+=course.getCourseNo()+"\t"+course.getCourseName()+"\t"+df.format(max)+"\t"+df.format(min)+"\t"+df.format(average)+"\n";
			}
		}
		
		this.result.setText(str);
	}
}

class Student {
	private String stuNo;											//编号
	private String stuName;										//姓名
	private String stuSex;											//性别
	private String stuBirthday;									//出生日期
	Score score;															//销售情况
	
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

class Course{
	private String courseNo;										///商品代码
	private String courseName;									//商品名称
	private double courseScore;								//商品类别
	private String teacher;											//商品品牌
	
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public double getCourseScore() {
		return courseScore;
	}
	public void setCourseScore(double courseScore) {
		this.courseScore = courseScore;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
}

class Score{
	private String stuNo;											//编号
	private String courseNo;										///商品代码
	private double score;											//销售
	
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
}

@SuppressWarnings("serial")
class LoginFrame extends JFrame implements ActionListener{
	JTextField username=new JTextField(10);
	JPasswordField pwd=new JPasswordField(10);
	LoginFrame(){
		this.setTitle("系统登录");
		this.setResizable(false);
		this.setSize(250,130);
		this.setLocationRelativeTo(this.getOwner());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lb1=new JLabel("用户名：");
		JLabel lb2=new JLabel("密　码：");
		JButton btn=new JButton("登录");
		btn.addActionListener(this);
		Container c=this.getContentPane();
		c.setLayout(null);
		lb1.setBounds(30, 10, 100, 20);
		c.add(lb1);
		username.setBounds(120, 10, 100, 20);
		username.addActionListener(this);
		c.add(username);
		lb2.setBounds(30, 40, 100, 20);
		c.add(lb2);
		pwd.setBounds(120, 40, 100, 20);
		pwd.addActionListener(this);
		c.add(pwd);
		btn.setBounds(80, 70, 90, 20);
		c.add(btn);
		this.setVisible(true);
		this.username.requestFocus();
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.username){
			this.pwd.requestFocus();
			return;
		}
		try {
			FileInputStream fis;
			fis = new FileInputStream("Password.txt");
			InputStreamReader dis=new InputStreamReader(fis);
			BufferedReader reader=new BufferedReader(dis);
			String s;
			String clearText = 	"0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			String cipherText=	"UADKIy3FxgVkl5iZzWuGd1HNhOCtvjJ2pEn6Yw7PqrcQReB8Mfm0STsLX9a4ob";
			if((s=reader.readLine())!=null){
				if(username.getText().trim().equals("admin")){
					boolean isCorrect=true;
					char[] ch1=pwd.getPassword();
					char[] ch2=s.toCharArray();
					if(ch1.length==ch2.length){
						for(int i=0;i<ch1.length;i++){
							if(clearText.indexOf(ch1[i])!=cipherText.indexOf(ch2[i])){
								isCorrect=false;
								break;
							}
						}
					}else{
						isCorrect=false;
					}
					if(isCorrect){
						this.setVisible(false);
						this.dispose();
						Main f=new Main();
						f.setVisible(true);
					}else{
						JOptionPane.showMessageDialog(null, "您填写的密码不正确", "用户登录", JOptionPane.WARNING_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "您填写的用户名不正确", "用户登录", JOptionPane.WARNING_MESSAGE);
					this.username.requestFocus();
				}
			}
			reader.close();
			dis.close();
			fis.close();
		}catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}

