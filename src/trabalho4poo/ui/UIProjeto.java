package trabalho4poo.ui;

import java.util.List;
import java.util.Scanner;

import trabalho4poo.dados.RepositorioProjeto;
import trabalho4poo.negocio.Div;
import trabalho4poo.negocio.Formulario;
import trabalho4poo.negocio.Input;
import trabalho4poo.negocio.Paragrafo;
import trabalho4poo.negocio.Projeto;
import trabalho4poo.negocio.Sistema;
import trabalho4poo.negocio.Usuario;

public class UIProjeto {

	private static Scanner scn = new Scanner(System.in);

	public void add() {

		System.out.println("================================================================");
		System.out.println("--- Criando um novo projeto ---");

		System.out.print("\nNome do projeto: ");
		String nmProjeto = scn.nextLine();

		if (nmProjeto == null || nmProjeto.trim().isEmpty()) {

			System.out.println("Nome inválido.");

			return;
		}

		System.out.print("\nNome do proprietário: ");

		String proprietario = scn.nextLine();

		while (!Sistema.getInstance().existeNomeUsuario(proprietario)) {

			System.out.println("Usuário não encontrado.");

			System.out.print("Nome do proprietário: ");

			proprietario = scn.nextLine();
		}

		System.out.println("\nO projeto é Publico ou Privado?");

		System.out.println("Digite exatamente 'Publico' ou 'Privado'.");

		String privacidade = scn.nextLine();

		while (!privacidade.equals("Publico") && !privacidade.equals("Privado")) {

			System.out.println("Opção inválida.");

			System.out.print("Digite Publico ou Privado: ");

			privacidade = scn.nextLine();
		}

		Usuario usuarioProprietario = Sistema.getInstance().buscarUsuarioPorLogin(proprietario);

		if (Sistema.getInstance().criarProjeto(nmProjeto, usuarioProprietario, privacidade)) {

			System.out.println("\nProjeto criado com sucesso!");

		} else {

			System.out.println("\nFalha ao criar projeto.");
		}
	}

	public void addColaborador() {

		System.out.println();
		System.out.println("----- Adicionar colaborador -----");

		System.out.println("\n---- Usuários ----");

		listarUsuarios();

		System.out.print("\nCódigo do projeto: ");

		int codigoProjeto = lerInteiro();

		Projeto projeto = Sistema.getInstance().buscarProjetoPorCodigoPrivado(codigoProjeto);

		if (projeto == null) {

			System.out.println("Projeto não encontrado.");

			return;
		}

		Usuario usuarioLogado = Sistema.getInstance().getUsuarioLogado();

		if (usuarioLogado == null) {

			System.out.println("Nenhum usuário está logado.");

			return;
		}

		if (projeto.getProprietario().getCdUsuario() != usuarioLogado.getCdUsuario()) {

			System.out.println("Somente o proprietário " + "pode adicionar colaboradores.");

			return;
		}

		System.out.print("\nNome do colaborador: ");

		String colaborador = scn.nextLine();

		while (!Sistema.getInstance().existeNomeUsuario(colaborador)) {

			System.out.println("Usuário não encontrado.");

			System.out.print("Nome do colaborador: ");

			colaborador = scn.nextLine();
		}

		Usuario usuarioColaborador = Sistema.getInstance().buscarUsuarioPorLogin(colaborador);

		boolean resultado = Sistema.getInstance().adicionarColaborador(codigoProjeto, usuarioColaborador);

		if (resultado) {

			System.out.println("Colaborador adicionado com sucesso.");

		} else {

			System.out.println("Falha ao adicionar colaborador.");
		}
	}

	private void listarUsuarios() {

		List<Usuario> usuarios = Sistema.getInstance().listarUsuarios();

		System.out.println("|COD. | NOME");

		for (int i = 0; i < usuarios.size(); i++) {

			Usuario usuario = usuarios.get(i);

			if (usuario != null) {

				System.out.printf("%-6s %-20s%n", usuario.getCdUsuario(), usuario.getNmUsuario());
			}
		}
	}

