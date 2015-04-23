package hsd.symptom.checker;

import hsd.symptom.checker.adapter.CalendarAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CalendarView extends FragmentActivity {

	public GregorianCalendar gmonth, itemmonth;// calendar instances.

	public CalendarAdapter adapter;// adapter instance
	public Handler handler;// for grabbing some event values for showing the dot
							// marker.
	public ArrayList<String> items; // container to store calendar items which
									// needs showing the event marker

	TextView textViewday, textViewmonth, textViewyear, textViewDateSetter;
	String itemvalue;

	Button buttonNext;
	static String dateSelectedToSend;

	private int day, month, year, ok = -99;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar);

		Locale.setDefault(Locale.US);
		gmonth = (GregorianCalendar) GregorianCalendar.getInstance();
		itemmonth = (GregorianCalendar) gmonth.clone();
		buttonNext = (Button) findViewById(R.id.buttonNext);
		textViewday = (TextView) findViewById(R.id.textviewDay);
		textViewmonth = (TextView) findViewById(R.id.textviewMonth);
		textViewyear = (TextView) findViewById(R.id.textviewYear);
		Calendar calendarRange = Calendar.getInstance();
		int maxYear = calendarRange.get(Calendar.YEAR);
		int maxMonth = calendarRange.get(Calendar.MONTH);
		int maxDay = calendarRange.get(Calendar.DAY_OF_MONTH);
		textViewday.setText(maxDay + "");
		textViewmonth.setText(getMonth(maxMonth));
		textViewyear.setText(maxYear + "");
		textViewDateSetter = (TextView) findViewById(R.id.textViewDateSetter);
		textViewDateSetter.setText("Please Select a date");
		items = new ArrayList<String>();
		adapter = new CalendarAdapter(getApplicationContext(), gmonth);

		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(adapter);

		handler = new Handler();
		handler.post(calendarUpdater);

		TextView title = (TextView) findViewById(R.id.title);
		title.setText(android.text.format.DateFormat
				.format("MMMM yyyy", gmonth));

		RelativeLayout previous = (RelativeLayout) findViewById(R.id.previous);

		previous.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setPreviousMonth();
				refreshCalendar();
				adapter.notifyDataSetChanged();
			}
		});

		RelativeLayout next = (RelativeLayout) findViewById(R.id.next);
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setNextMonth();
				refreshCalendar();
				adapter.notifyDataSetChanged();
			}
		});

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				((CalendarAdapter) parent.getAdapter())
						.setSelected(v, position);
				String selectedGridDate = CalendarAdapter.dayString
						.get(position);
				String[] separatedTime = selectedGridDate.split("-");
				String gridvalueString = separatedTime[2].replaceFirst("^0*",
						"");// taking last part of date. ie; 2 from 2012-12-02.
				int gridvalue = Integer.parseInt(gridvalueString);
				// navigate to next or previous month on clicking offdays.
				if ((gridvalue > 10) && (position < 8)) {
					setPreviousMonth();
					refreshCalendar();
				} else if ((gridvalue < 7) && (position > 28)) {
					setNextMonth();
					refreshCalendar();
				}
				((CalendarAdapter) parent.getAdapter())
						.setSelected(v, position);
				String segments[] = selectedGridDate.split("-");
				day = Integer.parseInt(segments[segments.length - 1]);
				textViewday.setText(day + "");
				month = Integer.parseInt(segments[segments.length - 2]);
				textViewmonth.setText(getMonth(month));
				year = Integer.parseInt(segments[segments.length - 3].trim());
				textViewyear.setText(year + "");
				String actualStringToDisplay = getResources().getString(
						R.string.chosen)
						+ " "
						+ "<font COLOR=\"#45b97c\"><b>"
						+ day
						+ getPoster(day)
						+ " "
						+ "of "
						+ getMonth(month)
						+ "</b></font>"
						+ ", "
						+ getResources().getString(R.string.carry_on);
				textViewDateSetter.setText(Html.fromHtml(actualStringToDisplay));
			}

		});
		buttonNext.setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View view) {
				if (textViewDateSetter.getText().toString()
						.equals("Please Select a date")) {
					Toast.makeText(getApplicationContext(),
							"Please choose a date first", Toast.LENGTH_SHORT)
							.show();
				} else {

					dateSelectedToSend = textViewday.getText().toString() + "-"
							+ textViewmonth.getText().toString() + "-"
							+ textViewyear.getText().toString();
					ok = 1;
					finish();
				}
			}
		});
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.putExtra("Day", day);
		intent.putExtra("Month", month - 1);
		intent.putExtra("Year", year);
		intent.putExtra("ok", ok);
		setResult(RESULT_OK, intent);
		super.finish();
	}

	protected void setNextMonth() {
		if (gmonth.get(GregorianCalendar.MONTH) == gmonth
				.getActualMaximum(GregorianCalendar.MONTH)) {
			gmonth.set((gmonth.get(GregorianCalendar.YEAR) + 1),
					gmonth.getActualMinimum(GregorianCalendar.MONTH), 1);
		} else {
			gmonth.set(GregorianCalendar.MONTH,
					gmonth.get(GregorianCalendar.MONTH) + 1);
		}

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

	protected void setPreviousMonth() {
		if (gmonth.get(GregorianCalendar.MONTH) == gmonth
				.getActualMinimum(GregorianCalendar.MONTH)) {
			gmonth.set((gmonth.get(GregorianCalendar.YEAR) - 1),
					gmonth.getActualMaximum(GregorianCalendar.MONTH), 1);
		} else {
			gmonth.set(GregorianCalendar.MONTH,
					gmonth.get(GregorianCalendar.MONTH) - 1);
		}

	}

	protected void showToast(String string) {
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT)
				.show();

	}

	public void refreshCalendar() {
		TextView title = (TextView) findViewById(R.id.title);

		adapter.refreshDays();
		adapter.notifyDataSetChanged();
		handler.post(calendarUpdater); // generate some calendar items

		title.setText(android.text.format.DateFormat
				.format("MMMM yyyy", gmonth));
	}

	public Runnable calendarUpdater = new Runnable() {

		@Override
		public void run() {
			items.clear();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
			for (int i = 0; i < 7; i++) {
				itemvalue = df.format(itemmonth.getTime());
				itemmonth.add(GregorianCalendar.DATE, 1);
				items.add("2012-09-12");
				items.add("2012-10-07");
				items.add("2012-10-15");
				items.add("2012-10-20");
				items.add("2012-11-30");
				items.add("2012-11-28");
			}
			adapter.setItems(items);
			adapter.notifyDataSetChanged();
		}
	};

	private String getMonth(int month) {
		String sendMonth = null;
		switch (month) {
		case 1:
			sendMonth = "January";
			break;
		case 2:
			sendMonth = "February";
			break;
		case 3:
			sendMonth = "March";
			break;
		case 4:
			sendMonth = "April";
			break;
		case 5:
			sendMonth = "May";
			break;
		case 6:
			sendMonth = "June";
			break;
		case 7:
			sendMonth = "July";
			break;
		case 8:
			sendMonth = "August";
			break;
		case 9:
			sendMonth = "September";
			break;
		case 10:
			sendMonth = "October";
			break;
		case 11:
			sendMonth = "November";
			break;
		case 12:
			sendMonth = "December";
			break;
		}
		return sendMonth;
	}
}
