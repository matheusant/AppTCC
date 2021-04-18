package br.com.local.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class RecuperacaoActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {
    Button btnVoltarL, btnEnviar;
    TextInputEditText edtEmail;
    CheckBox checkBoxRecuperar;
    GoogleApiClient googleApiClient;

    String siteKey = "6LewXq4aAAAAAIa1MvVNm3nLu48omE_RDAPQrAsh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recuperacao_layout);

        btnEnviar = findViewById(R.id.btnEnviar);
        btnVoltarL = findViewById(R.id.btnVoltarL);
        edtEmail = findViewById(R.id.edtEmail);
        checkBoxRecuperar = findViewById(R.id.checkBoxRecuperar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();

                if ( checkBoxRecuperar.isChecked() ){
                    if ( email == null || email.equals("") ){
                        Snackbar.make(
                                view,
                                "Insira seu email",
                                Snackbar.LENGTH_SHORT).setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        }).setActionTextColor(getResources().getColor(R.color.colorAccent))
                                .setBackgroundTint(getResources().getColor(R.color.colorLetras))
                                .setTextColor(getResources().getColor(R.color.colorPrimaryDark))
                                .show();
                    }else{
                        startActivity(new Intent(getApplicationContext(), RecuperarSucessoActivity.class));
                        finish();
                    }

                }else{
                    /*Toast.makeText(getApplicationContext(),
                            "Captcha Inválida",
                            Toast.LENGTH_SHORT)
                            .show();*/
                    Snackbar.make(
                            view,
                            "Captcha Inválida",
                            Snackbar.LENGTH_SHORT).setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    }).setActionTextColor(getResources().getColor(R.color.colorAccent))
                            .setBackgroundTint(getResources().getColor(R.color.colorLetras))
                            .setTextColor(getResources().getColor(R.color.colorPrimaryDark))
                            .show();
                }

            }
        });

        btnVoltarL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });


        // Re-Captcha

        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(RecuperacaoActivity.this)
                .build();
                googleApiClient.connect();

                checkBoxRecuperar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if ( checkBoxRecuperar.isChecked() ){
                            SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient, siteKey)
                                    .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                                        @Override
                                        public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                            Status status = recaptchaTokenResult.getStatus();
                                        }
                                    });
                        }
                    }
                });


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}