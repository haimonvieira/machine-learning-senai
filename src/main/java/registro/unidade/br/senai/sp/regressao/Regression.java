package regressao;

//Este import foi usado um .jar neste link: https://search.maven.org

import org.apache.commons.math3.stat.regression.SimpleRegression;
import javax.swing.*;

public class Regression {

    public static void main(String[] args) {

        //Informar dados em uma lista
        double[] weight = {110, 85, 80, 94, 79, 40, 80, 120};
        double[] height = {1.9, 1.75, 1.4, 1.6, 1.57, 1.66, 1.75, 1.9};

        SimpleRegression regression = new SimpleRegression();

        for (int i = 0; i < weight.length; i++) {

            regression.addData(height[i], weight[i]);

        }

        //Obter parametros
        String interception;

        if(regression.getIntercept() < 0){

            interception = String.format("- %.3f", Math.abs(regression.getIntercept()));

        }else {

            interception = String.format("+ %.3f", regression.getIntercept());

        }

            String inclination = String.format("%.3fx ", regression.getSlope());
            String rSquare = String.format("%.4f", regression.getRSquare());


        JOptionPane.showMessageDialog(null,
                "A equação é " + inclination +
                        interception + "\nO Rˆ2 é " + rSquare, "Regressão",
                JOptionPane.INFORMATION_MESSAGE);


    }

}
