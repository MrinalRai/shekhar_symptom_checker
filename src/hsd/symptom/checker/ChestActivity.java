package hsd.symptom.checker;

import hsd.symptom.checker.services.ConnectionDetector;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ChestActivity extends Activity {
	private ConnectionDetector cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chest_menu);

		cd = new ConnectionDetector(this);

		findViewById(R.id.button_sternum).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(ChestActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Chest");
							intent.putExtra("sub_part", "Sternum");
							startActivity(intent);
						} else {
							Toast.makeText(ChestActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}

				});

		findViewById(R.id.button_breast).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(ChestActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Chest");
							intent.putExtra("sub_part", "Breast");
							startActivity(intent);
						} else {
							Toast.makeText(ChestActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		findViewById(R.id.button_heart).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(ChestActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Chest");
							intent.putExtra("sub_part", "Heart");
							startActivity(intent);
						} else {
							Toast.makeText(ChestActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		findViewById(R.id.button_lung).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(ChestActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Chest");
							intent.putExtra("sub_part", "Lung");
							startActivity(intent);
						} else {
							Toast.makeText(ChestActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}

}
