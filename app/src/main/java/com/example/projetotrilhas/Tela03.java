package com.example.projetotrilhas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;

public class Tela03 extends AppCompatActivity implements View.OnClickListener {

    private ImageView[] imgs = new ImageView[8];
    private ImageView imgPrimeiroToque, imgSegundoToque;

    private int imagemPrimeiroToque, imagemSegundoToque;
    private int contaToque = 0;
    private int quantasJogadas = 0;

    private boolean bloqueado = false;

    private TextView texto;
    private ArrayList<Integer> lista;

    private Button btn, btn2;
    private String nomeSujeito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela03);

        texto = findViewById(R.id.textView3);

        btn = findViewById(R.id.button3);
        btn2 = findViewById(R.id.button4);

        btn.setOnClickListener(this);
        btn2.setOnClickListener(this);

        // RECEBE NOME
        Intent i = getIntent();
        if (i != null && i.getExtras() != null) {
            nomeSujeito = i.getExtras().getString("nome");
        }

        // IMAGENS
        imgs[0] = findViewById(R.id.imageView2);
        imgs[1] = findViewById(R.id.imageView3);
        imgs[2] = findViewById(R.id.imageView4);
        imgs[3] = findViewById(R.id.imageView5);
        imgs[4] = findViewById(R.id.imageView6);
        imgs[5] = findViewById(R.id.imageView7);
        imgs[6] = findViewById(R.id.imageView8);
        imgs[7] = findViewById(R.id.imageView9);

        for (ImageView img : imgs) {
            img.setOnClickListener(this);
        }

        carregaTudo();
    }

    public void carregaTudo() {

        texto.setText(nomeSujeito);

        lista = new ArrayList<>();
        lista.add(R.drawable.ic_action_name_3);
        lista.add(R.drawable.ic_action_name_3);
        lista.add(R.drawable.ic_action_name_4);
        lista.add(R.drawable.ic_action_name_4);
        lista.add(R.drawable.ic_action_name_5);
        lista.add(R.drawable.ic_action_name_5);
        lista.add(R.drawable.ic_action_name_6);
        lista.add(R.drawable.ic_action_name_6);

        Collections.shuffle(lista);

        for (int i = 0; i < imgs.length; i++) {
            imgs[i].setImageResource(lista.get(i));
            imgs[i].setEnabled(false);
            imgs[i].setBackgroundColor(Color.TRANSPARENT);
        }

        new Handler().postDelayed(() -> {
            for (ImageView img : imgs) {
                img.setImageResource(R.drawable.ic_action_name_2);
                img.setEnabled(true);
            }
        }, 1500);

        contaToque = 0;
        bloqueado = false;
    }

    public void compara() {

        if (imagemPrimeiroToque == imagemSegundoToque) {

            imgPrimeiroToque.setEnabled(false);
            imgSegundoToque.setEnabled(false);

            imgPrimeiroToque.setBackgroundColor(Color.GREEN);
            imgSegundoToque.setBackgroundColor(Color.GREEN);

            bloqueado = false;

        } else {

            imgPrimeiroToque.setBackgroundColor(Color.RED);
            imgSegundoToque.setBackgroundColor(Color.RED);

            new Handler().postDelayed(() -> {

                imgPrimeiroToque.setImageResource(R.drawable.ic_action_name_2);
                imgSegundoToque.setImageResource(R.drawable.ic_action_name_2);

                imgPrimeiroToque.setBackgroundColor(Color.TRANSPARENT);
                imgSegundoToque.setBackgroundColor(Color.TRANSPARENT);

                bloqueado = false;

            }, 1000);
        }

        contaToque = 0;
    }

    @Override
    public void onClick(View v) {

        if (bloqueado) return;

        if (v == btn) {
            quantasJogadas++;
            carregaTudo();
            return;
        }

        if (v == btn2) {
            Intent i = new Intent(this, Tela04.class);
            i.putExtra("nome", nomeSujeito);
            i.putExtra("contador", quantasJogadas);
            startActivity(i);
            return;
        }

        ImageView clicada = (ImageView) v;

        int index = -1;
        for (int i = 0; i < imgs.length; i++) {
            if (v == imgs[i]) {
                index = i;
                break;
            }
        }

        if (index == -1) return;

        clicada.setImageResource(lista.get(index));

        if (contaToque == 0) {
            imgPrimeiroToque = clicada;
            imagemPrimeiroToque = lista.get(index);
            contaToque = 1;

        } else if (contaToque == 1 && clicada != imgPrimeiroToque) {

            imgSegundoToque = clicada;
            imagemSegundoToque = lista.get(index);

            contaToque = 2;
            bloqueado = true;

            compara();
        }
    }
}