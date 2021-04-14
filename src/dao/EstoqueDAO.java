package dao;

import conexao.Conexao;
import estoque.Estoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EstoqueDAO {

    private static Connection conexao;

    private static void estabelecerConexao() throws SQLException, ClassNotFoundException {
        EstoqueDAO.conexao = Conexao.getConexao();
    }

    public EstoqueDAO() {
        this.conexao = Conexao.getConexao();
    }

    public static void adicionar(Estoque estoque) throws SQLException, ClassNotFoundException {
        //Adicionar um novo elemento.
        estabelecerConexao();
        PreparedStatement estado;
        ResultSet resultadoBusca;
        ArrayList<Estoque> listaEstoqueCadastrados = null;
        listaEstoqueCadastrados = buscarTodosProdutos();
        for (int i = 0; i < listaEstoqueCadastrados.size(); i++) {
            if (listaEstoqueCadastrados.get(i).getCodigo().equals(estoque.getCodigo())) {
                int novaQtde = listaEstoqueCadastrados.get(i).getQtdeProduto() + estoque.getQtdeProduto();
                String msgSQL = "update estoque set qtdeProduto=? where codigo=?";
                try {
                    estado = EstoqueDAO.conexao.prepareStatement(msgSQL);
                    estado.setInt(1, novaQtde);
                    estado.setString(2, estoque.getCodigo());
                    estado.execute();
                    estado.close();
                    System.out.print("\n Informação atualizada com sucesso!");
                } catch (SQLException ex) {
                    System.out.println("\n Erro Conexão: " + ex.toString());
                }
            } else {
                String msgSQL = "insert into estoque(codigo,produto,valor,qtdeProduto) values(?,?,?,?)";
                try {
                    estado = EstoqueDAO.conexao.prepareStatement(msgSQL);
                    estado.setString(1, estoque.getCodigo());
                    estado.setString(2, estoque.getProduto());
                    estado.setFloat(3, estoque.getValor());
                    estado.setInt(4, estoque.getQtdeProduto());
                    estado.execute();
                    estado.close();
                    System.out.print("\n Informação adicionada com sucesso!");
                } catch (SQLException ex) {
                    System.out.println("\n Erro Conexão: " + ex.toString());
                }
            }
        }
    }

    public static ArrayList<Estoque> buscarCodigo(String codigo) {//Busca por um codigo específico.
        PreparedStatement estado;
        ResultSet resultadoBusca;
        ArrayList<Estoque> listaEstoqueCadastrados = null;
        try {
            String msgSQL = ("select * from estoque where codigo = ?");
            estado = EstoqueDAO.conexao.prepareStatement(msgSQL);
            estado.setString(1, codigo);
            resultadoBusca = estado.executeQuery();
            listaEstoqueCadastrados = new ArrayList();
            while (resultadoBusca.next()) {
                Estoque estoque = new Estoque();
                estoque.setCodigo(resultadoBusca.getString("codigo"));
                estoque.setProduto(resultadoBusca.getString("produto"));
                estoque.setValor(resultadoBusca.getFloat("valor"));
                estoque.setQtdeProduto(resultadoBusca.getInt("qtdeProduto"));
                listaEstoqueCadastrados.add(estoque);
            }
            resultadoBusca.close();
            estado.close();
        } catch (SQLException ex) {
            System.out.println("\n Erro Conexão: " + ex.toString());
        }
        return (listaEstoqueCadastrados);
    }

    public static ArrayList<Estoque> buscarTodosProdutos() throws SQLException, ClassNotFoundException {
        estabelecerConexao();
        PreparedStatement estado;
        ResultSet resultadoBusca;
        ArrayList<Estoque> listaEstoqueCadastrados = null;
        String operacao = "select * from estoque";
        estado = EstoqueDAO.conexao.prepareStatement(operacao);
        resultadoBusca = estado.executeQuery();
        resultadoBusca = estado.executeQuery();
        listaEstoqueCadastrados = new ArrayList();
        while (resultadoBusca.next()) {
            Estoque estoque = new Estoque();
            estoque.setCodigo(resultadoBusca.getString("codigo"));
            estoque.setProduto(resultadoBusca.getString("produto"));
            estoque.setValor(resultadoBusca.getFloat("valor"));
            estoque.setQtdeProduto(resultadoBusca.getInt("qtdeProduto"));
            listaEstoqueCadastrados.add(estoque);
        }
        resultadoBusca.close();
        estado.close();
        return (listaEstoqueCadastrados);
    }

    public static int vender(String codigo, int qtde) throws SQLException, ClassNotFoundException {
        PreparedStatement estado;
        ResultSet resultadoBusca;
        ArrayList<Estoque> listaEstoqueCadastrados = null;
        listaEstoqueCadastrados = buscarTodosProdutos();
        Estoque estoque = new Estoque();
        for (int i = 0; i < listaEstoqueCadastrados.size(); i++) {
            if (listaEstoqueCadastrados.get(i).getCodigo().equals(codigo)) {
                if (listaEstoqueCadastrados.get(i).getQtdeProduto() == 0) {
                    return 0;
                } else if (listaEstoqueCadastrados.get(i).getQtdeProduto() < qtde) {
                    return 0;
                }
                else {
                    estoque = listaEstoqueCadastrados.get(i);
                }
            }
        }
        int novaQtde = estoque.getQtdeProduto() - qtde;
        String msgSQL = "update estoque set qtdeProduto=? where codigo=?";
        try {
            estado = EstoqueDAO.conexao.prepareStatement(msgSQL);
            estado.setInt(1, novaQtde);
            estado.setString(2, codigo);
            estado.execute();
            estado.close();
            System.out.print("\n Informação atualizada com sucesso!");
        } catch (SQLException ex) {
            System.out.println("\n Erro Conexão: " + ex.toString());
        }
        return 1;
    }

    public static float saldo() throws SQLException, ClassNotFoundException {
        PreparedStatement estado;
        ResultSet resultadoBusca;
        float saldo = 0;
        ArrayList<Estoque> listaEstoqueCadastrados = null;
        listaEstoqueCadastrados = buscarTodosProdutos();
        for (int i = 0; i < listaEstoqueCadastrados.size(); i++) {
            saldo = saldo + listaEstoqueCadastrados.get(i).getValor();
        }
        return saldo;
    }
}
