package com.adrian971029.gametime.view;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

import com.adrian971029.gametime.R;
import com.adrian971029.gametime.base.BaseActivity;

public class JogoVelhaActivity extends BaseActivity {

    private static final String SALVAR_CAMPOS = "dadosCampos";

    private Button[] tabuleiro = new Button[10];
    private String vez = "X";
    private int jogadas = 0;
    private String[] matriz = new String[10];
    private int[] letraDoVencedor = new int[3];
    private String[] arrayEstados = new String[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo_velha);

        if (savedInstanceState != null) {
            if (savedInstanceState.getStringArray(SALVAR_CAMPOS) != null) {
                String[] dados = savedInstanceState.getStringArray(SALVAR_CAMPOS);
                prencherCampos(dados);
            }
        }

        inicializaTabuleiro();
        onClickTabuleiro();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        salvarEstados();
        outState.putStringArray(SALVAR_CAMPOS, arrayEstados);
    }

    private void salvarEstados() {
        arrayEstados[0] = tabuleiro[1].getText().toString();
        arrayEstados[1] = tabuleiro[2].getText().toString();
        arrayEstados[2] = tabuleiro[3].getText().toString();
        arrayEstados[3] = tabuleiro[4].getText().toString();
        arrayEstados[4] = tabuleiro[5].getText().toString();
        arrayEstados[5] = tabuleiro[6].getText().toString();
        arrayEstados[6] = tabuleiro[7].getText().toString();
        arrayEstados[7] = tabuleiro[8].getText().toString();
        arrayEstados[8] = tabuleiro[9].getText().toString();
    }

    private void prencherCampos(String[] arrayEstados) {
        tabuleiro[1].setText(arrayEstados[0]);
        tabuleiro[2].setText(arrayEstados[1]);
        tabuleiro[3].setText(arrayEstados[2]);
        tabuleiro[4].setText(arrayEstados[3]);
        tabuleiro[5].setText(arrayEstados[4]);
        tabuleiro[6].setText(arrayEstados[5]);
        tabuleiro[7].setText(arrayEstados[6]);
        tabuleiro[8].setText(arrayEstados[7]);
        tabuleiro[9].setText(arrayEstados[8]);
    }

    private void inicializaTabuleiro() {
        tabuleiro[1] = findViewById(R.id.btn1);
        tabuleiro[2] = findViewById(R.id.btn2);
        tabuleiro[3] = findViewById(R.id.btn3);
        tabuleiro[4] = findViewById(R.id.btn4);
        tabuleiro[5] = findViewById(R.id.btn5);
        tabuleiro[6] = findViewById(R.id.btn6);
        tabuleiro[7] = findViewById(R.id.btn7);
        tabuleiro[8] = findViewById(R.id.btn8);
        tabuleiro[9] = findViewById(R.id.btn9);
    }

    private void onClickTabuleiro() {
        for (int i = 1; i < 10; i++) {
            final int finalI = i;
            tabuleiro[finalI].setOnClickListener(view -> jogada(finalI));
            matriz[i] = "";
        }
    }

    private void jogada(int i) {
        if (matriz[i].equals("")) {
            matriz[i] = vez;
            jogadas++;
            if (vez.equals("X")) {
                vez = "O";
            } else {
                vez = "X";
            }
        }
        exibirTabuleiro();
        verificaFim();
    }

    private void exibirTabuleiro() {
        for (int i = 1; i < 10; i++) {
            tabuleiro[i].setText(matriz[i]);
        }
    }


    private void verificaFim() {
        if (matriz[1].equals(matriz[2]) && matriz[1].equals(matriz[3]) && !matriz[1].equals("")) {
            vencedor(matriz[1]);
            letraDoVencedor[0] = 1;
            letraDoVencedor[1] = 2;
            letraDoVencedor[2] = 3;
            return;
        }
        if (matriz[1].equals(matriz[4]) && matriz[1].equals(matriz[7]) && !matriz[1].equals("")) {
            vencedor(matriz[1]);
            letraDoVencedor[0] = 1;
            letraDoVencedor[1] = 4;
            letraDoVencedor[2] = 7;
            return;
        }
        if (matriz[1].equals(matriz[5]) && matriz[1].equals(matriz[9]) && !matriz[1].equals("")) {
            vencedor(matriz[1]);
            letraDoVencedor[0] = 1;
            letraDoVencedor[1] = 5;
            letraDoVencedor[2] = 9;
            return;
        }
        if (matriz[2].equals(matriz[5]) && matriz[2].equals(matriz[8]) && !matriz[2].equals("")) {
            vencedor(matriz[2]);
            letraDoVencedor[0] = 2;
            letraDoVencedor[1] = 5;
            letraDoVencedor[2] = 8;
            return;
        }
        if (matriz[3].equals(matriz[6]) && matriz[3].equals(matriz[9]) && !matriz[3].equals("")) {
            vencedor(matriz[3]);
            letraDoVencedor[0] = 3;
            letraDoVencedor[1] = 6;
            letraDoVencedor[2] = 9;
            return;
        }
        if (matriz[3].equals(matriz[5]) && matriz[7].equals(matriz[3]) && !matriz[3].equals("")) {
            vencedor(matriz[3]);
            letraDoVencedor[0] = 3;
            letraDoVencedor[1] = 5;
            letraDoVencedor[2] = 7;
            return;
        }
        if (matriz[4].equals(matriz[5]) && matriz[6].equals(matriz[4]) && !matriz[4].equals("")) {
            vencedor(matriz[4]);
            letraDoVencedor[0] = 4;
            letraDoVencedor[1] = 5;
            letraDoVencedor[2] = 6;
            return;
        }
        if (matriz[7].equals(matriz[8]) && matriz[7].equals(matriz[9]) && !matriz[7].equals("")) {
            vencedor(matriz[7]);
            letraDoVencedor[0] = 7;
            letraDoVencedor[1] = 8;
            letraDoVencedor[2] = 9;
            return;
        }
        if (jogadas == 9) {
            vencedor("");
        }

    }

    private void vencedor(String ganhador) {
        AlertDialog.Builder builder = new AlertDialog.Builder(JogoVelhaActivity.this);
        builder.setTitle("Fim de Jogo!");
        if (ganhador.equals("")) {
            builder.setMessage("Deu Velha!");
        } else {
            if (ganhador.equals("X")) {
                builder.setMessage("'X' Venceu! ");
            } else {
                builder.setMessage("'O' Venceu! ");
            }
        }
        builder.setPositiveButton("Novo Jogo", (dialogInterface, i) -> {
            jogadas = 0;
            for (int a = 1; a < 10; a++) {
                matriz[a] = "";
            }
            exibirTabuleiro();
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
