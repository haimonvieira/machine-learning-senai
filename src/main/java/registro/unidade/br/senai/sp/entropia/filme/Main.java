package registro.unidade.br.senai.sp.entropia.filme;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        //Base de dados sobre audiência nos filmes
        List<Filme> filmes = new ArrayList<>();
        filmes.add(new Filme("Ação", "Sim", "Boa"));
        filmes.add(new Filme("Ação", "Não", "Ruim"));
        filmes.add(new Filme("Comédia", "Sim", "Boa"));
        filmes.add(new Filme("Comédia", "Sim", "Ruim"));
        filmes.add(new Filme("Comédia", "Não", "Boa"));

        //Calcular entropia do conjunto inicial
        double entropiaInicial = Filme.calcularEntropia(filmes);
        System.out.println("Entropia inicial: " + entropiaInicial);

        //Entropia por atributo
        String atributoDivisao = "Gênero";
        Map<String, List<Filme>> subdivisoes = Filme.dividirPorAtributo(filmes, atributoDivisao);

        for(Map.Entry<String, List<Filme>> entry : subdivisoes.entrySet()){

            String valorAtributo = entry.getKey();
            List<Filme> sub = entry.getValue();
            double entropiaSub = Filme.calcularEntropia(sub);
            System.out.println("Entropia após dividir por " + atributoDivisao +
                    " = " + valorAtributo + ": " + entropiaSub);

        }

    }

}
