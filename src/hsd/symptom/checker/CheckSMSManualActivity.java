package hsd.symptom.checker;

import hsd.symptom.checker.constant.Constant;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class CheckSMSManualActivity extends ActionBarActivity {

	private EditText editTextOTP;
	private TextView textviewNumber;
	private Button buttonVerify;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_sms_manual);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("Phone Number Verification");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setIcon(R.drawable.ic_launcher);

		editTextOTP = (EditText) findViewById(R.id.editTextOTP);
		textviewNumber = (TextView) findViewById(R.id.textviewNumber);
		textviewNumber.setText(getIntent().getStringExtra("mobileNumber"));
		String otp = "";
		try {
			otp = getIntent().getStringExtra("otp_val");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String otp_recieved = "n";
		try {
			otp_recieved = getIntent().getStringExtra("otp_recieved");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		editTextOTP.setText(otp);
		buttonVerify = (Button) findViewById(R.id.buttonVerify);

		buttonVerify.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				sendOTPToVerify(textviewNumber.getText().toString(),
						editTextOTP.getText().toString());
			}
		});

		if (otp_recieved.equals("y")) {
			buttonVerify.performClick();
		}
	}

	private void sendOTPToVerify(final String phoneNumber, final String otp) {

		Log.e("sendOTPToVerify", "sendOTPToVerify called");

		RequestQueue mRequestQueue;
		mRequestQueue = Volley.newRequestQueue(this);
		final ProgressDialog pd = new ProgressDialog(this);
		pd.setTitle("Please wait...");
		pd.setMessage("Loggin you in...");
		pd.setCancelable(false);
		pd.setIcon(R.drawable.ic_launcher);
		pd.setIndeterminate(true);
		pd.show();
		String url = getResources().getString(R.string.host_url) + ""
				+ getResources().getString(R.string.check_otp_php);
		StringRequest request = new StringRequest(Method.POST, url,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {

						String has_id = "";
						Log.e("resp", response);

						JSONObject jsonObject = null;
						try {
							jsonObject = new JSONObject(response);
							has_id = jsonObject.getString("has_id");
							Log.e("has_id", jsonObject.getString("has_id"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						pd.cancel();
						if (has_id.equals("true")) {
							Toast.makeText(getApplicationContext(), "Verified",
									Toast.LENGTH_LONG).show();

							String user_id = "", user_image = "", user_email = "", display_name = "";
							try {
								user_id = jsonObject.getString("user_id");
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								user_image = jsonObject.getString("user_image");
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								user_email = jsonObject.getString("user_email");
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								display_name = jsonObject
										.getString("display_name");
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							SharedPreferences prefs = getSharedPreferences(
									Constant.MyPREFERENCES, MODE_PRIVATE);
							Editor editor = prefs.edit();
							editor.putBoolean(Constant.USER_LOGGED_IN, true);
							editor.putString(Constant.USER_ID, user_id);
							editor.putString(Constant.USER_LOGGED_IN_NAME,
									display_name);
							editor.putString(Constant.USER_LOGGED_IN_EMAIL,
									user_email);
							editor.putString(Constant.USER_LOGGED_IN_IMAGE,
									user_image);
							editor.commit();
							finish();
							startActivity(new Intent(
									CheckSMSManualActivity.this,
									Symptom_Checker_Menu_Activity.class));
						} else {
							Toast.makeText(getApplicationContext(),
									"Invalid OTP", Toast.LENGTH_LONG).show();
						}
					}
				}, new ErrorListener() {
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
				Map<String, String> map = new HashMap<String, String>();
				map.put("phoneNumber", phoneNumber);
				map.put("otp", otp);
				return map;
			}
		};
		mRequestQueue.add(request);
	}
}
