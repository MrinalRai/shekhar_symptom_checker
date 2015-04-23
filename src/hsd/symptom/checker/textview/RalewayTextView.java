package hsd.symptom.checker.textview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class RalewayTextView extends TextView {

	public RalewayTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public RalewayTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public RalewayTextView(Context context) {
		super(context);
		init();
	}

	private void init() {
		Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
				"Raleway-Regular.ttf");
		setTypeface(tf);
	}

}