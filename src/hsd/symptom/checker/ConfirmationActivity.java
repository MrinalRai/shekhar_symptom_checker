package hsd.symptom.checker;

import hsd.symptom.checker.database.Timing;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ParseException;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
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

public class ConfirmationActivity extends Activity {

	private int doc_id;
	private RequestQueue mRequestQueue;
	private ProgressDialog pd;
	String TAG = "DoctorDetailsActivity";
	private JSONObject scheduleString;

	private ArrayList<Timing> listSortTimes;
	private TextView textView;
	LinearLayout layout_popup;
	TextView textView_popup, textviewArea, textviewDate, textviewTime;
	String date_selected, time_selected;

	private TableRow.LayoutParams layoutParams, layoutParamsWrapWidth;
	private int width;

	// private Button button_confirm;
	private TableLayout table_content;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.confirmation);

		doc_id = getIntent().getIntExtra("doc_id", 0);

		pd = new ProgressDialog(this);
		pd.setTitle("Please wait...");
		pd.setMessage("Getting Schedule...");
		pd.setCancelable(false);
		pd.setIcon(R.drawable.ic_launcher);
		pd.setIndeterminate(true);

		mRequestQueue = Volley.newRequestQueue(this);

		DisplayMetrics display = getResources().getDisplayMetrics();

		table_content = (TableLayout) findViewById(R.id.table_content);

		width = (int) (display.widthPixels / 2.5);
		layoutParamsWrapWidth = new TableRow.LayoutParams(
				TableRow.LayoutParams.WRAP_CONTENT, width / 5);
		layoutParams = new TableRow.LayoutParams(width, width / 5);

		findDoctors(doc_id);
	}

	private void findDoctors(int _id) {

		pd.show();
		String url = getResources().getString(R.string.host_url) + ""
				+ getResources().getString(R.string.get_test_php);
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
						Toast.makeText(getApplicationContext(), "Error: ",
								Toast.LENGTH_SHORT).show();
						pd.cancel();
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {

				JSONObject myo = new JSONObject();
				try {
					myo.put("doc_id", doc_id);

				} catch (JSONException e) {
					e.printStackTrace();
				}
				Map<String, String> map = new HashMap<String, String>();
				map.put("doc_id_detail", myo.toString());
				return map;
			}
		};
		mRequestQueue.add(request);
	}

	@SuppressLint("SimpleDateFormat")
	private void processData(String response) {
		JSONObject object = null;
		try {
			object = new JSONObject(response);

			scheduleString = object.getJSONObject("schedule");

			ArrayList<Timing> listMain = new ArrayList<Timing>();

			Iterator<String> keys = scheduleString.keys();

			while (keys.hasNext()) {
				// loop to get the dynamic key

				String currentDynamicKey = (String) keys.next();

				// get the value of the dynamic key
				JSONObject currentDynamicValue = null;
				try {
					currentDynamicValue = scheduleString
							.getJSONObject(currentDynamicKey);
				} catch (JSONException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				Log.e("date", currentDynamicKey.toString());

				Iterator<String> keysDT = currentDynamicValue.keys();

				while (keysDT.hasNext()) {
					// loop to get the dynamic key
					String currentDynamicKeyTime = (String) keysDT.next();
					// get the value of the dynamic key
					JSONObject currentDynamicsLOT = null;
					try {
						currentDynamicsLOT = currentDynamicValue
								.getJSONObject(currentDynamicKeyTime);
					} catch (JSONException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// Log.e("time", currentDynamicKeyTime);
					// Log.e("slot", currentDynamicsLOT.toString());

					Iterator<String> keysSlots = currentDynamicsLOT.keys();

					ArrayList<String> timings = new ArrayList<>();
					while (keysSlots.hasNext()) {
						String currentDynamicKeySlots = (String) keysSlots
								.next();
						// Log.e("keysSlots", currentDynamicKeySlots);

						try {
							if (currentDynamicsLOT.getString(
									currentDynamicKeySlots).length() != 11) {
								timings.add("0"
										+ currentDynamicsLOT
												.getString(currentDynamicKeySlots));
							} else {
								timings.add(currentDynamicsLOT
										.getString(currentDynamicKeySlots));
							}
							// Log.e("time check", currentDynamicsLOT
							// .getString(currentDynamicKeySlots));
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}

					Timing mTiming = new Timing();
					mTiming.setDate(currentDynamicKey);
					mTiming.setTime(currentDynamicKeyTime);
					mTiming.setSlots(timings);
					listMain.add(mTiming);
				}
			}

			Collections.sort(listMain, new Comparator<Timing>() {

				public int compare(Timing lhs, Timing rhs) {

					try {
						SimpleDateFormat dateFormatlhs = new SimpleDateFormat(
								"yyyy-MM-dd");
						Date convertedDatelhs = null;
						try {
							convertedDatelhs = dateFormatlhs.parse(lhs
									.getDate());
						} catch (java.text.ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Calendar calendarlhs = Calendar.getInstance();
						calendarlhs.setTime(convertedDatelhs);

						SimpleDateFormat dateFormatrhs = new SimpleDateFormat(
								"yyyy-MM-dd");
						Date convertedDaterhs = null;
						try {
							convertedDaterhs = dateFormatrhs.parse(rhs
									.getDate());
						} catch (java.text.ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Calendar calendarrhs = Calendar.getInstance();
						calendarrhs.setTime(convertedDaterhs);

						if (calendarlhs.getTimeInMillis() > calendarrhs
								.getTimeInMillis()) {
							return -1;
						} else {
							return 1;
						}
					} catch (ParseException e) {
						e.printStackTrace();
					}
					return 0;
				}
			});

			String prev = listMain.get(listMain.size() - 1).getDate();

			TableRow contentRow = new TableRow(getApplicationContext());
			textView = new TextView(getApplicationContext());

			listSortTimes = new ArrayList<Timing>();
			for (int i = listMain.size() - 1; i >= 0; i--) {
				Timing mTiming = listMain.get(i);

				Log.e("prev", prev);
				Log.e("prev", mTiming.getDate());
				if (!prev.equals(mTiming.getDate())) {
					Log.e("sorrtedd", listSortTimes.get(0).getDate());
					contentRow = new TableRow(getApplicationContext());
					textView = new TextView(getApplicationContext());
					textView.setGravity(Gravity.CENTER);
					textView.setLayoutParams(layoutParamsWrapWidth);
					textView.setTextSize(20);
					Drawable img = getResources().getDrawable(R.drawable.date);
					img.setBounds(0, 0, 30, 30);
					textView.setCompoundDrawables(img, null, null, null);

					SimpleDateFormat fromUser = new SimpleDateFormat(
							"yyyy-MM-dd");
					SimpleDateFormat myFormat = new SimpleDateFormat("d/M/yy");

					try {
						String reformattedStr = myFormat.format(fromUser
								.parse(listSortTimes.get(0).getDate()));

						textView.setText(reformattedStr.trim());
					} catch (java.text.ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					textView.setTextColor(Color.BLACK);
					contentRow.addView(textView);
					// table_content.addView(contentRow);

					for (int j = 0; j < listSortTimes.size(); j++) {

						if (listSortTimes.size() == 2) {
							for (int j1 = 0; j1 < listSortTimes.size(); j1++) {
								char charac = listSortTimes.get(j1).getTime()
										.charAt(0);
								if (charac == 'E') {
									Timing timing = listSortTimes.get(j1);
									listSortTimes.remove(j1);
									listSortTimes.add(0, timing);
								} else if (charac == 'N') {
									Timing timing = listSortTimes.get(j1);
									listSortTimes.remove(j1);
									listSortTimes.add(1, timing);
								}
							}
						} else if (listSortTimes.size() == 3) {
							for (int j1 = 0; j1 < listSortTimes.size(); j1++) {
								char charac = listSortTimes.get(j1).getTime()
										.charAt(0);
								if (charac == 'A') {
									Timing timing = listSortTimes.get(j1);
									listSortTimes.remove(j1);
									listSortTimes.add(0, timing);
								} else if (charac == 'E') {
									Timing timing = listSortTimes.get(j1);
									listSortTimes.remove(j1);
									listSortTimes.add(1, timing);
								} else if (charac == 'N') {
									Timing timing = listSortTimes.get(j1);
									listSortTimes.remove(j1);
									listSortTimes.add(2, timing);
								}
							}
						} else if (listSortTimes.size() == 4) {
							for (int j1 = 0; j1 < listSortTimes.size(); j1++) {
								char charac = listSortTimes.get(j1).getTime()
										.charAt(0);
								if (charac == 'M') {
									Timing timing = listSortTimes.get(j1);
									listSortTimes.remove(j1);
									listSortTimes.add(0, timing);
								} else if (charac == 'A') {
									Timing timing = listSortTimes.get(j1);
									listSortTimes.remove(j1);
									listSortTimes.add(1, timing);
								} else if (charac == 'E') {
									Timing timing = listSortTimes.get(j1);
									listSortTimes.remove(j1);
									listSortTimes.add(2, timing);
								} else if (charac == 'N') {
									Timing timing = listSortTimes.get(j1);
									listSortTimes.remove(j1);
									listSortTimes.add(3, timing);
								}
							}
						}
						contentRow = new TableRow(getApplicationContext());
						textView = new TextView(getApplicationContext());
						textView.setGravity(Gravity.CENTER);
						textView.setLayoutParams(layoutParams);
						textView.setTextSize(20);
						textView.setText(listSortTimes.get(j).getTime());
						textView.setTextColor(Color.BLACK);
						contentRow.addView(textView);
						table_content.addView(contentRow);

						ArrayList<String> timeArray = new ArrayList<>();

						for (int j1 = 0; j1 < listSortTimes.get(j).getSlots()
								.size(); j1++) {
							timeArray.add(listSortTimes.get(j).getSlots()
									.get(j1));
						}

						Collections.sort(timeArray, new Comparator<String>() {
							public int compare(String v1, String v2) {
								return v1.compareTo(v2);
							}
						});

						for (int j2 = 0; j2 < timeArray.size(); j2++) {
							textView = new TextView(getApplicationContext());
							textView.setGravity(Gravity.CENTER);
							textView.setLayoutParams(layoutParams);
							textView.setTextSize(20);
							textView.setText(timeArray.get(j2));
							textView.setTextColor(Color.BLACK);
							contentRow.addView(textView);
							textView.setOnClickListener(new OnClickListener() {

								@Override
								public void onClick(View v) {
									date_selected = listSortTimes.get(0)
											.getDate();
									time_selected = textView.getText()
											.toString();
									// showPopUp();
								}
							});

							if (j2 % 2 == 0 && j2 != 0) {
								table_content.addView(contentRow);
								contentRow = new TableRow(
										getApplicationContext());
							}
						}
						try {
							table_content.addView(contentRow);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					listSortTimes.clear();
					Log.e("clear", "clear");
					listSortTimes = new ArrayList<>();
					listSortTimes.add(mTiming);
					prev = mTiming.getDate();
				} else {
					listSortTimes.add(mTiming);
					Log.e("adddddeddd", mTiming.getDate());
					prev = mTiming.getDate();
				}
			}

			Log.e("sorrtedd", listSortTimes.get(0).getDate());
			contentRow = new TableRow(getApplicationContext());
			textView = new TextView(getApplicationContext());
			textView.setGravity(Gravity.CENTER);
			textView.setLayoutParams(layoutParamsWrapWidth);
			textView.setTextSize(20);

			SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat myFormat = new SimpleDateFormat("d/M/yy");

			try {
				String reformattedStr = myFormat.format(fromUser
						.parse(listSortTimes.get(0).getDate()));

				textView.setText(reformattedStr.trim());
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Drawable img = getResources().getDrawable(R.drawable.date);
			img.setBounds(0, 0, 30, 30);
			textView.setCompoundDrawables(img, null, null, null);
			textView.setTextColor(Color.BLACK);
			contentRow.addView(textView);
			// table_content.addView(contentRow);

			for (int j = 0; j < listSortTimes.size(); j++) {
				Log.e("listSortTimes size", listSortTimes.size() + "");

				if (listSortTimes.size() == 2) {
					for (int j1 = 0; j1 < listSortTimes.size(); j1++) {
						char charac = listSortTimes.get(j1).getTime().charAt(0);
						if (charac == 'E') {
							Timing timing = listSortTimes.get(j1);
							listSortTimes.remove(j1);
							listSortTimes.add(0, timing);
						} else if (charac == 'N') {
							Timing timing = listSortTimes.get(j1);
							listSortTimes.remove(j1);
							listSortTimes.add(1, timing);
						}
					}
				} else if (listSortTimes.size() == 3) {
					for (int j1 = 0; j1 < listSortTimes.size(); j1++) {
						char charac = listSortTimes.get(j1).getTime().charAt(0);
						if (charac == 'A') {
							Timing timing = listSortTimes.get(j1);
							listSortTimes.remove(j1);
							listSortTimes.add(0, timing);
						} else if (charac == 'E') {
							Timing timing = listSortTimes.get(j1);
							listSortTimes.remove(j1);
							listSortTimes.add(1, timing);
						} else if (charac == 'N') {
							Timing timing = listSortTimes.get(j1);
							listSortTimes.remove(j1);
							listSortTimes.add(2, timing);
						}
					}
				} else if (listSortTimes.size() == 4) {
					for (int j1 = 0; j1 < listSortTimes.size(); j1++) {
						char charac = listSortTimes.get(j1).getTime().charAt(0);
						if (charac == 'M') {
							Timing timing = listSortTimes.get(j1);
							listSortTimes.remove(j1);
							listSortTimes.add(0, timing);
						} else if (charac == 'A') {
							Timing timing = listSortTimes.get(j1);
							listSortTimes.remove(j1);
							listSortTimes.add(1, timing);
						} else if (charac == 'E') {
							Timing timing = listSortTimes.get(j1);
							listSortTimes.remove(j1);
							listSortTimes.add(2, timing);
						} else if (charac == 'N') {
							Timing timing = listSortTimes.get(j1);
							listSortTimes.remove(j1);
							listSortTimes.add(3, timing);
						}
					}
				}

				contentRow = new TableRow(getApplicationContext());
				textView = new TextView(getApplicationContext());
				textView.setLayoutParams(layoutParams);
				textView.setTextSize(20);
				textView.setText(listSortTimes.get(j).getTime());
				textView.setTextColor(Color.BLACK);
				contentRow.addView(textView);
				table_content.addView(contentRow);
				contentRow = new TableRow(getApplicationContext());

				ArrayList<String> timeArray = new ArrayList<>();

				for (int j1 = 0; j1 < listSortTimes.get(j).getSlots().size(); j1++) {
					timeArray.add(listSortTimes.get(j).getSlots().get(j1));
				}

				Collections.sort(timeArray, new Comparator<String>() {
					public int compare(String v1, String v2) {
						return v1.compareTo(v2);
					}
				});

				for (int j2 = 0; j2 < timeArray.size(); j2++) {
					textView = new TextView(getApplicationContext());
					textView.setGravity(Gravity.CENTER);
					textView.setLayoutParams(layoutParams);
					textView.setTextSize(20);
					textView.setText(timeArray.get(j2));
					textView.setTextColor(Color.BLACK);
					contentRow.addView(textView);
					textView.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {

							SimpleDateFormat fromUser = new SimpleDateFormat(
									"yyyy-MM-dd");
							SimpleDateFormat myFormat = new SimpleDateFormat(
									"d/M/yy");

							try {
								String reformattedStr = myFormat
										.format(fromUser.parse(listSortTimes
												.get(0).getDate()));

								date_selected = reformattedStr.trim();
							} catch (java.text.ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							time_selected = textView.getText().toString();

							// textviewArea.setText(type_names.get(0));
							// textviewDate.setText(date_selected);
							// textviewTime.setText(time_selected);
							// showPopUp();
						}
					});

					int jj = j2 + 1;
					if (jj % 2 == 0) {
						table_content.addView(contentRow);
						contentRow = new TableRow(getApplicationContext());
					}
				}
				try {
					table_content.addView(contentRow);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
