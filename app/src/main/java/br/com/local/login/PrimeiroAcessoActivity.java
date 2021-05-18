package br.com.local.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class PrimeiroAcessoActivity extends AppCompatActivity {

    private Button btnCadastrar, btnVoltarL;
    private EditText edtRmAcesso, edtEmailAcesso, edtConfirmarEmailAcesso, edtSenhaAcesso, edtConfirmarSenhaAcesso;
    private String txtRmAcesso, txtEmailAcesso, txtConfirmarEmailAcesso, txtSenhaAcesso, txtConfiramrSenhaAcesso;

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

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
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
    }

}