package br.com.local.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class QrActivity extends AppCompatActivity {
    Button btnSair;
    ImageView ivQR;
    TextView txtRm, textData;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date data = new Date();
    String dataFormatada = dateFormat.format( data );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_layout);

        ivQR = findViewById(R.id.ivQR);
        textData = findViewById(R.id.textData);

        textData.setText( "Data: " + dataFormatada );

        gerarQRcode();


        txtRm = findViewById(R.id.txtRm);
        btnSair = findViewById(R.id.btnSair);

        Intent intent = getIntent();
        String texto = intent.getStringExtra("USERLOGIN");

        txtRm.setText(texto);

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });


    }

    private void gerarQRcode() {
        Intent intent = getIntent();
        String texto = intent.getStringExtra("USERLOGIN");
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(texto + " " + dataFormatada, BarcodeFormat.QR_CODE,250,250);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            ivQR.setImageBitmap(bitmap);
        }catch (WriterException e){
            e.printStackTrace();
        }
    }


}