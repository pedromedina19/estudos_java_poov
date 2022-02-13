package rh;

import pessoa.Pessoa;
import java.util.ArrayList;

public class RH {

    public static double calcularIdadeMedia(ArrayList<Pessoa> listaPessoas) {
        double somarIdades = 0;
        double media = 0;
        int i = 0;
        for (i = 0; i < listaPessoas.size(); i++) {
            somarIdades = somarIdades + listaPessoas.get(i).getIdade();
        }
        media = somarIdades / i;
        return (media);
    }
}
