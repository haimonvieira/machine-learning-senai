package registro.unidade.br.senai.sp.regressao_csv.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.util.List;

public class Chart {

    private List<double[]> data;
    private double[] coefficients;

    public void setDataAndCoefficients(List<double[]> data, double[] coefficients){
        this.coefficients = coefficients;
        this.data = data;
    }

    public void make(){

        XYSeries xySeries = new XYSeries("Data");

        for(double[] point : data){

            //Adicionar valores da primeira e segunda coluna
            xySeries.add(point[0], point[1]);

        }

        XYSeries linearRegression = new XYSeries("Regression Line");

        double minX = data.stream()
                .mapToDouble(p -> p[0])
                .min()
                .orElse(0);

        double maxX = data.stream()
                .mapToDouble(p -> p[1])
                .max()
                .orElse(0);

        linearRegression.add(minX, coefficients[0] + coefficients[1] * minX);
        linearRegression.add(maxX, coefficients[0] + coefficients[1] * maxX);

        //Criar a estrutura do gráfico: eixos e o conteúdo (pontos de dispersão)
        XYSeriesCollection dataSet = new XYSeriesCollection();

        dataSet.addSeries(xySeries);//Pontos da planilha
        dataSet.addSeries(linearRegression);//Reta obtida na regressão linear

        //Compor gráfico usando JFreeChart
        //Linear Regression; Age; Weight
        JFreeChart chart = ChartFactory.createScatterPlot("Linear Regression",
                "Age",
                "Weight", dataSet,
                PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = chart.getXYPlot();

        //Chamando renderizador do grafico
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(1, true);
        renderer.setSeriesShapesVisible(1, true);
        plot.setRenderer(renderer);

        //Mostrando os dados graficamente com JFrame
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Gráfico de Regressão Linear");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new ChartPanel(chart));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);


        });



    }

}
