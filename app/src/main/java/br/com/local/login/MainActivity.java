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
                //Transformar variáveis editText em string
                String usuario = edtUsu.getText().toString();
                String senha = edtSen.getText().toString();

                if (senha.equals("etecia")) {
                    Intent intent= new Intent(getApplicationContext(), QrActivity.class);
                    intent.putExtra("USERLOGIN", usuario);
                    intent.putExtra("USERPASSWORD", senha);
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

                limpar();

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

    public void limpar(){

        edtUsu.setText("");
        edtSen.setText("");
        edtUsu.requestFocus();
    }


}