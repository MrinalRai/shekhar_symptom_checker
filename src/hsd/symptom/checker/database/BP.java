package hsd.symptom.checker.database;

public class BP {
	private Float _bp;
	private String _date;

	public BP(Float _bp, String _date) {
		this._bp = _bp;
		this._date = _date;
	}

	public Float get_bp() {
		return _bp;
	}

	public void set_bp(Float _bp) {
		this._bp = _bp;
	}

	public String get_date() {
		return _date;
	}

	public void set_date(String _date) {
		this._date = _date;
	}
}
