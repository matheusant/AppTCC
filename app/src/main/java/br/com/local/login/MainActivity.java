package br.com.local.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {
    Button btnEntrar, btnPrimeiroA;
    EditText edtUsu, edtSen;
    TextView txtEsqueceu;
    GoogleApiClient googleApiClient;
    private String URL = "http://192.168.15.28/login_alunos/login.php";

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

        /*btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient, siteKey)
                        .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                            @Override
                            public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                Status status = recaptchaTokenResult.getStatus();

                                //Transformar variáveis editText em string
                                String usuario = edtUsu.getText().toString().trim();
                                String senha = edtSen.getText().toString().trim();

                                if (!usuario.equals("") && senha.equals("etecia") && status.isSuccess()) {
                                    Intent intent = new Intent(getApplicationContext(), QrActivity.class);
                                    intent.putExtra("USERLOGIN", usuario);

                                    startActivity(intent);
                                    finish();

                                } else if (usuario.equals("") || senha.equals("")) {
                                    Toast.makeText(getApplicationContext(),
                                            "RM ou Senha em Branco",
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            "RM ou Senha Incorreto",
                                            Toast.LENGTH_LONG).show();
                                }
                            }
                        });


            }

        });*/

        btnPrimeiroA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PrimeiroAcessoActivity.class));
                finish();
            }
        });

        /*googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(MainActivity.this)
                .build();
        googleApiClient.connect();*/

    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    public void logar(View view){
        final String usuario = edtUsu.getText().toString().trim();
        final String senha = edtSen.getText().toString().trim();

        if (!usuario.equals("") && !senha.equals("")){

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Intent intent = new Intent(getApplicationContext(), QrActivity.class);
                        intent.putExtra("USERLOGIN", usuario);
                        startActivity(intent);
                        finish();
                    } else if (response.equals("failure")) {
                        Toast.makeText(MainActivity.this, "RM ou Senha Incorreto", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("rm_aluno", usuario);
                    data.put("senha_aluno", senha);
                    return data;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(getApplicationContext(), "Preencha Todos os Campos", Toast.LENGTH_SHORT).show();
        }
    }
}