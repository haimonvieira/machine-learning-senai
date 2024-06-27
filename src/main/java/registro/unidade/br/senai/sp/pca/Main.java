package registro.unidade.br.senai.sp.pca;

import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        //Analisar os dados provenientes de analises de 10 amostras de solo, considerando a porcentagem de
        //argila, silte e areia

        double[][] data ={

                //argila, silte, areia
                {50, 30, 20},//amostra 1
                {49, 30.5, 20.5},//amostra 2
                {49, 30, 21},//amostra 3
                {51, 29, 20},//amostra 4
                {49.5, 30, 20.5},//amostra 5
                {60, 20, 20},//amostra 6
                {60, 19.5, 20.5},//amostra 7
                {55, 14.5, 30.5},//amostra 8
                {10, 19.5, 70.5},//amostra 9
                {19.5, 60, 30.5}//amostra 10

        };

        //Criar matriz de dados das amotras do solo
        RealMatrix matrix = MatrixUtils.createRealMatrix(data);

        //Calcular a matriz de covariância
        RealMatrix covarianceMatrix = new Covariance(matrix).getCovarianceMatrix();

        //Calcular os autovalores e os autovetores
        EigenDecomposition eigenDecomposition = new EigenDecomposition(covarianceMatrix);

        //Obter os autovetores - componentes principaos (CP1 e CP2)
        RealMatrix eigenVectors = eigenDecomposition.getV();

        //Selecionar os dois primeiros componentes principais
        RealMatrix pcaComponents = eigenVectors.getSubMatrix(0, 1, 0, matrix.getColumnDimension() - 1);

        //Projetar os dados nos componentes principais
        RealMatrix projectData = matrix.multiply(pcaComponents.transpose());

        //Dados da plotagem
        double[][] plotData = projectData.getData();
        
        //Determinar as séries de dados para o gráfico
        XYSeries series = new XYSeries("PCA");

        for (int i = 0; i < plotData.length; i++){

            series.add(plotData[i][0], plotData[i][1]);

        }

        XYSeriesCollection dataSet = new XYSeriesCollection(series);

        //Criar gráfico de dispersao
        JFreeChart chart = ChartFactory.createScatterPlot("PCA", "Componente 1", "Componente 2",
                dataSet, PlotOrientation.VERTICAL, true, true, false);

        ChartPanel panel = new ChartPanel(chart);
        panel.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame("PCA - Amostras de solo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);

    }

}
