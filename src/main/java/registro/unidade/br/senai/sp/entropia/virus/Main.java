package registro.unidade.br.senai.sp.entropia.virus;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        String[] casosDengue = {
                "positivo", "positivo", "positivo", "negativo", "positivo", "positivo",
                "positivo", "positivo", "positivo", "positivo"
        };

        double entropia = calcularEntropia(casosDengue);

        //A saida mostrara se ha uma incerteza sobre os casos de dengue
        // quanto mais proximo de 1, mais incerto
        System.out.println("Entropia dos casos de dengue: " + entropia);

    }

    //Definir o cálculo da entropia
    public static double calcularEntropia(String[] rotulos){

        Map<String, Integer> contagemRotulos= new HashMap<>();

        //Realizar a contagem de cada rótulo
        for(String rotulo : rotulos){

            contagemRotulos
                    .put(rotulo, contagemRotulos.getOrDefault(rotulo, 0) + 1);

        }

        //Calcular entropia
        double entropia = 0;
        int totalRotulos = rotulos.length;

        for(Map.Entry<String, Integer> entry : contagemRotulos.entrySet()){

            double probabilidade = (double) entry.getValue() / totalRotulos;
            entropia -= probabilidade * log2(probabilidade);

        }

        return entropia;

    }

    public static double log2(double valor){

       return Math.log(valor) / Math.log(2);

    }

}
