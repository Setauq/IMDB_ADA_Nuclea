import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Filme {
    private String titulo;

    private  String genero;
    private String descricao;
    private int ano;
    private List<Diretor> diretores;
    private List<Ator> atores;




    public Filme(String titulo, String genero, String descricao, int ano) {
        this.titulo = titulo;
        this.genero = genero;
        this.descricao = descricao;
        this.ano = ano;
        this.diretores = new ArrayList<>();
        this.atores = new ArrayList<>();

    }


    public void adicionarAtor(Ator ator) {

        atores.add(ator);
    }
    public void adicionarDiretor(Diretor diretor) {

        diretores.add(diretor);
    }


    public String getTitulo() {

        return titulo;
    }

    public int getAno() {

        return ano;
    }

    public String getGenero() {
        return genero;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Ator> getAtores() {

        return atores;
    }

    public List<Diretor> getDiretores() {

        return diretores;
    }
}
