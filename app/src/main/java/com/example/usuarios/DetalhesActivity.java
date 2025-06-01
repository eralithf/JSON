package com.example.usuarios;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetalhesActivity extends AppCompatActivity {

    TextView nome, email, telefone, cidade, empresa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        nome = findViewById(R.id.txtNome);
        email = findViewById(R.id.txtEmail);
        telefone = findViewById(R.id.txtTelefone);
        cidade = findViewById(R.id.txtCidade);
        empresa = findViewById(R.id.txtEmpresa);

        nome.setText("Nome: " + getIntent().getStringExtra("nome"));
        email.setText("Email: " + getIntent().getStringExtra("email"));
        telefone.setText("Telefone: " + getIntent().getStringExtra("telefone"));
        cidade.setText("Cidade: " + getIntent().getStringExtra("cidade"));
        empresa.setText("Empresa: " + getIntent().getStringExtra("empresa"));
    }
}
