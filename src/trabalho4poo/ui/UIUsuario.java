package trabalho4poo.ui;

import trabalho4poo.ui.*;

import java.util.List;

import trabalho4poo.negocio.*;
public class UIUsuario {
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
}
