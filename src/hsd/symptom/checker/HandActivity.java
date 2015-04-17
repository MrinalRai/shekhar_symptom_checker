package hsd.symptom.checker;

import hsd.symptom.checker.services.ConnectionDetector;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class HandActivity extends Activity {
	private ConnectionDetector cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hand_menu);

		cd = new ConnectionDetector(this);

		findViewById(R.id.button_shoulder).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(HandActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Hand");
							intent.putExtra("sub_part", "Upper Hand");
							startActivity(intent);
						} else {
							Toast.makeText(HandActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}

				});

		findViewById(R.id.button_armpit).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(HandActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Hand");
							intent.putExtra("sub_part", "Lower Hand");
							startActivity(intent);
						} else {
							Toast.makeText(HandActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		findViewById(R.id.button_upper_arm).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(HandActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Hand");
							intent.putExtra("sub_part", "Upper Arm");
							startActivity(intent);
						} else {
							Toast.makeText(HandActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}

				});

		findViewById(R.id.button_elbow).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(HandActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Hand");
							intent.putExtra("sub_part", "Elbow");
							startActivity(intent);
						} else {
							Toast.makeText(HandActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		findViewById(R.id.button_forearm).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(HandActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Hand");
							intent.putExtra("sub_part", "Forearm");
							startActivity(intent);
						} else {
							Toast.makeText(HandActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}

				});

		findViewById(R.id.button_wrist).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(HandActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Hand");
							intent.putExtra("sub_part", "Wrist");
							startActivity(intent);
						} else {
							Toast.makeText(HandActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		findViewById(R.id.button_palm).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(HandActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Hand");
							intent.putExtra("sub_part", "Palm");
							startActivity(intent);
						} else {
							Toast.makeText(HandActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}

				});

		findViewById(R.id.button_fingers).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(HandActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Hand");
							intent.putExtra("sub_part", "Fingers");
							startActivity(intent);
						} else {
							Toast.makeText(HandActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}

}
