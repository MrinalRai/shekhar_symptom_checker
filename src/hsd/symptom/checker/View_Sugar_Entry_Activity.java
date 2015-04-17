package hsd.symptom.checker;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class View_Sugar_Entry_Activity extends Activity {
	public static final String TYPE = "type";
	private XYMultipleSeriesDataset mDataset = getDemoDataset();
	private XYMultipleSeriesRenderer mRenderer = getDemoRenderer();
	private GraphicalView mChartView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_sugar_entry);

		setRendererStyling();
		if (mChartView == null) {
			LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
			mChartView = ChartFactory.getLineChartView(this, mDataset,
					mRenderer);
			mRenderer.setSelectableBuffer(100);
			layout.addView(mChartView, new LayoutParams(
					LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		} else
			mChartView.repaint();
	}

	private void setRendererStyling() {
		mRenderer.setApplyBackgroundColor(true);
		mRenderer.setBackgroundColor(Color.WHITE);
		mRenderer.setAxisTitleTextSize(16);
		mRenderer.setChartTitleTextSize(20);
		mRenderer.setLabelsTextSize(15);
		mRenderer.setLegendTextSize(15);
		mRenderer.setMargins(new int[] { 20, 30, 15, 0 });
		mRenderer.setZoomButtonsVisible(true);
		mRenderer.setPointSize(10);
	}

	private XYMultipleSeriesDataset getDemoDataset() {
		double[] seriesFirstY = { 120, 220, 167, 180, 245, 224, 199, 234, 188,
				220, 167, 180, 245, 224, 199, 234, 188, 220, 167, 180, 245,
				224, 199, 234, 188, 220, 167, 180, 245, 224, 199, 234, 188,
				220, 167, 180, 245, 224, 199, 234, 188, 220, 167, 180, 245,
				224, 199, 234, 188, 220, 167, 180, 245, 224, 199, 234, 188,
				220, 167, 180, 245, 224, 199, 234, 188, 220, 167, 180, 245,
				224, 199, 234, 188, 220, 167, 180, 245, 224, 199, 234, 188,
				220, 167, 180, 245, 224, 199, 234, 188, 220, 167, 180, 245,
				224, 199, 234, 188, 220, 167, 180, 245, 224, 199, 234, 188,
				220, 167, 180, 245, 224, 199, 234, 188, 220, 167, 180, 245,
				224, 199, 234, 188, 220, 167, 180, 245, 224, 199, 234, 188,
				220, 167, 180, 245, 224, 199, 234, 188, 220, 167, 180, 245,
				224, 199, 234, 188, 220, 167, 180, 245, 224, 199, 234, 188,
				220, 167, 180, 245, 224, 199, 234, 188, 220, 167, 180, 245,
				224, 199, 234, 188, 220, 167, 180, 245, 224, 199, 234, 188,
				220, 167, 180, 245, 224, 199, 234, 188, 220, 167, 180, 245,
				224, 199, 234, 188, 220, 167, 180, 245, 224, 199, 234, 188,
				220, 167, 180, 245, 224, 199, 234, 188, 220, 167, 180, 245,
				224, 199, 234, 188, 220, 167, 180, 245, 224, 199, 234, 188,
				220, 167, 180, 245, 224, 199, 234, 188, 220, 167, 180, 245,
				224, 199, 234, 188, 220, 167, 180, 245, 224, 199, 234, 188,
				220, 167, 180, 245, 224, 199, 234, 188, 220, 167, 180, 245,
				224, 199, 234, 188, 220, 167, 180, 245, 224, 199, 234, 188,
				220, 167, 180, 245, 224, 199, 234, 188, 220, 167, 180, 245,
				224, 199, 234, 188, 220, 167, 180, 245, 224, 199, 234, 188,
				220, 167, 180, 245, 224, 199, 234, 188, 220, 167, 180, 245,
				224, 199, 234, 188, 220, 167, 180, 245, 224, 199, 234, 188,
				220, 167, 180, 245, 224, 199, 234, 188, 220, 167, 180, 245,
				224, 199, 234, 188 };

		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

		XYSeries firstSeries = new XYSeries("Sample series One");
		for (int i = 0; i < 109; i++)
			firstSeries.add(i, seriesFirstY[i]);
		dataset.addSeries(firstSeries);
		return dataset;
	}

	@SuppressWarnings("deprecation")
	private XYMultipleSeriesRenderer getDemoRenderer() {
		String[] date = { "1/3", "1/4", "1/5", "1/6", "1/4", "1/5", "1/6",
				"1/4", "1/5", "1/6", "1/4", "1/5", "1/6", "1/4", "1/5", "1/6",
				"1/4", "1/5", "1/6" };
		XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
		renderer.setMargins(new int[] { 20, 30, 15, 0 });
		XYSeriesRenderer r = new XYSeriesRenderer();
		r = new XYSeriesRenderer();
		r.setPointStyle(PointStyle.CIRCLE);
		r.setColor(Color.parseColor("#45b97c"));
		r.setFillPoints(true);
		for (int i = 0; i < date.length; i++) {

			renderer.addTextLabel(i, date[i]);
		}
		renderer.setXLabelsAlign(Align.CENTER);
		renderer.setXLabels(0);
		renderer.setTextTypeface("sans_serif", Typeface.NORMAL);
		renderer.setBackgroundColor(Color.WHITE);
		renderer.setXLabelsColor(Color.BLACK);
		renderer.setYLabelsColor(0, Color.BLACK);
		renderer.setMarginsColor(Color.WHITE);
		renderer.setAxesColor(Color.BLACK);
		renderer.setLabelsColor(Color.WHITE);
		renderer.setXAxisMax(10);
		renderer.addSeriesRenderer(r);
		return renderer;
	}
}