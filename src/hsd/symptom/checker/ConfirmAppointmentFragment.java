package hsd.symptom.checker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ConfirmAppointmentFragment extends Fragment {

	private View content;

	private TextView textViewPatientName, textViewDoctorName, textViewReason,
			textViewPlace, textViewDate, textViewTime;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		content = inflater.inflate(R.layout.confirm_appointment, container,
				false);
		textViewPatientName = (TextView) content
				.findViewById(R.id.textViewPatientName);
		textViewDoctorName = (TextView) content
				.findViewById(R.id.textViewDoctorName);
		textViewReason = (TextView) content.findViewById(R.id.textViewReason);
		textViewPlace = (TextView) content.findViewById(R.id.textViewPlace);
		textViewDate = (TextView) content.findViewById(R.id.textViewDate);
		textViewTime = (TextView) content.findViewById(R.id.textViewTime);

		Bundle b = getArguments();
		textViewPatientName.setText(b.getString("patient_name"));
		textViewDoctorName.setText(b.getString("doctor_name"));
		textViewReason.setText(b.getString("reason"));
		textViewPlace.setText(b.getString("location_selected"));
		textViewDate.setText(b.getString("date_selected"));
		textViewTime.setText(b.getString("time_selected") + " ("
				+ b.getString("session") + ")");

		return content;
	}
}
