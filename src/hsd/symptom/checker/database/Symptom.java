package hsd.symptom.checker.database;

import java.io.Serializable;

public class Symptom implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name, cname;
	String speciality1, speciality2, speciality3;

	public Symptom(String name, String cname, String speciality1,
			String speciality2, String speciality3) {
		this.name = name;
		this.cname = cname;
		this.speciality1 = speciality1;
		this.speciality2 = speciality2;
		this.speciality3 = speciality3;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getSpeciality1() {
		return speciality1;
	}

	public void setSpeciality1(String speciality1) {
		this.speciality1 = speciality1;
	}

	public String getSpeciality2() {
		return speciality2;
	}

	public void setSpeciality2(String speciality2) {
		this.speciality2 = speciality2;
	}

	public String getSpeciality3() {
		return speciality3;
	}

	public void setSpeciality3(String speciality3) {
		this.speciality3 = speciality3;
	}
}
