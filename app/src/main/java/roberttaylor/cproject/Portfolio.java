package roberttaylor.cproject;

import android.content.Intent;
import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.entity.mime.Header;

public class Portfolio extends AppCompatActivity {
    // Constants:
    // TODO: Create the base URL
    private final String BASE_URL = "https://apiv2.bitcoinaverage.com/indices/global/ticker/BTCAUD";
    //private final String BASE_URL = "http://wtfdnsftw.freeddns.org:5000/candlestick?exchange=binance&market=NEOBTC&start_date=2017-11-19%2020:00:00.000";
    private TextView mPriceTextView;
    // Member Variables:
    private Button buttonTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        //mPriceTextView= findViewById(R.id.priceLabel);
        buttonTest = (Button) findViewById(R.id.findPriceButton);
        Intent intent= getIntent();
        mPriceTextView =(TextView)findViewById(R.id.priceLabel);
        String txt_put =intent.getStringExtra("Price");
        mPriceTextView.setText(txt_put);


        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Bitcoin", "" + v);
                String finalUrl = BASE_URL;
                Log.d("Bitcoin", "Final url is: " + finalUrl);
                letsDoSomeNetworking(finalUrl);
            }
        });
    }
    private void letsDoSomeNetworking(String url) {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                // called when response HTTP status is "200 OK"
                Log.d("Bitcoin", "JSON: " + response.toString());
                try{
                    String price = response.getString("high");
//                   JSONObject jsonObject = response.getJSONObject(0);
//                   String price = jsonObject.getString("time");
                    mPriceTextView.setText(price);
                }catch(JSONException e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, Throwable e, JSONObject response) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                Log.d("Bitcoin", "Request fail! Status code: " + statusCode);
                Log.d("Bitcoin", "Fail response: " + response);
                Log.e("ERROR", e.toString());

            }
        });


    }

}
