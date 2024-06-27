package registro.unidade.br.senai.sp.regressao_csv;

import registro.unidade.br.senai.sp.regressao_csv.regression.Regression;
import registro.unidade.br.senai.sp.regressao_csv.file.ReadFile;
import registro.unidade.br.senai.sp.regressao_csv.chart.Chart;

import java.util.List;

public class Main {

    public static void main(String[] args){

        //Diferenca entre instanciar um objeto e chamar o metodo estatico da classe
        //Classe: Regressao
        //
        //Métodos da classe:
        //
        //-static: linear -> Regressao.linear() //Aqui foi chamada a classe Regressao
        //-exponencial -> Regressao regressao = new Regressao() -> regressao.exponencial
        //-polinomial -> Regressao regressao = new Regressao() -> regressao.polinomial


        ReadFile readFile = new ReadFile();
        readFile.setPath("C:\\Users\\ALUNO\\Desktop\\dados_peso_pessoa.csv");

        List<double[]> data = readFile.readCSV();

        Regression regression = new Regression();
        regression.setData(data);

        if(!data.isEmpty()){

            double[] coefficients = regression.getCoefficients();
            System.out.println("Coeficientes da regressão linear: ");
            System.out.println("Interceptacao: " + coefficients[0]);
            System.out.println("Inclinação: " + coefficients[1]);
            System.out.println("R^2: " + coefficients[2]);

            Chart chart = new Chart();
            chart.setDataAndCoefficients(data, coefficients);
            chart.make();

        }else {
            System.out.println("Falha na leitura do arquivo CSV");
        }

    }



    //Ler o arquivo
//    private static List<double[]> readCSVFile(String path)throws FileNotFoundException{
//
//        List<double[]> data = new ArrayList<>();
//
//        try (CSVReader csvReader = new CSVReaderBuilder(new FileReader(path))
//                .withCSVParser(new CSVParserBuilder().withSeparator(';')
//                        .build())
//                .build()) {
//
//            String[] nextLine;
//            boolean firstLine = true;
//
//            while ((nextLine = csvReader.readNext()) != null){
//
//                //Pular primeira linha (rotulo)
//                if(firstLine){
//                    firstLine = false;
//                    continue;
//                }
//
//                double x = Double.parseDouble(nextLine[0].trim());
//                double y = Double.parseDouble(nextLine[1].trim());
//                data.add(new double[]{x, y});
//
//            }
//
//
//        }catch (IOException | CsvValidationException | NumberFormatException e) {
//            throw new RuntimeException(e);
//        }
//
//        return data;
//
//    }

    //Metodo para construir uma equacao por regressao linear
//    private static double[] linearRegression(List<double[]> data) {
//
//        int n = data.size();
//        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;
//
//        for(double[] point : data){
//
//            double x = point[0];
//            double y = point[1];
//
//            sumX += x;
//            sumY += y;
//            sumXY += x * y;
//            sumX2 += x * x;
//
//        }
//
//        double inclination = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
//        double interception = (sumY - inclination * sumX) / n;
//
//        //Calcular R^2
//         double averageY = sumY / n;
//         double squareSumTotal = 0;
//         double squareSumResidue = 0;//soma quadrado residuo
//
//         for(double[] point : data){
//
//             double x = point[0];
//             double y = point[1];
//             double predictY = interception + inclination * x;
//             squareSumTotal += Math.pow(y - averageY, 2);
//             squareSumResidue += Math.pow(y - predictY, 2);
//
//         }
//
//         double rSquare = 1 - (squareSumResidue / squareSumTotal);
//
//        return new double[]{interception, inclination, rSquare};
//    }

    //Criar gráfico de dispersão com a reta obtida pelo método de regressão
    //Coefficients = interception and inclination
//    private static void makeGraphic(List<double[]> data, double[] coefficients){
//
//        XYSeries xySeries = new XYSeries("Data");
//
//        for(double[] point : data){
//
//            //Adicionar valores da primeira e segunda coluna
//            xySeries.add(point[0], point[1]);
//
//        }
//
//        XYSeries linearRegression = new XYSeries("Regression Line");
//
//        double minX = data.stream()
//                .mapToDouble(p -> p[0])
//                .min()
//                .orElse(0);
//
//        double maxX = data.stream()
//                .mapToDouble(p -> p[1])
//                .max()
//                .orElse(0);
//
//        linearRegression.add(minX, coefficients[0] + coefficients[1] * minX);
//        linearRegression.add(maxX, coefficients[0] + coefficients[1] * maxX);
//
//        //Criar a estrutura do gráfico: eixos e o conteúdo (pontos de dispersão)
//        XYSeriesCollection dataSet = new XYSeriesCollection();
//
//        dataSet.addSeries(xySeries);//Pontos da planilha
//        dataSet.addSeries(linearRegression);//Reta obtida na regressão linear
//
//        //Compor gráfico usando JFreeChart
//        //Linear Regression; Age; Weight
//        JFreeChart chart = ChartFactory.createScatterPlot("Linear Regression", "Age",
//                "Weight", dataSet, PlotOrientation.VERTICAL, true, true, false);
//
//        XYPlot plot = chart.getXYPlot();
//
//        //Chamando renderizador do grafico
//        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
//        renderer.setSeriesLinesVisible(1, true);
//        renderer.setSeriesShapesVisible(1, true);
//        plot.setRenderer(renderer);
//
//        //Mostrando os dados graficamente com JFrame
//        SwingUtilities.invokeLater(() -> {
//
//            JFrame frame = new JFrame("Gráfico de Regressão Linear");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.add(new ChartPanel(chart));
//            frame.pack();
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//
//
//        });
//
//
//
//    }

}

