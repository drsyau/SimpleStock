
package com.christianphan.simplestock;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import yahoofinance.YahooFinance;
import yahoofinance.histquotes.Interval;

/**
 * Created by christian on 7/1/16.
 */
// In this case, the fragment displays simple text based on the page
public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private String index = "";
    private String value = "1";
    private String color = "";
    private String name = "";
    private String percent = "";
    private String volume = "";
    private String annual = "";
    private String open = "";
    private String high = "";
    private String low = "";
    private String change = "";
    private int test = 0;
    private String date1 = "1";
    private String date2 = "2";
    private String date3 = "3";
    private String date4 = "4";
    private String price1 = "1";
    private String price2 = "2";
    private String price3 = "3";
    private String price4 = "4";
    private String price5 = "5";
    private String time = "";
    TextView valueshown;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AdditonalInfo activity = (AdditonalInfo) getActivity();
        index = activity.getIndex();
        change = activity.getChange();
        volume = activity.getVolume();
        test = activity.getTest();

        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_page, container, false);

        CircularProgressView progressView = (CircularProgressView) view.findViewById(R.id.progress_view);
        progressView.startAnimation();

        TextView infotitleshown = (TextView) view.findViewById(R.id.titleInfo);
        TextView datetitleshown = (TextView) view.findViewById(R.id.dateinfo);
        TextView opentitleshown = (TextView) view.findViewById(R.id.openTitle);
        TextView valuetitleshown = (TextView) view.findViewById(R.id.currentTitle);
        TextView hightitleshown = (TextView) view.findViewById(R.id.highTitle);
        TextView lowtitleshown = (TextView) view.findViewById(R.id.lowTitle) ;
        TextView perenttitleshown = (TextView) view.findViewById(R.id.percentTitle);
        TextView totalgaintitleshown = (TextView) view.findViewById(R.id.totalGainTitle);
        TextView volumetitleshown = (TextView) view.findViewById(R.id.volumeTitle);
        TextView annualtitleshown = (TextView) view.findViewById(R.id.annualTitle);
        LineChart chart = (LineChart) view.findViewById(R.id.chart);




        infotitleshown.setVisibility(view.GONE);
        valuetitleshown.setVisibility(view.GONE);
        datetitleshown.setVisibility(view.GONE);
        opentitleshown.setVisibility(view.GONE);
        hightitleshown.setVisibility(view.GONE);
        lowtitleshown.setVisibility(view.GONE);
        perenttitleshown.setVisibility(view.GONE);
        totalgaintitleshown.setVisibility(view.GONE);
        volumetitleshown .setVisibility(view.GONE);
        annualtitleshown.setVisibility(view.GONE);
        chart.setVisibility(view.GONE);



         new NewActivity().execute();


        return view;
    }


    public class NewActivity extends AsyncTask<View, View, View> {


        AdditonalInfo activity = (AdditonalInfo) getActivity();
        private String result;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();



        }
        @Override
        protected View doInBackground(View... args) {

            try {

                yahoofinance.Stock stock = YahooFinance.get(index);

                value = stock.getQuote().getPrice().toString();
                percent = stock.getQuote().getChangeInPercent().toString();
                change = stock.getQuote().getChange().toString();
                open = stock.getQuote().getOpen().toString();
                high = stock.getQuote().getDayHigh().toString();
                low = stock.getQuote().getDayLow().toString();
                volume = stock.getQuote().getVolume().toString();
                annual = stock.getQuote().getYearHigh().toString();
                time = stock.getQuote().getLastTradeDateStr();

                price5 = stock.getHistory(Interval.DAILY).get(0).getClose().toString();
                price4 = stock.getHistory(Interval.DAILY).get(1).getClose().toString();
                price3 = stock.getHistory(Interval.DAILY).get(2).getClose().toString();
                price2 = stock.getHistory(Interval.DAILY).get(3).getClose().toString();
                price1 = stock.getHistory(Interval.DAILY).get(4).getClose().toString();
                date4 = "";
                date3 = "";
                date2 = "";
                date1 = "";


            } catch (MalformedURLException ex) {
                result = "Connection Error";
                return null;
            } catch (IOException ex) {
                result = "Connection Error";
                ;
                return null;
            } catch (Exception e) {
                result = "Connection Error";
                return null;
            }


            result = "success";
            return null;
        }




        @Override
        protected void onPostExecute(View avoid) {


            if(result != "Connection Error" && !isCancelled()) {


                super.onPostExecute(avoid);
                //value
                valueshown = (TextView) getView().findViewById(R.id.valueadditional);
                valueshown.setText("$" + value);

                //open
                TextView openshown = (TextView) getView().findViewById(R.id.openadditional);
                openshown.setText("$" + open);


                //time
                TextView timeshown = (TextView) getView().findViewById(R.id.dateinfo);
                timeshown.setText("Last Trade Date: " + time);

                //high
                TextView highshown = (TextView) getView().findViewById(R.id.highadditional);
                highshown.setText("$" + high );

                //low
                TextView lowshown = (TextView) getView().findViewById(R.id.lowadditional);
                lowshown.setText( "$" + low);

                //change
                TextView changeshown = (TextView) getView().findViewById(R.id.changeadditional);
                changeshown.setText(change);
                if(test == 1)
                {

                    changeshown.setText("+" + change);
                }
                else if (test == 0)
                {
                    changeshown.setTextColor(Color.parseColor("#B00B1E"));
                }


                //percent
                TextView percentshown = (TextView) getView().findViewById(R.id.percentadditional);
                percentshown.setText(percent + "%");
                if(test == 1)
                {

                    percentshown.setText("+" + percent + "%");
                }
                else if (test == 0)
                {
                    percentshown.setTextColor(Color.parseColor("#B00B1E"));
                }




                //volume
                int volumeint = Integer.parseInt(volume);
                String volumeinput = String.format("%.2fM", volumeint/ 1000000.0);
                TextView volumeshown = (TextView) getView().findViewById(R.id.volumeadditional);
                volumeshown.setText(volumeinput);

                //annual
                TextView annualshown = (TextView) getView().findViewById(R.id.annualadditional);
                annualshown.setText(annual);

                //chart
                ArrayList<String> xaxis = new ArrayList<>();
                ArrayList<Entry> yaxis = new ArrayList<>();



                xaxis.add(0,date1 );
                xaxis.add(1, date2);
                xaxis.add(2, date3);
                xaxis.add(3, date4);
                xaxis.add(4, "Today");



                yaxis.add(new Entry(Float.parseFloat(price1), 0));
                yaxis.add(new Entry(Float.parseFloat(price2), 1));
                yaxis.add(new Entry(Float.parseFloat(price3), 2));
                yaxis.add(new Entry(Float.parseFloat(price4), 3));
                yaxis.add(new Entry(Float.parseFloat(price5), 4));


                LineChart chart = (LineChart) getView().findViewById(R.id.chart);


                String[] Xaxis = new String[xaxis.size()];
                for(int i = 0; i < Xaxis.length; i++)
                {
                    Xaxis[i] = xaxis.get(i);
                }

                ArrayList<ILineDataSet> lineDataSet = new ArrayList<>();
                LineDataSet lineDataSet1 = new LineDataSet(yaxis,index + " value");
                lineDataSet1.setDrawCircles(true);
                lineDataSet1.setCircleColorHole(Color.BLUE);
                lineDataSet1.setColor(Color.BLUE);
                lineDataSet1.setDrawFilled(true);
                lineDataSet1.setCircleColor(Color.BLUE);

                lineDataSet.add(lineDataSet1);

                YAxis leftAxis = chart.getAxisLeft();
                YAxis rightAxis = chart.getAxisRight();

                float lowestprice = 0;
                float highestprice = 0;
                int degree = 0;

                for(int i = 0; i < yaxis.size(); i++)
                {
                    if(lowestprice > yaxis.get(i).getVal()) {
                        lowestprice = yaxis.get(i).getVal();

                    }
                    else if(lowestprice == 0)
                    {
                        lowestprice = yaxis.get(i).getVal();
                    }

                    if(highestprice < yaxis.get(i).getVal())
                    {
                        highestprice = yaxis.get(i).getVal();
                    }


                }


                degree = Math.round(highestprice - lowestprice);



                chart.getAxisRight().setStartAtZero(false);
                chart.getAxisLeft().setStartAtZero(false);
                leftAxis.setAxisMinValue(lowestprice - degree);
                rightAxis.setAxisMinValue(lowestprice - degree);
                chart.setPinchZoom(false);


                chart.setDescription("Close Value Over Last 5 Trading Days");

                chart.setData(new LineData(Xaxis, lineDataSet));
                chart.notifyDataSetChanged();
                chart.invalidate();



                CircularProgressView progressView = (CircularProgressView) getView().findViewById(R.id.progress_view);
                progressView.setVisibility(getView().GONE);

                TextView infotitleshown = (TextView) getView().findViewById(R.id.titleInfo);
                TextView datetitleshown = (TextView) getView().findViewById(R.id.dateinfo);
                TextView opentitleshown = (TextView) getView().findViewById(R.id.openTitle);
                TextView valuetitleshown = (TextView) getView().findViewById(R.id.currentTitle);
                TextView hightitleshown = (TextView) getView().findViewById(R.id.highTitle);
                TextView lowtitleshown = (TextView) getView().findViewById(R.id.lowTitle) ;
                TextView perenttitleshown = (TextView) getView().findViewById(R.id.percentTitle);
                TextView totalgaintitleshown = (TextView) getView().findViewById(R.id.totalGainTitle);
                TextView volumetitleshown = (TextView) getView().findViewById(R.id.volumeTitle);
                TextView annualtitleshown = (TextView) getView().findViewById(R.id.annualTitle);


                infotitleshown.setVisibility(getView().VISIBLE);
                valuetitleshown.setVisibility(getView().VISIBLE);
                datetitleshown.setVisibility(getView().VISIBLE);
                opentitleshown.setVisibility(getView().VISIBLE);
                hightitleshown.setVisibility(getView().VISIBLE);
                lowtitleshown.setVisibility(getView().VISIBLE);
                perenttitleshown.setVisibility(getView().VISIBLE);
                totalgaintitleshown.setVisibility(getView().VISIBLE);
                volumetitleshown .setVisibility(getView().VISIBLE);
                annualtitleshown.setVisibility(getView().VISIBLE);
                chart.setVisibility(getView().VISIBLE);


            }



        }



    }







}



