package dao;

import conexao.Conexao;
import estoque.Estoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EstoqueDAO {

    Connection conexao;

    public EstoqueDAO() {
        this.conexao = Conexao.getConexao();
    }

    public void adicionar(Estoque estoque) {
        //Adicionar um novo elemento.
        PreparedStatement estado;
        ResultSet resultadoBusca;
        ArrayList<Estoque> listaEstoqueCadastrados = null;
        try {
            estado = this.conexao.prepareStatement("select * from estoque");
            resultadoBusca = estado.executeQuery();
            listaEstoqueCadastrados = new ArrayList();
            while (resultadoBusca.next()) {
                Estoque estoqueAntigo = new Estoque();
                estoqueAntigo.setCodigo(resultadoBusca.getString("codigo"));
                estoqueAntigo.setProduto(resultadoBusca.getString("produto"));
                estoqueAntigo.setValor(resultadoBusca.getFloat("valor"));
                estoqueAntigo.setQtdeProduto(resultadoBusca.getInt("qtdeProduto"));
                listaEstoqueCadastrados.add(estoqueAntigo);
            }
            resultadoBusca.close();
            estado.close();
        } catch (SQLException ex) {
            System.out.println("\n Erro Conexão: " + ex.toString());
        }
        for (Estoque listaEstoqueCadastrado : listaEstoqueCadastrados) {
            if (listaEstoqueCadastrado.getCodigo().equals(estoque.getCodigo())) {
                int novaQtde = listaEstoqueCadastrado.getQtdeProduto() + estoque.getQtdeProduto();
                String msgSQL = "update estoque set qtdeProduto=? where codigo=?";
                try {
                    estado = this.conexao.prepareStatement(msgSQL);
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
                    estado = this.conexao.prepareStatement(msgSQL);
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

    public ArrayList<Estoque> buscarCodigo(String codigo) {//Busca por um codigo específico.
        PreparedStatement estado;
        ResultSet resultadoBusca;
        ArrayList<Estoque> listaEstoqueCadastrados = null;
        try {
            estado = this.conexao.prepareStatement("select * from estoque where codigo = ?");
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

    public ArrayList<Estoque> buscarTodosProdutos() {
        PreparedStatement estado;
        ResultSet resultadoBusca;
        ArrayList<Estoque> listaEstoqueCadastrados = null;
        try {
            estado = this.conexao.prepareStatement("select * from estoque");
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

    public int vender(String codigo, int qtde) {
        PreparedStatement estado;
        ResultSet resultadoBusca;
        ArrayList<Estoque> listaEstoqueCadastrados = null;
        try {
            estado = this.conexao.prepareStatement("select * from estoque");
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
        for (Estoque listaEstoqueCadastrado : listaEstoqueCadastrados) {
            if (listaEstoqueCadastrado.getCodigo().equals(codigo)) {
                if ((listaEstoqueCadastrado.getQtdeProduto() == 0) || (listaEstoqueCadastrado.getQtdeProduto() < qtde)) {
                    return 0;
                } else {
                    int novaQtde = listaEstoqueCadastrado.getQtdeProduto() - qtde;
                    String msgSQL = "update estoque set qtdeProduto=? where codigo=?";
                    try {
                        estado = this.conexao.prepareStatement(msgSQL);
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
            }
            else {
                return 0;
            }
        }
        return 0;
    }
}