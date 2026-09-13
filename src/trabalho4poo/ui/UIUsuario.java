Spackage trabalho4poo.ui;

import trabalho4poo.ui.*;


import java.util.List;
import java.util.Scanner;
import trabalho4poo.negocio.*;
public class UIUsuario {
	
	static Scanner scn = new Scanner(System.in);
	static Scanner scl = new Scanner(System.in);
	
	public void listar(List <Usuario> listaCopia) {
		System.out.println("\n--- Lista de usuarios cadastrados: ---");
		System.out.println("|COD.\t |NOME\t");
		for (int i = 0; i < listaCopia.size(); i++) {
			if (listaCopia.get(i) != null) {
				System.out.printf("%-8s %-20s%n", listaCopia.get(i).getCdUsuario(),
						listaCopia.get(i).getNmUsuario());
			}
		}
	}
	
	public void login() {
		System.out.println("\n================================================================");
		System.out.println("----- LOGIN: ----");

		System.out.print("\nNome do usuário: ");
		scn.nextLine();
		String nomeUsuario1 = scn.nextLine();

		System.out.print("\nSenha: ");
		String senha1 = scn.nextLine();

		Usuario usuarioLogado = Sistema.getInstance().autenticarLogin(nomeUsuario1, senha1);
		if (usuarioLogado != null) {
			System.out.println("Perfil encontrado!");
		} else {
			System.out.println("Perfil nao encontrado ou inexistente");
		}
	}
	
	public void alterar() {
		Usuario uAlterado = Sistema.getInstance().getUsuarioLogado();
		int escolha = 0;
		
		String nomeUsuario = null;
		do {
			System.out.println("Alterar nome de usuário? 1- sim/ 2- não");
			escolha = scn.nextInt();
			if (escolha == 1) {
				System.out.print("Novo Nome Login [" + Sistema.getInstance().getUsuarioLogado().getNmUsuario() + "]: ");
				uAlterado.setNmUsuario(scl.next());
			}
			
		}while(escolha < 1 || escolha > 2 && !uAlterado.setNmUsuario(nomeUsuario));

		
		String senha = null;
		do {
			System.out.println("Alterar senha? 1- sim/ 2- não");
			escolha = scn.nextInt();
			if (escolha == 1) {
				System.out.print("Nova senha: ");
				uAlterado.setSenha(scl.next());
			}
		}while(escolha < 1 || escolha > 2 && !uAlterado.setSenha(senha));
		
		if (Sistema.getInstance().alterarUsuario(uAlterado))
			System.out.println("Usuário alterado com sucesso!");
		else
			System.out.println("Falha em alterar usuário.");
	}
}
