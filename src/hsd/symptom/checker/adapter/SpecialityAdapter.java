package hsd.symptom.checker.adapter;

import hsd.symptom.checker.R;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SpecialityAdapter extends ArrayAdapter<String> {
	private LayoutInflater mInflater;
	private List<String> mSymptom;

	public SpecialityAdapter(Context context, int resource,
			List<String> Symptoms) {
		super(context, resource, Symptoms);
		mInflater = LayoutInflater.from(context);
		mSymptom = Symptoms;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ViewHolder holder;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.speciality_item, parent, false);
			holder = new ViewHolder();
			holder.imageView_speciality = (ImageView) view
					.findViewById(R.id.imageView_speciality);
			holder.textView_speciality = (TextView) view
					.findViewById(R.id.textView_speciality);
			view.setTag(holder);
		} else {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}

		String speciality = mSymptom.get(position);

		holder.textView_speciality.setText(speciality);
		if (position == 0) {
			holder.imageView_speciality
					.setImageResource(R.drawable.general_practitioner);
		}
		if (position == 1) {
			holder.imageView_speciality.setImageResource(R.drawable.consultant);
		}
		if (position == 2) {
			holder.imageView_speciality
					.setImageResource(R.drawable.super_specialist);
		}
		return view;
	}

	private class ViewHolder {
		public ImageView imageView_speciality;
		public TextView textView_speciality;
	}
}