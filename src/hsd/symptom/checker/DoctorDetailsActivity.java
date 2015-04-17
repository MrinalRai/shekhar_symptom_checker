//package hsd.symptom.checker;
//
//import hsd.symptom.checker.database.Timing;
//
//import java.text.DateFormatSymbols;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Map;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.Color;
//import android.graphics.drawable.Drawable;
//import android.net.ParseException;
//import android.os.Bundle;
//import android.support.v4.app.FragmentActivity;
//import android.util.DisplayMetrics;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TableLayout;
//import android.widget.TableRow;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request.Method;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.Response.Listener;
//import com.android.volley.VolleyError;
//import com.android.volley.VolleyLog;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
//import com.nostra13.universalimageloader.core.assist.FailReason;
//import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
//import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
//import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
//
//public class DoctorDetailsActivity extends FragmentActivity {
//
//	private int doc_id;
//	private RequestQueue mRequestQueue;
//	private DisplayImageOptions options;
//	private ProgressDialog pd;
//	private String TAG = "DoctorDetailsActivity";
//
//	private ArrayList<String> type_names;
//
//	private ImageView circularImageView;
//	private TextView textViewInitials, textViewName, textViewSpeciality,
//			textViewFee, textViewLocation;
//
//	private TextView textViewSelectDate, textViewSelectDateth,
//			textViewSelectDay, textViewSelectMonth;
//
//	private TextView textViewMorning, textViewAfternoon, textViewEvening,
//			textViewNight;
//
//	private String session;
//
//	private TextView textviewArea, textviewDayDate, textviewSessionTime;
//
//	private RelativeLayout relativeLayoutDateSelector;
//
//	private Button button_confirm;
//
//	private JSONObject scheduleString;
//	private TableRow.LayoutParams layoutParams, layoutParamsWrapWidth;
//	private int width;
//	private ArrayList<Timing> listSortTimes;
//	private TextView textView;
//	private String date_selected, time_selected;
//	private TableLayout table_morning, table_afternoon, table_evening,
//			table_night;
//
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.doctor_details_activity);
//		doc_id = getIntent().getIntExtra("doc_id", 0);
//
//		mRequestQueue = Volley.newRequestQueue(this);
//		pd = new ProgressDialog(this);
//		pd.setTitle("Please wait...");
//		pd.setMessage("Getting list of symptoms...");
//		pd.setCancelable(false);
//		pd.setIcon(R.drawable.ic_launcher);
//		pd.setIndeterminate(true);
//
//		DisplayMetrics display = getResources().getDisplayMetrics();
//
//		table_morning = (TableLayout) findViewById(R.id.table_morning);
//		table_afternoon = (TableLayout) findViewById(R.id.table_afternoon);
//		table_evening = (TableLayout) findViewById(R.id.table_evening);
//		table_night = (TableLayout) findViewById(R.id.table_night);
//
//		table_morning.setVisibility(View.GONE);
//		table_afternoon.setVisibility(View.GONE);
//		table_evening.setVisibility(View.GONE);
//		table_night.setVisibility(View.GONE);
//
//		width = (int) (display.widthPixels / 2.5);
//		layoutParamsWrapWidth = new TableRow.LayoutParams(
//				TableRow.LayoutParams.WRAP_CONTENT, width / 5);
//		layoutParams = new TableRow.LayoutParams(width, width / 5);
//
//		options = new DisplayImageOptions.Builder()
//				.showImageOnLoading(R.drawable.no_image)
//				.showImageForEmptyUri(R.drawable.no_image)
//				.showImageOnFail(R.drawable.no_image).cacheInMemory(true)
//				.cacheOnDisk(true).considerExifParams(true)
//				.displayer(new RoundedBitmapDisplayer(20)).build();
//
//		findDoctors(doc_id);
//
//		circularImageView = (ImageView) findViewById(R.id.circularImageView);
//		textViewInitials = (TextView) findViewById(R.id.textViewInitials);
//		textViewName = (TextView) findViewById(R.id.textViewName);
//		textViewSpeciality = (TextView) findViewById(R.id.textViewSpeciality);
//		textViewFee = (TextView) findViewById(R.id.textViewFee);
//		textViewLocation = (TextView) findViewById(R.id.textViewLocation);
//
//		textViewSelectDate = (TextView) findViewById(R.id.textViewSelectDate);
//		textViewSelectDay = (TextView) findViewById(R.id.textViewSelectDay);
//		textViewSelectDateth = (TextView) findViewById(R.id.textViewSelectDateth);
//		textViewSelectMonth = (TextView) findViewById(R.id.textViewSelectMonth);
//
//		textViewMorning = (TextView) findViewById(R.id.textViewMorning);
//		textViewAfternoon = (TextView) findViewById(R.id.textViewAfternoon);
//		textViewEvening = (TextView) findViewById(R.id.textViewEvening);
//		textViewNight = (TextView) findViewById(R.id.textViewNight);
//
//		textViewMorning.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				session = "(M)";
//				textviewSessionTime.setText("Not Selected");
//				table_morning.setVisibility(View.VISIBLE);
//				table_afternoon.setVisibility(View.GONE);
//				table_evening.setVisibility(View.GONE);
//				table_night.setVisibility(View.GONE);
//			}
//		});
//
//		textViewAfternoon.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				session = "(A)";
//				textviewSessionTime.setText("Not Selected");
//				table_morning.setVisibility(View.GONE);
//				table_afternoon.setVisibility(View.VISIBLE);
//				table_evening.setVisibility(View.GONE);
//				table_night.setVisibility(View.GONE);
//			}
//		});
//		textViewEvening.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				session = "(E)";
//				textviewSessionTime.setText("Not Selected");
//				table_morning.setVisibility(View.GONE);
//				table_afternoon.setVisibility(View.GONE);
//				table_evening.setVisibility(View.VISIBLE);
//				table_night.setVisibility(View.GONE);
//			}
//		});
//		textViewNight.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				session = "(N)";
//				textviewSessionTime.setText("Not Selected");
//				table_morning.setVisibility(View.GONE);
//				table_afternoon.setVisibility(View.GONE);
//				table_evening.setVisibility(View.GONE);
//				table_night.setVisibility(View.VISIBLE);
//			}
//		});
//
//		textviewArea = (TextView) findViewById(R.id.textviewArea);
//		textviewDayDate = (TextView) findViewById(R.id.textviewDayDate);
//		textviewSessionTime = (TextView) findViewById(R.id.textviewSessionTime);
//
//		relativeLayoutDateSelector = (RelativeLayout) findViewById(R.id.relativeLayoutDateSelector);
//
//		button_confirm = (Button) findViewById(R.id.button_confirm);
//
//		Calendar calendar = Calendar.getInstance();
//
//		int month = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
//		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//		int year = calendar.get(Calendar.YEAR);
//		int hr = calendar.get(Calendar.HOUR_OF_DAY);
//		disableTextView(hr);
//
//		textViewSelectDate.setText("" + dayOfMonth);
//		textViewSelectDay.setText("" + getDayOfWeek(dayOfWeek));
//		textViewSelectDateth.setText("" + getPoster(dayOfMonth));
//		textViewSelectMonth.setText("" + getMonthForInt(month));
//
//		textviewDayDate.setText("Date: " + dayOfMonth + "/" + (month + 1) + "/"
//				+ year);
//
//		button_confirm.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				Intent next = new Intent(DoctorDetailsActivity.this,
//						ConfirmAppointmentActivity.class);
//				next.putExtra("Patient", "Patient Name: SheKhar Khandeparkar");
//				next.putExtra("Reason", "Reason: Blur Vision");
//				next.putExtra("Doctor", "Doctor Name: "
//						+ textViewName.getText().toString());
//				next.putExtra("Place", textviewArea.getText().toString() + "");
//				next.putExtra("Date", textviewDayDate.getText().toString() + "");
//				next.putExtra("Time", textviewSessionTime.getText().toString()
//						+ "");
//				startActivity(next);
//
//			}
//		});
//
//		relativeLayoutDateSelector.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent next = new Intent(DoctorDetailsActivity.this,
//						CalendarView.class);
//				startActivityForResult(next, 1);
//			}
//		});
//	}
//
//	@Override
//	public void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
//		int day = data.getIntExtra("Day", 0);
//		int month = data.getIntExtra("Month", 0);
//		int year = data.getIntExtra("Year", 0);
//		if (data.getIntExtra("ok", 0) == 1) {
//			Calendar calendar = Calendar.getInstance();
//			calendar.set(Calendar.DAY_OF_MONTH, day);
//			calendar.set(Calendar.MONTH, month);
//			calendar.set(Calendar.YEAR, year);
//			textViewSelectDate.setText("" + day);
//			textViewSelectDay.setText(""
//					+ getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)));
//			textViewSelectDateth.setText("" + getPoster(day));
//			textViewSelectMonth.setText("" + getMonthForInt(month));
//
//			mRequestQueue = Volley.newRequestQueue(this);
//
//			String dayString = day + "";
//			if (dayString.length() == 1) {
//				dayString = "0" + day;
//			}
//			String monthString = (month + 1) + "";
//			if (monthString.length() == 1) {
//				monthString = "0" + (month + 1);
//			}
//
//			findDoctorsSchedule(doc_id, year + "-" + monthString + "-"
//					+ dayString);
//
//			textviewDayDate.setText("Date: " + day + "/" + (month + 1) + "/"
//					+ year);
//		}
//	}
//
//	private void findDoctorsSchedule(int doc_id2, final String string) {
//		pd.show();
//		String url = getResources().getString(R.string.host_url) + ""
//				+ getResources().getString(R.string.get_test_php);
//		StringRequest request = new StringRequest(Method.POST, url,
//				new Listener<String>() {
//
//					@Override
//					public void onResponse(String response) {
//						pd.cancel();
//						processData(response);
//					}
//				}, new Response.ErrorListener() {
//					@Override
//					public void onErrorResponse(VolleyError error) {
//						VolleyLog.d("TAG", "Error: " + error.getMessage());
//						Toast.makeText(getApplicationContext(), "Error: ",
//								Toast.LENGTH_SHORT).show();
//						pd.cancel();
//					}
//				}) {
//			@Override
//			protected Map<String, String> getParams() throws AuthFailureError {
//
//				JSONObject myo = new JSONObject();
//				try {
//					myo.put("doc_id", doc_id);
//					myo.put("date_sent", string);
//
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//				Map<String, String> map = new HashMap<String, String>();
//				map.put("doc_id_detail", myo.toString());
//				Log.e("doc_id_detail", myo.toString());
//				return map;
//			}
//		};
//		mRequestQueue.add(request);
//	}
//
//	private void findDoctors(int _id) {
//
//		pd.show();
//		String url = getResources().getString(R.string.host_url) + ""
//				+ getResources().getString(R.string.get_particular_doctor_php);
//		StringRequest request = new StringRequest(Method.POST, url,
//				new Listener<String>() {
//
//					@Override
//					public void onResponse(String response) {
//						pd.cancel();
//						processData(response);
//					}
//				}, new Response.ErrorListener() {
//					@Override
//					public void onErrorResponse(VolleyError error) {
//						VolleyLog.d("TAG", "Error: " + error.getMessage());
//						Toast.makeText(getApplicationContext(), "Error: ",
//								Toast.LENGTH_SHORT).show();
//						pd.cancel();
//					}
//				}) {
//			@Override
//			protected Map<String, String> getParams() throws AuthFailureError {
//
//				JSONObject myo = new JSONObject();
//				try {
//					myo.put("doc_id", doc_id);
//
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//				Map<String, String> map = new HashMap<String, String>();
//				map.put("doc_id_detail", myo.toString());
//				return map;
//			}
//		};
//		mRequestQueue.add(request);
//	}
//
//	private void processData(String response) {
//
//		textViewMorning.setEnabled(true);
//		textViewAfternoon.setEnabled(true);
//		textViewEvening.setEnabled(true);
//		textViewNight.setEnabled(true);
//
//		table_morning.removeAllViews();
//		table_afternoon.removeAllViews();
//		table_evening.removeAllViews();
//		table_night.removeAllViews();
//
//		JSONObject object = null;
//		try {
//			object = new JSONObject(response);
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		try {
//			String name = object.getString("DisplayName");
//			String[] splited = name.split("\\s+");
//
//			try {
//				String fName = splited[0];
//				String lName = splited[1];
//				textViewInitials
//						.setText(fName.charAt(0) + "" + lName.charAt(0));
//			} catch (Exception e) {
//			}
//
//			textViewName.setText(name);
//			textViewSpeciality.setText(object.getString("speciality"));
//			textViewFee.setText(object.getString("fee"));
//			textViewLocation.setText(object.getString("area"));
//
//			textviewArea.setText("Area: " + object.getString("area"));
//
//			type_names = new ArrayList<>();
//			type_names.add(object.getString("area"));
//
//			ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
//					DoctorDetailsActivity.this).build();
//
//			ImageLoader imageLoader = ImageLoader.getInstance();
//			imageLoader.init(config);
//			imageLoader.displayImage(object.getString("DocImage"),
//					circularImageView, options, new ImageLoadingListener() {
//						final List<String> displayedImages = Collections
//								.synchronizedList(new LinkedList<String>());
//
//						@Override
//						public void onLoadingStarted(String imageUri, View view) {
//							// TODO Auto-generated method stub
//							textViewInitials.setVisibility(View.VISIBLE);
//						}
//
//						@Override
//						public void onLoadingFailed(String imageUri, View view,
//								FailReason failReason) {
//							textViewInitials.setVisibility(View.VISIBLE);
//						}
//
//						@Override
//						public void onLoadingComplete(String imageUri,
//								View view, Bitmap loadedImage) {
//							// TODO Auto-generated method stub
//							if (loadedImage != null) {
//								ImageView imageView = (ImageView) view;
//								boolean firstDisplay = !displayedImages
//										.contains(imageUri);
//								if (firstDisplay) {
//									FadeInBitmapDisplayer.animate(imageView,
//											500);
//									displayedImages.add(imageUri);
//								}
//								textViewInitials.setVisibility(View.GONE);
//							}
//						}
//
//						@Override
//						public void onLoadingCancelled(String imageUri,
//								View view) {
//							// TODO Auto-generated method stub
//						}
//					});
//
//			scheduleString = object.getJSONObject("schedule");
//
//			getTimeTable(scheduleString);
//		} catch (JSONException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	private void getTimeTable(JSONObject schedule) {
//		ArrayList<Timing> listMain = new ArrayList<Timing>();
//
//		Iterator<String> keys = schedule.keys();
//
//		while (keys.hasNext()) {
//			// loop to get the dynamic key
//
//			String currentDynamicKey = (String) keys.next();
//
//			// get the value of the dynamic key
//			JSONObject currentDynamicValue = null;
//			try {
//				currentDynamicValue = schedule.getJSONObject(currentDynamicKey);
//			} catch (JSONException e2) {
//				// TODO Auto-generated catch block
//				e2.printStackTrace();
//			}
//
//			Log.e("date", currentDynamicKey.toString());
//
//			Iterator<String> keysDT = currentDynamicValue.keys();
//
//			while (keysDT.hasNext()) {
//				// loop to get the dynamic key
//				String currentDynamicKeyTime = (String) keysDT.next();
//				// get the value of the dynamic key
//				JSONObject currentDynamicsLOT = null;
//				try {
//					currentDynamicsLOT = currentDynamicValue
//							.getJSONObject(currentDynamicKeyTime);
//				} catch (JSONException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//
//				// Log.e("time", currentDynamicKeyTime);
//				// Log.e("slot", currentDynamicsLOT.toString());
//
//				Iterator<String> keysSlots = currentDynamicsLOT.keys();
//
//				ArrayList<String> timings = new ArrayList<>();
//				while (keysSlots.hasNext()) {
//					String currentDynamicKeySlots = (String) keysSlots.next();
//					// Log.e("keysSlots", currentDynamicKeySlots);
//
//					try {
//						if (currentDynamicsLOT
//								.getString(currentDynamicKeySlots).length() != 11) {
//							timings.add("0"
//									+ currentDynamicsLOT
//											.getString(currentDynamicKeySlots));
//						} else {
//							timings.add(currentDynamicsLOT
//									.getString(currentDynamicKeySlots));
//						}
//						// Log.e("time check", currentDynamicsLOT
//						// .getString(currentDynamicKeySlots));
//					} catch (JSONException e) {
//						e.printStackTrace();
//					}
//				}
//
//				Timing mTiming = new Timing();
//				mTiming.setDate(currentDynamicKey);
//				mTiming.setTime(currentDynamicKeyTime);
//				mTiming.setSlots(timings);
//				listMain.add(mTiming);
//			}
//		}
//
//		Collections.sort(listMain, new Comparator<Timing>() {
//
//			public int compare(Timing lhs, Timing rhs) {
//
//				try {
//					SimpleDateFormat dateFormatlhs = new SimpleDateFormat(
//							"yyyy-MM-dd");
//					Date convertedDatelhs = null;
//					try {
//						convertedDatelhs = dateFormatlhs.parse(lhs.getDate());
//					} catch (java.text.ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					Calendar calendarlhs = Calendar.getInstance();
//					calendarlhs.setTime(convertedDatelhs);
//
//					SimpleDateFormat dateFormatrhs = new SimpleDateFormat(
//							"yyyy-MM-dd");
//					Date convertedDaterhs = null;
//					try {
//						convertedDaterhs = dateFormatrhs.parse(rhs.getDate());
//					} catch (java.text.ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					Calendar calendarrhs = Calendar.getInstance();
//					calendarrhs.setTime(convertedDaterhs);
//
//					if (calendarlhs.getTimeInMillis() > calendarrhs
//							.getTimeInMillis()) {
//						return -1;
//					} else {
//						return 1;
//					}
//				} catch (ParseException e) {
//					e.printStackTrace();
//				}
//				return 0;
//			}
//		});
//
//		TableRow contentRow = new TableRow(getApplicationContext());
//		contentRow.setGravity(Gravity.CENTER);
//		textView = new TextView(getApplicationContext());
//
//		listSortTimes = new ArrayList<Timing>();
//		for (int i = listMain.size() - 1; i >= 0; i--) {
//			Timing mTiming = listMain.get(i);
//			listSortTimes.add(mTiming);
//		}
//
//		Log.e("sorrtedd", listSortTimes.get(0).getDate());
//		contentRow = new TableRow(getApplicationContext());
//		contentRow.setGravity(Gravity.CENTER);
//		textView = new TextView(getApplicationContext());
//		textView.setGravity(Gravity.CENTER);
//		textView.setLayoutParams(layoutParamsWrapWidth);
//		textView.setTextSize(20);
//
//		SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat myFormat = new SimpleDateFormat("d/M/yy");
//
//		try {
//			String reformattedStr = myFormat.format(fromUser
//					.parse(listSortTimes.get(0).getDate()));
//
//			textView.setText(reformattedStr.trim());
//		} catch (java.text.ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		Drawable img = getResources().getDrawable(R.drawable.date);
//		img.setBounds(0, 0, 30, 30);
//		textView.setCompoundDrawables(img, null, null, null);
//		textView.setTextColor(Color.BLACK);
//		contentRow.addView(textView);
//		// table_content.addView(contentRow);
//
//		textViewMorning.setEnabled(false);
//		textViewAfternoon.setEnabled(false);
//		textViewEvening.setEnabled(false);
//		textViewNight.setEnabled(false);
//
//		for (int j = 0; j < listSortTimes.size(); j++) {
//			Log.e("listSortTimes size", listSortTimes.size() + "");
//
//			if (listSortTimes.get(j).getTime().startsWith("M")) {
//				textViewMorning.setEnabled(true);
//			}
//			if (listSortTimes.get(j).getTime().startsWith("A")) {
//				textViewAfternoon.setEnabled(true);
//			}
//			if (listSortTimes.get(j).getTime().startsWith("E")) {
//				textViewEvening.setEnabled(true);
//			}
//			if (listSortTimes.get(j).getTime().startsWith("N")) {
//				textViewNight.setEnabled(true);
//			}
//
//			if (listSortTimes.size() == 2) {
//
//				for (int j1 = 0; j1 < listSortTimes.size(); j1++) {
//					char charac = listSortTimes.get(j1).getTime().charAt(0);
//					if (charac == 'E') {
//						Timing timing = listSortTimes.get(j1);
//						listSortTimes.remove(j1);
//						listSortTimes.add(0, timing);
//					} else if (charac == 'N') {
//						Timing timing = listSortTimes.get(j1);
//						listSortTimes.remove(j1);
//						listSortTimes.add(1, timing);
//					}
//				}
//			} else if (listSortTimes.size() == 3) {
//
//				for (int j1 = 0; j1 < listSortTimes.size(); j1++) {
//					char charac = listSortTimes.get(j1).getTime().charAt(0);
//					if (charac == 'A') {
//						Timing timing = listSortTimes.get(j1);
//						listSortTimes.remove(j1);
//						listSortTimes.add(0, timing);
//					} else if (charac == 'E') {
//						Timing timing = listSortTimes.get(j1);
//						listSortTimes.remove(j1);
//						listSortTimes.add(1, timing);
//					} else if (charac == 'N') {
//						Timing timing = listSortTimes.get(j1);
//						listSortTimes.remove(j1);
//						listSortTimes.add(2, timing);
//					}
//				}
//			} else if (listSortTimes.size() == 4) {
//				for (int j1 = 0; j1 < listSortTimes.size(); j1++) {
//					char charac = listSortTimes.get(j1).getTime().charAt(0);
//					if (charac == 'M') {
//						Timing timing = listSortTimes.get(j1);
//						listSortTimes.remove(j1);
//						listSortTimes.add(0, timing);
//					} else if (charac == 'A') {
//						Timing timing = listSortTimes.get(j1);
//						listSortTimes.remove(j1);
//						listSortTimes.add(1, timing);
//					} else if (charac == 'E') {
//						Timing timing = listSortTimes.get(j1);
//						listSortTimes.remove(j1);
//						listSortTimes.add(2, timing);
//					} else if (charac == 'N') {
//						Timing timing = listSortTimes.get(j1);
//						listSortTimes.remove(j1);
//						listSortTimes.add(3, timing);
//					}
//				}
//			}
//
//			contentRow = new TableRow(getApplicationContext());
//			contentRow.setGravity(Gravity.CENTER);
//
//			ArrayList<String> timeArray = new ArrayList<>();
//
//			for (int j1 = 0; j1 < listSortTimes.get(j).getSlots().size(); j1++) {
//				timeArray.add(listSortTimes.get(j).getSlots().get(j1));
//			}
//
//			Collections.sort(timeArray, new Comparator<String>() {
//				public int compare(String v1, String v2) {
//					return v1.compareTo(v2);
//				}
//			});
//
//			for (int j2 = 0; j2 < timeArray.size(); j2++) {
//				textView = new TextView(getApplicationContext());
//				textView.setGravity(Gravity.CENTER);
//				textView.setLayoutParams(layoutParams);
//				textView.setTextSize(20);
//				textView.setText(timeArray.get(j2));
//				textView.setTextColor(Color.BLACK);
//				contentRow.addView(textView);
//				textView.setOnClickListener(new OnClickListener() {
//
//					@Override
//					public void onClick(View v) {
//
//						SimpleDateFormat fromUser = new SimpleDateFormat(
//								"yyyy-MM-dd");
//						SimpleDateFormat myFormat = new SimpleDateFormat(
//								"d/M/yy");
//
//						try {
//							String reformattedStr = myFormat.format(fromUser
//									.parse(listSortTimes.get(0).getDate()));
//
//							date_selected = reformattedStr.trim();
//						} catch (java.text.ParseException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						time_selected = textView.getText().toString();
//
//						textviewSessionTime.setText("Time: "
//								+ ((TextView) v).getText().toString().trim()
//								+ " " + session);
//
//						// textviewArea.setText(type_names.get(0));
//						// textviewDate.setText(date_selected);
//						// textviewTime.setText(time_selected);
//						// showPopUp();
//					}
//				});
//
//				int jj = j2 + 1;
//				if (jj % 2 == 0) {
//					if (listSortTimes.get(j).getTime().charAt(0) == 'M') {
//						table_morning.addView(contentRow);
//						contentRow = new TableRow(getApplicationContext());
//						contentRow.setGravity(Gravity.CENTER);
//					}
//					if (listSortTimes.get(j).getTime().charAt(0) == 'A') {
//						table_afternoon.addView(contentRow);
//						contentRow = new TableRow(getApplicationContext());
//						contentRow.setGravity(Gravity.CENTER);
//					}
//					if (listSortTimes.get(j).getTime().charAt(0) == 'E') {
//						table_evening.addView(contentRow);
//						contentRow = new TableRow(getApplicationContext());
//						contentRow.setGravity(Gravity.CENTER);
//					}
//					if (listSortTimes.get(j).getTime().charAt(0) == 'N') {
//						table_night.addView(contentRow);
//						contentRow = new TableRow(getApplicationContext());
//						contentRow.setGravity(Gravity.CENTER);
//					}
//				}
//			}
//			try {
//				if (listSortTimes.get(j).getTime().charAt(0) == 'M') {
//					table_morning.addView(contentRow);
//				}
//				if (listSortTimes.get(j).getTime().charAt(0) == 'A') {
//					table_afternoon.addView(contentRow);
//				}
//				if (listSortTimes.get(j).getTime().charAt(0) == 'E') {
//					table_evening.addView(contentRow);
//				}
//				if (listSortTimes.get(j).getTime().charAt(0) == 'N') {
//					table_night.addView(contentRow);
//				}
//				if (textViewMorning.isEnabled()) {
//					textViewMorning.performClick();
//				} else {
//					if (textViewAfternoon.isEnabled()) {
//						textViewAfternoon.performClick();
//					} else {
//						if (textViewNight.isEnabled()) {
//							textViewNight.performClick();
//						}
//					}
//				}
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public String getDayOfWeek(int day) {
//		String day_name = "";
//		switch (day) {
//		case 1:
//			day_name = "Sunday";
//			break;
//		case 2:
//			day_name = "Monday";
//			break;
//		case 3:
//			day_name = "Tueseday";
//			break;
//		case 4:
//			day_name = "Wednesday";
//			break;
//		case 5:
//			day_name = "Thursday";
//			break;
//		case 6:
//			day_name = "Friday";
//			break;
//		case 7:
//			day_name = "Saturday";
//			break;
//		}
//		return day_name;
//	}
//
//	String getMonthForInt(int num) {
//		String month = "wrong";
//		DateFormatSymbols dfs = new DateFormatSymbols();
//		String[] months = dfs.getMonths();
//		if (num >= 0 && num <= 11) {
//			month = months[num];
//		}
//		return month;
//	}
//
//	private String getPoster(int day) {
//		String poster = null;
//		switch (day) {
//		case 1:
//			poster = "st";
//			break;
//		case 2:
//			poster = "nd";
//			break;
//		case 3:
//			poster = "rd";
//			break;
//		default:
//			poster = "th";
//			break;
//		case 21:
//			poster = "st";
//			break;
//		case 22:
//			poster = "nd";
//			break;
//		case 23:
//			poster = "rd";
//			break;
//		case 31:
//			poster = "st";
//			break;
//		}
//		return poster;
//	}
//
//	private void disableTextView(int time) {
//		if (time > 12 && time <= 15) {
//			textViewMorning.setEnabled(false);
//		} else if (time > 15 && time <= 18) {
//			textViewMorning.setEnabled(false);
//			textViewAfternoon.setEnabled(false);
//		} else if (time > 18) {
//			textViewMorning.setEnabled(false);
//			textViewAfternoon.setEnabled(false);
//			textViewEvening.setEnabled(false);
//			textViewNight.setEnabled(true);
//		}
//	}
//}
