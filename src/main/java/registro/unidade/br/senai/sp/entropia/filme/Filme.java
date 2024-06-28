package registro.unidade.br.senai.sp.entropia.filme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filme {

    private String genero;
    private String atoresFamoso;
    private String qualidade;

    public Filme() {
    }

    public Filme(String genero, String atoresFamoso, String qualidade) {
        this.genero = genero;
        this.atoresFamoso = atoresFamoso;
        this.qualidade = qualidade;
    }

    public static double calcularEntropia(List<Filme> filmes){

        int total = filmes.size();
        Map<String, Integer> contagemClasses = new HashMap<>();

        for(Filme filme : filmes){

            String classe = filme.qualidade;//alvo
            contagemClasses.put(classe,
                    contagemClasses.getOrDefault(classe, 0) + 1);

        }

        //Calcular entropia
        double entropia = 0;

        for(Map.Entry<String, Integer> entry : contagemClasses.entrySet()){

            double probabilidade = (double) entry.getValue() / total;
            entropia -= probabilidade * Math.log(probabilidade) / Math.log(2);

        }

        return entropia;

    }

    //Definir o método para dividir os dados por atributo - ex.: gênero
    public static Map<String, List<Filme>> dividirPorAtributo(List<Filme> filmes,
                                                              String atributo){

        Map<String, List<Filme>> subdivisoes = new HashMap<>();

        // ':' signigica 'in'
        for(Filme filme : filmes){

            String valorAtributo = null;

            switch (atributo){

                case "Gênero":
                    valorAtributo = filme.genero;
                    break;

                case "Atores famosos":
                    valorAtributo = filme.atoresFamoso;
                    break;

                case "Qualidade":
                    valorAtributo = filme.qualidade;
                    break;

                default:
                    System.out.println("Atributo não encontrado.");
                    break;


            }

            if(!subdivisoes.containsKey(valorAtributo)){

                subdivisoes.put(valorAtributo, new ArrayList<>());

            }

            subdivisoes.get(valorAtributo).add(filme);

        }

        return subdivisoes;

    }

}
