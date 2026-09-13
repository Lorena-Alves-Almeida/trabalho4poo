package trabalho4poo.ui;

import java.util.List;
import java.util.Scanner;

import trabalho4poo.negocio.Projeto;
import trabalho4poo.negocio.Sistema;
import trabalho4poo.negocio.Usuario;

public class UIUsuario {

    private static Scanner scn = new Scanner(System.in);

    public void cadastrar() {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("               CADASTRAR USUÁRIO");
        System.out.println("==================================================");

        System.out.print("Nome de usuário: ");
        String nome = scn.nextLine();

        if (nome == null || nome.trim().isEmpty()) {
            System.out.println("Nome inválido.");
            return;
        }

        if (Sistema.getInstance().existeNomeUsuario(nome)) {
            System.out.println("Esse usuário já existe.");
            return;
        }

        System.out.print("Senha: ");
        String senha = scn.nextLine();

        if (senha == null || senha.trim().isEmpty()) {
            System.out.println("Senha inválida.");
            return;
        }

        Usuario usuario = Usuario.getInstance(nome, senha);

        if (Sistema.getInstance().addUsuario(usuario)) {

            System.out.println(
                    "\nUsuário cadastrado com sucesso!");

        } else {

            System.out.println(
                    "\nFalha ao cadastrar usuário.");
        }
    }

    public void listar() {

        List<Usuario> listaCopia =
                Sistema.getInstance().listarUsuarios();

        System.out.println();
        System.out.println(
                "--- Lista de usuários cadastrados ---");

        if (listaCopia.isEmpty()) {

            System.out.println(
                    "Não existem usuários cadastrados.");

            return;
        }

        System.out.println("|COD. | NOME");

        for (int i = 0; i < listaCopia.size(); i++) {

            if (listaCopia.get(i) != null) {

                System.out.printf(
                        "%-6s %-20s%n",
                        listaCopia.get(i).getCdUsuario(),
                        listaCopia.get(i).getNmUsuario());
            }
        }
    }

    public void login() {

        System.out.println();
        System.out.println("==================================================");
        System.out.println("                     LOGIN");
        System.out.println("==================================================");

        System.out.print("Nome do usuário: ");
        String nomeUsuario = scn.nextLine();

        System.out.print("Senha: ");
        String senha = scn.nextLine();

        Usuario usuarioLogado =
                Sistema.getInstance()
                        .autenticarLogin(nomeUsuario, senha);

        if (usuarioLogado != null) {

            System.out.println(
                    "\nPerfil encontrado!");

        } else {

            System.out.println(
                    "\nPerfil não encontrado ou inexistente.");
        }
    }

    public void consultar() {

        System.out.println();
        System.out.println(
                "--- Consultar usuário ---");

        System.out.print("Código do usuário: ");
        int codigo = lerInteiro();

        Usuario usuario =
                Sistema.getInstance()
                        .buscarUsuarioPorId(codigo);

        if (usuario == null) {

            System.out.println(
                    "Usuário não encontrado.");

            return;
        }

        System.out.println();
        System.out.println(
                "Código: " + usuario.getCdUsuario());

        System.out.println(
                "Nome: " + usuario.getNmUsuario());
    }

    public void alterar() {

        Usuario uAlterado =
                Sistema.getInstance().getUsuarioLogado();

        if (uAlterado == null) {

            System.out.println(
                    "Nenhum usuário está logado.");

            return;
        }

        int escolha;

        do {

            System.out.println();
            System.out.println(
                    "==================================================");
            System.out.println(
                    "                ALTERAR USUÁRIO");
            System.out.println(
                    "==================================================");

            System.out.println(
                    "Usuário atual: "
                    + uAlterado.getNmUsuario());

            System.out.println("1 - Alterar nome");
            System.out.println("2 - Alterar senha");
            System.out.println("3 - Adicionar colaborador");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            escolha = lerInteiro();

            switch (escolha) {

            case 1:
                alterarNome(uAlterado);
                break;

            case 2:
                alterarSenha(uAlterado);
                break;

            case 3:
                UIProjeto uiProjeto =
                        new UIProjeto();

                uiProjeto.addColaborador();
                break;

            case 0:
                break;

            default:
                System.out.println(
                        "Opção inválida.");
                break;
            }

        } while (escolha != 0);
    }