	public void fazer() {

		System.out.println("=== CONFIGURADOR DE LAYOUT DINÂMICO ===");

        // 1. Definição da quantidade de Colunas e Divs
        System.out.print("\nQuantas Colunas deseja criar no container principal? ");
        int qtdColunas = scn.nextInt();

        System.out.print("Quantas Divs deseja criar empilhadas por coluna? ");
        int qtdDivs = scn.nextInt();

        // 2. Definição dos Formulários e seus Inputs
        System.out.print("Quantos Formulários deseja criar? ");
        int qtdForms = scn.nextInt();
        scn.nextLine(); // Consome a quebra de linha do scanner

        // 3. Posicionamento e Altura
        System.out.println("\nQual a disposição no container principal?");
        System.out.println("1 - Colunas de Divs na Esquerda | Formulários na Direita");
        System.out.println("2 - Formulários na Esquerda | Colunas de Divs na Direita");
        System.out.print("Opção: ");
        int posicao = scn.nextInt();

        System.out.print("\nQual a altura total do container (em px)? ");
        int alturaPx = scn.nextInt();
        scn.nextLine();

        if (qtdColunas <= 0) {
            qtdColunas = 1;
        }

        // --- CONSTRUÇÃO DA ESTRUTURA HTML ---

        // Container Principal
        Div containerPrincipal = new Div("main-container", "flex-container");
        containerPrincipal.adicionarEstilo("display", "flex");
        containerPrincipal.adicionarEstilo("gap", "20px");
        containerPrincipal.adicionarEstilo("width", "100%");
        containerPrincipal.adicionarEstilo("height", alturaPx + "px");

        // Cálculo dinâmico da largura de cada coluna no layout
        int totalColunasLayout = qtdForms > 0 ? (qtdColunas + 1) : qtdColunas;
        int larguraColunaPorcentagem = 100 / totalColunasLayout;

        // Coluna de Formulários
        Div colunaForms = new Div("coluna-forms", "coluna");
        colunaForms.adicionarEstilo("display", "flex");
        colunaForms.adicionarEstilo("flex-direction", "column");
        colunaForms.adicionarEstilo("width", larguraColunaPorcentagem + "%");
        colunaForms.adicionarEstilo("height", "100%");
        colunaForms.adicionarEstilo("gap", "15px");

        if (qtdForms > 0) {
            for (int f = 1; f <= qtdForms; f++) {
                System.out.println("\n--- CONFIGURAÇÃO DO FORMULÁRIO " + f + " ---");
                System.out.print("Quantos inputs este formulário terá? ");
                int qtdInputs = scn.nextInt();
                scn.nextLine();

                Formulario form = new Formulario("form-" + f, "formulario-estilizado", "/enviar", "POST");
                form.adicionarEstilo("display", "flex");
                form.adicionarEstilo("flex-direction", "column");
                form.adicionarEstilo("gap", "10px");
                form.adicionarEstilo("background-color", "#f9f9f9");
                form.adicionarEstilo("padding", "15px");

                for (int inp = 1; inp <= qtdInputs; inp++) {
                    System.out.print("Texto do placeholder para o Input " + inp + ": ");
                    String placeholder = scn.nextLine();

                    Input input = new Input(
                        "input-" + f + "-" + inp,
                        "campo",
                        "text",
                        "campo_" + f + "_" + inp,
                        placeholder
                    );
                    form.adicionarFilho(input);
                }
                colunaForms.adicionarFilho(form);
            }
        }

        // Se a opção for 2, os formulários entram primeiro (na esquerda)
        if (posicao == 2 && qtdForms > 0) {
            containerPrincipal.adicionarFilho(colunaForms);
        }

        // Criação das colunas de Divs informadas pelo usuário
        int alturaPorcentagemDiv = qtdDivs > 0 ? 100 / qtdDivs : 100;

        for (int c = 1; c <= qtdColunas; c++) {
            Div colunaDivs = new Div("coluna-divs-" + c, "coluna");
            colunaDivs.adicionarEstilo("display", "flex");
            colunaDivs.adicionarEstilo("flex-direction", "column");
            colunaDivs.adicionarEstilo("width", larguraColunaPorcentagem + "%");
            colunaDivs.adicionarEstilo("height", "100%");
            colunaDivs.adicionarEstilo("gap", "10px");

            for (int i = 1; i <= qtdDivs; i++) {
                Div divFilha = new Div("div-c" + c + "-filha-" + i, "caixa-filha");
                divFilha.adicionarEstilo("height", alturaPorcentagemDiv + "%");
                divFilha.adicionarEstilo("background-color", "#e0e0e0");
                divFilha.adicionarFilho(new Paragrafo("p-c" + c + "-" + i, "texto", "Conteúdo da Div " + i + " (Coluna " + c + ")"));
                colunaDivs.adicionarFilho(divFilha);
            }

            containerPrincipal.adicionarFilho(colunaDivs);
        }

        // Se a opção for 1, os formulários entram no final (na direita)
        if (posicao == 1 && qtdForms > 0) {
            containerPrincipal.adicionarFilho(colunaForms);
        }

        // Guarda o resultado gerado em uma variável String usando o método original
        String codigoFinalHtml = containerPrincipal.renderizar(0);

        // Renderização do HTML gerado
        System.out.println("\n=== HTML GERADO COM SUCESSO ===");
        System.out.println(codigoFinalHtml);
        RepositorioProjeto.projetos.get(Projeto.quantProjetos).projetoCodigo = codigoFinalHtml;
	}

