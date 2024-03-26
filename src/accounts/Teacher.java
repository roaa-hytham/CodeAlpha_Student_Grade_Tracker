package accounts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import exceptions.EmptyInputException;
import exceptions.NegativeInputException;
import exceptions.NonNumberException;

public class Teacher {
	private static ArrayList<Student> students;

	public Teacher() {
		students = new ArrayList<Student>();
	}

	public ArrayList<Student> getStudents() {
		return students;
	}

	public double getAverage() {
		if (students.isEmpty())
			return -1;
		int num = students.size();
		double sum = 0;
		for (int i = 0; i < num; i++)
			sum += students.get(i).getGrade();
		return sum / num;
	}

	public double getHighest() {
		if (students.isEmpty())
			return -1;
		double max = -1;
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getGrade() > max)
				max = students.get(i).getGrade();
		}
		return max;
	}

	public double getLowest() {
		if (students.isEmpty())
			return -1;
		double min = 9999;
		for (int i = 0; i < students.size(); i++) {
			if (students.get(i).getGrade() < min)
				min = students.get(i).getGrade();
		}
		return min;
	}

	public void enterGrade(String sname, String g) throws EmptyInputException, NonNumberException, NegativeInputException, IOException {
		if (g.isBlank())
			throw new EmptyInputException();
		if (sname.isBlank())
			throw new EmptyInputException();
		for (int i = 0; i < g.length(); i++)
			if (g.charAt(i) >= 'a' && g.charAt(i) <= 'z' || g.charAt(i) >= 'A' && g.charAt(i) <= 'Z')
				throw new NonNumberException();
		double gg = Double.parseDouble(g);
		if (gg < 0)
			throw new NegativeInputException();
		for(int i=0; i<students.size(); i++)
			if(students.get(i).getName().compareTo(sname) == 0)
				students.get(i).setGrade(gg);
		System.out.println("Grade added!");
	}
	
	public boolean checkName(String n) {
		for(int i=0; i<students.size(); i++)
			if(students.get(i).getName().compareTo(n) == 0)
				return true;
		return false;
	}

	@SuppressWarnings("resource")
	public void loadStudents() throws IOException, EmptyInputException, NonNumberException, NegativeInputException {
		students.clear();
		BufferedReader br = new BufferedReader(new FileReader("Students.csv"));
		String line = br.readLine();
		while (line != null) {
			String[] content = line.split(",");
			Student s = null;
			String g= content[1];
			if (g.isBlank())
				throw new EmptyInputException();
			for (int i = 0; i < g.length(); i++)
				if (g.charAt(i) >= 'a' && g.charAt(i) <= 'z' || g.charAt(i) >= 'A' && g.charAt(i) <= 'Z')
					throw new NonNumberException();
			double gg = Double.parseDouble(g);
			if (gg < 0)
				throw new NegativeInputException();
			s = new Student(content[0],gg);
			students.add(s);
			line = br.readLine();
		}
		br.close();
		System.out.println("Students[] loaded!");
	}
	
	public void reloadStudents() throws IOException, EmptyInputException, NonNumberException, NegativeInputException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("Students.csv",false));
		for(int i=0; i<students.size(); i++){
			bw.write(students.get(i).getName()+","+ students.get(i).getGrade());
			bw.newLine();
		}
		bw.close();
		loadStudents();
		System.out.println("Students[] reloaded!");
	}

}
