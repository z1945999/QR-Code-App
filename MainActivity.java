package edu.niu.android.qrcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    Button scan, list;
    TextView format, content;
    DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseManager(this);

        //Initialize the variables to the XML objects.
        scan = findViewById(R.id.scan);
        format = findViewById(R.id.format);
        list = findViewById(R.id.list);
        content = findViewById(R.id.content);
        content.setAutoLinkMask(Linkify.WEB_URLS);
        content.setMovementMethod(LinkMovementMethod.getInstance());
        //Make the content a possible clickable link

        scan.setOnClickListener(v -> {
            IntentIntegrator intent = new IntentIntegrator(this); //Instance var
            intent.setPrompt("Scan QR Code"); //Sets a prompt
            intent.setOrientationLocked(true);//Set to current orientation
            intent.initiateScan();//Initiate the scan by opening the camera
        });
        list.setOnClickListener(v -> {
            Intent intent = new Intent(this, ViewActivity.class);
            this.startActivity(intent);
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intent = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(intent != null)
        {
            if(intent.getContents() == null)
            {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_LONG).show();
            }//If there is no code scanned and the operation is canceled, display a message
            //to update the user.
            else
            {
                content.setText(intent.getContents());//Get the text
                format.setText(intent.getFormatName());//Get the format

                Codes code = new Codes(0, intent.getFormatName(), intent.getContents());
                db.insert(code);

                Toast.makeText(getBaseContext(), "Scan Saved!", Toast.LENGTH_LONG).show();
            }//If a scan is successful, display the data from it onto the two text views
            //then insert it into the SQLite table.
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }//This method is called when the scanner button is clicked and a scan begins.
}