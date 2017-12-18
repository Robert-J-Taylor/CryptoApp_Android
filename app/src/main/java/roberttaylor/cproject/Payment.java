package roberttaylor.cproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class Payment extends AppCompatActivity {
    private Spinner walletSpinner;
    private Spinner userSpinner;
    private Button completeTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        addListenerOnSpinnerItemSelection();
    }
    public void addListenerOnSpinnerItemSelection() {
        walletSpinner = (Spinner) findViewById(R.id.selectWalletSpinner);
        walletSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        completeTransaction=(Button) findViewById(R.id.completeTransaction);
        completeTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Payment.this,ViewCoinList.class));
            }
        });
        userSpinner = (Spinner) findViewById(R.id.selectUserSpinner);
        userSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
}
