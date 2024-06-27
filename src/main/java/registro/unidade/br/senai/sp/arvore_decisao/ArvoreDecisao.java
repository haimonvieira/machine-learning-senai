package registro.unidade.br.senai.sp.arvore_decisao;

import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import java.util.HashMap;
import javax.swing.*;
import java.util.Map;
import java.util.Objects;

/*
Árvore de decisão:

— Raiz
— Nó
— Folhas
 */
public class ArvoreDecisao {

    public static No construirArvore(String[][] dados){

        //Pergunta chave - a raiz
        No raiz = new No("Está chovendo?\n(Sim) .......... (Não)");
        No no1 = new No("Está ventando?\n(Sim) .......... (Não)");

        //dados[0][2] esta pegando a resposta na folha da matriz
        No folha1 = new No("Decisão", dados[0][2]);
        No folha2 = new No("Decisão", dados[1][2]);
        No folha3 = new No("Decisão", dados[2][2]);

        raiz.definirFilhos(no1, folha1);
        no1.definirFilhos(folha2, folha3);

        return raiz;

    }

    private static void construirGrafico(No no, Graph<String, DefaultEdge> grafico,
                                         Map<No, String> nos){

        if(!nos.containsKey(no)){

            String rotulo = no.pergunta;

            if(no.decisao != null){
                rotulo += ": " + no.decisao;
            }

            grafico.addVertex(rotulo);
            nos.put(no, rotulo);

        }

        if(no.verdadeiro != null){

            construirGrafico(no.verdadeiro, grafico, nos);
            grafico.addEdge(nos.get(no), nos.get(no.verdadeiro));

        }

        if(no.falso != null){

            construirGrafico(no.falso, grafico, nos);
            grafico.addEdge(nos.get(no), nos.get(no.falso));

        }

    }

    public static void desenharArvore(No raiz){

        Graph<String, DefaultEdge> grafico =
                new DefaultDirectedGraph<>(DefaultEdge.class);
        Map<No, String> nos = new HashMap<>();
        construirGrafico(raiz, grafico, nos);

        mxGraph mxGraph = new mxGraph();
        Object parent = mxGraph.getDefaultParent();
        mxGraph.getModel().beginUpdate();

        try {
            Map<String, Object> graficoNos = new HashMap<>();
            for(String vertex : grafico.vertexSet()){

                graficoNos.put(vertex, mxGraph.insertVertex(parent, null, vertex,
                        20, 20, 200, 50));

            }

            for(DefaultEdge edge : grafico.edgeSet()){

                String fonte = grafico.getEdgeSource(edge);
                String alvo = grafico.getEdgeTarget(edge);
                mxGraph.insertEdge(parent, null, "", graficoNos.get(fonte),
                        graficoNos.get(alvo));

            }

        }finally {
            mxGraph.getModel().endUpdate();
        }

        mxCompactTreeLayout layout = new mxCompactTreeLayout(mxGraph, false);
        layout.execute(mxGraph.getDefaultParent());

        JFrame frame = new JFrame("Árvore de decisão");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new mxGraphComponent(mxGraph));
        frame.setSize(800, 600);
        frame.setVisible(true);

    }

}
