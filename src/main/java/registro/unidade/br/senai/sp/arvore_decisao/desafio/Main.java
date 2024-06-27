package registro.unidade.br.senai.sp.arvore_decisao;

public class Main {

    public static void main(String[] args) {

        //Definir os dados da árvore
        String[][] dados = {
                     //o segundo nao desta linha apenas para manter a simetria,
                //pois no nosso modelo nao tem
                {"Não", "Não", "Não levar guarda-chuva"},//folha 1
                {"Sim", "Sim", "Levar capa de chuva"},//folha 2
                {"Sim", "Não", "Levar guarda-chuva"}//folha 3

        };

        //Criar a árvore de decisão
        No raiz = ArvoreDecisao.construirArvore(dados);

        //Desenhar árvore e exibir no frame
        ArvoreDecisao.desenharArvore(raiz);

    }

}
