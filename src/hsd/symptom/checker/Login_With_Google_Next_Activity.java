package hsd.symptom.checker;

import hsd.symptom.checker.constant.Constant;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
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
import android.widget.RadioButton;
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

public class Login_With_Google_Next_Activity extends ActionBarActivity {

	private String name, email, from;
	private int gender;
	private EditText editText_name, editText_email, editText_mobile,
			editText_gender, editText_password, editText_reenter_password;
	private Button button_submit;
	private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

	private RequestQueue mRequestQueue;
	private ProgressDialog pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_with_google_next_activity);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("Login Details");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setIcon(R.drawable.ic_launcher);

		pd = new ProgressDialog(this);
		pd.setTitle("Please wait...");
		pd.setMessage("Registering your details...");
		pd.setCancelable(false);
		pd.setIcon(R.drawable.ic_launcher);
		pd.setIndeterminate(true);

		editText_name = (EditText) findViewById(R.id.editText_name);
		editText_email = (EditText) findViewById(R.id.editText_email);
		editText_mobile = (EditText) findViewById(R.id.editText_mobile);
		editText_gender = (EditText) findViewById(R.id.editText_gender);
		editText_password = (EditText) findViewById(R.id.editText_password);
		editText_reenter_password = (EditText) findViewById(R.id.editText_reenter_password);

		button_submit = (Button) findViewById(R.id.button_submit);
		name = getIntent().getStringExtra("name");
		email = getIntent().getStringExtra("email");
		from = getIntent().getStringExtra("from");
		editText_name.setText(name);
		editText_email.setText(email);

		if (from.equals("google")) {
			String gender_string = getIntent().getStringExtra("gender");
			if (gender_string.equals("Male")) {
				editText_gender.setText("Male");
			} else if (gender_string.equals("Female")) {
				editText_gender.setText("Female");
			}
		} else if (from.equals("facebook")) {
			String gender_string = getIntent().getStringExtra("gender");
			if (gender_string.equals("male")) {
				gender = 0;
				editText_gender.setText("Male");
			} else if (gender_string.equals("female")) {
				gender = 1;
				editText_gender.setText("Female");
			}
		}

		editText_gender.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Dialog dialog = new Dialog(
						Login_With_Google_Next_Activity.this);
				dialog.setContentView(R.layout.gender_popup);
				dialog.setTitle("Select gender");
				dialog.setCancelable(true);
				// there are a lot of settings, for dialog, check them all out!
				// set up radiobutton
				final RadioButton rd1 = (RadioButton) dialog
						.findViewById(R.id.rd_1);
				final RadioButton rd2 = (RadioButton) dialog
						.findViewById(R.id.rd_2);
				Button rd_ok = (Button) dialog.findViewById(R.id.rd_ok);

				if (gender == 0) {
					rd1.setChecked(true);
					gender = 0;
				} else if (gender == 1) {
					rd2.setChecked(true);
					gender = 1;
				}

				rd_ok.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (rd1.isChecked()) {
							editText_gender.setText("Male");
						} else if (rd2.isChecked()) {
							editText_gender.setText("Female");
						}
						dialog.cancel();
					}
				});

				// now that the dialog is set up, it's time to show it
				dialog.show();
			}
		});

		button_submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (validateDetails()) {
					sendOTPToVerify(editText_name.getText().toString(),
							editText_email.getText().toString(),
							editText_mobile.getText().toString(),
							editText_gender.getText().toString(),
							editText_password.getText().toString());
				}
			}
		});

	}

	private boolean validateDetails() {
		boolean all_set = true;

		if (editText_reenter_password.getText().toString().length() == 0) {
			editText_reenter_password.setError("re-enter password");
			editText_reenter_password.requestFocus();
			all_set = false;
		} else {
			editText_reenter_password.setError(null);
			if (!editText_reenter_password.getText().toString()
					.equals(editText_password.getText().toString())) {
				editText_reenter_password.setError("passwords doesnt match");
				editText_password.setError("passwords doesnt match");
				editText_password.requestFocus();
				all_set = false;
			} else {
				editText_reenter_password.setError(null);
				editText_password.setError(null);
			}
		}
		if (editText_password.getText().toString().length() == 0) {
			editText_password.setError("enter password");
			editText_password.requestFocus();
			all_set = false;
		} else {
			editText_password.setError(null);
			if (!editText_reenter_password.getText().toString()
					.equals(editText_password.getText().toString())) {
				editText_reenter_password.setError("passwords doesnt match");
				editText_password.setError("passwords doesnt match");
				editText_password.requestFocus();
				all_set = false;
			} else {
				editText_reenter_password.setError(null);
				editText_password.setError(null);
			}
		}

		if (editText_gender.getText().toString().length() == 0) {
			editText_gender.setError("enter gender");
			editText_gender.requestFocus();
			all_set = false;
		} else {
			editText_gender.setError(null);
		}
		if (editText_mobile.getText().toString().length() != 10) {
			editText_mobile.setError("enter mobile number properly");
			editText_mobile.requestFocus();
			all_set = false;
		} else {
			editText_mobile.setError(null);
		}
		if (!editText_email.getText().toString().trim().matches(emailPattern)) {
			editText_email.setError("enter email properly");
			editText_email.requestFocus();
			all_set = false;
		} else {
			editText_email.setError(null);
		}
		if (editText_name.getText().toString().length() == 0) {
			editText_name.setError("enter name");
			editText_name.requestFocus();
			all_set = false;
		} else {
			editText_name.setError(null);
			String[] nameSplit = editText_name.getText().toString().split(" ");
			if (nameSplit.length == 1) {
				editText_name.setError("enter firstname lastname");
				editText_name.requestFocus();
				all_set = false;
			} else {
				editText_name.setError(null);
			}
		}

		return all_set;
	}

	@Override
	public void onBackPressed() {
		finish();
		startActivity(new Intent(Login_With_Google_Next_Activity.this,
				Login_Activity.class));
		super.onBackPressed();
	}

	private void sendOTPToVerify(final String name, final String email,
			final String mobile, final String gender, final String password) {

		mRequestQueue = Volley.newRequestQueue(this);
		pd.show();
		String url = getResources().getString(R.string.host_url) + ""
				+ getResources().getString(R.string.check_hs_login_exists_php);
		StringRequest request = new StringRequest(Method.POST, url,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {

						// String otp = "";
						// Log.e("resp", response);
						//
						// JSONObject jsonObject;
						// try {
						// jsonObject = new JSONObject(response);
						// otp = jsonObject.getString("otp");
						// Log.e("otp", jsonObject.getString("otp"));
						// } catch (JSONException e) {
						// // TODO Auto-generated catch block
						// e.printStackTrace();
						// }

						JSONObject jsonObject = null;
						String has_id = "";
						try {
							jsonObject = new JSONObject(response);
							has_id = jsonObject.getString("has_id");
							Log.e("has_id", jsonObject.getString("has_id") + "");
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						pd.cancel();
						if (has_id.equals("true")) {
							Toast.makeText(getApplicationContext(),
									"Login Exists", Toast.LENGTH_LONG).show();
						} else {
							finish();
							Intent intent = new Intent(
									Login_With_Google_Next_Activity.this,
									CheckSMSAutoActivity.class);
							// intent.putExtra("otp", otp);
							intent.putExtra("mobileNumber", editText_mobile
									.getText().toString());
							// intent.putExtra("email", editText_email.getText()
							// .toString());
							startActivity(intent);
						}
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
				Map<String, String> map = new HashMap<String, String>();
				map.put("name", name);
				String[] nameSplit = name.split(" ");
				map.put("fname", nameSplit[0]);
				String lastName = "";
				for (int i = 1; i < nameSplit.length; i++) {
					lastName = lastName + nameSplit[i] + " ";
				}
				map.put("lname", lastName);
				map.put("email", email);
				map.put("mobile", mobile);
				map.put("gender", gender);
				map.put("password", password);
				map.put("from", from);
				return map;
			}
		};
		mRequestQueue.add(request);
	}
}
