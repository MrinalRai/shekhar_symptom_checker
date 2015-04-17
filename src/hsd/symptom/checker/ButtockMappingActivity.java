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

public class ButtockMappingActivity extends Activity {

	private ConnectionDetector cd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.man_back_buttock_mapping);
		cd = new ConnectionDetector(this);

		findViewById(R.id.man_back_buttock).setOnTouchListener(
				new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						// TODO Auto-generated method stub
						if (event.getAction() == MotionEvent.ACTION_DOWN) {
							int x = (int) event.getX();
							int y = (int) event.getY();
							int colour = getColour(x, y);
							if (cd.isConnectingToInternet()) {
								if (Color.rgb(237, 28, 36) == colour) {
									Intent intent = new Intent(
											ButtockMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Buttock");
									intent.putExtra("sub_part", "Buttock");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Buttock", Toast.LENGTH_SHORT)
											.show();
								}
								if (Color.rgb(255, 242, 0) == colour) {
									Intent intent = new Intent(
											ButtockMappingActivity.this,
											Find_Symptoms_Activity.class);
									intent.putExtra("part", "Buttock");
									intent.putExtra("sub_part", "Hips");
									startActivity(intent);
									Toast.makeText(getApplicationContext(),
											"Hips", Toast.LENGTH_SHORT).show();
								}
							} else {
								Toast.makeText(ButtockMappingActivity.this,
										"Not connected to internet!",
										Toast.LENGTH_SHORT).show();
							}
						}
						return true;
					}
				});
	}

	private int getColour(int x, int y) {
		ImageView img = (ImageView) findViewById(R.id.man_back_buttock_clickable);
		img.setDrawingCacheEnabled(true);
		Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
		img.setDrawingCacheEnabled(false);
		return hotspots.getPixel(x, y);
	}
}
