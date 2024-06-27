package forma_geometrica;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        Area area = new Area();
        double areaFormaGeometrica = 0;
        String escolha = "";

        String[] formasGeometricas = {"triangulo", "quadrado", "retangulo", "circulo", "pentagono"};

        while (!escolha.equalsIgnoreCase("n")) {

            String formaGeometrica = (String) JOptionPane.showInputDialog(null,
                    "Selecione a forma geométrica desejada: ", "Seleção de forma geométrica",
                    JOptionPane.PLAIN_MESSAGE, null, formasGeometricas, formasGeometricas[0]);

            int pos = 0;

            for (int i = 0; i < formasGeometricas.length; i++) {

                if (formasGeometricas[i].equalsIgnoreCase(formaGeometrica)) {

                    pos = i;

                }

            }

            List<String> formasGeometricasList = Arrays.stream(formasGeometricas).toList();

            double b;
            double h;
            double l;

            if (formaGeometrica != null && formaGeometrica.equalsIgnoreCase(formasGeometricasList.get(pos))) {

                switch (formaGeometrica) {

                    case "triangulo":

                        b = Double.parseDouble(JOptionPane.showInputDialog("Insira a base do "
                                + formaGeometrica));
                        h = Double.parseDouble(JOptionPane.showInputDialog("Agora insira a altura do "
                                + formaGeometrica));
                        areaFormaGeometrica = area.triangulo(b, h);

                        break;

                    case "quadrado":

                        l = Double.parseDouble(JOptionPane.showInputDialog("Insira o lado do "
                                + formaGeometrica));
                        areaFormaGeometrica = area.quadrado(l);

                        break;

                    case "retangulo":

                        b = Double.parseDouble(JOptionPane.showInputDialog("Insira a base do "
                                + formaGeometrica));
                        h = Double.parseDouble(JOptionPane.showInputDialog("Agora insira a altura do "
                                + formaGeometrica));
                        areaFormaGeometrica = area.retangulo(b, h);

                        break;

                    case "circulo":

                        double r = Double.parseDouble(JOptionPane.showInputDialog("Insira o raio do " +
                                formaGeometrica));

                        areaFormaGeometrica = area.circulo(r);

                        break;

                    case "pentagono":

                        l = Double.parseDouble(JOptionPane.showInputDialog("Insira o lado do " + formaGeometrica));
                        double a = Double.parseDouble(JOptionPane.showInputDialog("Agora insira a apótema do "
                                + formaGeometrica));

                        areaFormaGeometrica = area.pentagono(l, a);

                        break;


                    default:
                        JOptionPane.showMessageDialog(null, "Opção inválida.");
                        break;

                }

                String mensagem = String.format("Área do %s é: %.2f", formaGeometrica, areaFormaGeometrica);
                JOptionPane.showMessageDialog(null, mensagem);


            } else {
                JOptionPane.showMessageDialog(null, "Forma geométrica informada não encontrada." +
                        " Insira novamente.");
            }

            escolha = JOptionPane.showInputDialog("Deseja calcular área de outra forma? (s/n)");

        }

    }


}
