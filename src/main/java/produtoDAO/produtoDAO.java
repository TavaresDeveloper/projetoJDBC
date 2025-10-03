package produtoDAO;

import connection.ConnectionFactory;
import jdk.internal.org.jline.terminal.TerminalBuilder;
import produtos.Produto;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class produtoDAO {

    public void criarProduto(Produto produto){

        String sql = "insert into produtos(produtoNome, produtoPreco, produtoQTD) values (?, ?, ?)";

        try(Connection conn= ConnectionFactory.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)){

            pst.setInt(1, produto.getProdutoID());
            pst.setString(2,produto.getProdutoNome());
            pst.setDouble(3, produto.getProdutoPreco());
            pst.setInt(4, produto.getProdutoQTD());
            pst.executeUpdate();
            System.out.println("Produto cadastrado com Sucesso!");

        } catch (SQLException e) {
            System.err.println("Falha ao cadastrar produto." + e.getMessage());
        }

    }

     public List<Produto> findAll(){

          String sql = "select * from produtos";

          List<Produto> produtos = new ArrayList<>();


          try(Connection con = ConnectionFactory.getConnection();
              PreparedStatement pst = con.prepareStatement(sql);
              ResultSet rs = pst.executeQuery();) {

              while (rs.next()){

                  Produto produto = new Produto();
                   produto.setProdutoID(rs.getInt("produtoID"));
                   produto.setProdutoNome(rs.getString("produtoNome"));
                   produto.setProdutoPreco(rs.getDouble("produtoPreco"));
                   produto.setProdutoQTD(rs.getInt("produtoQTD"));


              }
              System.out.println("Produtos listdos com sucesso");
          }catch (SQLException e){

              System.err.println("Erro ao listar os produtos." + e.getMessage());

          }

         return produtos;

     }

     public void update(Produto produto){

          String sql = "update produtos set produtoNome = ?, produtoPreco = ?, produtoQTD = ? where produtoID = ?";

             try(Connection con = ConnectionFactory.getConnection();
                 PreparedStatement pst = con.prepareStatement(sql);){

                 pst.setInt(1, produto.getProdutoID());
                 pst.setString(2, produto.getProdutoNome());
                 pst.setDouble(3, produto.getProdutoPreco());
                 pst.setInt(4, produto.getProdutoQTD());
                 pst.executeUpdate();

             }catch (SQLException e){

                 System.err.println("Erro ao atualizar os produtos." + e.getMessage());

             }



     }

     public void DeleteByID(int produtoID){

        String sql = "Delete from produtos where produtoID = ?";

        try(Connection con = ConnectionFactory.getConnection();
           PreparedStatement pst = con.prepareStatement(sql)){


            pst.setInt(1, produtoID);
             pst.executeUpdate();
             System.out.println("Produto deletado com sucesso");
        }catch (SQLException e){
            System.err.println("Falha ao deletar produto" + e.getMessage());
        }


    }

}
