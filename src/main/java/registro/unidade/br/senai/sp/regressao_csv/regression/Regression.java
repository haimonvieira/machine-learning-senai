package registro.unidade.br.senai.sp.regressao_csv.regression;

import java.util.List;

public class Regression {

    private List<double[]> data;
    public void setData(List<double[]> data) {
        this.data = data;
    }

    public double[] getCoefficients() {

        int n = data.size();
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;

        for(double[] point : data){

            double x = point[0];
            double y = point[1];

            sumX += x;
            sumY += y;
            sumXY += x * y;
            sumX2 += x * x;

        }

        double inclination = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        double interception = (sumY - inclination * sumX) / n;

        //Calcular R^2
        double averageY = sumY / n;
        double squareSumTotal = 0;
        double squareSumResidue = 0;//soma quadrado residuo

        for(double[] point : data){

            double x = point[0];
            double y = point[1];
            double predictY = interception + inclination * x;
            squareSumTotal += Math.pow(y - averageY, 2);
            squareSumResidue += Math.pow(y - predictY, 2);

        }

        double rSquare = 1 - (squareSumResidue / squareSumTotal);

        return new double[]{interception, inclination, rSquare};
    }

}
