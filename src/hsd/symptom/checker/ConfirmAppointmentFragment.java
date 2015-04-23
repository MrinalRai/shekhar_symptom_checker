package hsd.symptom.checker;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class ConfirmAppointmentFragment extends Fragment {

	private View content;

	private TextView textViewPatientName, textViewDoctorName, textViewReason,
			textViewPlace, textViewDate, textViewTime;

	private ProgressDialog pd;
	private RequestQueue mRequestQueue;

	private String doc_id, patient_id, location_id, location_selected,
			date_selected, time_selected, session, patient_name, reason,
			doctor_name;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		content = inflater.inflate(R.layout.confirm_appointment, container,
				false);

		mRequestQueue = Volley.newRequestQueue(getActivity());
		pd = new ProgressDialog(getActivity());
		pd.setTitle("Please wait...");
		pd.setMessage("Booking your appointment...");
		pd.setCancelable(false);
		pd.setIcon(R.drawable.ic_launcher);
		pd.setIndeterminate(true);

		textViewPatientName = (TextView) content
				.findViewById(R.id.textViewPatientName);
		textViewDoctorName = (TextView) content
				.findViewById(R.id.textViewDoctorName);
		textViewReason = (TextView) content.findViewById(R.id.textViewReason);
		textViewPlace = (TextView) content.findViewById(R.id.textViewPlace);
		textViewDate = (TextView) content.findViewById(R.id.textViewDate);
		textViewTime = (TextView) content.findViewById(R.id.textViewTime);

		Bundle b = getArguments();
		doc_id = b.getString("doc_id");
		patient_id = b.getString("patient_id");
		location_id = b.getString("location_id");
		date_selected = b.getString("date_selected");
		location_selected = b.getString("location_selected");
		time_selected = b.getString("time_selected");
		session = b.getString("session");
		patient_name = b.getString("patient_name");
		doctor_name = b.getString("doctor_name");
		reason = b.getString("reason");

		textViewPatientName.setText(patient_name + "");
		textViewDoctorName.setText(doctor_name);
		textViewReason.setText(reason);
		textViewPlace.setText(location_selected);
		textViewDate.setText(date_selected);
		textViewTime.setText(time_selected + " " + session + "");

		content.findViewById(R.id.imageButton_confirm_appointment)
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						bookAppointment();
					}
				});

		return content;
	}

	private void bookAppointment() {
		pd.show();
		String url = getResources().getString(R.string.host_url) + ""
				+ getResources().getString(R.string.book_appointment_php);
		StringRequest request = new StringRequest(Method.POST, url,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {
						pd.cancel();
						processData(response);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d("TAG", "Error: " + error.getMessage());
						Toast.makeText(getActivity(), "Error: ",
								Toast.LENGTH_SHORT).show();
						getActivity().onBackPressed();
						pd.cancel();
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				Map<String, String> map = new HashMap<String, String>();
				map.put("doc_id", doc_id);
				map.put("patient_id", patient_id);
				map.put("location_id", location_id);
				map.put("date_selected", date_selected);
				map.put("time_selected", time_selected);
				map.put("patient_name", patient_name);
				map.put("reason", reason);
				return map;
			}
		};
		mRequestQueue.add(request);
	}

	private void processData(String response) {
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String booked = "false";
		try {
			booked = jsonObject.getString("success");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (booked.equals("true")) {
			Toast.makeText(getActivity(), "Appointment Booked",
					Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(getActivity(), "Appointment Booking Failed",
					Toast.LENGTH_LONG).show();
		}
	}
}
