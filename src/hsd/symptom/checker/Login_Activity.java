package hsd.symptom.checker;

import hsd.symptom.checker.constant.Constant;
import hsd.symptom.checker.services.ConnectionDetector;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
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
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionLoginBehavior;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

public class Login_Activity extends ActionBarActivity implements
		ConnectionCallbacks, OnConnectionFailedListener {

	private Toolbar toolbar;

	private LinearLayout linearLayout_sign_up, linearLayout_sign_in;
	private boolean isPanelShown;
	int screenHeight = 0;

	private static final int RC_SIGN_IN = 0;
	// Logcat tag
	private static final String TAG = "MainActivity";

	// Google client to interact with Google API
	private GoogleApiClient mGoogleApiClient;

	// Your Facebook APP ID
	// private static String APP_ID = "1556520331297413"; // Replace with your
	// App
	// ID

	// Instance of Facebook Class
	private Session.StatusCallback sessionStatusCallback;
	private Session currentSession;

	/**
	 * A flag indicating that a PendingIntent is in progress and prevents us
	 * from starting further intents.
	 */
	private boolean mIntentInProgress;

	private boolean mSignInClicked;

	private ConnectionResult mConnectionResult;

	// ********* hangout ************
	private TextView hangoutTvOne;
	private TextView hangoutTvTwo;
	private TextView hangoutTvThree;
	private ObjectAnimator waveOneAnimator;
	private ObjectAnimator waveTwoAnimator;
	private ObjectAnimator waveThreeAnimator;
	private RelativeLayout waveAnimation;

	private EditText editText_username, editText_password;
	private RequestQueue mRequestQueue;
	private ConnectionDetector cd;
	private ProgressDialog pd;

	private EditText editText_sign_up_name, editText_sign_up_password,
			editText_sign_up_password_re_enter, editText_sign_up_mobile,
			editText_sign_up_email, editText_sign_up_gender;
	private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
	private int gender = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("Login");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setIcon(R.drawable.ic_launcher);

		cd = new ConnectionDetector(this);
		mRequestQueue = Volley.newRequestQueue(this);

		pd = new ProgressDialog(this);
		pd.setTitle("Please wait...");
		pd.setMessage("Loggin you in...");
		pd.setCancelable(false);
		pd.setIcon(R.drawable.ic_launcher);
		pd.setIndeterminate(true);

		editText_username = (EditText) findViewById(R.id.editText_username);
		editText_password = (EditText) findViewById(R.id.editText_password);

		editText_sign_up_name = (EditText) findViewById(R.id.editText_sign_up_name);
		editText_sign_up_password = (EditText) findViewById(R.id.editText_sign_up_password);
		editText_sign_up_password_re_enter = (EditText) findViewById(R.id.editText_sign_up_password_re_enter);
		editText_sign_up_mobile = (EditText) findViewById(R.id.editText_sign_up_mobile);
		editText_sign_up_email = (EditText) findViewById(R.id.editText_sign_up_email);
		editText_sign_up_gender = (EditText) findViewById(R.id.editText_sign_up_gender);

		editText_sign_up_gender.setText("Male");

		editText_sign_up_gender.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final Dialog dialog = new Dialog(Login_Activity.this);
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
							editText_sign_up_gender.setText("Male");
						} else if (rd2.isChecked()) {
							editText_sign_up_gender.setText("Female");
						}
						dialog.cancel();
					}
				});

				// now that the dialog is set up, it's time to show it
				dialog.show();
			}
		});

		initiateAnimation();

		// Initializing google plus api client
		mGoogleApiClient = new GoogleApiClient.Builder(this)
				.addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this).addApi(Plus.API)
				.addScope(Plus.SCOPE_PLUS_LOGIN).build();

		sessionStatusCallback = new Session.StatusCallback() {

			@Override
			public void call(Session session, SessionState state,
					Exception exception) {
				onSessionStateChange(session, state, exception);

			}
		};

		linearLayout_sign_up = (LinearLayout) findViewById(R.id.linearLayout_sign_up);
		linearLayout_sign_in = (LinearLayout) findViewById(R.id.linearLayout_sign_in);

		ViewTreeObserver vto = linearLayout_sign_up.getViewTreeObserver();
		vto.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				screenHeight = linearLayout_sign_up.getHeight();
				linearLayout_sign_up.getViewTreeObserver();
			}
		});

		findViewById(R.id.button_google_login).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						signInWithGplus();
						new Handler().post(new Runnable() {

							@Override
							public void run() {
								waveAnimation.setVisibility(View.VISIBLE);
							}
						});
					}

				});

		findViewById(R.id.button_facebook_login).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						Log.d("Image Button", "button Clicked");
						loginToFacebook();
					}

				});

		findViewById(R.id.button_login).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// Intent intent = new Intent(Login_Activity.this,
						// Symptom_Checker_Menu_Activity.class);
						// startActivity(intent);
						if (validate()) {
							if (cd.isConnectingToInternet()) {

								pd.show();
								String url = getResources().getString(
										R.string.host_url)
										+ ""
										+ getResources().getString(
												R.string.login_check_php);
								StringRequest request = new StringRequest(
										Method.POST, url,
										new Listener<String>() {

											@Override
											public void onResponse(
													String response) {
												Log.e("response", response);
												loginSuccessCheck(response);
											}
										}, new ErrorListener() {
											@Override
											public void onErrorResponse(
													VolleyError error) {
												VolleyLog.d("TAG", "Error: "
														+ error.getMessage());
												Toast.makeText(
														getApplicationContext(),
														"Error: ",
														Toast.LENGTH_SHORT)
														.show();
											}
										}) {
									@Override
									protected Map<String, String> getParams()
											throws AuthFailureError {

										JSONObject myo = new JSONObject();
										try {
											myo.put("username",
													editText_username.getText()
															.toString());
											myo.put("password",
													editText_password.getText()
															.toString());

										} catch (JSONException e) {
											e.printStackTrace();
										}
										Log.e(TAG, myo.toString());
										Map<String, String> map = new HashMap<String, String>();
										map.put("login_check", myo.toString());
										return map;
									}
								};
								mRequestQueue.add(request);
							} else {
								Log.e(TAG, "No Internet");
							}
						} else {
							Toast.makeText(getApplicationContext(),
									"Please check your credentials!",
									Toast.LENGTH_LONG).show();
						}
					}

				});
		findViewById(R.id.button_sign_up).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						slideUpDown();
						linearLayout_sign_up.setVisibility(View.VISIBLE);
					}

				});
		findViewById(R.id.button_sign_up_sign_up).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (validate_signup()) {

							checkLoginDetailsSignUpHS(
									editText_sign_up_name.getText().toString(),
									editText_sign_up_email.getText().toString(),
									editText_sign_up_gender.getText()
											.toString(),
									editText_sign_up_mobile.getText()
											.toString(), "healthserve",
									editText_sign_up_password.getText()
											.toString());

							// sendOTPToVerify(editText_sign_up_name.getText()
							// .toString(), editText_sign_up_email
							// .getText().toString(),
							// editText_sign_up_mobile.getText()
							// .toString(),
							// editText_sign_up_gender.getText()
							// .toString(),
							// editText_sign_up_password.getText()
							// .toString(), "");
						}
					}
				});
		findViewById(R.id.button_login_back).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						slideUpDown();
						linearLayout_sign_in.setVisibility(View.VISIBLE);
					}

				});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (isPanelShown) {
			findViewById(R.id.button_login_back).performClick();

			editText_sign_up_name.setError(null);
			editText_sign_up_email.setError(null);
			editText_sign_up_mobile.setError(null);
			editText_sign_up_password.setError(null);
			editText_sign_up_password_re_enter.setError(null);
			editText_sign_up_gender.setText("Male");
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		if (!result.hasResolution()) {
			GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), this,
					0).show();
			return;
		}

		if (!mIntentInProgress) {
			// Store the ConnectionResult for later usage
			mConnectionResult = result;

			if (mSignInClicked) {
				// The user has already clicked 'sign-in' so we attempt to
				// resolve all
				// errors until the user is signed in, or they cancel.
				resolveSignInError();
			}
		}

	}

	private void signInWithGplus() {
		if (!mGoogleApiClient.isConnecting()) {
			mSignInClicked = true;
			resolveSignInError();
		}
	}

	protected void onStart() {
		super.onStart();
		mGoogleApiClient.connect();
	}

	protected void onStop() {
		super.onStop();
		if (mGoogleApiClient.isConnected()) {
			mGoogleApiClient.disconnect();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int responseCode,
			Intent intent) {
		if (requestCode == RC_SIGN_IN) {
			if (responseCode != RESULT_OK) {
				mSignInClicked = false;
			}

			mIntentInProgress = false;

			if (!mGoogleApiClient.isConnecting()) {
				mGoogleApiClient.connect();
			}

		}
		if (currentSession != null) {
			currentSession.onActivityResult(this, requestCode, responseCode,
					intent);
		}
	}

	@Override
	public void onConnected(Bundle arg0) {

		mSignInClicked = false;
		Toast.makeText(this, "User is connected!", Toast.LENGTH_LONG).show();

		// Get user's information
		getProfileInformation();
		// Update the UI after signin

	}

	@Override
	public void onConnectionSuspended(int arg0) {
		mGoogleApiClient.connect();
	}

	/**
	 * Method to resolve any signin errors
	 * */
	private void resolveSignInError() {
		if (mConnectionResult.hasResolution()) {
			try {
				mIntentInProgress = true;
				mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
			} catch (SendIntentException e) {
				mIntentInProgress = false;
				mGoogleApiClient.connect();
			}

			waveAnimation.setVisibility(View.GONE);
		}
	}

	private void getProfileInformation() {
		try {
			if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
				Person currentPerson = Plus.PeopleApi
						.getCurrentPerson(mGoogleApiClient);

				Log.e(TAG, "DisplayName: " + currentPerson.getDisplayName()
						+ " Nickname: " + currentPerson.getNickname()
						+ " FirstName: "
						+ currentPerson.getName().getGivenName()
						+ " LastName: "
						+ currentPerson.getName().getFamilyName() + " email: "
						+ Plus.AccountApi.getAccountName(mGoogleApiClient)
						+ " Gender: " + currentPerson.getGender() + " Phone: "
						+ currentPerson.getGender());

				String g_gender;
				if (currentPerson.getGender() == 0) {
					g_gender = "Male";
				} else {
					g_gender = "Female";
				}
				checkLoginDetails(currentPerson.getDisplayName(),
						Plus.AccountApi.getAccountName(mGoogleApiClient),
						g_gender, "google");

				waveAnimation.setVisibility(View.GONE);
				signOutFromGplus();

			} else {
				waveAnimation.setVisibility(View.GONE);
				Toast.makeText(getApplicationContext(),
						"Person information is null", Toast.LENGTH_LONG).show();
				signOutFromGplus();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void signOutFromGplus() {
		if (mGoogleApiClient.isConnected()) {
			Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
			mGoogleApiClient.disconnect();
			mGoogleApiClient.connect();
		}
	}

	public void slideUpDown() {
		if (!isPanelShown) {
			// Show the panel
			linearLayout_sign_up.layout(linearLayout_sign_up.getLeft(),
					linearLayout_sign_up.getTop() - (screenHeight * 25 / 100),
					linearLayout_sign_up.getRight(),
					linearLayout_sign_up.getBottom()
							- (screenHeight * 25 / 100));

			linearLayout_sign_up.layout(linearLayout_sign_up.getLeft(),
					linearLayout_sign_up.getBottom(),
					linearLayout_sign_up.getRight(), screenHeight);
			linearLayout_sign_up.setVisibility(View.VISIBLE);

			Animation bottomUp = AnimationUtils.loadAnimation(this,
					R.anim.bottom_up);

			linearLayout_sign_up.startAnimation(bottomUp);

			Animation bottomDown = AnimationUtils.loadAnimation(this,
					R.anim.bottom_down);
			linearLayout_sign_in.startAnimation(bottomDown);

			isPanelShown = true;
			linearLayout_sign_in.setVisibility(View.GONE);

			toolbar.setTitle("SignUp");
		} else {
			linearLayout_sign_in.layout(linearLayout_sign_in.getLeft(),
					linearLayout_sign_in.getTop() - (screenHeight * 25 / 100),
					linearLayout_sign_in.getRight(),
					linearLayout_sign_in.getBottom()
							- (screenHeight * 25 / 100));

			linearLayout_sign_in.layout(linearLayout_sign_in.getLeft(),
					linearLayout_sign_in.getBottom(),
					linearLayout_sign_in.getRight(), screenHeight);
			linearLayout_sign_in.setVisibility(View.VISIBLE);

			Animation bottomUp = AnimationUtils.loadAnimation(this,
					R.anim.bottom_up);

			linearLayout_sign_in.startAnimation(bottomUp);

			Animation bottomDown = AnimationUtils.loadAnimation(this,
					R.anim.bottom_down);
			linearLayout_sign_up.startAnimation(bottomDown);

			isPanelShown = false;
			linearLayout_sign_up.setVisibility(View.GONE);

			toolbar.setTitle("Login");
		}
	}

	private void initiateAnimation() {

		waveAnimation = (RelativeLayout) findViewById(R.id.waveAnimation);
		hangoutTvOne = (TextView) findViewById(R.id.hangoutTvOne);
		hangoutTvTwo = (TextView) findViewById(R.id.hangoutTvTwo);
		hangoutTvThree = (TextView) findViewById(R.id.hangoutTvThree);

		waveAnimation();
	}

	@SuppressWarnings("static-access")
	public void waveAnimation() {
		PropertyValuesHolder tvOne_Y = PropertyValuesHolder.ofFloat(
				hangoutTvOne.TRANSLATION_Y, -40.0f);
		PropertyValuesHolder tvOne_X = PropertyValuesHolder.ofFloat(
				hangoutTvOne.TRANSLATION_X, 0);
		waveOneAnimator = ObjectAnimator.ofPropertyValuesHolder(hangoutTvOne,
				tvOne_X, tvOne_Y);
		waveOneAnimator.setRepeatCount(-1);
		waveOneAnimator.setRepeatMode(ValueAnimator.REVERSE);
		waveOneAnimator.setDuration(300);
		waveOneAnimator.start();

		PropertyValuesHolder tvTwo_Y = PropertyValuesHolder.ofFloat(
				hangoutTvTwo.TRANSLATION_Y, -40.0f);
		PropertyValuesHolder tvTwo_X = PropertyValuesHolder.ofFloat(
				hangoutTvTwo.TRANSLATION_X, 0);
		waveTwoAnimator = ObjectAnimator.ofPropertyValuesHolder(hangoutTvTwo,
				tvTwo_X, tvTwo_Y);
		waveTwoAnimator.setRepeatCount(-1);
		waveTwoAnimator.setRepeatMode(ValueAnimator.REVERSE);
		waveTwoAnimator.setDuration(300);
		waveTwoAnimator.setStartDelay(100);
		waveTwoAnimator.start();

		PropertyValuesHolder tvThree_Y = PropertyValuesHolder.ofFloat(
				hangoutTvThree.TRANSLATION_Y, -40.0f);
		PropertyValuesHolder tvThree_X = PropertyValuesHolder.ofFloat(
				hangoutTvThree.TRANSLATION_X, 0);
		waveThreeAnimator = ObjectAnimator.ofPropertyValuesHolder(
				hangoutTvThree, tvThree_X, tvThree_Y);
		waveThreeAnimator.setRepeatCount(-1);
		waveThreeAnimator.setRepeatMode(ValueAnimator.REVERSE);
		waveThreeAnimator.setDuration(300);
		waveThreeAnimator.setStartDelay(200);
		waveThreeAnimator.start();
	}

	public void loginToFacebook() {
		currentSession = new Session.Builder(this).build();
		currentSession.addCallback(sessionStatusCallback);

		Session.OpenRequest openRequest = new Session.OpenRequest(
				Login_Activity.this);
		openRequest.setPermissions(Arrays.asList("public_profile", "email"));
		openRequest.setLoginBehavior(SessionLoginBehavior.SUPPRESS_SSO);
		openRequest.setRequestCode(Session.DEFAULT_AUTHORIZE_ACTIVITY_CODE);
		currentSession.openForPublish(openRequest);
	}

	private void onSessionStateChange(Session session, SessionState state,
			Exception exception) {
		if (session != currentSession) {
			return;
		}

		if (state.isOpened()) {
			// Log in just happened.
			Toast.makeText(getApplicationContext(), "session opened",
					Toast.LENGTH_SHORT).show();

			Request me = Request.newMeRequest(session,
					new Request.GraphUserCallback() {

						@Override
						public void onCompleted(GraphUser user,
								Response response) {
							// TODO Auto-generated method stub
							if (user != null) {
								Log.e("Hello ",
										"Hello " + user.getName() + "!    "
												+ "email "
												+ user.getProperty("email")
												+ "!    " + "gender "
												+ user.getProperty("gender")
												+ "!    ");
								String email = (String) response
										.getGraphObject().getProperty("email");
								Log.e("email", email + "     @");

								checkLoginDetails(user.getName(),
										(String) response.getGraphObject()
												.getProperty("email"), user
												.getProperty("gender")
												.toString(), "facebook");

								waveAnimation.setVisibility(View.GONE);
								currentSession.closeAndClearTokenInformation();
								finish();
							}
						}
					});
			me.executeAsync();
		} else if (state.isClosed()) {
			// Log out just happened. Update the UI.
			Toast.makeText(getApplicationContext(), "session closed",
					Toast.LENGTH_SHORT).show();
		}
	}

	private boolean validate() {
		if (editText_username.getText().toString().trim().length() > 0
				&& editText_password.getText().toString().trim().length() > 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean validate_signup() {
		boolean all_set = true;
		if (editText_sign_up_password_re_enter.getText().toString().length() == 0) {
			editText_sign_up_password_re_enter.setError("re-enter password");
			editText_sign_up_password_re_enter.requestFocus();
			all_set = false;
		} else {
			editText_sign_up_password_re_enter.setError(null);
		}
		if (editText_sign_up_password.getText().toString().length() == 0) {
			editText_sign_up_password.setError("enter password");
			editText_sign_up_password.requestFocus();
			all_set = false;
		} else {
			editText_sign_up_password.setError(null);
		}

		if (!editText_sign_up_password_re_enter.getText().toString()
				.equals(editText_sign_up_password.getText().toString())) {
			editText_sign_up_password_re_enter
					.setError("passwords doesnt match");
			editText_sign_up_password.setError("passwords doesnt match");
			editText_sign_up_password.requestFocus();
			all_set = false;
		} else {
			editText_sign_up_password_re_enter.setError(null);
			editText_sign_up_password.setError(null);
		}

		if (editText_sign_up_gender.getText().toString().length() == 0) {
			editText_sign_up_gender.setError("enter gender");
			editText_sign_up_gender.requestFocus();
			all_set = false;
		} else {
			editText_sign_up_gender.setError(null);
		}
		if (editText_sign_up_mobile.getText().toString().length() != 10) {
			editText_sign_up_mobile.setError("enter mobile number properly");
			editText_sign_up_mobile.requestFocus();
			all_set = false;
		} else {
			editText_sign_up_mobile.setError(null);
		}
		if (!editText_sign_up_email.getText().toString().trim()
				.matches(emailPattern)) {
			editText_sign_up_email.setError("enter email properly");
			editText_sign_up_email.requestFocus();
			all_set = false;
		} else {
			editText_sign_up_email.setError(null);
		}
		if (editText_sign_up_name.getText().toString().length() == 0) {
			editText_sign_up_name.setError("enter firstname lastname");
			editText_sign_up_name.requestFocus();
			all_set = false;
		} else {
			editText_sign_up_name.setError(null);
			String[] nameSplit = editText_sign_up_name.getText().toString()
					.split(" ");
			if (nameSplit.length == 1) {
				editText_sign_up_name.setError("enter firstname lastname");
				editText_sign_up_name.requestFocus();
				all_set = false;
			} else {
				editText_sign_up_name.setError(null);
			}
		}
		return all_set;
	}

	private void loginSuccessCheck(String response) {
		JSONObject userCheck = null;
		try {
			userCheck = new JSONObject(response);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String chekc = "";
		try {
			chekc = userCheck.getString("succees");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (chekc.equals("true")) {
			SharedPreferences pref = getApplicationContext()
					.getSharedPreferences(Constant.MyPREFERENCES, MODE_PRIVATE);
			Editor editor = pref.edit();

			String user_id_set = "", user_name = "User";
			try {
				user_id_set = userCheck.getString("user_id");
				user_name = userCheck.getString("user_name");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			editor.putString(Constant.USER_ID, user_id_set);// Saving details
			editor.putBoolean(Constant.USER_LOGGED_IN, true); // Saving details
			editor.putString(Constant.USER_LOGGED_IN_NAME, user_name); // Saving
																		// details

			// Save the changes in SharedPreferences
			editor.commit(); // commit changes
			finish();
			Intent intent = new Intent(Login_Activity.this,
					Symptom_Checker_Menu_Activity.class);
			startActivity(intent);
		} else {
			Toast.makeText(this, "Please check your credentials!",
					Toast.LENGTH_LONG).show();
		}

		pd.cancel();
	}

	private void checkLoginDetailsSignUpHS(final String name,
			final String email, final String gender, final String mobile,
			final String auther, final String password) {
		mRequestQueue = Volley.newRequestQueue(this);
		String url = getResources().getString(R.string.host_url) + ""
				+ getResources().getString(R.string.check_hs_login_exists_php);
		StringRequest request = new StringRequest(Method.POST, url,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {

						Log.e("resp checkLoginDetails", response);

						JSONObject jsonObject;
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
									"Already registered", Toast.LENGTH_LONG)
									.show();
							onBackPressed();
						} else {
							finish();
							Intent intent = new Intent(Login_Activity.this,
									CheckSMSAutoActivity.class);
							startActivity(intent);
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
				map.put("name", name);
				String[] nameSplit = name.split(" ");
				map.put("fname", nameSplit[0]);
				String lastName = "";
				for (int i = 1; i < nameSplit.length; i++) {
					lastName = lastName + nameSplit[i] + " ";
				}
				map.put("lname", lastName);
				map.put("email", email);
				map.put("gender", gender);
				map.put("mobile", mobile);
				map.put("from", auther);
				map.put("password", password);
				return map;
			}
		};
		mRequestQueue.add(request);
	}

	private void checkLoginDetails(final String name, final String email,
			final String gender, final String auther) {
		mRequestQueue = Volley.newRequestQueue(this);
		String url = getResources().getString(R.string.host_url)
				+ ""
				+ getResources().getString(
						R.string.check_social_login_exists_php);
		StringRequest request = new StringRequest(Method.POST, url,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {

						Log.e("resp checkLoginDetails", response);

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
							SharedPreferences pref = getApplicationContext()
									.getSharedPreferences(
											Constant.MyPREFERENCES,
											MODE_PRIVATE);
							Editor editor = pref.edit();

							String user_id_set = "", user_name = "User";
							try {
								user_id_set = jsonObject.getString("user_id");
								user_name = jsonObject.getString("user_name");
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							editor.putString(Constant.USER_ID, user_id_set);// Saving
																			// details
							editor.putBoolean(Constant.USER_LOGGED_IN, true); // Saving
																				// details
							editor.putString(Constant.USER_LOGGED_IN_NAME,
									user_name); // Saving
												// details

							// Save the changes in SharedPreferences
							editor.commit(); // commit changes
							finish();
							Intent intent = new Intent(Login_Activity.this,
									Symptom_Checker_Menu_Activity.class);
							startActivity(intent);
						} else {
							finish();
							Intent intent = new Intent(Login_Activity.this,
									Login_With_Google_Next_Activity.class);
							intent.putExtra("name", name);
							intent.putExtra("email", email);
							intent.putExtra("gender", gender);
							intent.putExtra("from", auther);
							startActivity(intent);
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
				map.put("name", name);
				map.put("email", email);
				map.put("gender", gender);
				map.put("from", auther);
				return map;
			}
		};
		mRequestQueue.add(request);
	}

	private void sendOTPToVerify(final String name, final String email,
			final String phoneNumber, final String gender, final String password) {

		Log.e("sendOTPToVerify", "sendOTPToVerify called");

		mRequestQueue = Volley.newRequestQueue(this);
		pd.show();
		String url = getResources().getString(R.string.host_url)
				+ ""
				+ getResources().getString(
						R.string.get_register_user_with_otp_php);
		StringRequest request = new StringRequest(Method.POST, url,
				new Listener<String>() {

					@Override
					public void onResponse(String response) {

						String otp = "";
						Log.e("resp", response);

						JSONObject jsonObject;
						try {
							jsonObject = new JSONObject(response);
							otp = jsonObject.getString("otp");
							Log.e("otp", jsonObject.getString("otp"));
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						pd.cancel();
						finish();
						Intent intent = new Intent(Login_Activity.this,
								CheckSMSAutoActivity.class);
						intent.putExtra("otp", otp);
						intent.putExtra("mobileNumber", editText_sign_up_mobile
								.getText().toString());
						intent.putExtra("email", editText_sign_up_email
								.getText().toString());
						startActivity(intent);
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
				map.put("name", name);
				String[] nameSplit = name.split(" ");
				map.put("fname", nameSplit[0]);
				String lastName = "";
				for (int i = 1; i < nameSplit.length; i++) {
					lastName = lastName + nameSplit[i] + " ";
				}
				map.put("lname", lastName);
				map.put("email", email);
				map.put("phoneNumber", phoneNumber);
				map.put("gender", gender);
				map.put("password", password);
				map.put("from", "healthserve");
				return map;
			}
		};
		mRequestQueue.add(request);
	}
}
