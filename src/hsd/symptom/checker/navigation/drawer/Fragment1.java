package hsd.symptom.checker.navigation.drawer;

import hsd.symptom.checker.AbdomenMappingActivity;
import hsd.symptom.checker.BackHandMappingActivity;
import hsd.symptom.checker.BackHeadMappingActivity;
import hsd.symptom.checker.BackLegMappingActivity;
import hsd.symptom.checker.ButtockMappingActivity;
import hsd.symptom.checker.ChestMappingActivity;
import hsd.symptom.checker.HandMappingActivity;
import hsd.symptom.checker.HeadMappingActivity;
import hsd.symptom.checker.LegMappingActivity;
import hsd.symptom.checker.LowerBackMappingActivity;
import hsd.symptom.checker.PelvisMappingActivity;
import hsd.symptom.checker.R;
import hsd.symptom.checker.ShowSpecialityFragment;
import hsd.symptom.checker.UpperBackMappingActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class Fragment1 extends Fragment {

	private boolean front, male;
	private View content;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View content = inflater.inflate(R.layout.fragment1_layout, container,
				false);

		front = true;
		male = true;

		content.findViewById(R.id.imageView_male_front).setOnTouchListener(
				new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN) {
							int x = (int) event.getX();
							int y = (int) event.getY();
							int colour = getColour(x, y, 1);

							if (Color.rgb(237, 28, 36) == colour) {
								Toast.makeText(getActivity(), "head",
										Toast.LENGTH_SHORT).show();
							}
							if (Color.rgb(63, 72, 204) == colour) {
								Toast.makeText(getActivity(), "chest",
										Toast.LENGTH_SHORT).show();
							}
							if (Color.rgb(255, 127, 39) == colour) {
								Toast.makeText(getActivity(), "hand",
										Toast.LENGTH_SHORT).show();
							}
							if (Color.rgb(239, 228, 176) == colour) {
								Toast.makeText(getActivity(), "abdomen",
										Toast.LENGTH_SHORT).show();
							}
							if (Color.rgb(136, 0, 21) == colour) {
								Toast.makeText(getActivity(), "pelvis",
										Toast.LENGTH_SHORT).show();
							}
							if (Color.rgb(230, 5, 248) == colour) {
								Toast.makeText(getActivity(), "leg",
										Toast.LENGTH_SHORT).show();
							}
						}
						return true;
					}
				});

		content.findViewById(R.id.imageView_male_back).setOnTouchListener(
				new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN) {
							int x = (int) event.getX();
							int y = (int) event.getY();
							int colour = getColour(x, y, 2);

							if (Color.rgb(237, 28, 36) == colour) {
								Toast.makeText(getActivity(), "head",
										Toast.LENGTH_SHORT).show();
							}
							if (Color.rgb(63, 72, 204) == colour) {
								Toast.makeText(getActivity(), "upper back",
										Toast.LENGTH_SHORT).show();
							}
							if (Color.rgb(255, 174, 201) == colour) {
								Toast.makeText(getActivity(), "lower back",
										Toast.LENGTH_SHORT).show();
							}
							if (Color.rgb(34, 177, 76) == colour) {
								Toast.makeText(getActivity(), "buttock",
										Toast.LENGTH_SHORT).show();
							}
							if (Color.rgb(89, 230, 213) == colour) {
								Toast.makeText(getActivity(), "hand",
										Toast.LENGTH_SHORT).show();
							}
							if (Color.rgb(255, 128, 64) == colour) {
								Toast.makeText(getActivity(), "leg",
										Toast.LENGTH_SHORT).show();
							}
						}
						return true;
					}
				});

		content.findViewById(R.id.imageButton_switch).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (front) {
							front = false;
						} else {
							front = true;
						}
					}
				});

		return content;
	}

	private void navigateTo(String part, boolean gender) {
		Bundle args = new Bundle();
		args.putString("part", part);
		args.putBoolean("gender", gender);
		ShowSpecialityFragment showSpecialityFragment = new ShowSpecialityFragment();
		showSpecialityFragment.setArguments(args);
		getActivity().getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, showSpecialityFragment)
				.addToBackStack(null).commit();
	}

	private int getColour(int x, int y, int i) {
		ImageView img = (ImageView) content
				.findViewById(R.id.imageView_main_points);
		img.setDrawingCacheEnabled(true);
		Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
		img.setDrawingCacheEnabled(false);
		return hotspots.getPixel(x, y);
	}
}
