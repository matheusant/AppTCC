package br.com.local.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnEntrar, btnPrimeiroA;
    EditText edtUsu, edtSen;
    TextView txtEsqueceu;

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
                //Transformar variáveis edittext em string
                String usuario = edtUsu.getText().toString();
                String senha = edtSen.getText().toString();

                if (usuario.equals("12345") && senha.equals("etecia")) {
                    Intent intent= new Intent(getApplicationContext(), QrActivity.class);
                    startActivity(intent);
                    finish();
                }else if (usuario.equals("") || senha.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Usuário ou senha em branco",
                            Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),
                            "Usuário ou senha incorreto!!",
                            Toast.LENGTH_SHORT).show();
                }

            }

        });

        btnPrimeiroA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PrimeiroAcessoActivity.class));
                finish();
            }
        });

    }


}