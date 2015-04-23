package hsd.symptom.checker.navigation.drawer;

import hsd.symptom.checker.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentMyAppointments extends Fragment {

	private View content;
	private RelativeLayout relativeLayout_upcoming, relativeLayout_past,
			relativeLayout_upcoming_active, relativeLayout_past_active;
	private ImageView imageView_upcoming, imageView_past;
	private TextView textView_upcoming, textView_past;

	private boolean upcoming_view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		content = inflater.inflate(R.layout.fragment_my_appointments,
				container, false);

		relativeLayout_upcoming = (RelativeLayout) content
				.findViewById(R.id.relativeLayout_upcoming);
		relativeLayout_past = (RelativeLayout) content
				.findViewById(R.id.relativeLayout_past);
		relativeLayout_upcoming_active = (RelativeLayout) content
				.findViewById(R.id.relativeLayout_upcoming_active);
		relativeLayout_past_active = (RelativeLayout) content
				.findViewById(R.id.relativeLayout_past_active);
		textView_upcoming = (TextView) content
				.findViewById(R.id.textView_upcoming);
		textView_past = (TextView) content.findViewById(R.id.textView_past);
		imageView_upcoming = (ImageView) content
				.findViewById(R.id.imageView_upcoming);
		imageView_past = (ImageView) content.findViewById(R.id.imageView_past);

		relativeLayout_upcoming.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				textView_upcoming.setTextColor(getResources().getColor(
						R.color.primary));
				textView_past.setTextColor(getResources().getColor(
						R.color.primary_material_light));
				imageView_upcoming.setImageResource(R.drawable.ic_male_enabled);
				imageView_past.setImageResource(R.drawable.ic_male_enabled);
				relativeLayout_upcoming_active
						.setBackgroundColor(getResources().getColor(
								R.color.primary));
				relativeLayout_past_active.setBackgroundColor(getResources()
						.getColor(R.color.primary_material_light));
				showList(upcoming_view);
			}
		});

		relativeLayout_past.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				textView_upcoming.setTextColor(getResources().getColor(
						R.color.primary_material_light));
				textView_past.setTextColor(getResources().getColor(
						R.color.primary));
				imageView_upcoming.setImageResource(R.drawable.ic_male_enabled);
				imageView_past.setImageResource(R.drawable.ic_male_enabled);
				relativeLayout_upcoming_active
						.setBackgroundColor(getResources().getColor(
								R.color.primary_material_light));
				relativeLayout_past_active.setBackgroundColor(getResources()
						.getColor(R.color.primary));
				showList(upcoming_view);
			}
		});

		return content;
	}

	private void showList(boolean upcoming_view) {
		Toast.makeText(getActivity(), "hello " + upcoming_view,
				Toast.LENGTH_LONG).show();
	}
}
