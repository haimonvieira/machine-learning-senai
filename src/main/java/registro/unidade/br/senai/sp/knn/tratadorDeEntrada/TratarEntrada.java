package registro.unidade.br.senai.sp.knn.tratadorDeEntrada;

import org.apache.commons.lang3.StringUtils;
import javax.swing.*;

//Vai verificar as entra
public class TratarEntrada {

    public static boolean verificarEntrada(String... entradas){

        for(String entrada : entradas){

            if(StringUtils.isBlank(entrada)){

                JOptionPane.showMessageDialog(null,
                        "Entrada inválida",
                        "Mensagem", JOptionPane.INFORMATION_MESSAGE, null);
                return false;
            }

        }

        return true;

    }

}
