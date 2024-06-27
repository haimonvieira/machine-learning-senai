package registro.unidade.br.senai.sp.knn;

//Fazer euclidiana para encontrar a distancia mais proxima
public class Distancia {

    public static double encontrarDistancia(double[] a, double[] b){

        double soma = 0;

        for (int i = 0; i < a.length; i++) {

            soma += Math.pow(a[i] - b[i], 2);

        }

        return Math.sqrt(soma);

    }

}
