package registro.unidade.br.senai.sp.knn;

import registro.unidade.br.senai.sp.knn.tratadorDeEntrada.TratarEntrada;

import javax.swing.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        String url = "https://archive.ics.uci.edu" +
                "/ml/machine-learning-databases/iris/iris.data";

        List <Iris> dados = ProcessarDados.carregarDados(url);

        //Instanciar o KNN
        KNN knn = new KNN(dados, 3);

        //Para as caracteristicas teremos respectivamente:
        //Comprimento da sépala, largura da sépala, comprimento da petala e largura da petala

        boolean verificado = false;

        String comprimentoSepala = JOptionPane.showInputDialog
                ("Insira o comprimento da sépala da Iris");
        verificado = TratarEntrada.verificarEntrada(comprimentoSepala);
        String larguraSepala = JOptionPane.showInputDialog
                ("Insira a largura da sépala da Iris");
        verificado = TratarEntrada.verificarEntrada(larguraSepala);
        String comprimentoPetala = JOptionPane.showInputDialog
                ("Insira o comprimento da pétala da Iris");
        verificado = TratarEntrada.verificarEntrada(comprimentoPetala);
        String larguraPetala = JOptionPane.showInputDialog
                ("Insira a largura da pétala da Iris");
        verificado = TratarEntrada.verificarEntrada(larguraPetala);

        if(verificado){

            double cSepala = Double.parseDouble(comprimentoSepala);
            double lSepala = Double.parseDouble(larguraSepala);
            double cPetala = Double.parseDouble(comprimentoPetala);
            double lPetala = Double.parseDouble(larguraPetala);

            double[] caracteristicas = {cSepala, lSepala, cPetala, lPetala};
            Iris novaIris = new Iris(caracteristicas, "");
            String resultado = knn.classificar(novaIris);
            JOptionPane.showMessageDialog(null, "Espécie: " + resultado,
                    "Mensagem", JOptionPane.INFORMATION_MESSAGE, null);

        }


    }

}
