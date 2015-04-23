package hsd.symptom.checker;

import android.app.Application;

public class GlobalClass extends Application {
	private String name, image, blood_group, allergies, medical_conditions;
	private int sent;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getBlood_group() {
		return blood_group;
	}

	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getMedical_conditions() {
		return medical_conditions;
	}

	public void setMedical_conditions(String medical_conditions) {
		this.medical_conditions = medical_conditions;
	}

	public int getSent() {
		return sent;
	}

	public void setSent(int sent) {
		this.sent = sent;
	}
}