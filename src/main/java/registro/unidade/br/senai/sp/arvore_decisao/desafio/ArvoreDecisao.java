package registro.unidade.br.senai.sp.arvore_decisao.desafio;

import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import javax.swing.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
Árvore de decisão:

— Raiz
— Nó
— Folhas
 */
public class ArvoreDecisao {

    public static No construirArvore(String[][] dados) {

        //Pergunta chave - a raiz
        No raiz = new No("Está chovendo?\n(Sim) .......... (Não)");
        No no1 = new No("Está ventando?\n(Sim) .......... (Não)");
        No no2 = new No("O vento está forte?\n(Sim) .......... (Não)");

        //dados[0][3] esta pegando a resposta na folha da matriz
        No folha1 = new No("Decisão", dados[0][3]);
        No folha2 = new No("Decisão", dados[1][3]);
        No folha3 = new No("Decisão", dados[2][3]);
        No folha4 = new No("Decisão", dados[3][3]);

        raiz.definirFilhos(no1, folha1);
        no1.definirFilhos(no2, folha2);
        no2.definirFilhos(folha3, folha4);

        return raiz;

    }

    private static void construirGrafico(No no, Graph<String, DefaultEdge> grafico,
                                         Map<No, String> nos) {

        if (!nos.containsKey(no)) {

            String rotulo = no.pergunta;

            if (no.decisao != null) {
                rotulo += ": " + no.decisao;
            }

            grafico.addVertex(rotulo);
            nos.put(no, rotulo);

        }

        if (no.verdadeiro != null) {

            construirGrafico(no.verdadeiro, grafico, nos);
            grafico.addEdge(nos.get(no), nos.get(no.verdadeiro));

        }

        if (no.falso != null) {

            construirGrafico(no.falso, grafico, nos);
            grafico.addEdge(nos.get(no), nos.get(no.falso));

        }

    }

    public static void desenharArvore(No raiz) {

        Graph<String, DefaultEdge> grafico =
                new DefaultDirectedGraph<>(DefaultEdge.class);
        Map<No, String> nos = new HashMap<>();
        construirGrafico(raiz, grafico, nos);

        mxGraph mxGraph = new mxGraph();
        Object parent = mxGraph.getDefaultParent();
        mxGraph.getModel().beginUpdate();

        try {
            Map<String, Object> graficoNos = new HashMap<>();
            for (String vertex : grafico.vertexSet()) {

                graficoNos.put(vertex, mxGraph.insertVertex(parent, null, vertex,
                        20, 20, 200, 50));

            }

            for (DefaultEdge edge : grafico.edgeSet()) {

                String fonte = grafico.getEdgeSource(edge);
                String alvo = grafico.getEdgeTarget(edge);
                mxGraph.insertEdge(parent, null, "", graficoNos.get(fonte),
                        graficoNos.get(alvo));

            }

        } finally {
            mxGraph.getModel().endUpdate();
        }

        mxCompactTreeLayout layout = new mxCompactTreeLayout(mxGraph, false);
        layout.execute(mxGraph.getDefaultParent());

        JFrame frame = new JFrame("Árvore de decisão");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new mxGraphComponent(mxGraph));
        frame.setSize(1000, 600);
        frame.setVisible(true);

//        exibirArvore();

    }

    private static void mostrarDecisaoUsuario(No raiz){

        No noAtual = raiz;

        while (noAtual.verdadeiro != null && noAtual.falso != null){

            int resposta = JOptionPane.showOptionDialog(null,
                    noAtual.pergunta.replace("\n(Sim) .......... (Não)",
                            ""),
                    "Árvore de decisão", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null,
                    new String[] {"sim", "não"}, null);

            if (resposta == JOptionPane.YES_NO_OPTION){

                noAtual = noAtual.verdadeiro;

            }else {
                noAtual = noAtual.falso;
            }

            JOptionPane.showMessageDialog(null, "Decisão: " +
                    noAtual.decisao, "Resultado da Decisão",
                    JOptionPane.INFORMATION_MESSAGE);

        }

    }

    //Exibir arvore basica

    //Insercao de dados do usuario na arvore
    public static String[][] getDados(){

        String[][] dados = new String[4][4];
        String folha1 = "Não levar guarda-chuva";
        String folha2 = "Levar guarda-chuva";
        String folha3 = "Vá de carro";
        String folha4 = "Levar capa";

        String raiz = JOptionPane.showInputDialog("Está chovendo?\n(Sim) .......... (Não)");
        //Experssão regular para não aceitar caracteres especiais
        //Lembrar de preencher os espacos vazios
        raiz = raiz.replaceAll("[^\\p{ASCII}]", "");

        if (raiz.equalsIgnoreCase("nao")) {
            JOptionPane.showMessageDialog(null, "Não levar guarda-chuva.");
        } else {

            String no1 = JOptionPane.showInputDialog("Está ventando?\n(Sim) .......... (Não)");
            no1 = no1.replaceAll("[^\\p{ASCII}]", "");

            if (no1.equalsIgnoreCase("nao")) {

                JOptionPane.showMessageDialog(null, "Levar guarda-chuva.");

            } else {
                String no2 = JOptionPane.showInputDialog("O vento está forte?\n(Sim) .......... (Não)");
                no2 = no2.replaceAll("[^\\p{ASCII}]", "");

                if (no2.equalsIgnoreCase("nao")) {
                    JOptionPane.showMessageDialog(null, "Levar capa.");
                } else {
                    JOptionPane.showMessageDialog(null, "Vá de carro.");
                }

            }

        }

        return dados;

    }


}
