package hsd.symptom.checker;

import hsd.symptom.checker.adapter.SpecialityAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class SpecialityActivity extends ActionBarActivity {

	private ListView listView_speciality;
	private TextView textView_empty_view;
	ArrayList<String> fetchList;
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.speciality);

		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("Symptom Checker");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		fetchList = new ArrayList<String>();
		fetchList = getIntent().getStringArrayListExtra("symptomList");

		fetchList = removeDuplicates(fetchList);

		if (fetchList.contains("default")) {
			fetchList.remove("default");
			fetchList.add("General");
		}
		if (fetchList.contains("default")) {
			fetchList.remove("default");
			fetchList.add("General Doctor");
		}
		if (fetchList.contains("default")) {
			fetchList.remove("default");
			fetchList.add("General Doctor");
		}
		if (fetchList.contains("")) {
			fetchList.remove("");
		}

		listView_speciality = (ListView) findViewById(R.id.listView_speciality);
		textView_empty_view = (TextView) findViewById(R.id.textView_empty_view);

		SpecialityAdapter adapter = new SpecialityAdapter(
				SpecialityActivity.this, R.layout.speciality_item, fetchList);

		if (fetchList.size() > 0) {
			Collections.sort(fetchList, String.CASE_INSENSITIVE_ORDER);
		} else {
			textView_empty_view.setVisibility(View.VISIBLE);
		}

		listView_speciality.setAdapter(adapter);

		listView_speciality.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String url = "http://www.healthserve.in/#!/search/"
						+ fetchList.get(position).trim() + "/Pune";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));

				Intent i2 = new Intent(SpecialityActivity.this,
						DoctorListActivity.class);
				startActivity(i2);
			}
		});
	}

	static ArrayList<String> removeDuplicates(ArrayList<String> list) {

		// Store unique items in result.
		ArrayList<String> result = new ArrayList<>();

		// Record encountered Strings in HashSet.
		HashSet<String> set = new HashSet<>();

		// Loop over argument list.
		for (String item : list) {

			// If String is not in set, add it to the list and the set.
			if (!set.contains(item)) {
				result.add(item);
				set.add(item);
			}
		}
		return result;
	}
}
