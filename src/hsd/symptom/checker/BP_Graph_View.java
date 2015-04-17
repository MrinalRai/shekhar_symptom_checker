package hsd.symptom.checker;

import android.support.v4.app.Fragment;
import android.view.View;

public class BP_Graph_View extends Fragment {
	private static View graph_view;

	// static LineChart mChart;
	//
	// private Button button_show_list;
	//
	// private static Context mContext;
	//
	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	// graph_view = inflater.inflate(R.layout.layout_graph_view, container,
	// false);
	//
	// mContext = getActivity();
	//
	// button_show_list = (Button) graph_view
	// .findViewById(R.id.button_show_list);
	//
	// button_show_list.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// Add_BP_Entry_Activity.pager.setCurrentItem(1);
	// }
	// });
	//
	// generateGraph();
	// return graph_view;
	// }
	//
	// static void generateGraph() {
	//
	// Toast.makeText(mContext, "generateGraph", Toast.LENGTH_SHORT).show();
	// mChart = (LineChart) graph_view.findViewById(R.id.chart1);
	// // no description text
	// mChart.setDescription("");
	// mChart.setNoDataTextDescription("NO DATA");
	//
	// // enable value highlighting
	// mChart.setHighlightEnabled(true);
	//
	// // enable touch gestures
	// mChart.setTouchEnabled(true);
	//
	// // enable scaling and dragging
	// mChart.setDragEnabled(true);
	// mChart.setScaleEnabled(false);
	// mChart.setDoubleTapToZoomEnabled(false);
	// mChart.zoomIn();
	//
	// // if disabled, scaling can be done on x- and y-axis separately
	// mChart.setPinchZoom(false);
	//
	// // set an alternative background color
	// // mChart.setBackgroundColor(Color.GRAY);
	//
	// // create a custom MarkerView (extend MarkerView) and specify the layout
	// // to use for it
	// MyMarkerView mv = new MyMarkerView(mContext,
	// R.layout.custom_marker_view);
	//
	// // set the marker to the chart
	// mChart.setMarkerView(mv);
	//
	// // enable/disable highlight indicators (the lines that indicate the
	// // highlighted Entry)
	// mChart.setHighlightIndicatorEnabled(false);
	//
	// // add data
	// setData();
	// mChart.animateX(1500);
	// // mChart.setVisibleYRange(30, AxisDependency.LEFT);
	//
	// // // restrain the maximum scale-out factor
	// // mChart.setScaleMinima(3f, 3f);
	// //
	// // // center the view to a specific position inside the chart
	// // mChart.centerViewPort(10, 50, AxisDependency.LEFT);
	//
	// // get the legend (only possible after setting data)
	// Legend l = mChart.getLegend();
	//
	// // modify the legend ...
	// // l.setPosition(LegendPosition.LEFT_OF_CHART);
	// l.setForm(LegendForm.LINE);
	//
	// // // dont forget to refresh the drawing
	// // mChart.invalidate();
	// }
	//
	// private static void setData() {
	// Float max = 0f;
	// int count = Add_BP_Entry_Activity.xdVals.size();
	// ArrayList<String> xVals = new ArrayList<String>();
	// for (int i = 0; i < count; i++) {
	// xVals.add(Add_BP_Entry_Activity.xdVals.get(i).get_date() + "");
	//
	// if (max < Add_BP_Entry_Activity.xdVals.get(i).get_value()) {
	// max = Add_BP_Entry_Activity.xdVals.get(i).get_value();
	// }
	// }
	//
	// ArrayList<Entry> yVals = new ArrayList<Entry>();
	//
	// for (int i = 0; i < count; i++) {
	// Float val = Add_BP_Entry_Activity.xdVals.get(i).get_value();
	// yVals.add(new Entry(val, i));
	// }
	//
	// // create a dataset and give it a type
	// LineDataSet set1 = new LineDataSet(yVals, "Bp graph");
	// // set1.setFillAlpha(110);
	// // set1.setFillColor(Color.RED);
	//
	// // set the line to be drawn like mContext "- - - - - -"
	// set1.enableDashedLine(10f, 0f, 0f);
	// set1.setColor(Color.BLUE);
	// set1.setCircleColor(Color.BLACK);
	// set1.setLineWidth(1f);
	// set1.setCircleSize(1f);
	// set1.setFillAlpha(65);
	// set1.setFillColor(Color.BLACK);
	// // set1.setShader(new LinearGradient(0, 0, 0, mChart.getHeight(),
	// // Color.BLACK, Color.WHITE, Shader.TileMode.MIRROR));
	//
	// ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
	// dataSets.add(set1); // add the datasets
	//
	// // create a data object with the datasets
	// LineData data = new LineData(xVals, dataSets);
	// data.setValueTextSize(10f);
	//
	// LimitLine ll1 = new LimitLine(130f, "Normal");
	// ll1.setLineWidth(4f);
	// ll1.enableDashedLine(10f, 10f, 0f);
	// ll1.setLabelPosition(LimitLabelPosition.POS_RIGHT);
	// ll1.setTextSize(10f);
	//
	// YAxis leftAxis = mChart.getAxisLeft();
	// leftAxis.addLimitLine(ll1);
	// leftAxis.setAxisMaxValue(max);
	// leftAxis.setAxisMinValue(-10f);
	// leftAxis.setStartAtZero(false);
	//
	// YAxis righttAxis = mChart.getAxisRight();
	// righttAxis.setSpaceBottom(40f);
	//
	// mChart.getAxisRight().setEnabled(true);
	// mChart.setData(data);
	// }
}
