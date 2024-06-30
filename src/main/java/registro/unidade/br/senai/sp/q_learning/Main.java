package registro.unidade.br.senai.sp.q_learning;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(()->{

            Labirinto labirinto = new Labirinto();
            labirinto.setVisible(true);

        });

    }

}
