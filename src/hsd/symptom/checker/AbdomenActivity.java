package hsd.symptom.checker;

import hsd.symptom.checker.services.ConnectionDetector;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class AbdomenActivity extends Activity {
	private ConnectionDetector cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.abdomen_menu);

		cd = new ConnectionDetector(this);

		findViewById(R.id.button_upper_abdomen).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(AbdomenActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Abdomen");
							intent.putExtra("sub_part", "Upper Abdomen");
							startActivity(intent);
						} else {
							Toast.makeText(AbdomenActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}

				});

		findViewById(R.id.button_lower_abdomen).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (cd.isConnectingToInternet()) {
							Intent intent = new Intent(AbdomenActivity.this,
									Find_Symptoms_Activity.class);
							intent.putExtra("part", "Abdomen");
							intent.putExtra("sub_part", "Lower Abdomen");
							startActivity(intent);
						} else {
							Toast.makeText(AbdomenActivity.this,
									"Not connected to internet!",
									Toast.LENGTH_SHORT).show();
						}
					}
				});
	}

}
