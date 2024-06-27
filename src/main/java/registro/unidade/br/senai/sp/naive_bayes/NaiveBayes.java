package registro.unidade.br.senai.sp.naive_bayes;

import java.util.HashMap;//"chave" : "valor"
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Probabilidade

/*
Irá treinar a base de dados para encontrar o padrão de SPAM ou HAM (nao spam)
 */
public class NaiveBayes {

    //Definir modelo de classificação que utiliza como
    //recurso a inferência bayesiana. O algoritmo gera uma tabela
    //de probabilidades para a classificação.

    //Contar palavras para emails de spam
    private Map<String, Integer> spamCountWords;
    //Contar palavras para emails de ham (nao spam)
    private Map<String, Integer> hamCountWord;
    //Dicionário
    private Set<String> dictionary;
    //Total de emails de spam
    private int qtySpam;
    //Total de emails de ham
    private int qtyHam;

    public NaiveBayes (){

        spamCountWords = new HashMap<>();
        hamCountWord = new HashMap<>();
        dictionary = new HashSet<>();
        qtySpam = 0;
        qtyHam = 0;

    }

    /*
    O método abaixo recebe dados para o treino, na forma de uma matriz,
    de Strings, onde cada entrada consiste em um rótulo (spam ou ham) e
    o texto de email
    */
    public void training(String[][] dataTraining){

        for(String[] email : dataTraining){

            //Rótulo
            String label = email[0];
            //Dividindo o texto em palavras
            String[] words = email[1].split("\\s+");

            //Atualizar contagem de palavras
            if(label.equals("spam")){

                qtySpam++;

                for(String word : words){

                    spamCountWords.put(word, spamCountWords.getOrDefault(word,
                            0) + 1);
                    dictionary.add(word);

                }

            }else if(label.contains("ham")){

                qtyHam++;

                for(String word : words){

                    hamCountWord.put(word, hamCountWord.getOrDefault(word,
                            0) + 1);
                    dictionary.add(word);

                }

            }

        }

    }

    //Criar o método para classificar o e-mail, utilizando a
    //probabilidade (suavização de Laplace). Será considerado
    //'spam', caso a pontuação obtida no processo seja maior ou
    // igual a pontuação de 'ham', caso contrário, será 'ham'
    public String classify(String email){

        String [] words = email.split("\\s+");
        double spamPoints = Math.log((double) qtySpam / (qtySpam + qtyHam));
        double hamPoints = Math.log((double) qtyHam / (qtyHam + qtySpam));

        for(String word : words){
            //Calcular a probabilidade condicional usando a
            //suavização de Laplace
            if(dictionary.contains(word)){

                double probSpam = (double) (spamCountWords.getOrDefault(word,
                        0) + 1) / (qtySpam + dictionary.size());

                double probHam = (double) (hamCountWord.getOrDefault(word,
                        0) + 1) / (qtyHam + dictionary.size());

                spamPoints += Math.log(probSpam);
                hamPoints += Math.log(probHam);

            }

        }

        //Definir se é spam ou ham
        return spamPoints >= hamPoints ? "spam" : "ham";

    }


}
