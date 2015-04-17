package hsd.symptom.checker;

import hsd.symptom.checker.services.ConnectionDetector;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class LegActivity extends Activity {
	private ConnectionDetector cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.leg_menu);

		cd = new ConnectionDetector(this);

		findViewById(R.id.button_thigh).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(LegActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Leg");
							intent.putExtra("sub_part", "Thigh");
							startActivity(intent);
						} else {
							Toast.makeText(LegActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}

				});

		findViewById(R.id.button_hamstring).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(LegActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Leg");
							intent.putExtra("sub_part", "Hamstring");
							startActivity(intent);
						} else {
							Toast.makeText(LegActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		findViewById(R.id.button_knee).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(LegActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Leg");
							intent.putExtra("sub_part", "Knee");
							startActivity(intent);
						} else {
							Toast.makeText(LegActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}

				});

		findViewById(R.id.button_calf).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(LegActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Leg");
							intent.putExtra("sub_part", "Calf");
							startActivity(intent);
						} else {
							Toast.makeText(LegActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		findViewById(R.id.button_shin).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(LegActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Leg");
							intent.putExtra("sub_part", "Shin");
							startActivity(intent);
						} else {
							Toast.makeText(LegActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}

				});

		findViewById(R.id.button_ankle).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(LegActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Leg");
							intent.putExtra("sub_part", "Ankle");
							startActivity(intent);
						} else {
							Toast.makeText(LegActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		findViewById(R.id.button_heel).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(LegActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Leg");
							intent.putExtra("sub_part", "Heel");
							startActivity(intent);
						} else {
							Toast.makeText(LegActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}

				});

		findViewById(R.id.button_sole).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(LegActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Leg");
							intent.putExtra("sub_part", "Sole");
							startActivity(intent);
						} else {
							Toast.makeText(LegActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		findViewById(R.id.button_toe).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (cd.isConnectingToInternet()) {
					Intent intent = new Intent(LegActivity.this,
							Find_Symptoms_Activity.class);
					intent.putExtra("part", "Leg");
					intent.putExtra("sub_part", "Toe");
					startActivity(intent);
				} else {
					Toast.makeText(LegActivity.this,
							"Not connected to internet!", Toast.LENGTH_SHORT)
							.show();
				}
			}

		});
	}

}
