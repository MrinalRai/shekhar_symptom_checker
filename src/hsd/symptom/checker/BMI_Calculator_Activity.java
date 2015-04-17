package hsd.symptom.checker;

import java.util.ArrayList;
import java.util.regex.Pattern;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BMI_Calculator_Activity extends ActionBarActivity {

	private Button button_calculate;
	private EditText editText_height, editText_weight;
	private TextView textView_your_bmi, textView_bmi, textView_you_fall_bmi;
	// private LinearLayout linearLayout_category;
	private RelativeLayout relativeLayout_bmi;

	private RadioGroup radioGroup1, radioGroup2;

	ArrayList<String> category;
	ArrayList<String> bmi_range;
	ArrayList<String> color_range;
	ArrayList<String> bmi_prime;
	private boolean cms;
	private boolean kgs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bmi_calculator);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setTitle("BMI Calculator");
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		category = new ArrayList<>();
		category.add("Category");
		category.add("Very severely underweight");
		category.add("Severely underweight");
		category.add("Underweight");
		category.add("Normal (healthy weight)");
		category.add("Overweight");
		category.add("Obese Class I (Moderately obese)");
		category.add("Obese Class II (Severely obese)");
		category.add("Obese Class III (Very severely obese)");

		bmi_range = new ArrayList<>();
		bmi_range.add("BMI range – kg/m2");
		bmi_range.add("less than 15");
		bmi_range.add("from 15.0 to 16.0");
		bmi_range.add("from 16.0 to 18.5");
		bmi_range.add("from 18.5 to 25");
		bmi_range.add("from 25 to 30");
		bmi_range.add("from 30 to 35");
		bmi_range.add("from 35 to 40");
		bmi_range.add("over 40");

		bmi_prime = new ArrayList<>();
		bmi_prime.add("Category");
		bmi_prime.add("less than 0.60");
		bmi_prime.add("from 0.60 to 0.64");
		bmi_prime.add("from 0.64 to 0.74");
		bmi_prime.add("from 0.74 to 1.0");
		bmi_prime.add("from 1.0 to 1.2");
		bmi_prime.add("from 1.2 to 1.4");
		bmi_prime.add("from 1.4 to 1.6");
		bmi_prime.add("over 1.6");

		color_range = new ArrayList<>();
		color_range.add("#ccffcc");
		color_range.add("#ff4c4c");
		color_range.add("#ff8000");
		color_range.add("#ffff00");
		color_range.add("#00ff00");
		color_range.add("#ffff00");
		color_range.add("#ff8000");
		color_range.add("#ff4c4c");
		color_range.add("#b20000");

		editText_height = (EditText) findViewById(R.id.editText_height);
		editText_weight = (EditText) findViewById(R.id.editText_weight);
		textView_your_bmi = (TextView) findViewById(R.id.textView_your_bmi);
		textView_bmi = (TextView) findViewById(R.id.textView_bmi);
		textView_you_fall_bmi = (TextView) findViewById(R.id.textView_you_fall_bmi);
		relativeLayout_bmi = (RelativeLayout) findViewById(R.id.relativeLayout_bmi);

		radioGroup1 = (RadioGroup) findViewById(R.id.radioGroup1);
		radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);

		cms = true;
		kgs = true;

		radioGroup1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int id1) {
				if (id1 != -1) {
					if (id1 == R.id.radio1) {
						cms = true;
						textView_bmi.setText("");
						relativeLayout_bmi
								.setBackgroundColor(Color.TRANSPARENT);
						textView_you_fall_bmi.setText("");
						textView_your_bmi.setVisibility(View.GONE);
					}
					if (id1 == R.id.radio2) {
						cms = false;
						textView_bmi.setText("");
						relativeLayout_bmi
								.setBackgroundColor(Color.TRANSPARENT);
						textView_you_fall_bmi.setText("");
						textView_your_bmi.setVisibility(View.GONE);
					}
				}
			}
		});

		radioGroup2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int id1) {
				if (id1 != -1) {
					if (id1 == R.id.radio3) {
						kgs = true;
						textView_bmi.setText("");
						relativeLayout_bmi
								.setBackgroundColor(Color.TRANSPARENT);
						textView_you_fall_bmi.setText("");
						textView_your_bmi.setVisibility(View.GONE);
					}
					if (id1 == R.id.radio4) {
						kgs = false;
						textView_bmi.setText("");
						relativeLayout_bmi
								.setBackgroundColor(Color.TRANSPARENT);
						textView_you_fall_bmi.setText("");
						textView_your_bmi.setVisibility(View.GONE);
					}
				}
			}
		});

		button_calculate = (Button) findViewById(R.id.button_calculate);
		button_calculate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				int id1 = radioGroup1.getCheckedRadioButtonId();
				if (id1 != -1) {
					if (id1 == R.id.radio1) {
						cms = true;
					}
					if (id1 == R.id.radio2) {
						cms = false;
					}
				}

				int id2 = radioGroup2.getCheckedRadioButtonId();
				if (id2 != -1) {
					if (id2 == R.id.radio3) {
						kgs = true;
					}
					if (id2 == R.id.radio4) {
						kgs = false;
					}
				}

				if (editText_height.getText().length() > 0
						&& editText_weight.getText().length() > 0) {
					Double height_ = Double.parseDouble(editText_height
							.getText().toString());
					Double weight_ = Double.parseDouble(editText_weight
							.getText().toString());

					textView_you_fall_bmi.setText("");
					textView_your_bmi.setVisibility(View.VISIBLE);
					try {
						final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (!cms) {
						Log.e("cms", "cms = >");
						if (editText_height.getText().toString().contains(".")) {
							String[] parts = editText_height.getText()
									.toString().split(Pattern.quote("."));
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
							Double height_ft = Double
									.parseDouble(editText_height.getText()
											.toString());
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
					textView_bmi.setText(bmi + "");

					if (bmi < 15.0) {
						Log.e("tah bmi color", "15.0");
						relativeLayout_bmi.setBackgroundColor(Color
								.parseColor(color_range.get(1)));
						textView_you_fall_bmi.append("You fall in "
								+ category.get(1) + " category.");
					} else if (bmi >= 15.0 && bmi < 16.0) {
						Log.e("tah bmi color", "16.0");
						relativeLayout_bmi.setBackgroundColor(Color
								.parseColor(color_range.get(2)));
						textView_you_fall_bmi.append("You fall in "
								+ category.get(2) + " category.");
					} else if (bmi >= 16.0 && bmi < 18.5) {
						Log.e("tah bmi color", "18.5");
						relativeLayout_bmi.setBackgroundColor(Color
								.parseColor(color_range.get(3)));
						textView_you_fall_bmi.append("You fall in "
								+ category.get(3) + " category.");
					} else if (bmi >= 18.5 && bmi < 25.0) {
						Log.e("tah bmi color", "25.0");
						relativeLayout_bmi.setBackgroundColor(Color
								.parseColor(color_range.get(4)));
						textView_you_fall_bmi.append("You fall in "
								+ category.get(4) + " category.");
					} else if (bmi >= 25.0 && bmi < 30.0) {
						Log.e("tah bmi color", "30.0");
						relativeLayout_bmi.setBackgroundColor(Color
								.parseColor(color_range.get(5)));
						textView_you_fall_bmi.append("You fall in "
								+ category.get(5) + " category.");
					} else if (bmi >= 30.0 && bmi < 35.0) {
						Log.e("tah bmi color", "35.0");
						relativeLayout_bmi.setBackgroundColor(Color
								.parseColor(color_range.get(6)));
						textView_you_fall_bmi.append("You fall in "
								+ category.get(6) + " category.");
					} else if (bmi >= 35.0 && bmi < 40.0) {
						Log.e("tah bmi color", "40.0");
						relativeLayout_bmi.setBackgroundColor(Color
								.parseColor(color_range.get(7)));
						textView_you_fall_bmi.append("You fall in "
								+ category.get(7) + " category.");
					} else if (bmi > 40.0) {
						Log.e("tah bmi color", "45.0");
						relativeLayout_bmi.setBackgroundColor(Color
								.parseColor(color_range.get(8)));
						textView_you_fall_bmi.append("You fall in "
								+ category.get(8) + " category.");
					}
				} else {
					if (editText_height.getText().length() > 0) {
						editText_height.setError(null);
					} else {
						editText_height.setError("Please enter height!");
					}
					if (editText_weight.getText().length() > 0) {
						editText_weight.setError(null);
					} else {
						editText_weight.setError("Please enter weight!");
					}
					textView_bmi.setText("");
					relativeLayout_bmi.setBackgroundColor(Color.TRANSPARENT);
				}
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
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
