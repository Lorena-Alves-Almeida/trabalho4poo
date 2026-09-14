package trabalho4poo.negocio;

import java.util.List;

public class Sistema {

    private static Sistema instance;

    private Usuario usuarioLogado;

    private ControladorUsuario cUsuario;
    private ControladorProjeto cProjeto;

    private Sistema() {

        cUsuario = new ControladorUsuario();
        cProjeto = new ControladorProjeto();
    }

    public static Sistema getInstance() {

        if (instance == null) {
            instance = new Sistema();
        }

        return instance;
    }

    // ============================================================
    // USUÁRIOS
    // ============================================================

    public boolean addUsuario(Usuario u) {

        return cUsuario.add(u);
    }

    public Usuario buscarUsuarioPorId(int id) {

        return cUsuario.buscarPorCodigo(id);
    }

    public Usuario buscarUsuarioPorLogin(
            String nomeUsuario) {

        return cUsuario.buscarPorNome(nomeUsuario);
    }

    public boolean existeNomeUsuario(
            String nomeUsuario) {

        return cUsuario.buscarPorNome(nomeUsuario) != null;
    }

    public List<Usuario> listarUsuarios() {

        return cUsuario.listar();
    }

    public boolean alterarUsuario(
            Usuario uAlterado) {

        return cUsuario.alterar(uAlterado);
    }

    public boolean excluirUsuario(int id) {

        return cUsuario.excluir(id);
    }

	public Usuario autenticarLogin(
            String nmUsuario,
            String senha) {

        Usuario usuario =
                cUsuario.buscarPorNome(nmUsuario);

        if (usuario != null
                && usuario.getSenha().equals(senha)) {

            usuarioLogado = usuario;

            return usuario;
        }

        return null;
    }

    public Usuario getUsuarioLogado() {

        return usuarioLogado;
    }

    public void logout() {

        usuarioLogado = null;
    }

    public boolean criarProjeto(
            String nmProjeto,
            Usuario proprietario,
            String privacidade) {

        return cProjeto.add(
                nmProjeto,
                proprietario,
                privacidade);
    }

    public List<Projeto> listarProjetos() {

        return cProjeto.listar();
    }

    public Projeto buscarProjetoPorCodigo(
            int codigo) {

        return cProjeto.buscar(codigo);
    }

    public Projeto buscarProjetoPorCodigoPrivado(
            int codigo) {

        return cProjeto.buscarPrivado(codigo);
    }

    public boolean excluirProjeto(
            int codigoProjeto,
            Usuario usuarioProprietario) {

        return cProjeto.excluir(
                codigoProjeto,
                usuarioProprietario);
    }

    public boolean adicionarColaborador(
            int codigoProjeto,
            Usuario usuarioColaborador) {

        return cProjeto.addColaborador(
                codigoProjeto,
                usuarioColaborador);
    }

    public boolean adicionarColaborador(
            Usuario usuarioColaborador) {

        return cProjeto.addColaborador(
                usuarioColaborador);
    }

    public boolean alterarProjeto(
            Projeto projeto) {

        return cProjeto.alterar(projeto);
    }

    public boolean alterarCodigoProjeto(
            int codigoProjeto,
            String codigoHTML) {

        return cProjeto.alterarCodigo(
                codigoProjeto,
                codigoHTML);
    }

    // ============================================================
    // PROJETOS DE UM USUÁRIO
    // ============================================================

    public List<Projeto> buscarProjetosDoUsuario(
            Usuario usuario) {


        return cProjeto.buscarProjetosDoUsuario(
                usuario);
    }
}
