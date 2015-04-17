package hsd.symptom.checker;

import hsd.symptom.checker.adapter.SugarBpAdapter;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class BP_List_View extends Fragment {
	private View list_view;

	private static ListView listViewChart;

	private Button button_show_chart;

	private static Context mContext;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		list_view = inflater.inflate(R.layout.layout_list_view, container,
				false);

		mContext = getActivity();

		button_show_chart = (Button) list_view
				.findViewById(R.id.button_show_chart);

		listViewChart = (ListView) list_view.findViewById(R.id.listViewChart);

		SugarBpAdapter adapter = new SugarBpAdapter(mContext,
				R.layout.speciality_item, Add_BP_Entry_Activity.xdVals);

		listViewChart.setAdapter(adapter);

		button_show_chart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Add_BP_Entry_Activity.pager.setCurrentItem(0);
			}
		});
		return list_view;
	}

	static void generateListview() {

		Toast.makeText(mContext, "generateListview", Toast.LENGTH_SHORT).show();
		SugarBpAdapter adapter = new SugarBpAdapter(mContext,
				R.layout.speciality_item, Add_BP_Entry_Activity.xdVals);

		listViewChart.setAdapter(adapter);
	}
}
