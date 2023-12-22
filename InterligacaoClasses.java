import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InterligacaoClasses {

    private static final int OPCAO_SAIR = 0;
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Filme> filmes = new ArrayList<>();
    private static List<Diretor> diretores = new ArrayList<>();
    private static List<Ator> atores = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {


        inicializarDados();
        exibirMenuInicial();

    }

    private static void inicializarDados() {
        cadastrarUsuariosPredefinidos();
        adicionarAtoresPredefinidos();
    }


    public static void cadastrarUsuariosPredefinidos() {
        usuarios.add(new Usuario("Vanessa", "vanessa@gmail.com", LocalDate.of(2001, 1, 1)));
        usuarios.add(new Usuario("Moacyr", "moacyr", LocalDate.of(1960, 9, 28)));
        usuarios.add(new Usuario("Tiago", "tiago@gmail.com", LocalDate.of(1995, 2, 1)));
    }

    private static void exibirMenuInicial() {
        int escolha;
        do {
            System.out.println("\nBem-vindo ao Sistema de Catálogo de Filmes!");
            System.out.println("Se você ainda não tem um usuário, por favor, cadastre-se para acessar o sistema.\n");
            System.out.println("\n===== Menu Inicial =====");
            System.out.println(OPCAO_SAIR + ". Sair");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Acessar usuário");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Opção inválida. Digite novamente.");
                scanner.next();
            }

            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    cadastrarNovoUsuario(escolha);
                    break;
                case 2:
                    acessarUsuario();
                    break;
                case 0:
                    System.out.println("Saindo do programa. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolha != 0);

    }

    private static void exibirMenuPrincipal() {
        int escolha;
        do {

            System.out.println("\n====== Menu Principal ======");
            System.out.println(OPCAO_SAIR + ". Sair");
            System.out.println("1. Cadastrar usuário");
            System.out.println("2. Acessar usuário");
            System.out.println("3. Cadastrar filme");
            System.out.println("4. Exibir detalhes de um filme");
            System.out.println("5. Exibir detalhes de um ator");
            System.out.println("6. Exibir detalhes de um diretor");
            System.out.println("7. Excluir usuario");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Opção inválida. Digite novamente.");
                scanner.next();
            }

            escolha = scanner.nextInt();

            switch (escolha) {
                case 1:
                    cadastrarNovoUsuario(escolha);
                    break;
                case 2:
                    acessarUsuario();
                    break;
                case 3:
                    cadastrarNovoFilme();
                    break;
                case 4:
                    exibirDetalhesDoFilme();
                    break;
                case 5:
                    exibirDetalhesDoAtor();
                    break;
                case 6:
                    exibirDetalhesDoDiretor();
                    break;
                case 7:
                    excluir();
                    break;
                case 0:
                    System.out.println("Saindo do programa. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (escolha != 0);
    }


    public static void preencherDadosUsuario() {
        System.out.println("Digite o nome: ");
        System.out.println("> ");
        scanner.nextLine();
        String nome = scanner.nextLine();


        while (nome.trim().isEmpty()) {
            System.out.println("Campo não pode estar em branco. Por favor, digite novamente:");
            nome = scanner.nextLine();
        }

        System.out.print("Digite o email: ");
        String email = scanner.nextLine();
        while (email.trim().isEmpty()) {
            System.out.println("Campo não pode estar em branco. Por favor, digite novamente:");
            email = scanner.nextLine();
        }

        System.out.print("Digite a data de nascimento (no formato YYYY-MM-DD): ");

        boolean dataValida = false;
        LocalDate dataNascimento = LocalDate.of(1900, 01, 01);

        while (!dataValida) {
            try {
                String input = scanner.nextLine();
                dataNascimento = LocalDate.parse(input);
                dataValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de data inválido. Por favor, digite novamente (formato YYYY-MM-DD):");
            }
        }

        Usuario usuario = new Usuario(nome, email, dataNascimento);
        salvarUsuario(usuario);
    }

    public static void cadastrarNovoUsuario(int escolha) {

        if (escolha == 1) {
            preencherDadosUsuario();
            exibirMenuInicial();
        }

    }

    public static void acessarUsuario() {
        System.out.println("Informe seu email: ");
        System.out.println("> ");
        scanner.nextLine();
        String email = scanner.nextLine();

        while (email.trim().isEmpty() || !existeUsuarioComEmail(email)) {
            System.out.println("Usuário não cadastrado ou email incorreto. Tente novamente.");
            System.out.println("Digite 0 para sair");
            System.out.println("> ");
            email = scanner.nextLine();

        }

        if (email.equalsIgnoreCase("0")){
            exibirMenuPrincipal();
        } else {
            System.out.println("Usuário ativo");
            exibirMenuPrincipal();}
    }

    public static boolean salvarUsuario(Usuario usuario) {
        if (existeUsuarioComEmail(usuario.getEmail())) {
            System.out.println("Usuário duplicado. Não foi possível salvar.");
            return false;
        } else {
            usuarios.add(usuario);
            System.out.println("Usuário salvo com sucesso.");
            return true;
        }
    }

    public static boolean existeUsuarioComEmail(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return true; // Usuário com o mesmo e-mail encontrado.
            } else if (email.equalsIgnoreCase("0")) {
                return true;
            }
        }
        return false; // Nenhum usuário com o mesmo e-mail encontrado.
    }

    private static void adicionarAtoresPredefinidos() {
        // Adicionando alguns atores à lista


        Filme filme = new Filme("Caminhos Da Floresta", "Fatasia", "Um padeiro e sua mulher vivem " +
                "em um vilarejo, onde lidam com " +
                "vários personagens famosos dos contos de fadas", 2014);


        Ator ator = new Ator("Johnny Depp", LocalDate.of(1963, 6,9), "Americano");
        atores.add(ator);
        filme.adicionarAtor(ator);
        Ator ator1 = new Ator("Meryl Streep", LocalDate.of(1949,6,22), "Americana");
        atores.add(ator1);
        filme.adicionarAtor(ator1);
        Diretor diretor = new Diretor("Rob Marshall", LocalDate.of(1960,10,17), "Americano");
        diretores.add(diretor);
        filme.adicionarDiretor(diretor);
        filmes.add(filme);


        Filme filme1 = new Filme("Náufrago", "Aventura", "Chuck Noland viaja a trabalho para a Malásia quando " +
                "o avião da empresa cai no oceano " +
                "Pacífico durante uma tempestade", 2000);
        Ator ator2 = new Ator("Tom Hanks", LocalDate.of(1956,7,9), "Americano");
        atores.add(ator2);
        filme1.adicionarAtor(ator2);
        Ator ator3 = new Ator("Helen Hunt", LocalDate.of(1963,6,15), "Americana");
        atores.add(ator3);
        filme1.adicionarAtor(ator3);
        Diretor diretor1 = new Diretor("Robert Zemeckis", LocalDate.of(1952,5,14), "Americano");
        diretores.add(diretor1);
        filme1.adicionarDiretor(diretor1);
        filmes.add(filme1);




    }

    private static void cadastrarNovoFilme() {
        System.out.println("Cadastro de Filmes:");
        int a = 99;

        while (a != 0) {

            int b = 99;

            System.out.println("Informe os dados do filme :");
            System.out.print("Título: ");
            scanner.nextLine();
            String titulo = scanner.nextLine();
            while (titulo.trim().isEmpty()) {
                System.out.println("Campo não pode estar em branco. Por favor, digite novamente:");
                titulo = scanner.nextLine();
            }

            boolean resultado = true;
            resultado = verificarFilme(titulo);
            if(resultado == false) {
                System.out.println("Filme já cadastrado");
                exibirMenuInicial();
            }


            System.out.print("Genero: ");
            String genero = scanner.nextLine();
            while (genero.trim().isEmpty()) {
                System.out.println("Campo não pode estar em branco. Por favor, digite novamente:");
                genero = scanner.nextLine();
            }


            System.out.print("Descricao: ");
            String descricao = scanner.nextLine();

            while (descricao.trim().isEmpty()) {
                System.out.println("Campo não pode estar em branco. Por favor, digite novamente:");
                descricao = scanner.nextLine();
            }


            int ano;

            while (true) {
                System.out.print("Ano de lançamento (no formato YYYY): ");

                if (scanner.hasNextInt()) {
                    ano = scanner.nextInt();
                    if (ano >= 1000 && ano <= 9999) {
                        break;
                    } else {
                        System.out.println("Ano inválido. Deve ter quatro dígitos. Tente novamente.");
                    }
                } else {
                    System.out.println("Entrada inválida. Digite novamente.");
                    scanner.next();
                }
            }

            Filme filme = new Filme(titulo, genero, descricao, ano);

            System.out.println("Informe os dados do diretor :");
            System.out.print("Nome: ");
            scanner.nextLine();
            String nome;
            nome = scanner.nextLine();
            while (nome.trim().isEmpty()) {
                System.out.println("Campo não pode estar em branco. Por favor, digite novamente:");
                nome = scanner.nextLine();
            }


            System.out.print("Digite a data de nascimento (no formato YYYY-MM-DD): ");

            boolean dataValida = false;
            LocalDate dataNascimento = LocalDate.of(1900, 01, 01);

            while (!dataValida) {
                try {
                    String input = scanner.nextLine();
                    dataNascimento = LocalDate.parse(input);
                    dataValida = true;
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de data inválido. Por favor, digite novamente (formato YYYY-MM-DD):");
                }
            }

            System.out.print("Nacionalidade: ");
            String nacionalidade = scanner.nextLine();
            while (nacionalidade.trim().isEmpty()) {
                System.out.println("Campo não pode estar em branco. Por favor, digite novamente:");
                nacionalidade = scanner.nextLine();
            }


            Diretor diretor = new Diretor(nome, dataNascimento, nacionalidade);
            diretores.add(diretor);

            filme.adicionarDiretor(diretor);

            while (b != 0) {

                System.out.println("Informe os dados do ator ou atriz :");
                System.out.print("Nome: ");
                nome = scanner.nextLine();
                while (nome.trim().isEmpty()) {
                    System.out.println("Campo não pode estar em branco. Por favor, digite novamente:");
                    nome = scanner.nextLine();
                }


                System.out.print("Digite a data de nascimento (no formato YYYY-MM-DD): ");


                while (!dataValida) {
                    try {
                        String input = scanner.nextLine();
                        dataNascimento = LocalDate.parse(input);
                        dataValida = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato de data inválido. Por favor, digite novamente (formato YYYY-MM-DD):");
                    }
                }

                System.out.print("Nacionalidade: ");
                nacionalidade = scanner.nextLine();
                while (nacionalidade.trim().isEmpty()) {
                    System.out.println("Campo não pode estar em branco. Por favor, digite novamente:");
                    nacionalidade = scanner.nextLine();
                }


                Ator ator = new Ator(nome, dataNascimento, nacionalidade);
                atores.add(ator);

                filme.adicionarAtor(ator);

                System.out.println("Digite qualquer numero para cadastrar outro ator ou atriz" +
                        " ou " + OPCAO_SAIR + " para sair.");
                while (!scanner.hasNextInt()) {
                    System.out.println("Entrada inválida. Digite novamente.");
                    scanner.next();
                }
                b = scanner.nextInt();

                scanner.nextLine();
            }

            filmes.add(filme);

            System.out.println("Digite qualquer numero para cadastrar outro filme ou " + OPCAO_SAIR + " para sair.");
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Digite novamente.");
                scanner.next();
            }
            a = scanner.nextInt();

            scanner.nextLine();

        }
        exibirMenuPrincipal();
    }

    public static boolean verificarFilme(String titulo) {
        if (existeFilme(titulo)) {
            return false;
        } else {

            return true;
        }
    }

    public static boolean existeFilme(String titulo) {
        for (Filme filme : filmes) {
            if (filme.getTitulo().equalsIgnoreCase(titulo)) {
                return true; // Filme ja cadastrado encontrado
            } else if (titulo.equalsIgnoreCase("0")) {
                return true;
            }
        }
        return false; // Nenhum filme encontrado
    }


    private static void exibirAtoresESeusFilmes() {
        System.out.println("Lista de Atores e Seus Filmes:");

        for (Ator ator : atores) {
            System.out.println("Ator: " + ator.getNome());
            System.out.println("Idade: " + ator.getDataDeNascimento());
            System.out.println("Nacionalidade: " + ator.getNacionalidade());
            System.out.println("Filmes:");

            for (Filme filme : filmes) {
                if (filme.getAtores().contains(ator)) {
                    System.out.println("- " + filme.getTitulo() + " (" + filme.getAno() + ")");
                }
            }

            System.out.println("---");
        }
    }

    private static void exibirDetalhesDoFilme() {
        System.out.println("\nExibir Detalhes de um Filme:");
        System.out.print("Informe o título do filme: ");
        scanner.nextLine();
        String tituloFilme = scanner.nextLine();
        while (tituloFilme.trim().isEmpty()) {
            System.out.println("Campo não pode estar em branco. Por favor, digite novamente:");
            tituloFilme = scanner.nextLine();
        }

        tituloFilme.equalsIgnoreCase(tituloFilme);


        Filme filmeEncontrado = procurarFilmePorTitulo(tituloFilme);

        if (filmeEncontrado != null) {

            System.out.println("Detalhes do Filme:");
            System.out.println("Título: " + filmeEncontrado.getTitulo());
            System.out.println("Genero: " + filmeEncontrado.getGenero());
            System.out.println("Descrição: " + filmeEncontrado.getDescricao());
            System.out.println("Ano de lançamento: " + filmeEncontrado.getAno());
            System.out.println("Diretor: " + filmeEncontrado.getDiretores().toString());
            System.out.println("Atores:");

            for (Ator ator : filmeEncontrado.getAtores()) {
                System.out.println("- " + ator.getNome());
            }
        } else {
            System.out.println("Filme não encontrado.");
        }
        System.out.println("Enter para continuar.");
        scanner.nextLine();
        exibirMenuPrincipal();
    }

    private static Filme procurarFilmePorTitulo(String titulo) {
        for (Filme filme : filmes) {
            if (filme.getTitulo().equalsIgnoreCase(titulo)) {
                return filme;
            }
        }
        return null;
    }

    private static void exibirDetalhesDoAtor() {
        System.out.println("\nExibir Detalhes de um Ator ou atriz:");
        System.out.print("Informe o nome do ator ou atriz: ");
        scanner.nextLine();
        String nomeAtor = scanner.nextLine();
        while (nomeAtor.trim().isEmpty()) {
            System.out.println("Campo não pode estar em branco. Por favor, digite novamente:");
            nomeAtor = scanner.nextLine();
        }

        Ator atorEncontrado = procurarAtorPorNome(nomeAtor);

        if (atorEncontrado != null) {
            System.out.println("Detalhes do Ator:");
            System.out.println("Nome: " + atorEncontrado.getNome());
            System.out.println("Idade: " + atorEncontrado.getDataDeNascimento());
            System.out.println("Nacionalidade: " + atorEncontrado.getNacionalidade());
            System.out.println("Filmes:");

            boolean encontrouFilmes = false;

            for (Filme filme : filmes) {
                for (Ator atorFilme : filme.getAtores()) {
                    if (atorFilme.getNome().equalsIgnoreCase(atorEncontrado.getNome())) {
                        System.out.println("- " + filme.getTitulo() + " (" + filme.getAno() + ")");
                        encontrouFilmes = true;
                    }
                }
            }

            if (!encontrouFilmes) {
                System.out.println("O  ator ou atriz não encontrado.");
            }
        } else {
            System.out.println("Ator ou atriz não encontrado.");
        }
        System.out.println("Enter para continuar");
        scanner.nextLine();
        exibirMenuPrincipal();
    }

    private static Ator procurarAtorPorNome(String nome) {
        for (Ator ator : atores) {
            if (ator.getNome().equalsIgnoreCase(nome)) {
                return ator;
            }
        }
        return null;
    }

    private static void exibirDetalhesDoDiretor() {
        System.out.println("\nExibir Detalhes de um Diretor:");
        System.out.print("Informe o nome do Diretor: ");
        scanner.nextLine();
        String nomeDiretor = scanner.nextLine();
        while (nomeDiretor.trim().isEmpty()) {
            System.out.println("Campo não pode estar em branco. Por favor, digite novamente:");
            nomeDiretor = scanner.nextLine();
        }


        Diretor diretorEncontrado = procurarDiretorPorNome(nomeDiretor);


        if (diretorEncontrado != null) {
            System.out.println("\nDetalhes do Diretor:");
            System.out.println("Nome: " + diretorEncontrado.getNome());
            System.out.println("Idade: " + diretorEncontrado.getDataDeNascimento());
            System.out.println("Nacionalidade: " + diretorEncontrado.getNacionalidade());
            System.out.println("Filmes:");


            for (Filme filme : filmes) {
                for (Diretor diretorFilme : filme.getDiretores()) {
                    if (diretorFilme.getNome().equalsIgnoreCase(diretorEncontrado.getNome())) {
                        System.out.println("- " + filme.getTitulo() + " (" + filme.getAno() + ")");
                    }

                }
            }
        } else {
            System.out.println("Diretor não encontrado.");
        }

        System.out.println("Enter para continuar");
        scanner.nextLine();
        exibirMenuPrincipal();
    }

    private static Diretor procurarDiretorPorNome(String nome) {
        for (Diretor diretor : diretores) {
            if (diretor.getNome().equalsIgnoreCase(nome)) {
                return diretor;
            }
        }
        return null;
    }

    public static void excluir() {
        System.out.println("\nInformar o email do usuario");
        System.out.println("Usuario será excluido");
        scanner.nextLine();
        String email = scanner.nextLine();
        while (email.trim().isEmpty()) {
            System.out.println("Campo não pode estar em branco. Por favor, digite novamente:");
            email = scanner.nextLine();
        }

        int contadorIndex = 0;
        for (Usuario user : usuarios) {
            if (user.getEmail().equals(email)) {
                System.out.println("Removendo usuário e-mail " + email);
                usuarios.remove(user);
                break;
            }
            contadorIndex++;
        }

        exibirMenuPrincipal();

    }

}

