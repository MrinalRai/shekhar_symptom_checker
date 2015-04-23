package hsd.symptom.checker;

import hsd.symptom.checker.adapter.CustomViewPager;
import hsd.symptom.checker.database.SC_Database;
import hsd.symptom.checker.database.SugarBp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Add_BP_Entry_Activity extends FragmentActivity {

	private MyPageAdapter pageAdapter;
	static FragmentManager fragmentManager;
	static CustomViewPager pager;
	static List<SugarBp> xdVals;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_bp_entry);

		getActionBar().setTitle("SugarBp Checker");

		SC_Database database = new SC_Database(this);
		xdVals = new ArrayList<SugarBp>();
		xdVals = database.getAllBps();

		List<Fragment> fragments = getFragments();
		pageAdapter = new MyPageAdapter(getSupportFragmentManager(), fragments);
		pager = (CustomViewPager) findViewById(R.id.viewpager);
		pager.setAdapter(pageAdapter);
		pager.setPagingEnabled(false);
		pager.setOffscreenPageLimit(2);

		fragmentManager = getSupportFragmentManager();

		findViewById(R.id.button_add_bp).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// custom dialog
						final Dialog dialog = new Dialog(
								Add_BP_Entry_Activity.this);
						dialog.setContentView(R.layout.custom_alert_dialogue_add_bp);

						// set the custom dialog components - text, image and
						// button
						TextView text = (TextView) dialog
								.findViewById(R.id.text);
						text.setText("Add your bp here!");

						Button dialogButtonOk = (Button) dialog
								.findViewById(R.id.dialogButtonOK);

						final EditText editText_bp = (EditText) dialog
								.findViewById(R.id.editText_bp);
						// if button is clicked, close the custom dialog
						dialogButtonOk
								.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(View v) {
										if (editText_bp.getText().toString()
												.trim().length() > 1) {
											Calendar cal = Calendar
													.getInstance();
											String _date = cal
													.get(Calendar.DAY_OF_MONTH)
													+ "-"
													+ cal.get(Calendar.MONTH)
													+ "-"
													+ cal.get(Calendar.YEAR);

											SC_Database database = new SC_Database(
													Add_BP_Entry_Activity.this);
											database.addBp(editText_bp
													.getText().toString(),
													_date);
											dialog.dismiss();
											xdVals = new ArrayList<SugarBp>();
											xdVals = database.getAllBps();
											// BP_Graph_View.mChart.clear();
											// BP_Graph_View.generateGraph();
											// BP_List_View.generateListview();
										}
									}
								});

						Button dialogButtonCancel = (Button) dialog
								.findViewById(R.id.dialogButtonCancel);
						// if button is clicked, close the custom dialog
						dialogButtonCancel
								.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(View v) {
										dialog.dismiss();
									}
								});

						dialog.show();
					}
				});
	}

	private List<Fragment> getFragments() {
		List<Fragment> fList = new ArrayList<Fragment>();

		// fList.add(new BP_Graph_View());
		// fList.add(new BP_List_View());

		return fList;
	}

	class MyPageAdapter extends FragmentPagerAdapter {
		private List<Fragment> fragments;

		public MyPageAdapter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;
		}

		@Override
		public Fragment getItem(int position) {
			return this.fragments.get(position);
		}

		@Override
		public int getCount() {
			return this.fragments.size();
		}
	}
}
