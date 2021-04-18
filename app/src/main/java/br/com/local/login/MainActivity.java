package br.com.local.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {
    Button btnEntrar, btnPrimeiroA;
    EditText edtUsu, edtSen;
    TextView txtEsqueceu;
    GoogleApiClient googleApiClient;

    String siteKey = "6LewXq4aAAAAAIa1MvVNm3nLu48omE_RDAPQrAsh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtEsqueceu = findViewById(R.id.txtEsqueceu);
        btnEntrar = findViewById(R.id.btnEntrar);
        btnPrimeiroA = findViewById(R.id.btnPrimeiroA);
        edtUsu = findViewById(R.id.edtUsu);
        edtSen = findViewById(R.id.edtSen);

        txtEsqueceu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), RecuperacaoActivity.class));
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient, siteKey)
                        .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                            @Override
                            public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                Status status = recaptchaTokenResult.getStatus();

                                //Transformar variáveis editText em string
                                String usuario = edtUsu.getText().toString();
                                String senha = edtSen.getText().toString();

                                if (senha.equals("etecia") && status.isSuccess()) {
                                    Intent intent = new Intent(getApplicationContext(), QrActivity.class);
                                    intent.putExtra("USERLOGIN", usuario);
                                    intent.putExtra("USERPASSWORD", senha);

                                    startActivity(intent);
                                    finish();

                                } else if (usuario.equals("") || senha.equals("")) {
                                    Toast.makeText(getApplicationContext(),
                                            "Usuário ou senha em branco",
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            "Usuário ou senha incorreto",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }

        });

        btnPrimeiroA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PrimeiroAcessoActivity.class));
                finish();
            }
        });

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(MainActivity.this)
                .build();
        googleApiClient.connect();

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}