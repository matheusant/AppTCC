package br.com.local.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class PrimeiroAcessoActivity extends AppCompatActivity {

    private Button btnCadastrar, btnVoltarL;
    private EditText edtRmAcesso, edtEmailAcesso, edtConfirmarEmailAcesso, edtSenhaAcesso, edtConfirmarSenhaAcesso;
    private String txtRmAcesso, txtEmailAcesso, txtConfirmarEmailAcesso, txtSenhaAcesso, txtConfiramrSenhaAcesso;
    private String URL = "http://192.168.15.28/login_alunos/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.primeiro_acesso_layout);
        btnCadastrar = findViewById(R.id.btnCadastro);
        btnVoltarL = findViewById(R.id.btnVoltarL);
        edtRmAcesso = findViewById(R.id.edtRmAcesso);
        edtEmailAcesso = findViewById(R.id.edtEmailAcesso);
        edtConfirmarEmailAcesso = findViewById(R.id.edtConfirmarEmailAcesso);
        edtSenhaAcesso = findViewById(R.id.edtSenhaAcesso);
        edtConfirmarSenhaAcesso = findViewById(R.id.edtConfirmarSenhaAcesso);

        btnVoltarL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        /*btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtRmAcesso = edtRmAcesso.getText().toString();
                txtEmailAcesso = edtEmailAcesso.getText().toString();
                txtConfirmarEmailAcesso = edtConfirmarEmailAcesso.getText().toString();
                txtSenhaAcesso = edtSenhaAcesso.getText().toString();
                txtConfiramrSenhaAcesso = edtConfirmarSenhaAcesso.getText().toString();

                if (txtEmailAcesso.equals(txtConfirmarEmailAcesso)) {
                    if (txtSenhaAcesso.equals(txtConfiramrSenhaAcesso)) {
                        if (!txtRmAcesso.equals("") && !txtEmailAcesso.equals("") && !txtConfirmarEmailAcesso.equals("") && !txtSenhaAcesso.equals("") && !txtConfiramrSenhaAcesso.equals("")) {
                            startActivity(new Intent(getApplicationContext(), CadastroActivity.class));
                            finish();
                        } else {
                            Snackbar.make(
                                    view,
                                    "Preencha Todos os Campos",
                                    Snackbar.LENGTH_SHORT).setAction("OK", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                }
                            }).setActionTextColor(getResources().getColor(R.color.colorAccent))
                                    .setBackgroundTint(getResources().getColor(R.color.colorLetras))
                                    .setTextColor(getResources().getColor(R.color.colorPrimaryDark))
                                    .show();
                        }
                    } else {
                        Snackbar.make(
                                view,
                                "Senhas Diferentes",
                                Snackbar.LENGTH_SHORT).setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        }).setActionTextColor(getResources().getColor(R.color.colorAccent))
                                .setBackgroundTint(getResources().getColor(R.color.colorLetras))
                                .setTextColor(getResources().getColor(R.color.colorPrimaryDark))
                                .show();

                    }

                } else {
                    Snackbar.make(
                            view,
                            "Emails Diferentes",
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

         */
    }

    public void registrar(View view){

        txtRmAcesso = edtRmAcesso.getText().toString();
        txtEmailAcesso = edtEmailAcesso.getText().toString();
        txtConfirmarEmailAcesso = edtConfirmarEmailAcesso.getText().toString();
        txtSenhaAcesso = edtSenhaAcesso.getText().toString();
        txtConfiramrSenhaAcesso = edtConfirmarSenhaAcesso.getText().toString();

        if(!txtEmailAcesso.equals(txtConfirmarEmailAcesso)){
            Toast.makeText(getApplicationContext(), "Emails Diferentes", Toast.LENGTH_SHORT).show();
        }else if (!txtSenhaAcesso.equals(txtConfiramrSenhaAcesso)){
            Toast.makeText(getApplicationContext(), "Senhas Diferentes", Toast.LENGTH_SHORT).show();
        }else if (!txtRmAcesso.equals("") && !txtEmailAcesso.equals("") && !txtSenhaAcesso.equals("")){

            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equals("success")) {
                        Toast.makeText(getApplicationContext(), "Cadastrado com Sucesso", Toast.LENGTH_SHORT).show();
                        btnCadastrar.setClickable(false);
                    } else if (response.equals("failure")) {
                        Toast.makeText(getApplicationContext(), "Algo Está Errado", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("email_aluno", txtEmailAcesso);
                    data.put("senha_aluno", txtSenhaAcesso);
                    data.put("rm_aluno", txtRmAcesso);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else if(txtRmAcesso.equals("") && txtEmailAcesso.equals("") && txtSenhaAcesso.equals("")){
            Toast.makeText(getApplicationContext(), "Preencha Todos os Campos", Toast.LENGTH_SHORT).show();
        }

    }

}