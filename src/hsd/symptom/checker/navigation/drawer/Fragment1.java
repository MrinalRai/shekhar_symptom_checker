package hsd.symptom.checker.navigation.drawer;

import hsd.symptom.checker.FindSymptomsFragment;
import hsd.symptom.checker.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class Fragment1 extends Fragment {

	private boolean front, male;
	private View content;
	private Button button_male, button_female;
	private ImageView imageView_body, imageView_toast_points;

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		content = inflater.inflate(R.layout.fragment1_layout, container, false);

		front = true;
		male = true;

		Typeface font = Typeface.createFromAsset(getResources().getAssets(),
				"Raleway-Regular.ttf");
		button_male = (Button) content.findViewById(R.id.button_male);
		button_female = (Button) content.findViewById(R.id.button_female);
		button_male.setTypeface(font);
		button_female.setTypeface(font);

		imageView_body = (ImageView) content.findViewById(R.id.imageView_body);

		imageView_toast_points = (ImageView) content
				.findViewById(R.id.imageView_toast_points);

		imageView_body.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					int x = (int) event.getX();
					int y = (int) event.getY();

					Log.e("x value", "=> " + x);
					Log.e("y value", "=> " + y);
					final Point p = new Point(235, 390);

					int colour = getColour(x, y);
					if (front) {
						if (Color.rgb(216, 6, 6) == colour) {
							// Toast.makeText(getActivity(), "head",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.head_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									// navigateTo("head", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
									showPopup(getActivity(), p);
								}
							}, 1000);
						} else if (Color.rgb(114, 21, 202) == colour) {
							// Toast.makeText(getActivity(), "neck",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.neck_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("neck", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else if (Color.rgb(81, 70, 92) == colour) {
							// Toast.makeText(getActivity(), "shoulder",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.shoulder_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("shoulder", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else if (Color.rgb(65, 201, 116) == colour) {
							// Toast.makeText(getActivity(), "arm",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.arms_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("arm", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else if (Color.rgb(63, 121, 85) == colour) {
							// Toast.makeText(getActivity(), "hand",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.hands_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("hand", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else if (Color.rgb(43, 230, 252) == colour) {
							// Toast.makeText(getActivity(), "chest",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.chest_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("chest", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else if (Color.rgb(183, 128, 22) == colour) {
							// Toast.makeText(getActivity(), "abdomen",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.abdomen_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("abdomen", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else if (Color.rgb(81, 117, 176) == colour) {
							// Toast.makeText(getActivity(), "pelvis",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.pelvis_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("pelvis", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else if (Color.rgb(113, 123, 139) == colour) {
							// Toast.makeText(getActivity(), "hips",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.hips_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("hips", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else if (Color.rgb(217, 215, 9) == colour) {
							// Toast.makeText(getActivity(), "leg",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.leg_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("leg", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else if (Color.rgb(69, 93, 109) == colour) {
							// Toast.makeText(getActivity(), "feet",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.feet_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("feet", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else {
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.front_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						}
					} else {
						if (Color.rgb(216, 6, 6) == colour) {
							// Toast.makeText(getActivity(), "head",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.head_back_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("head", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else if (Color.rgb(114, 21, 202) == colour) {
							// Toast.makeText(getActivity(), "neck",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.neck_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("neck", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else if (Color.rgb(81, 70, 92) == colour) {
							// Toast.makeText(getActivity(), "shoulder",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.shoulder_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("shoulder", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else if (Color.rgb(65, 201, 116) == colour) {
							// Toast.makeText(getActivity(), "arm",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.arms_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("arm", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else if (Color.rgb(63, 121, 85) == colour) {
							// Toast.makeText(getActivity(), "hand",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.hands_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("hand", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else if (Color.rgb(43, 230, 252) == colour) {
							// Toast.makeText(getActivity(), "back",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.chest_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("back", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else if (Color.rgb(183, 128, 22) == colour) {
							// Toast.makeText(getActivity(), "lower back",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.abdomen_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("lower back", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else if (Color.rgb(81, 117, 176) == colour) {
							// Toast.makeText(getActivity(), "buttock",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.pelvis_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("buttock", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else if (Color.rgb(113, 123, 139) == colour) {
							// Toast.makeText(getActivity(), "hips",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.hips_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("hips", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else if (Color.rgb(217, 215, 9) == colour) {
							// Toast.makeText(getActivity(), "leg",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.leg_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("leg", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else if (Color.rgb(69, 93, 109) == colour) {
							// Toast.makeText(getActivity(), "feet",
							// Toast.LENGTH_SHORT).show();
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.feet_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									navigateTo("feet", male);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						} else {
							imageView_toast_points.setVisibility(View.VISIBLE);
							imageView_toast_points
									.setImageResource(R.drawable.back_points);
							new Handler().postDelayed(new Runnable() {

								@Override
								public void run() {
									imageView_toast_points
											.setVisibility(View.GONE);
									imageView_toast_points
											.setImageResource(android.R.color.transparent);
								}
							}, 1000);
						}
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
				imageView_body.setImageResource(R.drawable.male_front);
			} else {
				imageView_body.setImageResource(R.drawable.male_back);
			}
		} else {
			if (front) {
				imageView_body.setImageResource(R.drawable.female_front);
			} else {
				imageView_body.setImageResource(R.drawable.female_back);
			}
		}
	}

	private void navigateTo(String part, boolean gender) {
		Bundle args = new Bundle();
		args.putString("part", part);
		args.putBoolean("gender", gender);
		FindSymptomsFragment findSymptomsFragment = new FindSymptomsFragment();
		findSymptomsFragment.setArguments(args);
		getActivity().getSupportFragmentManager().beginTransaction()
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.add(R.id.container, findSymptomsFragment).addToBackStack(null)
				.commit();
	}

	private void navigateToHead(String part, boolean gender) {
		Bundle args = new Bundle();
		args.putString("part", "Head");
		args.putString("sub_part", part);
		args.putBoolean("gender", gender);

		FindSymptomsFragment findSymptomsFragment = new FindSymptomsFragment();
		findSymptomsFragment.setArguments(args);
		getActivity().getSupportFragmentManager().beginTransaction()
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.add(R.id.container, findSymptomsFragment).addToBackStack(null)
				.commit();
	}

	private int getColour(int x, int y) {
		ImageView img = (ImageView) content
				.findViewById(R.id.imageView_main_points);
		img.setDrawingCacheEnabled(true);
		Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
		img.setDrawingCacheEnabled(false);
		return hotspots.getPixel(x, y);
	}

	@SuppressWarnings("deprecation")
	private void showPopup(final Activity context, Point p) {

		// Inflate the popup_layout.xml
		LinearLayout viewGroup = (LinearLayout) context
				.findViewById(R.id.popup);
		LayoutInflater layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = layoutInflater.inflate(R.layout.popup_layout, viewGroup);

		// Creating the PopupWindow
		final PopupWindow popup = new PopupWindow(context);
		popup.setContentView(layout);
		popup.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
		popup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
		popup.setFocusable(true);

		// Clear the default translucent background
		popup.setBackgroundDrawable(new BitmapDrawable());

		// Displaying the popup at the specified location.
		popup.showAtLocation(layout, Gravity.NO_GRAVITY, p.x, p.y);

		layout.findViewById(R.id.imageButton_eyes).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						navigateToHead("Eyes", male);
						popup.dismiss();
					}
				});
		layout.findViewById(R.id.imageButton_ear).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						navigateToHead("Ears", male);
						popup.dismiss();
					}
				});
		layout.findViewById(R.id.imageButton_nose).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						navigateToHead("Nose", male);
						popup.dismiss();
					}
				});
		layout.findViewById(R.id.imageButton_mouth).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						navigateToHead("Mouth", male);
						popup.dismiss();
					}
				});
		layout.findViewById(R.id.imageButton_teeth).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						navigateToHead("Teeth", male);
						popup.dismiss();
					}
				});
		layout.findViewById(R.id.imageButton_face).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						navigateToHead("Face", male);
						popup.dismiss();
					}
				});

		// Getting a reference to Close button, and close the popup when
		// clicked.
		imageView_body.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				popup.dismiss();
			}
		});
	}
}
