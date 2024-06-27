package registro.unidade.br.senai.sp.knn;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
//Ordenar lista e colocar em ordem de prioridade
import java.util.PriorityQueue;

//k nearest neighbors
public class KNN {

    private List<Iris> baseDados;
    private int k;//Numero de vizinhos

    public KNN (List<Iris> baseDados, int k){
        this.baseDados = baseDados;
        this.k = k;
    }

    public String classificar(Iris novaIris){

        //Armazenar os elementos de maneira ordenada
        PriorityQueue<IrisDistancia> ordem =
                new PriorityQueue<>(Comparator
                        .comparingDouble(IrisDistancia::pegarDistancia));


        for(Iris iris : baseDados){

            double distancia = Distancia
                    .encontrarDistancia(novaIris.caracteristicas,
                            iris.caracteristicas);

            ordem.add(new IrisDistancia(iris, distancia));

        }

        List<Iris> kNearstNeighbors = new ArrayList<>();

        for (int i = 0; i < k; i++) {

            //Adicionando os dados e ordenando eles pela menor distancia
            kNearstNeighbors.add(ordem.poll().iris);

        }

        //Retorna o dado
        return votoMajoritario(kNearstNeighbors);

    }

    private String votoMajoritario(List<Iris> kNearstNeighbors) {

        int[] contagem = new int[3];

        for(Iris neighbor : kNearstNeighbors){

            switch (neighbor.rotulo) {

                case "Iris-setosa":
                    contagem[0]++;
                    break;

                case "Iris-versicolor":
                    contagem[1]++;
                    break;

                case "Iris-virginica":
                    contagem[2]++;
                    break;

            }

        }
        if(contagem[0] > contagem[1] && contagem[0] > contagem[2]){

            return "Iris-setosa";

        }else if(contagem[1] > contagem[0] && contagem[1] > contagem[2]){

            return "Iris-versicolor";

        }else {

            return "Iris-virginica";

        }

    }

    private static class IrisDistancia{
        Iris iris;
        double distancia;

        IrisDistancia (Iris iris, double distancia){
            this.iris = iris;
            this.distancia = distancia;
        }

        double pegarDistancia(){
            return distancia;
        }

    }

}
