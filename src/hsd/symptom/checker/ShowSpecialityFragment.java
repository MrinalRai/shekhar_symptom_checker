package hsd.symptom.checker;

import hsd.symptom.checker.adapter.SpecialityAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class ShowSpecialityFragment extends Fragment {

	private View content;

	private ListView listView_speciality;
	private TextView textView_empty_view;
	ArrayList<String> fetchList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		content = inflater.inflate(R.layout.speciality, container, false);

		Bundle b = getArguments();
		fetchList = new ArrayList<String>();
		fetchList = b.getStringArrayList("symptomList");

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

		listView_speciality = (ListView) content
				.findViewById(R.id.listView_speciality);
		textView_empty_view = (TextView) content
				.findViewById(R.id.textView_empty_view);

		SpecialityAdapter adapter = new SpecialityAdapter(getActivity(),
				R.layout.speciality_item, fetchList);

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

				// Intent i2 = new Intent(getActivity(),
				// DoctorListActivity.class);
				// startActivity(i2);

				Bundle args = new Bundle();
				// args.putString("part",
				// textView_body_part.getText().toString()
				// .replace(" selected", ""));
				// args.putBoolean("gender", male);
				DoctorListingFragment doctorListingFragment = new DoctorListingFragment();
				doctorListingFragment.setArguments(args);
				getActivity().getSupportFragmentManager().beginTransaction()
						.replace(R.id.container, doctorListingFragment)
						.addToBackStack(null).commit();
			}
		});

		return content;
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