    private void alterarNome(Usuario usuario) {

        System.out.print(
                "\nNovo nome de usuário: ");

        String novoNome =
                scn.nextLine();

        if (novoNome == null
                || novoNome.trim().isEmpty()) {

            System.out.println(
                    "Nome inválido.");

            return;
        }

        if (!novoNome.equals(
                usuario.getNmUsuario())
                && Sistema.getInstance()
                    .existeNomeUsuario(novoNome)) {

            System.out.println(
                    "Esse nome já está sendo utilizado.");

            return;
        }

        if (usuario.setNmUsuario(novoNome)
                && Sistema.getInstance()
                    .alterarUsuario(usuario)) {

            System.out.println(
                    "Usuário alterado com sucesso.");

        } else {

            System.out.println(
                    "Falha em alterar usuário.");
        }
    }

    private void alterarSenha(Usuario usuario) {

        System.out.print(
                "\nNova senha: ");

        String novaSenha =
                scn.nextLine();

        if (novaSenha == null
                || novaSenha.trim().isEmpty()) {

            System.out.println(
                    "Senha inválida.");

            return;
        }

        if (usuario.setSenha(novaSenha)
                && Sistema.getInstance()
                    .alterarUsuario(usuario)) {

            System.out.println(
                    "Senha alterada com sucesso.");

        } else {

            System.out.println(
                    "Falha em alterar senha.");
        }
    }

    public void excluir() {

        Usuario usuario =
                Sistema.getInstance()
                        .getUsuarioLogado();

        if (usuario == null) {

            System.out.println(
                    "Nenhum usuário está logado.");

            return;
        }

        System.out.println();
        System.out.println(
                "--- Excluir usuário ---");

        System.out.print(
                "Deseja realmente excluir seu usuário? "
                + "(1-Sim / 2-Não): ");

        int escolha = lerInteiro();

        if (escolha != 1) {
            return;
        }

        if (Sistema.getInstance()
                .excluirUsuario(
                        usuario.getCdUsuario())) {

            System.out.println(
                    "Usuário excluído com sucesso.");

            Sistema.getInstance().logout();

        } else {

            System.out.println(
                    "Falha ao excluir usuário.");
        }
    }

    public void consultarProjetos() {

        System.out.println();
        System.out.println(
                "--- Consultar projetos do usuário ---");

        System.out.print(
                "Nome do usuário: ");

        String nome =
                scn.nextLine();

        Usuario usuario =
                Sistema.getInstance()
                        .buscarUsuarioPorLogin(nome);

        if (usuario == null) {

            System.out.println(
                    "Usuário não encontrado.");

            return;
        }

        List<Projeto> projetos =
                Sistema.getInstance()
                        .buscarProjetosDoUsuario(usuario);

        if (projetos.isEmpty()) {

            System.out.println(
                    "Esse usuário não possui projetos.");

            return;
        }

        System.out.println();
        System.out.println(
                "Projetos de "
                + usuario.getNmUsuario()
                + ":");

        for (int i = 0; i < projetos.size(); i++) {

            Projeto projeto =
                    projetos.get(i);

            System.out.println();
            System.out.println(
                    "Código: "
                    + projeto.getCdProjeto());

            System.out.println(
                    "Nome: "
                    + projeto.getNmProjeto());

            if (projeto.getProprietario()
                    .getCdUsuario()
                    == usuario.getCdUsuario()) {

                System.out.println(
                        "Função: Proprietário");

            } else {

                System.out.println(
                        "Função: Colaborador");
            }

            System.out.println(
                    "Privacidade: "
                    + projeto.getPrivacidade());
        }
    }

    private int lerInteiro() {

        while (true) {

            try {

                return Integer.parseInt(
                        scn.nextLine());

            } catch (NumberFormatException e) {

                System.out.print(
                        "Digite um número válido: ");
            }
        }
    }
}
