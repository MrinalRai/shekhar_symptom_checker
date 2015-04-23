package hsd.symptom.checker;

import hsd.symptom.checker.constant.Constant;
import hsd.symptom.checker.services.IncomingSms;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ProgressBar;
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

public class CheckSMSAutoActivity extends ActionBarActivity {

	private ProgressBar progressBar;
	private TextView textviewNumber, textviewTime;

	private static String mobileNumber;
	private static CountDownTimer countDownTimer;
	private static Activity mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_sms_auto);

		mContext = this;

		mobileNumber = getIntent().getStringExtra("mobileNumber");
		progressBar = (ProgressBar) findViewById(R.id.progressBar);

		textviewTime = (TextView) findViewById(R.id.textviewTime);
		textviewNumber = (TextView) findViewById(R.id.textviewNumber);
		textviewNumber.setText(mobileNumber + "");

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("Phone Number Verification");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setIcon(R.drawable.ic_launcher);

		startService(new Intent(CheckSMSAutoActivity.this, IncomingSms.class));

		countDownTimer = new CountDownTimer(120000, 1000) {

			public void onTick(long millisUntilFinished) {
				int seconds = (int) (millisUntilFinished / 1000);
				int SECONDS_IN_A_MINUTE = 60;
				int minutes = seconds / SECONDS_IN_A_MINUTE;
				seconds -= minutes * SECONDS_IN_A_MINUTE;

				Log.e("setProgress",
						(((120000 - millisUntilFinished) / 1000) * 100) / 120
								+ " 120000 - millisUntilFinished");
				progressBar
						.setProgress((int) ((((120000 - millisUntilFinished) / 1000) * 100) / 120));
				textviewTime.setText(minutes + ":" + seconds);
			}

			public void onFinish() {
				Log.e("seconds remaining: ", "done");
				textviewTime.setText("0:0");
				progressBar.setProgress(100);
				finish();
				Intent intent = new Intent(CheckSMSAutoActivity.this,
						CheckSMSManualActivity.class);
				intent.putExtra("mobileNumber", mobileNumber);
				startActivity(intent);
			}
		}.start();

	}

	public static void sendOTPVerification(final String otp) {
		// if (otp.equals(message)) {
		countDownTimer.cancel();
		sendOTPToVerify(mobileNumber, otp);
		// }
	}

	public static void sendOTPToVerify(final String phoneNumber,
			final String otp) {

		Log.e("sendOTPToVerify", "sendOTPToVerify called");

		RequestQueue mRequestQueue;
		mRequestQueue = Volley.newRequestQueue(mContext);
		final ProgressDialog pd = new ProgressDialog(mContext);
		pd.setTitle("Please wait...");
		pd.setMessage("Loggin you in...");
		pd.setCancelable(false);
		pd.setIcon(R.drawable.ic_launcher);
		pd.setIndeterminate(true);
		pd.show();
		String url = mContext.getResources().getString(R.string.host_url) + ""
				+ mContext.getResources().getString(R.string.check_otp_php);
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
							Toast.makeText(mContext, "Verified",
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
							SharedPreferences prefs = mContext
									.getSharedPreferences(
											Constant.MyPREFERENCES,
											MODE_PRIVATE);
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

							mContext.finish();
							mContext.startActivity(new Intent(mContext,
									Symptom_Checker_Menu_Activity.class));
						} else {
							Toast.makeText(mContext, "Invalid OTP",
									Toast.LENGTH_LONG).show();
							mContext.finish();
							mContext.startActivity(new Intent(mContext,
									CheckSMSManualActivity.class));
						}
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d("TAG", "Error: " + error.getMessage());
						Toast.makeText(mContext, "Error: ", Toast.LENGTH_SHORT)
								.show();
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
