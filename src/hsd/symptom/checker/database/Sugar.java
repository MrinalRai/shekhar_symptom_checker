package hsd.symptom.checker.database;

public class Sugar {
	private Float _sugar;
	private String _date;

	public Sugar(Float _sugar, String _date) {
		this._sugar = _sugar;
		this._date = _date;
	}

	public Float get_sugar() {
		return _sugar;
	}

	public void set_sugar(Float _sugar) {
		this._sugar = _sugar;
	}

	public String get_date() {
		return _date;
	}

	public void set_date(String _date) {
		this._date = _date;
	}
}
