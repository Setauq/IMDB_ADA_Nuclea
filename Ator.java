import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Ator extends Pessoa {


    public Ator(String nome, LocalDate dataDeNascimentos, String nacionalidade) {
        super(nome, dataDeNascimentos, nacionalidade);
    }

    @Override
    public String getDescricao() {
        return "Ator";
    }


}
