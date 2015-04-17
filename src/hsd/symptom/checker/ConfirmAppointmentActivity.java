package hsd.symptom.checker;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ConfirmAppointmentActivity extends Activity {

	private TextView textViewPatientName, textViewDoctorName, textViewReason,
			textViewPlace, textViewDate, textViewTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirm_appointment);

		textViewPatientName = (TextView) findViewById(R.id.textViewPatientName);
		textViewDoctorName = (TextView) findViewById(R.id.textViewDoctorName);
		textViewReason = (TextView) findViewById(R.id.textViewReason);
		textViewPlace = (TextView) findViewById(R.id.textViewPlace);
		textViewDate = (TextView) findViewById(R.id.textViewDate);
		textViewTime = (TextView) findViewById(R.id.textViewTime);

		textViewPatientName.setText(getIntent().getStringExtra("Patient"));
		textViewDoctorName.setText(getIntent().getStringExtra("Doctor"));
		textViewReason.setText(getIntent().getStringExtra("Reason"));
		textViewPlace.setText(getIntent().getStringExtra("Place"));
		textViewDate.setText(getIntent().getStringExtra("Date"));
		textViewTime.setText(getIntent().getStringExtra("Time"));
	}
}
