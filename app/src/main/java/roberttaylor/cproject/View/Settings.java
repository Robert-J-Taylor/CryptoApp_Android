package roberttaylor.cproject.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import roberttaylor.cproject.Payment;
import roberttaylor.cproject.R;

public class Settings extends AppCompatActivity {
    private TextView profile;
    private TextView notifications;
    private TextView wallets;
    private TextView payment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        profile = (TextView) findViewById(R.id.settingsProfile);
        notifications= (TextView) findViewById(R.id.settingsNotifications);
        wallets = (TextView) findViewById(R.id.settingsWallet);
        payment = (TextView) findViewById(R.id.paymentTransferSettings);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this,UserProfile.class));
            }
        });

        notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this,Notifications.class));
            }
        });

        wallets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this,APIKeySettings.class));
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this,Payment.class));
            }
        });
    }
}
