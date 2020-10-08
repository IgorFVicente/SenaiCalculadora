package br.senai.sc.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private String numeroAtual = "";
    private ArrayList<Float> numeros = new ArrayList();
    private ArrayList<String> operacoes = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Minha Calculadora");
        display = findViewById(R.id.tv_display);
    }

    public void onClickBotao1(View v) {
        atualizarNumeroDigitado("1");
        atualizarDisplay("1");
    }

    public void onClickBotao2(View v) {
        atualizarNumeroDigitado("2");
        atualizarDisplay("2");
    }

    public void onClickBotao3(View v) {
        atualizarNumeroDigitado("3");
        atualizarDisplay("3");
    }

    public void onClickBotao4(View v) {
        atualizarNumeroDigitado("4");
        atualizarDisplay("4");
    }

    public void onClickBotao5(View v) {
        atualizarNumeroDigitado("5");
        atualizarDisplay("5");
    }

    public void onClickBotao6(View v) {
        atualizarNumeroDigitado("6");
        atualizarDisplay("6");
    }

    public void onClickBotao7(View v) {
        atualizarNumeroDigitado("7");
        atualizarDisplay("7");
    }

    public void onClickBotao8(View v) {
        atualizarNumeroDigitado("8");
        atualizarDisplay("8");
    }

    public void onClickBotao9(View v) {
        atualizarNumeroDigitado("9");
        atualizarDisplay("9");
    }

    public void onClickBotao0(View v) {
        atualizarNumeroDigitado("0");
        atualizarDisplay("0");
    }

    public void onClickAdicao(View v) {
        novaOperacao("+");
    }

    public void onClickSubtracao(View v) {
        novaOperacao("-");
    }

    public void onClickMultiplicacao(View v) {
        novaOperacao("*");
    }

    public void onClickDivisao(View v) {
        novaOperacao("/");
    }

    public void onClickIgual(View v) {
        if (!numeroAtual.equals("")) {
            numeros.add(Float.parseFloat(numeroAtual));
        }
        if (numeros.size() > operacoes.size()) {
            while (!operacoes.isEmpty()) {
                for (int i = 0; i < operacoes.size(); i++) {
                    if (operacoes.get(i).equals("/")) {
                        if (numeros.get(i + 1) == 0) {
                            Toast.makeText(MainActivity.this, "Divisão por 0 detectada, operação cancelada", Toast.LENGTH_LONG).show();
                            resetAll();
                        } else {
                            float resultado = divisao(i);
                            i = 0;
                            continue;
                        }
                    }
                    if (operacoes.get(i).equals("*")) {
                        float resultado = multiplicacao(i);
                        i = 0;
                        continue;
                    }
                }
                if (!operacoes.isEmpty()) {
                    if (operacoes.get(0).equals("+")) {
                        float resultado = adicao(0);
                        continue;
                    } else if (operacoes.get(0).equals("-")) {
                        float resultado = subtracao(0);
                        continue;
                    }
                }
            }
            if (numeros.get(0) % 1 == 0) {
                display.setText(String.valueOf(Math.round(numeros.get(0))));
            } else {
                display.setText(String.valueOf(numeros.get(0)));
            }
            numeroAtual = String.valueOf(numeros.get(0));
            numeros.clear();
        } else {
            Toast.makeText(MainActivity.this, "Você precisa definir mais um número para executar a operação", Toast.LENGTH_LONG).show();
        }
    }

    public void onClickReset(View v) {
        resetAll();
    }

    private void resetAll() {
        numeros.clear();
        operacoes.clear();
        display.setText("");
        numeroAtual = "";
    }

    private void atualizarDisplay(String texto) {
        String textoDisplay = display.getText().toString();
        textoDisplay = textoDisplay + texto;
        display.setText(textoDisplay);
    }

    private void atualizarNumeroDigitado(String numero) {
        numeroAtual = numeroAtual + numero;
    }

    private void novaOperacao(String operacao) {
        if (numeroAtual.equals("")) {
            Toast.makeText(MainActivity.this, "Selecione um novo número para realizar operações", Toast.LENGTH_LONG).show();
        } else {
            numeros.add(Float.parseFloat(numeroAtual));
            numeroAtual = "";
            operacoes.add(operacao);
            atualizarDisplay(operacao);
        }
    }

    private float adicao(int posicao) {
        float resultado = numeros.get(posicao) + numeros.get(posicao + 1);
        numeros.set(posicao, resultado);
        numeros.remove(posicao + 1);
        operacoes.remove(posicao);
        return resultado;
    }

    private float subtracao(int posicao) {
        float resultado = numeros.get(posicao) - numeros.get(posicao + 1);
        numeros.set(posicao, resultado);
        numeros.remove(posicao + 1);
        operacoes.remove(posicao);
        return resultado;
    }

    private float multiplicacao(int posicao) {
        float resultado = numeros.get(posicao) * numeros.get(posicao + 1);
        numeros.set(posicao, resultado);
        numeros.remove(posicao + 1);
        operacoes.remove(posicao);
        return resultado;
    }

    private float divisao(int posicao) {
        float resultado = numeros.get(posicao) / numeros.get(posicao + 1);
        numeros.set(posicao, resultado);
        numeros.remove(posicao + 1);
        operacoes.remove(posicao);
        return resultado;
    }
}