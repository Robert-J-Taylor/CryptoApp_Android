package roberttaylor.cproject.View;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import roberttaylor.cproject.R;

public class APIKeySettings extends AppCompatActivity {
    private Button saveAPISettings;
    private Button addWallet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apikey_settings);
        addWallet = (Button) findViewById(R.id.addWallet);
        saveAPISettings = (Button) findViewById(R.id.saveChangesWalletList);
        saveAPISettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Saved Exchanges", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                startActivity(new Intent(APIKeySettings.this, Alerts.class));
            }
        });
        addWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(APIKeySettings.this,QRCode.class));
            }
        });
    }
}
