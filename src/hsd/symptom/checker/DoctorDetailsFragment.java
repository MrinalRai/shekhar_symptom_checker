package hsd.symptom.checker;

import hsd.symptom.checker.constant.Constant;
import hsd.symptom.checker.database.Timing;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ParseException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class DoctorDetailsFragment extends Fragment {

	private View content;

	private int doc_id;
	private RequestQueue mRequestQueue;
	private DisplayImageOptions options;
	private ProgressDialog pd;
	// private String TAG = "DoctorDetailsActivity";

	private ImageView circularImageView;
	private TextView textViewInitials, textViewName, textViewSpeciality,
			textViewFee;

	private TextView textViewSelectDate, textViewSelectDateth,
			textViewSelectDay, textViewSelectMonth, textViewSelectYear;

	private TextView textViewMorning, textViewAfternoon, textViewEvening,
			textViewNight;

	private String session;
	private LinearLayout linearLayoutLocation, linearLayout_empty;

	private RelativeLayout relativeLayoutDateSelector;

	private ImageButton imageButton_book_appointment;

	private JSONArray scheduleString;
	private TableRow.LayoutParams layoutParams, layoutParamsWrapWidth;
	private int width;
	private ArrayList<Timing> listSortTimes;
	private TextView textView;
	String location_selected, date_selected, time_selected, location_id;
	private TableLayout table_morning, table_afternoon, table_evening,
			table_night;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		content = inflater.inflate(R.layout.doctor_details_activity, container,
				false);

		Bundle b = getArguments();
		doc_id = b.getInt("doc_id");

		mRequestQueue = Volley.newRequestQueue(getActivity());
		pd = new ProgressDialog(getActivity());
		pd.setTitle("Please wait...");
		pd.setMessage("Getting list of symptoms...");
		pd.setCancelable(false);
		pd.setIcon(R.drawable.ic_launcher);
		pd.setIndeterminate(true);

		DisplayMetrics display = getResources().getDisplayMetrics();

		table_morning = (TableLayout) content.findViewById(R.id.table_morning);
		table_afternoon = (TableLayout) content
				.findViewById(R.id.table_afternoon);
		table_evening = (TableLayout) content.findViewById(R.id.table_evening);
		table_night = (TableLayout) content.findViewById(R.id.table_night);

		table_morning.setVisibility(View.GONE);
		table_afternoon.setVisibility(View.GONE);
		table_evening.setVisibility(View.GONE);
		table_night.setVisibility(View.GONE);

		width = (int) (display.widthPixels / 2.1);
		layoutParamsWrapWidth = new TableRow.LayoutParams(
				TableRow.LayoutParams.WRAP_CONTENT, width / 5);
		layoutParams = new TableRow.LayoutParams(width, width / 5);

		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.no_image)
				.showImageForEmptyUri(R.drawable.no_image)
				.showImageOnFail(R.drawable.no_image).cacheInMemory(true)
				.cacheOnDisk(true).considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(20)).build();

		findDoctors(doc_id);

		circularImageView = (ImageView) content
				.findViewById(R.id.circularImageView);
		textViewInitials = (TextView) content
				.findViewById(R.id.textViewInitials);
		textViewName = (TextView) content.findViewById(R.id.textViewName);
		textViewSpeciality = (TextView) content
				.findViewById(R.id.textViewSpeciality);
		textViewFee = (TextView) content.findViewById(R.id.textViewFee);
		// textViewLocation = (TextView) content
		// .findViewById(R.id.textViewLocation);
		linearLayoutLocation = (LinearLayout) content
				.findViewById(R.id.linearLayoutLocation);
		linearLayout_empty = (LinearLayout) content
				.findViewById(R.id.linearLayout_empty);
		textViewSelectDate = (TextView) content
				.findViewById(R.id.textViewSelectDate);
		textViewSelectDay = (TextView) content
				.findViewById(R.id.textViewSelectDay);
		textViewSelectDateth = (TextView) content
				.findViewById(R.id.textViewSelectDateth);
		textViewSelectMonth = (TextView) content
				.findViewById(R.id.textViewSelectMonth);
		textViewSelectYear = (TextView) content
				.findViewById(R.id.textViewSelectYear);

		textViewMorning = (TextView) content.findViewById(R.id.textViewMorning);
		textViewAfternoon = (TextView) content
				.findViewById(R.id.textViewAfternoon);
		textViewEvening = (TextView) content.findViewById(R.id.textViewEvening);
		textViewNight = (TextView) content.findViewById(R.id.textViewNight);

		textViewMorning.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				session = "(M)";
				Log.e("session", session);
				table_morning.setVisibility(View.VISIBLE);
				table_afternoon.setVisibility(View.GONE);
				table_evening.setVisibility(View.GONE);
				table_night.setVisibility(View.GONE);

				for (int iq = 0; iq < table_afternoon.getChildCount(); iq++) {
					ViewGroup parentchild = (ViewGroup) table_afternoon
							.getChildAt(iq);
					if (parentchild != null) {
						for (int iq1 = 0; iq1 < parentchild.getChildCount(); iq1++) {
							TextView tvc = (TextView) parentchild
									.getChildAt(iq1);
							tvc.setCompoundDrawablesWithIntrinsicBounds(
									R.drawable.ic_unselect, 0, 0, 0);
						}
					}
				}
				for (int iq = 0; iq < table_evening.getChildCount(); iq++) {
					ViewGroup parentchild = (ViewGroup) table_evening
							.getChildAt(iq);
					if (parentchild != null) {
						for (int iq1 = 0; iq1 < parentchild.getChildCount(); iq1++) {
							TextView tvc = (TextView) parentchild
									.getChildAt(iq1);
							tvc.setCompoundDrawablesWithIntrinsicBounds(
									R.drawable.ic_unselect, 0, 0, 0);
						}
					}
				}
				for (int iq = 0; iq < table_night.getChildCount(); iq++) {
					ViewGroup parentchild = (ViewGroup) table_night
							.getChildAt(iq);
					if (parentchild != null) {
						for (int iq1 = 0; iq1 < parentchild.getChildCount(); iq1++) {
							TextView tvc = (TextView) parentchild
									.getChildAt(iq1);
							tvc.setCompoundDrawablesWithIntrinsicBounds(
									R.drawable.ic_unselect, 0, 0, 0);
						}
					}
				}
			}
		});

		textViewAfternoon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				session = "(A)";
				Log.e("session", session);
				table_morning.setVisibility(View.GONE);
				table_afternoon.setVisibility(View.VISIBLE);
				table_evening.setVisibility(View.GONE);
				table_night.setVisibility(View.GONE);

				for (int iq = 0; iq < table_morning.getChildCount(); iq++) {
					ViewGroup parentchild = (ViewGroup) table_morning
							.getChildAt(iq);
					if (parentchild != null) {
						for (int iq1 = 0; iq1 < parentchild.getChildCount(); iq1++) {
							TextView tvc = (TextView) parentchild
									.getChildAt(iq1);
							tvc.setCompoundDrawablesWithIntrinsicBounds(
									R.drawable.ic_unselect, 0, 0, 0);
						}
					}
				}
				for (int iq = 0; iq < table_evening.getChildCount(); iq++) {
					ViewGroup parentchild = (ViewGroup) table_evening
							.getChildAt(iq);
					if (parentchild != null) {
						for (int iq1 = 0; iq1 < parentchild.getChildCount(); iq1++) {
							TextView tvc = (TextView) parentchild
									.getChildAt(iq1);
							tvc.setCompoundDrawablesWithIntrinsicBounds(
									R.drawable.ic_unselect, 0, 0, 0);
						}
					}
				}
				for (int iq = 0; iq < table_night.getChildCount(); iq++) {
					ViewGroup parentchild = (ViewGroup) table_night
							.getChildAt(iq);
					if (parentchild != null) {
						for (int iq1 = 0; iq1 < parentchild.getChildCount(); iq1++) {
							TextView tvc = (TextView) parentchild
									.getChildAt(iq1);
							tvc.setCompoundDrawablesWithIntrinsicBounds(
									R.drawable.ic_unselect, 0, 0, 0);
						}
					}
				}
			}
		});
		textViewEvening.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				session = "(E)";
				Log.e("session", session);
				table_morning.setVisibility(View.GONE);
				table_afternoon.setVisibility(View.GONE);
				table_evening.setVisibility(View.VISIBLE);
				table_night.setVisibility(View.GONE);

				for (int iq = 0; iq < table_morning.getChildCount(); iq++) {
					ViewGroup parentchild = (ViewGroup) table_morning
							.getChildAt(iq);
					if (parentchild != null) {
						for (int iq1 = 0; iq1 < parentchild.getChildCount(); iq1++) {
							TextView tvc = (TextView) parentchild
									.getChildAt(iq1);
							tvc.setCompoundDrawablesWithIntrinsicBounds(
									R.drawable.ic_unselect, 0, 0, 0);
						}
					}
				}
				for (int iq = 0; iq < table_afternoon.getChildCount(); iq++) {
					ViewGroup parentchild = (ViewGroup) table_afternoon
							.getChildAt(iq);
					if (parentchild != null) {
						for (int iq1 = 0; iq1 < parentchild.getChildCount(); iq1++) {
							TextView tvc = (TextView) parentchild
									.getChildAt(iq1);
							tvc.setCompoundDrawablesWithIntrinsicBounds(
									R.drawable.ic_unselect, 0, 0, 0);
						}
					}
				}
				for (int iq = 0; iq < table_night.getChildCount(); iq++) {
					ViewGroup parentchild = (ViewGroup) table_night
							.getChildAt(iq);
					if (parentchild != null) {
						for (int iq1 = 0; iq1 < parentchild.getChildCount(); iq1++) {
							TextView tvc = (TextView) parentchild
									.getChildAt(iq1);
							tvc.setCompoundDrawablesWithIntrinsicBounds(
									R.drawable.ic_unselect, 0, 0, 0);
						}
					}
				}
			}
		});
		textViewNight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				session = "(N)";
				Log.e("session", session);
				table_morning.setVisibility(View.GONE);
				table_afternoon.setVisibility(View.GONE);
				table_evening.setVisibility(View.GONE);
				table_night.setVisibility(View.VISIBLE);

				for (int iq = 0; iq < table_morning.getChildCount(); iq++) {
					ViewGroup parentchild = (ViewGroup) table_morning
							.getChildAt(iq);
					if (parentchild != null) {
						for (int iq1 = 0; iq1 < parentchild.getChildCount(); iq1++) {
							TextView tvc = (TextView) parentchild
									.getChildAt(iq1);
							tvc.setCompoundDrawablesWithIntrinsicBounds(
									R.drawable.ic_unselect, 0, 0, 0);
						}
					}
				}
				for (int iq = 0; iq < table_afternoon.getChildCount(); iq++) {
					ViewGroup parentchild = (ViewGroup) table_afternoon
							.getChildAt(iq);
					if (parentchild != null) {
						for (int iq1 = 0; iq1 < parentchild.getChildCount(); iq1++) {
							TextView tvc = (TextView) parentchild
									.getChildAt(iq1);
							tvc.setCompoundDrawablesWithIntrinsicBounds(
									R.drawable.ic_unselect, 0, 0, 0);
						}
					}
				}
				for (int iq = 0; iq < table_evening.getChildCount(); iq++) {
					ViewGroup parentchild = (ViewGroup) table_evening
							.getChildAt(iq);
					if (parentchild != null) {
						for (int iq1 = 0; iq1 < parentchild.getChildCount(); iq1++) {
							TextView tvc = (TextView) parentchild
									.getChildAt(iq1);
							tvc.setCompoundDrawablesWithIntrinsicBounds(
									R.drawable.ic_unselect, 0, 0, 0);
						}
					}
				}
			}
		});

		relativeLayoutDateSelector = (RelativeLayout) content
				.findViewById(R.id.relativeLayoutDateSelector);

		Calendar calendar = Calendar.getInstance();

		int month = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		int year = calendar.get(Calendar.YEAR);
		// disableTextView(hr);

		textViewSelectDate.setText("" + dayOfMonth);
		textViewSelectDay.setText("" + getDayOfWeek(dayOfWeek));
		textViewSelectDateth.setText("" + getPoster(dayOfMonth));
		textViewSelectMonth.setText("" + getMonthForInt(month));
		textViewSelectYear.setText("" + year);

		relativeLayoutDateSelector.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent next = new Intent(getActivity(), CalendarView.class);
				startActivityForResult(next, 1);
			}
		});

		imageButton_book_appointment = (ImageButton) content
				.findViewById(R.id.imageButton_book_appointment);
		imageButton_book_appointment.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Bundle args = new Bundle();
				SharedPreferences prefs = getActivity().getSharedPreferences(
						Constant.MyPREFERENCES, Context.MODE_PRIVATE);
				String patient_name = prefs.getString(
						Constant.USER_LOGGED_IN_NAME, "");
				String patient_id = prefs.getString(Constant.USER_ID, "");
				args.putString("doc_id", doc_id + "");
				args.putString("patient_id", patient_id + "");
				args.putString("location_id", location_id + "");
				args.putString("date_selected", date_selected);
				args.putString("patient_name", patient_name);
				args.putString("doctor_name", textViewName.getText().toString());
				args.putString("reason", "reason");
				args.putString("location_selected", location_selected);
				args.putString("time_selected", time_selected);
				args.putString("session", session);
				ConfirmAppointmentFragment confirmAppointmentFragment = new ConfirmAppointmentFragment();
				confirmAppointmentFragment.setArguments(args);
				getActivity().getSupportFragmentManager().beginTransaction()
						.add(R.id.container, confirmAppointmentFragment)
						.addToBackStack(null).commit();
			}
		});

		return content;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		int day = data.getIntExtra("Day", 0);
		int month = data.getIntExtra("Month", 0);
		int year = data.getIntExtra("Year", 0);
		if (data.getIntExtra("ok", 0) == 1) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.DAY_OF_MONTH, day);
			calendar.set(Calendar.MONTH, month);
			calendar.set(Calendar.YEAR, year);
			textViewSelectDate.setText("" + day);
			textViewSelectDay.setText(""
					+ getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)));
			textViewSelectDateth.setText("" + getPoster(day));
			textViewSelectMonth.setText("" + getMonthForInt(month));

			mRequestQueue = Volley.newRequestQueue(getActivity());

			String dayString = day + "";
			if (dayString.length() == 1) {
				dayString = "0" + day;
			}
			String monthString = (month + 1) + "";
			if (monthString.length() == 1) {
				monthString = "0" + (month + 1);
			}

			findDoctorsSchedule(doc_id, year + "-" + monthString + "-"
					+ dayString);

			// textviewDayDate.setText("Date: " + day + "/" + (month + 1) + "/"
			// + year);
		}
	}

	private void findDoctorsSchedule(final int doc_id, final String date) {
		pd.show();
		String url = getResources().getString(R.string.host_url) + ""
				+ getResources().getString(R.string.vtest_php);
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
						pd.cancel();
					}
				}) {
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {

				Map<String, String> map = new HashMap<String, String>();
				map.put("doc_id", doc_id + "");
				map.put("date_sent", date);
				return map;
			}
		};
		mRequestQueue.add(request);
	}

	private void findDoctors(int _id) {

		pd.show();
		String url = getResources().getString(R.string.host_url) + ""
				+ getResources().getString(R.string.vtest_php);
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

	private void processData(String response) {
		Log.e("res", response + "");

		textViewMorning.setEnabled(true);
		textViewAfternoon.setEnabled(true);
		textViewEvening.setEnabled(true);
		textViewNight.setEnabled(true);

		table_morning.removeAllViews();
		table_afternoon.removeAllViews();
		table_evening.removeAllViews();
		table_night.removeAllViews();
		JSONObject object = null;
		try {
			object = new JSONObject(response);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		try {
			String name = object.getString("DisplayName");
			String[] splited = name.split("\\s+");

			try {
				String fName = splited[0];
				String lName = splited[1];
				textViewInitials
						.setText(fName.charAt(0) + "" + lName.charAt(0));
			} catch (Exception e) {
			}

			textViewName.setText(name);
			textViewSpeciality.setText(object.getString("speciality"));
			textViewFee.setText(object.getString("fee"));
			// textViewLocation.setText(object.getString("area"));

			// textviewArea.setText("Area: " + object.getString("area"));

			// type_names = new ArrayList<>();
			// type_names.add(object.getString("area"));

			ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
					getActivity()).build();

			ImageLoader imageLoader = ImageLoader.getInstance();
			imageLoader.init(config);
			imageLoader.displayImage(object.getString("DocImage"),
					circularImageView, options, new ImageLoadingListener() {
						final List<String> displayedImages = Collections
								.synchronizedList(new LinkedList<String>());

						@Override
						public void onLoadingStarted(String imageUri, View view) {
							// TODO Auto-generated method stub
							textViewInitials.setVisibility(View.VISIBLE);
						}

						@Override
						public void onLoadingFailed(String imageUri, View view,
								FailReason failReason) {
							textViewInitials.setVisibility(View.VISIBLE);
						}

						@Override
						public void onLoadingComplete(String imageUri,
								View view, Bitmap loadedImage) {
							// TODO Auto-generated method stub
							if (loadedImage != null) {
								ImageView imageView = (ImageView) view;
								boolean firstDisplay = !displayedImages
										.contains(imageUri);
								if (firstDisplay) {
									FadeInBitmapDisplayer.animate(imageView,
											500);
									displayedImages.add(imageUri);
								}
								textViewInitials.setVisibility(View.GONE);
							}
						}

						@Override
						public void onLoadingCancelled(String imageUri,
								View view) {
							// TODO Auto-generated method stub
						}
					});

			scheduleString = object.getJSONArray("schedule");
			for (int i = 0; i < scheduleString.length(); i++) {
				JSONObject schedule = null;
				String clinic_area = "";
				try {
					schedule = scheduleString.getJSONObject(i);
				} catch (JSONException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				try {
					clinic_area = schedule.getString("clinic_area");
				} catch (JSONException e3) {
					// TODO Auto-generated catch blocklinearLayoutLocation
					e3.printStackTrace();
				}
				ImageView imageView = new ImageView(getActivity());
				imageView.setImageResource(R.drawable.bar_indicator);
				LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
						27, 17);
				layoutParams.setMargins(0, 0, 3, 0);
				imageView.setPadding(0, 0, 2, 0);
				imageView.setLayoutParams(layoutParams);
				linearLayoutLocation.addView(imageView);
				final TextView textView = new TextView(getActivity());
				textView.setText(clinic_area);
				textView.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						table_morning.removeAllViews();
						table_afternoon.removeAllViews();
						table_evening.removeAllViews();
						table_night.removeAllViews();
						for (int i = 0; i < linearLayoutLocation
								.getChildCount(); i++) {
							try {
								View children = linearLayoutLocation
										.getChildAt(i);
								if (children.getClass() == TextView.class) {
									((TextView) children)
											.setTextColor(getResources()
													.getColor(
															android.R.color.black));
								}
							} catch (NotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						textView.setTextColor(getResources().getColor(
								R.color.primary));
						location_selected = ((TextView) v).getText().toString();
						getTimeTable(scheduleString, textView.getText()
								.toString());
					}
				});
				linearLayoutLocation.addView(textView);
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int i = 0; i < linearLayoutLocation.getChildCount(); i++) {
			try {
				View children = linearLayoutLocation.getChildAt(i);
				if (children.getClass() == TextView.class) {
					((TextView) children).performClick();
					break;
				}
			} catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@SuppressLint("SimpleDateFormat")
	private void getTimeTable(JSONArray scheduleString, String clinic_name) {

		ArrayList<Timing> listMain = new ArrayList<Timing>();
		for (int i = 0; i < scheduleString.length(); i++) {
			JSONObject schedule = null;
			try {
				schedule = scheduleString.getJSONObject(i);
			} catch (JSONException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}

			JSONObject scheduleString2 = null;
			String loc_schdule = "";
			String clinic_area = "";
			try {
				loc_schdule = schedule.getString("loc_schdule");
				clinic_area = schedule.getString("clinic_area");
				location_id = schedule.getString("clinicID");
			} catch (JSONException e3) {
				// TODO Auto-generated catch blocklinearLayoutLocation
				e3.printStackTrace();
			}
			Log.e("clinic_area", "clinic_area => " + clinic_area);
			if (clinic_name.equals(clinic_area)) {
				if (!loc_schdule.equals("[]")) {
					try {
						scheduleString2 = new JSONObject(loc_schdule);
					} catch (JSONException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
					Iterator<String> keys = scheduleString2.keys();

					while (keys.hasNext()) {
						// loop to get the dynamic key

						String currentDynamicKey = (String) keys.next();

						// get the value of the dynamic key
						JSONObject currentDynamicValue = null;
						try {
							currentDynamicValue = scheduleString2
									.getJSONObject(currentDynamicKey);
						} catch (JSONException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}

						Log.e("date", currentDynamicKey.toString());

						Iterator<String> keysDT = currentDynamicValue.keys();

						while (keysDT.hasNext()) {
							// loop to get the dynamic key
							String currentDynamicKeyTime = (String) keysDT
									.next();
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

							Iterator<String> keysSlots = currentDynamicsLOT
									.keys();

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
				}
			}
		}

		Collections.sort(listMain, new Comparator<Timing>() {

			public int compare(Timing lhs, Timing rhs) {

				try {
					SimpleDateFormat dateFormatlhs = new SimpleDateFormat(
							"yyyy-MM-dd");
					Date convertedDatelhs = null;
					try {
						convertedDatelhs = dateFormatlhs.parse(lhs.getDate());
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
						convertedDaterhs = dateFormatrhs.parse(rhs.getDate());
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

		TableRow contentRow = new TableRow(getActivity());
		contentRow.setGravity(Gravity.CENTER);
		textView = new TextView(getActivity());

		listSortTimes = new ArrayList<Timing>();
		for (int i = listMain.size() - 1; i >= 0; i--) {
			Timing mTiming = listMain.get(i);
			listSortTimes.add(mTiming);
		}
		if (listSortTimes.size() > 0) {
			Log.e("sorrtedd", listSortTimes.get(0).getDate());
			contentRow = new TableRow(getActivity());
			contentRow.setGravity(Gravity.CENTER);
			textView = new TextView(getActivity());
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

				if (listSortTimes.get(j).getTime().startsWith("M")) {
					Log.e("listSortTimes size", listSortTimes.size() + "");
					textViewMorning.setEnabled(true);
				}
				if (listSortTimes.get(j).getTime().startsWith("A")) {
					Log.e("listSortTimes size", listSortTimes.size() + "");
					textViewAfternoon.setEnabled(true);
				}
				if (listSortTimes.get(j).getTime().startsWith("E")) {
					Log.e("listSortTimes size", listSortTimes.size() + "");
					textViewEvening.setEnabled(true);
				}
				if (listSortTimes.get(j).getTime().startsWith("N")) {
					Log.e("listSortTimes size", listSortTimes.size() + "");
					textViewNight.setEnabled(true);
				}

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

				contentRow = new TableRow(getActivity());
				contentRow.setGravity(Gravity.CENTER);

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
					textView = new TextView(getActivity());
					textView.setGravity(Gravity.CENTER);
					textView.setLayoutParams(layoutParams);
					textView.setTextSize(20);
					textView.setText(timeArray.get(j2));
					textView.setTextColor(Color.BLACK);
					textView.setCompoundDrawablesWithIntrinsicBounds(
							R.drawable.ic_unselect, 0, 0, 0);
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
							time_selected = ((TextView) v).getText().toString();
							ViewGroup parent = (ViewGroup) v.getParent();
							ViewGroup parentSParent = (ViewGroup) parent
									.getParent();
							if (parentSParent != null) {
								for (int iq = 0; iq < parentSParent
										.getChildCount(); iq++) {
									ViewGroup parentchild = (ViewGroup) parentSParent
											.getChildAt(iq);
									if (parentchild != null) {
										for (int iq1 = 0; iq1 < parentchild
												.getChildCount(); iq1++) {
											TextView tvc = (TextView) parentchild
													.getChildAt(iq1);
											tvc.setCompoundDrawablesWithIntrinsicBounds(
													R.drawable.ic_unselect, 0,
													0, 0);
										}
									}
								}
							}
							// if (parent != null) {
							// for (int iq = 0; iq < parent.getChildCount();
							// iq++) {
							// time_selected = ((TextView) v).getText()
							// .toString();
							// TextView tvc = (TextView) parent
							// .getChildAt(iq);
							// tvc.setCompoundDrawablesWithIntrinsicBounds(
							// R.drawable.ic_unselect, 0, 0, 0);
							// }
							// }
							((TextView) v)
									.setCompoundDrawablesWithIntrinsicBounds(
											R.drawable.ic_selected, 0, 0, 0);
							// Toast.makeText(getActivity(),
							// ((TextView) v).getText().toString(),
							// Toast.LENGTH_LONG).show();
						}
					});

					int jj = j2 + 1;
					if (jj % 2 == 0) {
						if (listSortTimes.get(j).getTime().charAt(0) == 'M') {
							table_morning.addView(contentRow);
							contentRow = new TableRow(getActivity());
							contentRow.setGravity(Gravity.CENTER);
						}
						if (listSortTimes.get(j).getTime().charAt(0) == 'A') {
							table_afternoon.addView(contentRow);
							contentRow = new TableRow(getActivity());
							contentRow.setGravity(Gravity.CENTER);
						}
						if (listSortTimes.get(j).getTime().charAt(0) == 'E') {
							table_evening.addView(contentRow);
							contentRow = new TableRow(getActivity());
							contentRow.setGravity(Gravity.CENTER);
						}
						if (listSortTimes.get(j).getTime().charAt(0) == 'N') {
							table_night.addView(contentRow);
							contentRow = new TableRow(getActivity());
							contentRow.setGravity(Gravity.CENTER);
						}
					}
				}
				try {
					if (listSortTimes.get(j).getTime().charAt(0) == 'M') {
						table_morning.addView(contentRow);
					}
					if (listSortTimes.get(j).getTime().charAt(0) == 'A') {
						table_afternoon.addView(contentRow);
					}
					if (listSortTimes.get(j).getTime().charAt(0) == 'E') {
						table_evening.addView(contentRow);
					}
					if (listSortTimes.get(j).getTime().charAt(0) == 'N') {
						table_night.addView(contentRow);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		View table_morning_child = table_morning.getChildAt(0);
		if (table_morning_child == null) {
			contentRow = new TableRow(getActivity());
			contentRow.setGravity(Gravity.CENTER);
			textView = new TextView(getActivity());
			textView.setGravity(Gravity.CENTER);
			textView.setLayoutParams(layoutParams);
			textView.setTextSize(20);
			textView.setText("No timings");
			textView.setTextColor(Color.BLACK);
			contentRow.addView(textView);
			table_morning.addView(contentRow);
			textViewMorning.setEnabled(false);
			textViewMorning.setTextColor(Color.LTGRAY);
		}

		View table_afternoon_child = table_afternoon.getChildAt(0);
		if (table_afternoon_child == null) {
			contentRow = new TableRow(getActivity());
			contentRow.setGravity(Gravity.CENTER);
			textView = new TextView(getActivity());
			textView.setGravity(Gravity.CENTER);
			textView.setLayoutParams(layoutParams);
			textView.setTextSize(20);
			textView.setText("No timings");
			textView.setTextColor(Color.BLACK);
			contentRow.addView(textView);
			table_afternoon.addView(contentRow);
			textViewAfternoon.setEnabled(false);
			textViewAfternoon.setTextColor(Color.LTGRAY);
		}

		View table_evening_child = table_evening.getChildAt(0);
		if (table_evening_child == null) {
			contentRow = new TableRow(getActivity());
			contentRow.setGravity(Gravity.CENTER);
			textView = new TextView(getActivity());
			textView.setGravity(Gravity.CENTER);
			textView.setLayoutParams(layoutParams);
			textView.setTextSize(20);
			textView.setText("No timings");
			textView.setTextColor(Color.BLACK);
			contentRow.addView(textView);
			table_evening.addView(contentRow);
			textViewEvening.setEnabled(false);
			textViewEvening.setTextColor(Color.LTGRAY);
		}

		View table_night_child = table_night.getChildAt(0);
		if (table_night_child == null) {
			contentRow = new TableRow(getActivity());
			contentRow.setGravity(Gravity.CENTER);
			textView = new TextView(getActivity());
			textView.setGravity(Gravity.CENTER);
			textView.setLayoutParams(layoutParams);
			textView.setTextSize(20);
			textView.setText("No timings");
			textView.setTextColor(Color.BLACK);
			contentRow.addView(textView);
			table_night.addView(contentRow);
			textViewNight.setEnabled(false);
			textViewNight.setTextColor(Color.LTGRAY);
		}

		if (textViewMorning.isEnabled()) {
			textViewMorning.performClick();
		} else {
			if (textViewAfternoon.isEnabled()) {
				textViewAfternoon.performClick();
			} else {
				if (textViewEvening.isEnabled()) {
					textViewEvening.performClick();
				} else {
					if (textViewNight.isEnabled()) {
						textViewNight.performClick();
					} else {
						TextView textView_empty = new TextView(getActivity());
						textView_empty.setGravity(Gravity.CENTER);
						textView_empty.setText("No Schedules");
						linearLayout_empty.addView(textView_empty);
					}
				}
			}
		}
	}

	public String getDayOfWeek(int day) {
		String day_name = "";
		switch (day) {
		case 1:
			day_name = "Sunday";
			break;
		case 2:
			day_name = "Monday";
			break;
		case 3:
			day_name = "Tueseday";
			break;
		case 4:
			day_name = "Wednesday";
			break;
		case 5:
			day_name = "Thursday";
			break;
		case 6:
			day_name = "Friday";
			break;
		case 7:
			day_name = "Saturday";
			break;
		}
		return day_name;
	}

	String getMonthForInt(int num) {
		String month = "wrong";
		DateFormatSymbols dfs = new DateFormatSymbols();
		String[] months = dfs.getMonths();
		if (num >= 0 && num <= 11) {
			month = months[num];
		}
		return month;
	}

	private String getPoster(int day) {
		String poster = null;
		switch (day) {
		case 1:
			poster = "st";
			break;
		case 2:
			poster = "nd";
			break;
		case 3:
			poster = "rd";
			break;
		default:
			poster = "th";
			break;
		case 21:
			poster = "st";
			break;
		case 22:
			poster = "nd";
			break;
		case 23:
			poster = "rd";
			break;
		case 31:
			poster = "st";
			break;
		}
		return poster;
	}

	// private void disableTextView(int time) {
	// if (time > 12 && time <= 15) {
	// textViewMorning.setEnabled(false);
	// } else if (time > 15 && time <= 18) {
	// textViewMorning.setEnabled(false);
	// textViewAfternoon.setEnabled(false);
	// } else if (time > 18) {
	// textViewMorning.setEnabled(false);
	// textViewAfternoon.setEnabled(false);
	// textViewEvening.setEnabled(false);
	// textViewNight.setEnabled(true);
	// }
	// }
}
