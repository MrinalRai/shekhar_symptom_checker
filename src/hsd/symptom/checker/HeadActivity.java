package hsd.symptom.checker;

import hsd.symptom.checker.services.ConnectionDetector;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class HeadActivity extends Activity {
	private ConnectionDetector cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.head_menu);

		cd = new ConnectionDetector(this);

		findViewById(R.id.button_forehead).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(HeadActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Head");
							intent.putExtra("sub_part", "ForeHead");
							startActivity(intent);
						} else {
							Toast.makeText(HeadActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		findViewById(R.id.button_brain).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(HeadActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Head");
							intent.putExtra("sub_part", "Brain");
							startActivity(intent);
						} else {
							Toast.makeText(HeadActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
		findViewById(R.id.button_left_eye).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(HeadActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Head");
							intent.putExtra("sub_part", "Eye");
							startActivity(intent);
						} else {
							Toast.makeText(HeadActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}

				});

		findViewById(R.id.button_right_eye).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(HeadActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Head");
							intent.putExtra("sub_part", "Eye");
							startActivity(intent);
						} else {
							Toast.makeText(HeadActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		findViewById(R.id.button_left_ear).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(HeadActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Head");
							intent.putExtra("sub_part", "Ear");
							startActivity(intent);
						} else {
							Toast.makeText(HeadActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		findViewById(R.id.button_right_ear).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(HeadActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Head");
							intent.putExtra("sub_part", "Ear");
							startActivity(intent);
						} else {
							Toast.makeText(HeadActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
		findViewById(R.id.button_nose).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(HeadActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Head");
							intent.putExtra("sub_part", "Nose");
							startActivity(intent);
						} else {
							Toast.makeText(HeadActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}

				});

		findViewById(R.id.button_cheek).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(HeadActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Head");
							intent.putExtra("sub_part", "Cheek");
							startActivity(intent);
						} else {
							Toast.makeText(HeadActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
		findViewById(R.id.button_lips).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(HeadActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Head");
							intent.putExtra("sub_part", "Lips");
							startActivity(intent);
						} else {
							Toast.makeText(HeadActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}

				});

		findViewById(R.id.button_teeth).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(HeadActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Head");
							intent.putExtra("sub_part", "Teeth");
							startActivity(intent);
						} else {
							Toast.makeText(HeadActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
		findViewById(R.id.button_chin).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(HeadActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Head");
							intent.putExtra("sub_part", "Chin");
							startActivity(intent);
						} else {
							Toast.makeText(HeadActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}

				});

		findViewById(R.id.button_neck).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(HeadActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Head");
							intent.putExtra("sub_part", "Neck");
							startActivity(intent);
						} else {
							Toast.makeText(HeadActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}

}
