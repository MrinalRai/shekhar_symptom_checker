package hsd.symptom.checker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class FrontBodyMappingActivity extends Activity {

	private boolean isBackVisible = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.man_front_body_mapping);

		findViewById(R.id.man_front).setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					int x = (int) event.getX();
					int y = (int) event.getY();
					int colour = getColour(x, y, 1);

					if (Color.rgb(237, 28, 36) == colour) {
						startActivity(new Intent(FrontBodyMappingActivity.this,
								HeadMappingActivity.class));
						Toast.makeText(getApplicationContext(), "head",
								Toast.LENGTH_SHORT).show();
					}
					if (Color.rgb(63, 72, 204) == colour) {
						startActivity(new Intent(FrontBodyMappingActivity.this,
								ChestMappingActivity.class));
						Toast.makeText(getApplicationContext(), "chest",
								Toast.LENGTH_SHORT).show();
					}
					if (Color.rgb(255, 127, 39) == colour) {
						startActivity(new Intent(FrontBodyMappingActivity.this,
								HandMappingActivity.class));
						Toast.makeText(getApplicationContext(), "hand",
								Toast.LENGTH_SHORT).show();
					}
					if (Color.rgb(239, 228, 176) == colour) {
						startActivity(new Intent(FrontBodyMappingActivity.this,
								AbdomenMappingActivity.class));
						Toast.makeText(getApplicationContext(), "abdomen",
								Toast.LENGTH_SHORT).show();
					}
					if (Color.rgb(136, 0, 21) == colour) {
						startActivity(new Intent(FrontBodyMappingActivity.this,
								PelvisMappingActivity.class));
						Toast.makeText(getApplicationContext(), "pelvis",
								Toast.LENGTH_SHORT).show();
					}
					if (Color.rgb(230, 5, 248) == colour) {
						startActivity(new Intent(FrontBodyMappingActivity.this,
								LegMappingActivity.class));
						Toast.makeText(getApplicationContext(), "leg",
								Toast.LENGTH_SHORT).show();
					}
				}
				return true;
			}
		});

		findViewById(R.id.man_back).setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					int x = (int) event.getX();
					int y = (int) event.getY();
					int colour = getColour(x, y, 2);

					if (Color.rgb(237, 28, 36) == colour) {
						startActivity(new Intent(FrontBodyMappingActivity.this,
								BackHeadMappingActivity.class));
						Toast.makeText(getApplicationContext(), "head",
								Toast.LENGTH_SHORT).show();
					}
					// if (Color.rgb(255, 242, 0) == colour) {
					// startActivity(new
					// Intent(FrontBodyMappingActivity.this,
					// BackHeadMappingActivity.class));
					// Toast.makeText(getApplicationContext(), "neck",
					// Toast.LENGTH_SHORT).show();
					// }
					if (Color.rgb(63, 72, 204) == colour) {
						startActivity(new Intent(FrontBodyMappingActivity.this,
								UpperBackMappingActivity.class));
						Toast.makeText(getApplicationContext(), "upper back",
								Toast.LENGTH_SHORT).show();
					}
					if (Color.rgb(255, 174, 201) == colour) {
						startActivity(new Intent(FrontBodyMappingActivity.this,
								LowerBackMappingActivity.class));
						Toast.makeText(getApplicationContext(), "lower back",
								Toast.LENGTH_SHORT).show();
					}
					if (Color.rgb(34, 177, 76) == colour) {
						startActivity(new Intent(FrontBodyMappingActivity.this,
								ButtockMappingActivity.class));
						Toast.makeText(getApplicationContext(), "buttock",
								Toast.LENGTH_SHORT).show();
					}
					if (Color.rgb(89, 230, 213) == colour) {
						startActivity(new Intent(FrontBodyMappingActivity.this,
								BackHandMappingActivity.class));
						Toast.makeText(getApplicationContext(), "hand",
								Toast.LENGTH_SHORT).show();
					}
					if (Color.rgb(255, 128, 64) == colour) {
						startActivity(new Intent(FrontBodyMappingActivity.this,
								BackLegMappingActivity.class));
						Toast.makeText(getApplicationContext(), "leg",
								Toast.LENGTH_SHORT).show();
					}
				}
				return true;
			}
		});

		findViewById(R.id.button_back).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						if (!isBackVisible) {
							isBackVisible = true;
							findViewById(R.id.man_front_view).setVisibility(
									View.GONE);
							findViewById(R.id.man_back_view).setVisibility(
									View.VISIBLE);
						} else {
							isBackVisible = false;
							findViewById(R.id.man_front_view).setVisibility(
									View.VISIBLE);
							findViewById(R.id.man_back_view).setVisibility(
									View.GONE);
						}
					}
				});

		// findViewById(R.id.button_back).setOnClickListener(
		// new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// finish();
		// startActivity(new Intent(FrontBodyMappingActivity.this,
		// FrontBodyMappingActivity.class));
		// }
		// });
	}

	private int getColour(int x, int y, int i) {
		ImageView img = null;
		switch (i) {
		case 1:
			img = (ImageView) findViewById(R.id.man_front_clickable);
			break;

		case 2:
			img = (ImageView) findViewById(R.id.man_back_clickable);
			break;
		case 3:
			img = (ImageView) findViewById(R.id.man_front_clickable);
			break;
		case 4:
			img = (ImageView) findViewById(R.id.man_front_clickable);
			break;
		}
		img.setDrawingCacheEnabled(true);
		Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache());
		img.setDrawingCacheEnabled(false);
		return hotspots.getPixel(x, y);
	}
}
