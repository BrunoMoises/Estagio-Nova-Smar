package principal;

import dao.EstoqueDAO;
import estoque.Estoque;
import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
       TelaCadastro tela = new TelaCadastro();
       tela.setTitle("Controle Estoque - Cadastro");
       tela.setVisible(true);
       tela.setLocationRelativeTo(null);
        
        //EstoqueDAO estoqueDAO = new EstoqueDAO(); 
        //ArrayList<Estoque> listaEstoques;
        
        //Estoque estoque = new Estoque("1324","Bico",10,5);
        //estoqueDAO.adicionar(estoque);
         
        //System.out.println("\n FIM.");
    }
}