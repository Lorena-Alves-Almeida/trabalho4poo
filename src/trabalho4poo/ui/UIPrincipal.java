package trabalho4poo.ui;

import java.util.Scanner;

public class UIPrincipal {

    private Scanner scn;
    private UIUsuario uiUsuario;
    private UIProjeto uiProjeto;

    public UIPrincipal() {
        scn = new Scanner(System.in);
        uiUsuario = new UIUsuario();
        uiProjeto = new UIProjeto();
    }

    public void iniciar() {

        int opcao;

        do {
            System.out.println();
            System.out.println("==================================================");
            System.out.println("             SISTEMA DE PROJETOS");
            System.out.println("==================================================");
            System.out.println("1 - Login");
            System.out.println("2 - Cadastrar usuário");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = lerInteiro();

            switch (opcao) {

            case 1:
                uiUsuario.login();

                if (trabalho4poo.negocio.Sistema.getInstance()
                        .getUsuarioLogado() != null) {

                    menuPrincipal();
                }
                break;

            case 2:
                uiUsuario.cadastrar();
                break;

            case 0:
                System.out.println("\nSaindo do sistema...");
                break;

            default:
                System.out.println("\nOpção inválida.");
                break;
            }

        } while (opcao != 0);
    }

    private void menuPrincipal() {

        int opcao;

        do {
            System.out.println();
            System.out.println("==================================================");
            System.out.println("                 MENU PRINCIPAL");
            System.out.println("==================================================");

            if (trabalho4poo.negocio.Sistema.getInstance()
                    .getUsuarioLogado() != null) {

                System.out.println(
                    "Usuário: "
                    + trabalho4poo.negocio.Sistema.getInstance()
                        .getUsuarioLogado().getNmUsuario());
            }

            System.out.println();
            System.out.println("1 - Menu de Usuários");
            System.out.println("2 - Menu de Projetos");
            System.out.println("0 - Logout");
            System.out.print("Escolha: ");

            opcao = lerInteiro();

            switch (opcao) {

            case 1:
                menuUsuarios();
                break;

            case 2:
                menuProjetos();
                break;

            case 0:
                trabalho4poo.negocio.Sistema.getInstance().logout();
                System.out.println("\nLogout realizado.");
                break;

            default:
                System.out.println("\nOpção inválida.");
                break;
            }

        } while (opcao != 0
                && trabalho4poo.negocio.Sistema.getInstance()
                    .getUsuarioLogado() != null);
    }

    private void menuUsuarios() {

        int opcao;

        do {
            System.out.println();
            System.out.println("==================================================");
            System.out.println("                  MENU DE USUÁRIOS");
            System.out.println("==================================================");
            System.out.println("1 - Cadastrar usuário");
            System.out.println("2 - Listar usuários");
            System.out.println("3 - Consultar usuário");
            System.out.println("4 - Alterar usuário");
            System.out.println("5 - Excluir usuário");
            System.out.println("6 - Consultar projetos do usuário");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = lerInteiro();

            switch (opcao) {

            case 1:
                uiUsuario.cadastrar();
                break;

            case 2:
                uiUsuario.listar();
                break;

            case 3:
                uiUsuario.consultar();
                break;

            case 4:
                uiUsuario.alterar();
                break;

            case 5:
                uiUsuario.excluir();
                break;

            case 6:
                uiUsuario.consultarProjetos();
                break;

            case 0:
                break;

            default:
                System.out.println("\nOpção inválida.");
                break;
            }

        } while (opcao != 0);
    }

    private void menuProjetos() {

        int opcao;

        do {
            System.out.println();
            System.out.println("==================================================");
            System.out.println("                  MENU DE PROJETOS");
            System.out.println("==================================================");
            System.out.println("1 - Criar projeto");
            System.out.println("2 - Listar projetos");
            System.out.println("3 - Consultar projeto");
            System.out.println("4 - Alterar projeto");
            System.out.println("5 - Excluir projeto");
            System.out.println("6 - Adicionar colaborador");
            System.out.println("7 - Fazer projeto");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");

            opcao = lerInteiro();

            switch (opcao) {

            case 1:
                uiProjeto.add();
                break;

            case 2:
                uiProjeto.listar();
                break;

            case 3:
                uiProjeto.projetoPorCodigo();
                break;

            case 4:
                uiProjeto.alterar();
                break;

            case 5:
                uiProjeto.excluir();
                break;

            case 6:
                uiProjeto.addColaborador();
                break;

            case 7:
                uiProjeto.fazer();
                break;

            case 0:
                break;

            default:
                System.out.println("\nOpção inválida.");
                break;
            }

        } while (opcao != 0);
    }

    private int lerInteiro() {

        while (true) {

            try {

                return Integer.parseInt(scn.nextLine());

            } catch (NumberFormatException e) {

                System.out.print("Digite um número válido: ");
            }
        }
    }
}
