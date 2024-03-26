package accounts;

public class Student {
	private String name;
	private double grade;

	public Student(String name) {
		grade = 0;
		this.name = name;
	}
	
	public Student(String n, double g) {
		grade= g;
		name = n;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double gg) {
		grade = gg;
	}

	public int compareTo(Student s) {
		if(name.compareTo(s.getName()) == 0)
			if(grade == s.getGrade())
				return 0;
		return -1;
	}

}
