/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        conn = new conectaDAO().connectDB();
        try {
            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            int rowsAffected = prep.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Cadastro realizado com sucesso!");
                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
            } else {
                System.out.println("Falha ao realizar cadastro.");
                JOptionPane.showMessageDialog(null, "Falha ao realizar cadastro.");
            }
        } catch (Exception erro){
            System.out.println("Erro ao cadastrar produto: " + erro.getMessage());
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        ArrayList<ProdutosDTO> listagem = new ArrayList<>();
        conn = new conectaDAO().connectDB();
        try {
            String sql = "SELECT * FROM produtos";
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            while (resultset.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("status"));
                listagem.add(produto);
            }
        } catch (Exception erro){
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + erro.getMessage());
        }
        return listagem;
    }
    
    
    //Nova adição da branch melhorias
    public void venderProduto(Integer id) {
    conn = new conectaDAO().connectDB();
    try {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        prep = conn.prepareStatement(sql);
        prep.setInt(1, id);
        int rowsAffected = prep.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Produto vendido com sucesso!");
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
        } else {
            System.out.println("Falha ao vender o produto.");
            JOptionPane.showMessageDialog(null, "Falha ao vender o produto.");
        }
    } catch (Exception erro){
        System.out.println("Erro ao vender produto: " + erro.getMessage());
    }
}

    public ArrayList<ProdutosDTO> listarProdutosVendidos(){
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    conn = new conectaDAO().connectDB();
    try {
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();
        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            listagem.add(produto);
        }
    } catch (Exception erro){
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos vendidos: " + erro.getMessage());
    }
    return listagem;
}

    
    
    
        
}

