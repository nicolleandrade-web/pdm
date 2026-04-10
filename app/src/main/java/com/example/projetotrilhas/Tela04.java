package com.example.projetotrilhas;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Tela04 extends AppCompatActivity {

    private TextView textView5 , txtJogadas, txtPontuacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela04);

        textView5 = findViewById(R.id.textView5);
        txtJogadas = findViewById(R.id.textView6);
        txtPontuacao = findViewById(R.id.textView7);

        Bundle caixa = getIntent().getExtras();

        if (caixa != null) {
            String nome = caixa.getString("nome");
            int jogadas = caixa.getInt("jogadas");
            int pontuacao = caixa.getInt("pontuacao");

            textView5.setText("Nome: " + nome);
            txtJogadas.setText("Jogadas: " + jogadas);
            txtPontuacao.setText("Pontuação: " + pontuacao);
        }
    }
}