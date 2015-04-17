package hsd.symptom.checker;

import hsd.symptom.checker.services.ConnectionDetector;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class PelvisMappingActivity extends Activity {

	private ConnectionDetector cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.man_pelvis_mapping);
		cd = new ConnectionDetector(this);

		findViewById(R.id.man_front_pelvis).setOnTouchListener(
				new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN) {
							int x = (int) event.getX();
							int y = (int) event.getY();
							int colour = getColour(x, y);
							if (cd.isConnectingToInternet()) {
								if (Color.rgb(153, 217, 234) == colour) {
									Intent intent = new Intent(
											PelvisMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Pelvic");
									intent.putExtra("sub_part", "Hip");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Hip", Toast.LENGTH_SHORT).show();
								}
								if (Color.rgb(255, 242, 0) == colour) {
									Intent intent = new Intent(
											PelvisMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Pelvic");
									intent.putExtra("sub_part", "Bladder");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Bladder", Toast.LENGTH_SHORT)
											.show();
								}
								if (Color.rgb(237, 28, 36) == colour) {
									Intent intent = new Intent(
											PelvisMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Pelvic");
									intent.putExtra("sub_part", "Genital");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Genital", Toast.LENGTH_SHORT)
											.show();
								}
								if (Color.rgb(34, 177, 76) == colour) {
									Intent intent = new Intent(
											PelvisMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Pelvic");
									intent.putExtra("sub_part", "Pelvis");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Pelvis", Toast.LENGTH_SHORT)
											.show();
								}
							} else {
								Toast.makeText(PelvisMappingActivity.this,
										"Not connected to internet!",
										Toast.LENGTH_SHORT).show();
							}
						}
						return true;
					}
				});
	}

	private int getColour(int x, int y) {
		ImageView img = (ImageView) findViewById(R.id.man_front_pelvis_clickable);
		img.setDrawingCacheEnabled(true);
		Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
		img.setDrawingCacheEnabled(false);
		return hotspots.getPixel(x, y);
	}
}
