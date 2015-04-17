package hsd.symptom.checker;

import hsd.symptom.checker.adapter.SymptomAdapter;
import hsd.symptom.checker.database.Symptom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
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

public class Find_Symptoms_Activity extends ActionBarActivity {

	private ProgressDialog pd;
	private String TAG = "Find_Symptoms_Activity";
	private RequestQueue mRequestQueue;

	private ListView listView_symptoms;

	private ArrayList<Symptom> symptomList;
	private TextView textView_empty_view, textView_body_part;
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.symptom_layout);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("Symptom Checker");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		symptomList = new ArrayList<Symptom>();
		pd = new ProgressDialog(this);
		pd.setTitle("Please wait...");
		pd.setMessage("Getting list of symptoms...");
		pd.setCancelable(false);
		pd.setIcon(R.drawable.ic_launcher);
		pd.setIndeterminate(true);

		listView_symptoms = (ListView) findViewById(R.id.listView_symptoms);
		textView_empty_view = (TextView) findViewById(R.id.textView_empty_view);
		textView_body_part = (TextView) findViewById(R.id.textView_body_part);

		listView_symptoms.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				ArrayList<String> symptomListSend = new ArrayList<String>();
				for (int i = 0; i < symptomList.size(); i++) {
					if (symptomList.get(i).getName()
							.equals(symptomList.get(position).getName())) {
						symptomListSend
								.add(symptomList.get(i).getSpeciality1());
						symptomListSend
								.add(symptomList.get(i).getSpeciality2());
						symptomListSend
								.add(symptomList.get(i).getSpeciality3());
					}
				}

				Intent intent = new Intent(Find_Symptoms_Activity.this,
						SpecialityActivity.class);
				intent.putStringArrayListExtra("symptomList", symptomListSend);
				startActivity(intent);
			}
		});

		mRequestQueue = Volley.newRequestQueue(this);

		String part = getIntent().getStringExtra("part");
		boolean gender = getIntent().getBooleanExtra("gender", false);
		String gender_string = "";
		if (gender) {
			gender_string = "Male";
		} else {
			gender_string = "Female";
		}

		Log.e("part", part);
		textView_body_part.setText(part + "");
		textView_body_part.append(" symptoms:");
		Log.e("gender_string", gender_string + "");
		findSymptoms(part, gender_string);
	}

	private void findSymptoms(final String part, final String gender) {

		pd.show();
		String url = getResources().getString(R.string.host_url) + ""
				+ getResources().getString(R.string.get_symptoms_php);
		StringRequest request = new StringRequest(Method.POST, url,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {
						pd.cancel();

						processData(response);

						SymptomAdapter adapter = new SymptomAdapter(
								Find_Symptoms_Activity.this,
								R.layout.symptom_item, symptomList);

						if (symptomList.size() > 0) {
							Collections.sort(symptomList,
									new Comparator<Symptom>() {
										@Override
										public int compare(
												final Symptom object1,
												final Symptom object2) {
											return object1.getName().compareTo(
													object2.getName());
										}
									});
						} else {
							textView_empty_view.setVisibility(View.VISIBLE);
						}

						listView_symptoms.setAdapter(adapter);
					}
				}, new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d("TAG", "Error: " + error.getMessage());
						Toast.makeText(getApplicationContext(), "Error: ",
								Toast.LENGTH_SHORT).show();
						pd.cancel();
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {

				JSONObject myo = new JSONObject();
				try {
					myo.put("part", part);
					myo.put("gender", gender);

				} catch (JSONException e) {
					e.printStackTrace();
				}
				Log.e(TAG, myo.toString());
				Map<String, String> map = new HashMap<String, String>();
				map.put("check_symptoms", myo.toString());
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
		JSONArray jsonArray = null;
		try {
			jsonArray = jsonObject.getJSONArray("details");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < jsonArray.length(); i++) {

			JSONObject object = null;
			try {
				object = jsonArray.getJSONObject(i);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				symptomList.add(new Symptom(object.getString("symptom_name"),
						object.getString("symptom_cname"), object
								.getString("speciality1"), object
								.getString("speciality2"), object
								.getString("speciality3")));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
