import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Diretor extends Pessoa {
    public Diretor(String nome, LocalDate dataDeNascimentos, String nacionalidade) {
        super(nome, dataDeNascimentos, nacionalidade);
    }

    @Override
    public String getDescricao() {
        return "Diretor";
    }


    @Override
    public String toString() {
        return getNome();
    }



}
