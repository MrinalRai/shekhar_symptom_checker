package hsd.symptom.checker.database;

public class Doctor {
	int doc_id;
	String doc_name, doc_speciality, doc_fee, doc_area, doc_image;

	public Doctor(int doc_id, String doc_name, String doc_speciality,
			String doc_fee, String doc_area, String doc_image) {
		this.doc_id = doc_id;
		this.doc_name = doc_name;
		this.doc_speciality = doc_speciality;
		this.doc_fee = doc_fee;
		this.doc_area = doc_area;
		this.doc_image = doc_image;
	}

	public int getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(int doc_id) {
		this.doc_id = doc_id;
	}

	public String getDoc_name() {
		return doc_name;
	}

	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}

	public String getDoc_speciality() {
		return doc_speciality;
	}

	public void setDoc_speciality(String doc_speciality) {
		this.doc_speciality = doc_speciality;
	}

	public String getDoc_fee() {
		return doc_fee;
	}

	public void setDoc_fee(String doc_fee) {
		this.doc_fee = doc_fee;
	}

	public String getDoc_area() {
		return doc_area;
	}

	public void setDoc_area(String doc_area) {
		this.doc_area = doc_area;
	}

	public String getDoc_image() {
		return doc_image;
	}

	public void setDoc_image(String doc_image) {
		this.doc_image = doc_image;
	}
}
