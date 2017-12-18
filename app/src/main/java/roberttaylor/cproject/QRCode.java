package roberttaylor.cproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QRCode extends AppCompatActivity {
    private Button saveWallet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        saveWallet=(Button) findViewById(R.id.saveWallet);
        saveWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QRCode.this,APIKeySettings.class));
            }
        });
    }
}
