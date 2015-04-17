package hsd.symptom.checker.navigation.drawer;

import hsd.symptom.checker.R;

import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class Fragment2 extends Fragment {

	private ScrollView scrollView_bmi;
	private LinearLayout linearLayout_chart;
	private boolean showed;

	private SeekBar seekBarAge, seekBarHeight, seekBarWeight;
	private TextView textViewAge, textView_show_age, textViewHeight,
			textView_show_height, textViewWeight, textView_show_weight,
			textView_you_fall_bmi, textView_bmi_val;
	private Spinner spinner_height, spinner_weight;
	private TextView textView_show_more;

	private boolean kgs, cms;

	public Fragment2() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onResume() {
		super.onResume();

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View content = inflater.inflate(R.layout.fragment2_layout, container,
				false);
		kgs = true;
		cms = true;
		scrollView_bmi = (ScrollView) content.findViewById(R.id.scrollView_bmi);
		linearLayout_chart = (LinearLayout) content
				.findViewById(R.id.linearLayout_chart);
		textView_show_more = (TextView) content
				.findViewById(R.id.textView_show_more);

		textView_show_more.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (showed) {
					linearLayout_chart.setVisibility(View.GONE);
					scrollView_bmi.fullScroll(ScrollView.FOCUS_UP);
					textView_show_more.setText("show more");
					textView_show_more.setCompoundDrawablesWithIntrinsicBounds(
							0, 0, R.drawable.ic_more, 0);
					showed = false;
				} else {
					linearLayout_chart.setVisibility(View.VISIBLE);
					scrollView_bmi.postDelayed(new Runnable() {
						@Override
						public void run() {
							scrollView_bmi.fullScroll(ScrollView.FOCUS_DOWN);
							textView_show_more.setText("show less");
							textView_show_more
									.setCompoundDrawablesWithIntrinsicBounds(0,
											0, R.drawable.ic_less, 0);
						}
					}, 100);
					showed = true;
				}
			}
		});

		seekBarAge = (SeekBar) content.findViewById(R.id.seekBarAge);
		seekBarHeight = (SeekBar) content.findViewById(R.id.seekBarHeight);
		seekBarWeight = (SeekBar) content.findViewById(R.id.seekBarWeight);
		seekBarWeight = (SeekBar) content.findViewById(R.id.seekBarWeight);

		textViewAge = (TextView) content.findViewById(R.id.textViewAge);
		textView_show_age = (TextView) content
				.findViewById(R.id.textView_show_age);
		textViewHeight = (TextView) content.findViewById(R.id.textViewHeight);
		textView_show_height = (TextView) content
				.findViewById(R.id.textView_show_height);
		textViewWeight = (TextView) content.findViewById(R.id.textViewWeight);
		textView_show_weight = (TextView) content
				.findViewById(R.id.textView_show_weight);
		textView_you_fall_bmi = (TextView) content
				.findViewById(R.id.textView_you_fall_bmi);
		textView_bmi_val = (TextView) content
				.findViewById(R.id.textView_bmi_val);

		spinner_height = (Spinner) content.findViewById(R.id.spinner_height);
		spinner_weight = (Spinner) content.findViewById(R.id.spinner_weight);

		spinner_height.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == 0) {
					kgs = true;
				} else {
					kgs = false;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		spinner_weight.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				if (position == 0) {
					cms = true;
				} else {
					cms = false;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		textViewAge.setText("0");
		textView_show_age.setText("0");
		textViewHeight.setText("0");
		textView_show_height.setText("0");
		textViewAge.setText("0");
		textViewWeight.setText("0");
		textViewAge.setText("0");
		textView_show_weight.setText("0");

		seekBarAge.setMax(99);
		seekBarAge.setProgress(0);
		seekBarAge.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				textViewAge.setVisibility(View.INVISIBLE);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				textViewAge.setVisibility(View.VISIBLE);
				final Animation animationFadeIn = AnimationUtils.loadAnimation(
						getActivity(), R.anim.fade_out);
				textViewAge.startAnimation(animationFadeIn);

			}
		});
		seekBarAge.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@SuppressLint("NewApi")
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.WRAP_CONTENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT);
				Rect thumbRect = seekBarAge.getThumb().getBounds();
				p.setMargins(thumbRect.left, 0, 0, 0);
				textViewAge.setLayoutParams(p);
				textViewAge.setText(String.valueOf(progress) + "");
				textView_show_age.setText(String.valueOf(progress) + "");
			}
		});

		seekBarHeight.setMax(200);
		seekBarHeight.setProgress(0);
		seekBarHeight.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				textViewHeight.setVisibility(View.INVISIBLE);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				textViewHeight.setVisibility(View.VISIBLE);
				final Animation animationFadeIn = AnimationUtils.loadAnimation(
						getActivity(), R.anim.fade_out);
				textViewHeight.startAnimation(animationFadeIn);
			}
		});
		seekBarHeight.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@SuppressLint("NewApi")
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.WRAP_CONTENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT);
				Rect thumbRect = seekBarHeight.getThumb().getBounds();
				p.setMargins(thumbRect.left, 0, 0, 0);
				textViewHeight.setLayoutParams(p);
				textViewHeight.setText(String.valueOf(progress) + "");
				textView_show_height.setText(String.valueOf(progress) + "");

				Double height_ = Double.parseDouble(textViewHeight.getText()
						.toString());
				Double weight_ = Double.parseDouble(textViewWeight.getText()
						.toString());

				textView_you_fall_bmi.setText("");
				if (!cms) {
					Log.e("cms", "cms = >");
					if (textViewHeight.getText().toString().contains(".")) {
						String[] parts = textViewHeight.getText().toString()
								.split(Pattern.quote("."));
						Double height_ft = Double.parseDouble(parts[0]);
						height_ft = height_ft * 30.48;
						Double height_in = 0.0;
						if (parts.length == 2) {
							try {
								height_in = Double.parseDouble(parts[1]);
							} catch (NumberFormatException e) {
								e.printStackTrace();
							}
						}
						height_in = height_in * 2.54;
						height_ = height_ft + height_in;
						height_ = height_ / 100;
						Log.e("height_", "height_ = >" + height_);
					} else {
						Double height_ft = Double.parseDouble(textViewHeight
								.getText().toString());
						height_ = height_ft * 30.48;
						height_ = height_ / 100;
					}
				} else {
					height_ = height_ / 100;
				}

				if (!kgs) {
					weight_ = weight_ * 0.453592;
				}

				Double bmi = (weight_ / (height_ * height_));
				bmi = round(bmi, 2);
				textView_bmi_val.setText(bmi + "");

				if (bmi < 20.0) {
					textView_you_fall_bmi.setText("(Underweight)");
					textView_bmi_val.setTextColor(Color.parseColor("#FF4C4C"));
					textView_you_fall_bmi.setTextColor(Color
							.parseColor("#FF4C4C"));
				} else if (bmi >= 20.0 && bmi < 25.0) {
					textView_you_fall_bmi.setText("(Normal)");
					textView_bmi_val.setTextColor(getResources().getColor(
							R.color.primary));
					textView_you_fall_bmi.setTextColor(getResources().getColor(
							R.color.primary));
				} else if (bmi >= 25.0 && bmi < 30.0) {
					textView_you_fall_bmi.setText("(Overweight)");
					textView_bmi_val.setTextColor(Color.parseColor("#ff8000"));
					textView_you_fall_bmi.setTextColor(Color
							.parseColor("#ff8000"));
				} else if (bmi >= 30.0 && bmi < 40.0) {
					textView_you_fall_bmi.setText("(Obese)");
					textView_bmi_val.setTextColor(Color.parseColor("#ff4000"));
					textView_you_fall_bmi.setTextColor(Color
							.parseColor("#ff4000"));
				} else if (bmi > 40.0) {
					textView_you_fall_bmi.setText("(Over Obese)");
					textView_bmi_val.setTextColor(Color.parseColor("#FF4C4C"));
					textView_you_fall_bmi.setTextColor(Color
							.parseColor("#FF4C4C"));
				}
			}
		});

		seekBarWeight.setMax(150);
		seekBarWeight.incrementProgressBy(1);
		seekBarWeight.setProgress(0);
		seekBarWeight.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				textViewWeight.setVisibility(View.INVISIBLE);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				textViewWeight.setVisibility(View.VISIBLE);
				final Animation animationFadeIn = AnimationUtils.loadAnimation(
						getActivity(), R.anim.fade_out);
				textViewWeight.startAnimation(animationFadeIn);

			}
		});
		seekBarWeight.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@SuppressLint("NewApi")
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.WRAP_CONTENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT);
				Rect thumbRect = seekBarWeight.getThumb().getBounds();
				p.setMargins(thumbRect.left, 0, 0, 0);
				textViewWeight.setLayoutParams(p);
				textViewWeight.setText(String.valueOf(progress) + "");
				textView_show_weight.setText(String.valueOf(progress) + "");

				Double height_ = Double.parseDouble(textViewHeight.getText()
						.toString());
				Double weight_ = Double.parseDouble(textViewWeight.getText()
						.toString());

				textView_you_fall_bmi.setText("");
				if (!cms) {
					Log.e("cms", "cms = >");
					if (textViewHeight.getText().toString().contains(".")) {
						String[] parts = textViewHeight.getText().toString()
								.split(Pattern.quote("."));
						Double height_ft = Double.parseDouble(parts[0]);
						height_ft = height_ft * 30.48;
						Double height_in = 0.0;
						if (parts.length == 2) {
							try {
								height_in = Double.parseDouble(parts[1]);
							} catch (NumberFormatException e) {
								e.printStackTrace();
							}
						}
						height_in = height_in * 2.54;
						height_ = height_ft + height_in;
						height_ = height_ / 100;
						Log.e("height_", "height_ = >" + height_);
					} else {
						Double height_ft = Double.parseDouble(textViewHeight
								.getText().toString());
						height_ = height_ft * 30.48;
						height_ = height_ / 100;
					}
				} else {
					height_ = height_ / 100;
				}

				if (!kgs) {
					weight_ = weight_ * 0.453592;
				}

				Double bmi = (weight_ / (height_ * height_));
				bmi = round(bmi, 2);
				textView_bmi_val.setText(bmi + "");

				if (bmi < 20.0) {
					textView_you_fall_bmi.setText("(Underweight)");
					textView_bmi_val.setTextColor(Color.parseColor("#FF4C4C"));
					textView_you_fall_bmi.setTextColor(Color
							.parseColor("#FF4C4C"));
				} else if (bmi >= 20.0 && bmi < 25.0) {
					textView_you_fall_bmi.setText("(Normal)");
					textView_bmi_val.setTextColor(getResources().getColor(
							R.color.primary));
					textView_you_fall_bmi.setTextColor(getResources().getColor(
							R.color.primary));
				} else if (bmi >= 25.0 && bmi < 30.0) {
					textView_you_fall_bmi.setText("(Overweight)");
					textView_bmi_val.setTextColor(Color.parseColor("#ff8000"));
					textView_you_fall_bmi.setTextColor(Color
							.parseColor("#ff8000"));
				} else if (bmi >= 30.0 && bmi < 40.0) {
					textView_you_fall_bmi.setText("(Obese)");
					textView_bmi_val.setTextColor(Color.parseColor("#ff4000"));
					textView_you_fall_bmi.setTextColor(Color
							.parseColor("#ff4000"));
				} else if (bmi > 40.0) {
					textView_you_fall_bmi.setText("(Over Obese)");
					textView_bmi_val.setTextColor(Color.parseColor("#FF4C4C"));
					textView_you_fall_bmi.setTextColor(Color
							.parseColor("#FF4C4C"));
				}
			}
		});

		return content;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

	}

	@Override
	public void onDetach() {
		super.onDetach();
	}

	private double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}