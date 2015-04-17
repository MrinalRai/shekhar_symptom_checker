package hsd.symptom.checker.adapter;

import hsd.symptom.checker.R;
import hsd.symptom.checker.database.Symptom;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SymptomAdapter extends ArrayAdapter<Symptom> {
	private LayoutInflater mInflater;
	private List<Symptom> mSymptom;

	public SymptomAdapter(Context context, int resource, List<Symptom> Symptoms) {
		super(context, resource, Symptoms);
		mInflater = LayoutInflater.from(context);
		mSymptom = Symptoms;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ViewHolder holder;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.symptom_item, parent, false);
			holder = new ViewHolder();
			holder.textView_symptom_name = (TextView) view
					.findViewById(R.id.textView_symptom_name);
			holder.textView_symptom_cname = (TextView) view
					.findViewById(R.id.textView_symptom_cname);
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}

		Symptom symptom = mSymptom.get(position);

		holder.textView_symptom_name.setText(symptom.getName());
		String cname = symptom.getCname() + "";
		if (!cname.equals("")) {
			holder.textView_symptom_cname.setText("(" + cname + ")");
		} else {
			holder.textView_symptom_cname.setText("");
			holder.textView_symptom_cname.setVisibility(View.GONE);
		}
		return view;
	}

	private class ViewHolder {
		public TextView textView_symptom_name, textView_symptom_cname;
	}
}