package hsd.symptom.checker.adapter;

import hsd.symptom.checker.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CalendarAdapter extends BaseAdapter {
	private Context mContext;

	private java.util.Calendar month;
	public GregorianCalendar pmonth; // calendar instance for previous month
	/**
	 * calendar instance for previous month for getting complete view
	 */
	public GregorianCalendar pmonthmaxset;
	private GregorianCalendar selectedDate;
	int firstDay;
	int maxWeeknumber;
	int maxP;
	int calMaxP;
	int lastWeekDay;
	int leftDays;
	int mnthlength;
	String itemvalue, curentDateString, todayDateString = null;
	DateFormat df;

	private ArrayList<String> items;
	public static List<String> dayString;
	private View previousView, currentDateView;

	public CalendarAdapter(Context c, GregorianCalendar monthCalendar) {
		CalendarAdapter.dayString = new ArrayList<String>();
		Locale.setDefault(Locale.US);
		month = monthCalendar;
		selectedDate = (GregorianCalendar) monthCalendar.clone();
		mContext = c;
		month.set(GregorianCalendar.DAY_OF_MONTH, 1);
		this.items = new ArrayList<String>();
		df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		curentDateString = df.format(selectedDate.getTime());
		if (todayDateString == null) {
			todayDateString = curentDateString;
		}
		refreshDays();
	}

	public void setItems(ArrayList<String> items) {
		for (int i = 0; i != items.size(); i++) {
			if (items.get(i).length() == 1) {
				items.set(i, "0" + items.get(i));
			}
		}
		this.items = items;
	}

	public int getCount() {
		return dayString.size();
	}

	public Object getItem(int position) {
		return dayString.get(position);
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new view for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		TextView dayView;
		if (convertView == null) { // if it's not recycled, initialize some
									// attributes
			LayoutInflater vi = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.calendar_item, null);

		}
		ImageView iw = (ImageView) v.findViewById(R.id.date_icon);
		iw.setImageResource(R.drawable.blank);
		// v.setBackgroundResource(R.drawable.blank);
		RelativeLayout linear_custom = (RelativeLayout) v
				.findViewById(R.id.linear_custom);
		dayView = (TextView) v.findViewById(R.id.date);
		iw = (ImageView) v.findViewById(R.id.date_icon);
		// separates daystring into parts.
		String[] separatedTime = dayString.get(position).split("-");
		// taking last part of date. ie; 2 from 2012-12-02
		String gridvalue = separatedTime[2].replaceFirst("^0*", "");
		// checking whether the day is in current month or not.
		if ((Integer.parseInt(gridvalue) > 1) && (position < firstDay)) {
			// setting offdays to white color.
			dayView.setTextColor(Color.DKGRAY);
			dayView.setClickable(false);
			dayView.setFocusable(false);
			linear_custom.setClickable(true);
		} else if ((Integer.parseInt(gridvalue) < 7) && (position > 28)) {
			dayView.setTextColor(Color.DKGRAY);
			dayView.setClickable(false);
			dayView.setFocusable(false);
			linear_custom.setClickable(true);
		} else {
			// setting curent month's days in blue color.
			Calendar current = Calendar.getInstance();
			String[] separatedTimes = dayString.get(position).split("-");
			// taking last part of date. ie; 2 from 2012-12-02
			int day = Integer.parseInt(separatedTimes[2]);
			int month = Integer.parseInt(separatedTimes[1]);
			int year = Integer.parseInt(separatedTimes[0]);
			current.set(year, month - 1, day);
			Calendar calendarRange = Calendar.getInstance();
			if (calendarRange.before(current)) {
				iw = (ImageView) v.findViewById(R.id.date_icon);
				iw.setImageResource(R.drawable.available);
				linear_custom.setClickable(false);
			} else {
				iw = (ImageView) v.findViewById(R.id.date_icon);
				iw.setImageResource(R.drawable.not);
				linear_custom.setClickable(true);
			}
			dayView.setTextColor(Color.WHITE);
		}

		if (dayString.get(position).equals(curentDateString)) {
			setSelected(v, position);
			previousView = v;
			currentDateView = v;
		} else {
			// v.setBackgroundResource(R.drawable.list_item_background);
		}
		if (dayString.get(position).equals(todayDateString)) {
			iw = (ImageView) v.findViewById(R.id.date_icon);
			iw.setImageResource(R.drawable.today);
			// v.setBackgroundResource(R.drawable.today);
			linear_custom.setClickable(false);
		}
		dayView.setText(gridvalue);

		// create date string for comparison
		String date = dayString.get(position);

		if (date.length() == 1) {
			date = "0" + date;
		}
		String monthStr = "" + (month.get(GregorianCalendar.MONTH) + 1);
		if (monthStr.length() == 1) {
			monthStr = "0" + monthStr;
		}

		// show icon if date is not empty and it exists in the items array
		// iw = (ImageView) v.findViewById(R.id.date_icon);
		// if (date.length() > 0 && items != null && items.contains(date)) {
		// iw.setVisibility(View.INVISIBLE);
		// } else {
		// iw.setVisibility(View.VISIBLE);
		// }
		return v;
	}

	public View setSelected(View view, int position) {
		if (previousView != null && previousView != currentDateView) {
			ImageView iw = (ImageView) previousView
					.findViewById(R.id.date_icon);
			iw.setImageResource(R.drawable.available);
			// previousView.setBackgroundResource(R.drawable.blank);
		}
		// view.setBackgroundResource(R.drawable.selected);
		ImageView iw = (ImageView) view.findViewById(R.id.date_icon);
		iw.setImageResource(R.drawable.selected);
		if (dayString.get(position).equals(todayDateString)) {
			// view.setBackgroundResource(R.drawable.today);
			iw = (ImageView) view.findViewById(R.id.date_icon);
			iw.setImageResource(R.drawable.today);
		} else {
			previousView = view;
		}
		return view;
	}

	public void refreshDays() {
		// clear items
		items.clear();
		dayString.clear();
		Locale.setDefault(Locale.US);
		pmonth = (GregorianCalendar) month.clone();
		// month start day. ie; sun, mon, etc
		firstDay = month.get(GregorianCalendar.DAY_OF_WEEK);
		// finding number of weeks in current month.
		maxWeeknumber = month.getActualMaximum(GregorianCalendar.WEEK_OF_MONTH);
		// allocating maximum row number for the gridview.
		mnthlength = maxWeeknumber * 7;
		maxP = getMaxP(); // previous month maximum day 31,30....
		calMaxP = maxP - (firstDay - 1);// calendar offday starting 24,25 ...
		/**
		 * Calendar instance for getting a complete gridview including the three
		 * month's (previous,current,next) dates.
		 */
		pmonthmaxset = (GregorianCalendar) pmonth.clone();
		/**
		 * setting the start date as previous month's required date.
		 */
		pmonthmaxset.set(GregorianCalendar.DAY_OF_MONTH, calMaxP + 1);

		/**
		 * filling calendar gridview.
		 */
		for (int n = 0; n < mnthlength; n++) {

			itemvalue = df.format(pmonthmaxset.getTime());
			pmonthmaxset.add(GregorianCalendar.DATE, 1);
			dayString.add(itemvalue);
		}
	}

	private int getMaxP() {
		int maxP;
		if (month.get(GregorianCalendar.MONTH) == month
				.getActualMinimum(GregorianCalendar.MONTH)) {
			pmonth.set((month.get(GregorianCalendar.YEAR) - 1),
					month.getActualMaximum(GregorianCalendar.MONTH), 1);
		} else {
			pmonth.set(GregorianCalendar.MONTH,
					month.get(GregorianCalendar.MONTH) - 1);
		}
		maxP = pmonth.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);

		return maxP;
	}

}