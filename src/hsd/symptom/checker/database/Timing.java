package hsd.symptom.checker.database;

import java.util.ArrayList;

public class Timing {
	String date;
	String time;
	ArrayList<String> slots;

	public Timing(String date, String time, ArrayList<String> slots) {
		this.date = date;
		this.time = time;
		this.slots = slots;
	}

	public Timing() {
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public ArrayList<String> getSlots() {
		return slots;
	}

	public void setSlots(ArrayList<String> slots) {
		this.slots = slots;
	}
}
