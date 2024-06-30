package registro.unidade.br.senai.sp.q_learning;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

//Aprendizagem por reforço
public class Labirinto extends JFrame{

    private static final int LARGURA = 800;
    private static final int ALTURA = 1000;
    private static final int LINHAS = 20;
    private static final int COLUNAS = 20;
    private static final int CELULA_TAMANHO = LARGURA / COLUNAS;

    private static final int[][] LABIRINTO = {

            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 0, 1, 0, 1, 0, 0, 1},
            {1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1},
            {1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1},
            {1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1},
            {1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1},
            {1, 1, 0, 1, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1},
            {1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 1},
            {1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1},
            {1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}

    };

        //Inicio = (1, 1) linha x coluna
        private static final int INICIO_LINHA = 1;
        private static final int INICIO_COLUNA = 1;

        //Fim = (18, 18) linha x coluna
        private static final int FIM_LINHA = 18;
        private static final int FIM_COLUNA = 18;

        //Parâmetros do Q-Learning
        private static final double ALPHA = 0.1;
        private static final double GAMMA = 0.9;
        private static final double EPSILON = 0.1;
        private static final int EPISODIOS = 1000;
        private double[][] Q;
        private int[] caminho;
        private int caminhoIndice;

        public Labirinto(){

            setTitle("Q-Learning - Labirinto");
            setSize(LARGURA, ALTURA);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            Q = new double[LINHAS * COLUNAS][4];
            caminho = new int[LINHAS * COLUNAS];
            treino();
            encontrarCaminho();

        }

    private void treino() {

        Random aleatorio = new Random();

        for(int episodio = 0; episodio < EPISODIOS; episodio++){

            int estado = INICIO_COLUNA * COLUNAS + INICIO_COLUNA;

            while (true){

                int acao;

                if(aleatorio.nextDouble() < EPSILON){

                    acao = aleatorio.nextInt(4);

                }else {
                    acao = maxAcoes(estado);
                }

                int novoEstado = obterNovoEstado(estado, acao);
                int recompensa = obterRecompensa(novoEstado);
                double maxQ = Q[novoEstado][maxAcoes(novoEstado)];
                Q[estado][acao] += ALPHA * (recompensa + GAMMA * maxQ - Q[estado][acao]);
                estado = novoEstado;

                if(estado == FIM_LINHA * COLUNAS + FIM_COLUNA){

                    break;

                }

            }


        }

    }

    private void encontrarCaminho(){

            int estado = INICIO_LINHA * COLUNAS + INICIO_COLUNA;
            caminhoIndice = 0;

            while (estado != FIM_LINHA * COLUNAS + FIM_COLUNA){

                int acao = maxAcoes(estado);
                caminho[caminhoIndice++] = estado;
                estado = obterNovoEstado(estado, acao);

            }

            caminho[caminhoIndice] = FIM_LINHA * COLUNAS + FIM_COLUNA;

    }

    private int obterNovoEstado(int estado, int acao){

            int linha = estado / COLUNAS;
            int coluna = estado % COLUNAS;

            switch (acao){


                case 1:
                    coluna++;//vai para direita
                    break;

                case 2:
                    linha++;//vai para baixo
                    break;

                case 3:
                    coluna--;//vai para esquerda
                    break;

                case 0:
                    linha--;//vai para cima
                    break;


            }

            if(LABIRINTO[linha][coluna] == 1){

                return estado;

            }

            return linha * COLUNAS + coluna;


    }

    private int obterRecompensa(int estado){

            if(estado == FIM_LINHA * COLUNAS + FIM_COLUNA){

                return  100;

            }

            return -1;

    }

    private int maxAcoes(int estado){

            int melhorAcao = 0;
            double maxQ = Q[estado][0];

            for(int i = 1; i < 4; i++){

                if(Q[estado][i] > maxQ){

                    maxQ = Q[estado][i];
                    melhorAcao = i;

                }

            }

            return melhorAcao;

    }

    @Override
    public void paint(Graphics graphics){

            super.paint(graphics);

            for(int linha = 0; linha < LINHAS; linha++){

                for(int coluna = 0; coluna < COLUNAS; coluna++){

                    if(LABIRINTO[linha][coluna] == 1){

                        //Parede
                        graphics.setColor(Color.WHITE);

                    }else {

                        //Caminho falso
                        graphics.setColor(Color.GRAY);

                    }

                    graphics.fillRect(coluna * CELULA_TAMANHO, linha * CELULA_TAMANHO,
                            CELULA_TAMANHO, CELULA_TAMANHO);

                }

            }

            //Caminho correto
            graphics.setColor(Color.blue);

            for(int i = 0; i <= caminhoIndice; i++){

                int linha = caminho[i] / COLUNAS;
                int coluna = caminho[i] % COLUNAS;
                graphics.fillRect(coluna * CELULA_TAMANHO, linha * CELULA_TAMANHO,
                        CELULA_TAMANHO, CELULA_TAMANHO);

            }


    }


}
