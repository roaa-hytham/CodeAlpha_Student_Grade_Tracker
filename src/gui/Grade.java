package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import accounts.Teacher;
import exceptions.EmptyInputException;
import exceptions.NegativeInputException;
import exceptions.NonNumberException;

@SuppressWarnings("serial")
public class Grade extends JFrame implements ActionListener {
	Teacher teacher;
	JTextPane student;
	JTextArea sname;
	JTextPane grade;
	JTextArea gin;
	JButton enterGrade;
	JTextPane gout;
	JButton avg;
	JButton high;
	JButton low;

	public Grade() throws IOException, EmptyInputException, NonNumberException, NegativeInputException {
		teacher = new Teacher();
		teacher.loadStudents();

		student = new JTextPane();
		student.setBounds(100, 45, 150, 30);
		student.setText("Student Name: ");
		student.setForeground(Color.black);
		student.setBackground(null);
		student.setEditable(false);
		student.setOpaque(true);
		student.setVisible(true);
		student.setFont(new Font("MV Boli", Font.BOLD, 15));

		sname = new JTextArea();
		sname.setBounds(250, 45, 100, 30);
		sname.setForeground(Color.black);
		sname.setBackground(Color.white);
		sname.setEditable(true);
		sname.setOpaque(true);
		sname.setVisible(true);
		sname.setFont(new Font("MV Boli", Font.BOLD, 15));

		grade = new JTextPane();
		grade.setBounds(100, 85, 70, 30);
		grade.setText("Grade: ");
		grade.setForeground(Color.black);
		grade.setBackground(null);
		grade.setEditable(false);
		grade.setOpaque(true);
		grade.setVisible(true);
		grade.setFont(new Font("MV Boli", Font.BOLD, 15));

		gin = new JTextArea();
		gin.setBounds(250, 85, 100, 30);
		gin.setForeground(Color.black);
		gin.setBackground(Color.white);
		gin.setEditable(true);
		gin.setOpaque(true);
		gin.setVisible(true);
		gin.setFont(new Font("MV Boli", Font.BOLD, 15));

		gout = new JTextPane();
		gout.setBounds(100, 120, 250, 30);
		gout.setForeground(Color.blue);
		gout.setBackground(null);
		gout.setEditable(false);
		gout.setOpaque(true);
		gout.setVisible(true);
		gout.setFont(new Font("MV Boli", Font.BOLD, 15));

		enterGrade = new JButton();
		enterGrade.addActionListener(this);
		enterGrade.setBounds(90, 160, 150, 30);
		enterGrade.setBackground(Color.LIGHT_GRAY);
		enterGrade.setFocusable(false);
		enterGrade.setText("Enter Grade");
		enterGrade.setBorderPainted(false);

		avg = new JButton();
		avg.addActionListener(this);
		avg.setBounds(90, 210, 150, 30);
		avg.setBackground(Color.LIGHT_GRAY);
		avg.setFocusable(false);
		avg.setText("Average Grade");
		avg.setBorderPainted(false);

		high = new JButton();
		high.addActionListener(this);
		high.setBounds(250, 160, 150, 30);
		high.setBackground(Color.LIGHT_GRAY);
		high.setFocusable(false);
		high.setText("Highest Grade");
		high.setBorderPainted(false);

		low = new JButton();
		low.addActionListener(this);
		low.setBounds(250, 210, 150, 30);
		low.setBackground(Color.LIGHT_GRAY);
		low.setFocusable(false);
		low.setText("Lowest Grade");
		low.setBorderPainted(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 500, 350);
		setLayout(null);
		setResizable(false);
		setVisible(true);
		add(student);
		add(sname);
		add(grade);
		add(gin);
		add(enterGrade);
		add(gout);
		add(avg);
		add(high);
		add(low);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enterGrade) {
			try {
				if (!teacher.checkName(sname.getText())) {
					gout.setText("Please Enter A Student Name!");
					gout.setForeground(Color.red);
					gout.setFont(new Font("MV Boli", Font.BOLD, 15));
				} else {
					teacher.enterGrade(sname.getText(), gin.getText());
					gout.setText("Grade Entered!");
					gout.setForeground(Color.blue);
					gout.setFont(new Font("MV Boli", Font.BOLD, 15));
					teacher.reloadStudents();
				}
			} catch (EmptyInputException e1) {
				gout.setText("Please Enter Student Name And Grade!");
				gout.setFont(new Font("MV Boli", Font.BOLD, 11));
				gout.setForeground(Color.red);
			} catch (NonNumberException e1) {
				gout.setText("Please Enter A Number!");
				gout.setForeground(Color.red);
				gout.setFont(new Font("MV Boli", Font.BOLD, 15));
			} catch (NegativeInputException e1) {
				gout.setText("Please Enter A Positive Number!");
				gout.setForeground(Color.red);
				gout.setFont(new Font("MV Boli", Font.BOLD, 15));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (e.getSource() == avg) {
			gout.setText("Average Grade= " + teacher.getAverage());
			gout.setForeground(Color.blue);
			gout.setFont(new Font("MV Boli", Font.BOLD, 15));
		}

		if (e.getSource() == high) {
			gout.setText("Highest Grade= " + teacher.getHighest());
			gout.setForeground(Color.blue);
			gout.setFont(new Font("MV Boli", Font.BOLD, 15));
		}

		if (e.getSource() == low) {
			gout.setText("Lowest Grade= " + teacher.getLowest());
			gout.setForeground(Color.blue);
			gout.setFont(new Font("MV Boli", Font.BOLD, 15));
		}
	}

	public static void main(String[] args)
			throws IOException, EmptyInputException, NonNumberException, NegativeInputException {
		new Grade();
	}

}
