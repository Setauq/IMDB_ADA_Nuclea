import java.time.LocalDate;

public abstract class Pessoa {
    private String nome;

    private LocalDate dataDeNascimento;

    private String nacionalidade;

    // Construtor
    public Pessoa(String nome, LocalDate dataDeNascimento, String nacionalidade) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.dataDeNascimento = dataDeNascimento;
    }

    // Métodos getters para obter os valores dos atributos
    public String getNome() {
        return nome;
    }



    public String getNacionalidade() {
        return nacionalidade;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public abstract String getDescricao();
}



