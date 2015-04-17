package hsd.symptom.checker.navigation.drawer;

import hsd.symptom.checker.R;
import hsd.symptom.checker.ShowSpecialityFragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Fragment1 extends Fragment {

	private boolean front, male;
	private View content;
	private Button button_male, button_female;
	private RelativeLayout relativeLayout_male_front, relativeLayout_male_back,
			relativeLayout_female_front, relativeLayout_female_back;
	private ImageView imageView_toast_points;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		content = inflater.inflate(R.layout.fragment1_layout, container, false);

		front = true;
		male = true;

		button_male = (Button) content.findViewById(R.id.button_male);
		button_female = (Button) content.findViewById(R.id.button_female);

		relativeLayout_male_front = (RelativeLayout) content
				.findViewById(R.id.relativeLayout_male_front);
		relativeLayout_male_back = (RelativeLayout) content
				.findViewById(R.id.relativeLayout_male_back);
		relativeLayout_female_front = (RelativeLayout) content
				.findViewById(R.id.relativeLayout_female_front);
		relativeLayout_female_back = (RelativeLayout) content
				.findViewById(R.id.relativeLayout_female_back);

		imageView_toast_points = (ImageView) content
				.findViewById(R.id.imageView_toast_points);

		content.findViewById(R.id.imageView_male_front).setOnTouchListener(
				new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN) {
							int x = (int) event.getX();
							int y = (int) event.getY();
							int colour = getColour(x, y, 1);

							if (Color.rgb(214, 6, 6) == colour) {
								// Toast.makeText(getActivity(), "head",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.head_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(112, 22, 201) == colour) {
								// Toast.makeText(getActivity(), "neck",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.neck_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(81, 71, 92) == colour) {
								// Toast.makeText(getActivity(), "shoulder",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.shoulder_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(66, 199, 115) == colour) {
								// Toast.makeText(getActivity(), "arm",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.arms_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(64, 120, 84) == colour) {
								// Toast.makeText(getActivity(), "hand",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.hands_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(45, 223, 250) == colour) {
								// Toast.makeText(getActivity(), "chest",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.chest_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(181, 126, 24) == colour) {
								// Toast.makeText(getActivity(), "abdomen",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.abdomen_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(83, 118, 176) == colour) {
								// Toast.makeText(getActivity(), "pelvis",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.pelvis_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(113, 123, 138) == colour) {
								// Toast.makeText(getActivity(), "hips",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.hips_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(217, 213, 11) == colour) {
								// Toast.makeText(getActivity(), "leg",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.leg_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(69, 92, 107) == colour) {
								// Toast.makeText(getActivity(), "feet",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.feet_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else {
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.front_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
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

							if (Color.rgb(214, 6, 6) == colour) {
								// Toast.makeText(getActivity(), "head",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.head_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(112, 22, 201) == colour) {
								// Toast.makeText(getActivity(), "neck",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.neck_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(81, 71, 92) == colour) {
								// Toast.makeText(getActivity(), "shoulder",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.shoulder_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(66, 199, 115) == colour) {
								// Toast.makeText(getActivity(), "arm",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.arms_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(64, 120, 84) == colour) {
								// Toast.makeText(getActivity(), "hand",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.hands_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(45, 223, 250) == colour) {
								// Toast.makeText(getActivity(), "back",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.chest_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(181, 126, 24) == colour) {
								// Toast.makeText(getActivity(), "lower back",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.abdomen_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(83, 118, 176) == colour) {
								// Toast.makeText(getActivity(), "buttock",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.pelvis_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(113, 123, 138) == colour) {
								// Toast.makeText(getActivity(), "hips",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.hips_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(217, 213, 11) == colour) {
								// Toast.makeText(getActivity(), "leg",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.leg_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(69, 92, 107) == colour) {
								// Toast.makeText(getActivity(), "feet",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.feet_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else {
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.front_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							}
						}
						return true;
					}
				});

		content.findViewById(R.id.imageView_female_front).setOnTouchListener(
				new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN) {
							int x = (int) event.getX();
							int y = (int) event.getY();
							int colour = getColour(x, y, 1);

							if (Color.rgb(214, 6, 6) == colour) {
								// Toast.makeText(getActivity(), "head",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.head_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(112, 22, 201) == colour) {
								// Toast.makeText(getActivity(), "neck",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.neck_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(81, 71, 92) == colour) {
								// Toast.makeText(getActivity(), "shoulder",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.shoulder_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(66, 199, 115) == colour) {
								// Toast.makeText(getActivity(), "arm",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.arms_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(64, 120, 84) == colour) {
								// Toast.makeText(getActivity(), "hand",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.hands_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(45, 223, 250) == colour) {
								// Toast.makeText(getActivity(), "chest",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.chest_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(181, 126, 24) == colour) {
								// Toast.makeText(getActivity(), "abdomen",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.abdomen_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(83, 118, 176) == colour) {
								// Toast.makeText(getActivity(), "pelvis",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.pelvis_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(113, 123, 138) == colour) {
								// Toast.makeText(getActivity(), "hips",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.hips_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(217, 213, 11) == colour) {
								// Toast.makeText(getActivity(), "leg",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.leg_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(69, 92, 107) == colour) {
								// Toast.makeText(getActivity(), "feet",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.feet_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else {
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.head_back_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							}
						}
						return true;
					}
				});

		content.findViewById(R.id.imageView_female_back).setOnTouchListener(
				new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN) {
							int x = (int) event.getX();
							int y = (int) event.getY();
							int colour = getColour(x, y, 2);

							if (Color.rgb(214, 6, 6) == colour) {
								// Toast.makeText(getActivity(), "head",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.head_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(112, 22, 201) == colour) {
								// Toast.makeText(getActivity(), "neck",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.neck_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(81, 71, 92) == colour) {
								// Toast.makeText(getActivity(), "shoulder",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.shoulder_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(66, 199, 115) == colour) {
								// Toast.makeText(getActivity(), "arm",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.arms_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(64, 120, 84) == colour) {
								// Toast.makeText(getActivity(), "hand",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.hands_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(45, 223, 250) == colour) {
								// Toast.makeText(getActivity(), "back",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.chest_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(181, 126, 24) == colour) {
								// Toast.makeText(getActivity(), "lower back",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.abdomen_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(83, 118, 176) == colour) {
								// Toast.makeText(getActivity(), "buttock",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.pelvis_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(113, 123, 138) == colour) {
								// Toast.makeText(getActivity(), "hips",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.hips_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(217, 213, 11) == colour) {
								// Toast.makeText(getActivity(), "leg",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.leg_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else if (Color.rgb(69, 92, 107) == colour) {
								// Toast.makeText(getActivity(), "feet",
								// Toast.LENGTH_SHORT).show();
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.feet_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							} else {
								imageView_toast_points
										.setVisibility(View.VISIBLE);
								imageView_toast_points
										.setImageResource(R.drawable.head_back_points);
								new Handler().postDelayed(new Runnable() {

									@Override
									public void run() {
										imageView_toast_points
												.setVisibility(View.GONE);
									}
								}, 1000);
							}
						}
						return true;
					}
				});

		button_male.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				male = true;
				front = true;
				button_male.setTextColor(getResources().getColor(
						R.color.primary));
				button_female.setTextColor(getResources().getColor(
						R.color.primary_material_light));
				button_male.setCompoundDrawablesWithIntrinsicBounds(
						R.drawable.ic_male_enabled, 0, 0, 0);
				button_female.setCompoundDrawablesWithIntrinsicBounds(
						R.drawable.ic_female_disabled, 0, 0, 0);
				showBody(male, front);
			}
		});

		button_female.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				male = false;
				front = true;
				button_male.setTextColor(getResources().getColor(
						R.color.primary_material_light));
				button_female.setTextColor(getResources().getColor(
						R.color.primary));
				button_male.setCompoundDrawablesWithIntrinsicBounds(
						R.drawable.ic_male_disabled, 0, 0, 0);
				button_female.setCompoundDrawablesWithIntrinsicBounds(
						R.drawable.ic_female_enabled, 0, 0, 0);
				showBody(male, front);
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
						showBody(male, front);
					}
				});

		return content;
	}

	private void showBody(boolean male, boolean front) {
		Log.e("", "caalled");
		if (male) {
			if (front) {
				relativeLayout_male_front.setVisibility(View.VISIBLE);
				relativeLayout_male_back.setVisibility(View.GONE);
				relativeLayout_female_front.setVisibility(View.GONE);
				relativeLayout_female_back.setVisibility(View.GONE);
			} else {
				relativeLayout_male_front.setVisibility(View.GONE);
				relativeLayout_male_back.setVisibility(View.VISIBLE);
				relativeLayout_female_front.setVisibility(View.GONE);
				relativeLayout_female_back.setVisibility(View.GONE);
			}
		} else {
			if (front) {
				relativeLayout_male_front.setVisibility(View.GONE);
				relativeLayout_male_back.setVisibility(View.GONE);
				relativeLayout_female_front.setVisibility(View.VISIBLE);
				relativeLayout_female_back.setVisibility(View.GONE);
			} else {
				relativeLayout_male_front.setVisibility(View.GONE);
				relativeLayout_male_back.setVisibility(View.GONE);
				relativeLayout_female_front.setVisibility(View.GONE);
				relativeLayout_female_back.setVisibility(View.VISIBLE);
			}
		}
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
