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


        Ator ator = new Ator("Johnny Depp", LocalDate.of(1963, 6, 9), "Americano");
        atores.add(ator);
        filme.adicionarAtor(ator);
        Ator ator1 = new Ator("Meryl Streep", LocalDate.of(1949, 6, 22), "Americana");
        atores.add(ator1);
        filme.adicionarAtor(ator1);
        Diretor diretor = new Diretor("Rob Marshall", LocalDate.of(1960, 10, 17), "Americano");
        diretores.add(diretor);
        filme.adicionarDiretor(diretor);
        filmes.add(filme);


        Filme filme1 = new Filme("Náufrago", "Aventura", "Chuck Noland viaja a trabalho para a Malásia quando " +
                "o avião da empresa cai no oceano " +
                "Pacífico durante uma tempestade", 2000);
        Ator ator2 = new Ator("Tom Hanks", LocalDate.of(1956, 7, 9), "Americano");
        atores.add(ator2);
        filme1.adicionarAtor(ator2);
        Ator ator3 = new Ator("Helen Hunt", LocalDate.of(1963, 6, 15), "Americana");
        atores.add(ator3);
        filme1.adicionarAtor(ator3);
        Diretor diretor1 = new Diretor("Robert Zemeckis", LocalDate.of(1952, 5, 14), "Americano");
        diretores.add(diretor1);
        filme1.adicionarDiretor(diretor1);
        filmes.add(filme1);

        Filme filme2 = new Filme("Titanic", "Romance", "Um artista pobre e uma jovem rica se conhecem e " +
                "se apaixonam na fatídica viagem inaugural do Titanic em 1912.", 1998);

        Ator ator4 = new Ator("Kate Winslet", LocalDate.of(1975, 10, 5), "Britanica");
        atores.add(ator4);
        filme2.adicionarAtor(ator4);

        Ator ator5 = new Ator("Leonardo DiCaprio", LocalDate.of(1974, 11, 11), "Americana");
        atores.add(ator5);
        filme2.adicionarAtor(ator5);

        Ator ator6 = new Ator("Kathy Bates",LocalDate.of(1948,6,28),"Americana");
        atores.add(ator6);
        filme2.adicionarAtor(ator6);

        Ator ator12 = new Ator("Bill Paxton",LocalDate.of(1955,05,17),"Americana");
        atores.add(ator12);
        filme2.adicionarAtor(ator12);

        Diretor diretor2 = new Diretor("James Camaron",LocalDate.of(1954,8,16),"Canadense");
        diretores.add(diretor2);
        filme2.adicionarDiretor(diretor2);
        filmes.add(filme2);

        Filme filme3 = new Filme("Oppenheimer","Suspense","O físico J. Robert Oppenheimer trabalha com uma " +
                "equipe de cientistas durante o Projeto Manhattan, " +
                "levando ao desenvolvimento da bomba atômica.",2023);

        Ator ator7 = new Ator("Cillian Murphy",LocalDate.of(1976,5,25),"Irlandesa");
        atores.add(ator7);
        filme3.adicionarAtor(ator7);

        Ator ator8 = new Ator("Robert Downey Jr.", LocalDate.of(1965,4,4), "Americana");
        atores.add(ator8);
        filme3.adicionarAtor(ator8);

        Ator ator9 = new Ator("Florence Pugh", LocalDate.of(1996,1,3),"Britânica");
        atores.add(ator9);
        filme3.adicionarAtor(ator9);

        Diretor diretor3 = new Diretor("Christopher Nolan",LocalDate.of(1970,7,30),"Britânica");
        diretores.add(diretor3);
        filme3.adicionarDiretor(diretor3);

        filmes.add(filme3);

        Filme filme4 = new Filme("Twister","Ação", "Enquanto a tempestade mais devastadora das " +
                "últimas décadas se aproxima, a professora universitária Jo Harding e uma " +
                "equipe de alunos com poucos recursos preparam o protótipo de Dorothy.",1996);

        Ator ator10 = new Ator("Helen Hunt",LocalDate.of(1963,6,15),"America");
        atores.add(ator10);
        filme4.adicionarAtor(ator10);

        Ator ator11 = new Ator("Bill Paxton",LocalDate.of(1955,05,17),"Americana");
        atores.add(ator11);
        filme4.adicionarAtor(ator11);

        Diretor diretor4 = new Diretor("Jan de Bont",LocalDate.of(1943,10,22),"Holandesa");
        diretores.add(diretor4);
        filme4.adicionarDiretor(diretor4);

        filmes.add(filme4);

        Filme filme5 = new Filme("Aquaman","Fantasia","A cidade de Atlantis, " +
                "que já foi lar de uma das mais avançadas civilizações do mundo," +
                " agora é um reino submerso dominado pelo ganancioso Rei Orm.",2018);

        Ator ator13 = new Ator("Jason Momoa",LocalDate.of(1979,8,1),"Americana");
        atores.add(ator13);
        filme5.adicionarAtor(ator13);

        Ator ator14 = new Ator("Patrick Wilson",LocalDate.of(1973,6,3),"Americana");
        atores.add(ator14);
        filme5.adicionarAtor(ator14);

        Ator ator15 = new Ator("Amber Heard",LocalDate.of(1986,4,22),"Americana");
        atores.add(ator15);
        filme5.adicionarAtor(ator15);

        Diretor diretor5 = new Diretor("James Wan", LocalDate.of(1977,2,26),"Malasiano");
        diretores.add(diretor5);
        filme5.adicionarDiretor(diretor5);

        filmes.add(filme5);

        Filme filme6 = new Filme("Sobrenatural","Terror","Josh e Renai se mudam com a família para uma casa maior. " +
                "Lá, o filho Dalton sofre um estranho acidente e entra em coma. " +
                "Enquanto eles tentam salvar o menino, entidades malignas atormentam a família.",2011);

        Ator ator16 = new Ator("Rose Byrne",LocalDate.of(1979,7,24),"Autralianq");
        atores.add(ator16);
        filme6.adicionarAtor(ator16);

        Ator ator17 = new Ator("Patrick Wilson",LocalDate.of(1973,6,3),"Americana");
        atores.add(ator17);
        filme6.adicionarAtor(ator17);

        Ator ator18 = new Ator("Leigh Whannell",LocalDate.of(1977,1,17),"Australiana");
        atores.add(ator18);
        filme6.adicionarAtor(ator18);

        Diretor diretor6 = new Diretor("James Wan", LocalDate.of(1977,2,26),"Malasiano");
        diretores.add(diretor6);
        filme6.adicionarDiretor(diretor6);

        filmes.add(filme6);














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

