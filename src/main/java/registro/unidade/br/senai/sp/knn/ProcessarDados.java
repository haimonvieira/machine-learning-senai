package registro.unidade.br.senai.sp.knn;

import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class ProcessarDados {

    public static List<Iris> carregarDados(String url)throws Exception{

        List<Iris> baseDados = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(new URL(url).openStream()));

        String linha;

        //Ler os dados da URL passada
        while ((linha = reader.readLine()) != null){

            if(!linha.trim().isEmpty()){

                //Quebrando o texto ate a virgula
                String[] partes = linha.split(",");
                //Criando vetor de 5 espacos
                double[] caracteristicas = new double[4];

                //Inserindo no vetor 'caracteristicas' as caracteristicas
                //da Iris
                for (int i = 0; i < 4; i++) {

                    caracteristicas[i] = Double.parseDouble(partes[i]);

                }

                //Pegando qual a espécie da Iris, que fica na posição 4
                String rotulo = partes[4];
                baseDados.add(new Iris(caracteristicas, rotulo));

            }

        }

        reader.close();

        return baseDados;

    }

}
