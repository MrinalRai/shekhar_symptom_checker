package hsd.symptom.checker.database;

public class SugarBp {
	private Float _value;
	private String _date;

	public SugarBp(Float _value, String _date) {
		this._value = _value;
		this._date = _date;
	}

	public Float get_value() {
		return _value;
	}

	public void set_value(Float _value) {
		this._value = _value;
	}

	public String get_date() {
		return _date;
	}

	public void set_date(String _date) {
		this._date = _date;
	}
}