	public void listar() {

		List<Projeto> listaCopia = Sistema.getInstance().listarProjetos();

		System.out.println("\n--- Lista de projetos ---");

		if (listaCopia.isEmpty()) {

			System.out.println("Não existem projetos cadastrados.");

			return;
		}

		System.out.println("|COD. | NOME | PROPRIETÁRIO | PRIVACIDADE");

		for (int i = 0; i < listaCopia.size(); i++) {

			Projeto projeto = listaCopia.get(i);

			System.out.printf("%-6s %-20s %-20s %-12s%n", projeto.getCdProjeto(), projeto.getNmProjeto(),
					projeto.getProprietario().getNmUsuario(), projeto.getPrivacidade());
		}
	}

	public void projetoPorCodigo() {

		System.out.println();
		System.out.println("--- Procurar projeto por código ---");

		System.out.print("Código: ");

		int codigo = lerInteiro();

		Projeto projeto = Sistema.getInstance().buscarProjetoPorCodigo(codigo);

		if (projeto != null) {

			System.out.println("\nCódigo: " + projeto.getCdProjeto());

			System.out.println("Nome: " + projeto.getNmProjeto());

			System.out.println("Proprietário: " + projeto.getProprietario().getNmUsuario());

			System.out.println("Privacidade: " + projeto.getPrivacidade());

		} else {

			System.out.println("Código não encontrado " + "ou projeto privado.");
		}
	}

	public void alterar() {

		System.out.println();
		System.out.println("--- Alterar projeto ---");

		System.out.print("Código do projeto: ");

		int codigo = lerInteiro();

		Projeto projeto = Sistema.getInstance().buscarProjetoPorCodigoPrivado(codigo);

		if (projeto == null) {

			System.out.println("Projeto não encontrado.");

			return;
		}

		Usuario usuarioLogado = Sistema.getInstance().getUsuarioLogado();

		if (usuarioLogado == null || projeto.getProprietario().getCdUsuario() != usuarioLogado.getCdUsuario()) {

			System.out.println("Somente o proprietário pode alterar o projeto.");

			return;
		}

		int opcao;

		do {

			System.out.println();
			System.out.println("1 - Alterar nome");

			System.out.println("2 - Alterar privacidade");

			System.out.println("3 - Adicionar colaborador");

			System.out.println("0 - Voltar");

			System.out.print("Escolha: ");

			opcao = lerInteiro();

			switch (opcao) {

			case 1:

				System.out.print("Novo nome: ");

				String novoNome = scn.nextLine();

				if (novoNome.trim().isEmpty()) {

					System.out.println("Nome inválido.");

					break;
				}

				projeto.setNmProjeto(novoNome);

				if (Sistema.getInstance().alterarProjeto(projeto)) {

					System.out.println("Nome alterado com sucesso.");

				} else {

					System.out.println("Falha ao alterar projeto.");
				}

				break;

			case 2:

				System.out.println("1 - Publico");

				System.out.println("2 - Privado");

				System.out.print("Escolha: ");

				int privacidade = lerInteiro();

				if (privacidade == 1) {

					projeto.setPrivacidade("Publico");

				} else if (privacidade == 2) {

					projeto.setPrivacidade("Privado");

				} else {

					System.out.println("Opção inválida.");

					break;
				}

				if (Sistema.getInstance().alterarProjeto(projeto)) {

					System.out.println("Privacidade alterada com sucesso.");

				} else {

					System.out.println("Falha ao alterar projeto.");
				}

				break;

			case 3:
				addColaborador();
				break;

			case 0:
				break;

			default:
				System.out.println("Opção inválida.");
			}

		} while (opcao != 0);
	}

	public void excluir() {

		System.out.println();
		System.out.println("--- Excluir projeto ---");

		System.out.print("Código do projeto: ");

		int codigoProjeto = lerInteiro();

		Projeto projeto = Sistema.getInstance().buscarProjetoPorCodigoPrivado(codigoProjeto);

		if (projeto == null) {

			System.out.println("Projeto não encontrado.");

			return;
		}

		Usuario usuarioLogado = Sistema.getInstance().getUsuarioLogado();

		if (usuarioLogado == null || projeto.getProprietario().getCdUsuario() != usuarioLogado.getCdUsuario()) {

			System.out.println("Somente o proprietário pode excluir o projeto.");

			return;
		}

		System.out.print("Deseja realmente excluir? " + "(1-Sim / 2-Não): ");

		int opcao = lerInteiro();

		if (opcao == 1) {

			if (Sistema.getInstance().excluirProjeto(codigoProjeto, usuarioLogado)) {

				System.out.println("Projeto excluído com sucesso.");

			} else {

				System.out.println("Falha ao excluir projeto.");
			}
		}
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
