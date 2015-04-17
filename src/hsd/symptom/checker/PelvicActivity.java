package hsd.symptom.checker;

import hsd.symptom.checker.services.ConnectionDetector;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class PelvicActivity extends Activity {
	private ConnectionDetector cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pelvic_menu);

		cd = new ConnectionDetector(this);

		findViewById(R.id.button_hip).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (cd.isConnectingToInternet()) {
					Intent intent = new Intent(PelvicActivity.this,
							Find_Symptoms_Activity.class);
					intent.putExtra("part", "Pelvic");
					intent.putExtra("sub_part", "Hip");
					startActivity(intent);
				} else {
					Toast.makeText(PelvicActivity.this,
							"Not connected to internet!", Toast.LENGTH_SHORT)
							.show();
				}
			}

		});

		findViewById(R.id.button_bladder).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(PelvicActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Pelvic");
							intent.putExtra("sub_part", "Bladder");
							startActivity(intent);
						} else {
							Toast.makeText(PelvicActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		findViewById(R.id.button_genital).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(PelvicActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Pelvic");
							intent.putExtra("sub_part", "Genital");
							startActivity(intent);
						} else {
							Toast.makeText(PelvicActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}

				});

		findViewById(R.id.button_pelvis).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(PelvicActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Pelvic");
							intent.putExtra("sub_part", "Pelvis");
							startActivity(intent);
						} else {
							Toast.makeText(PelvicActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		findViewById(R.id.button_buttock).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(PelvicActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Pelvic");
							intent.putExtra("sub_part", "Buttock");
							startActivity(intent);
						} else {
							Toast.makeText(PelvicActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}

				});
	}

}
