package com.projetoJAVA.view;

import connection.ConnectionFactory;
import produtoDAO.produtoDAO;
import produtos.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
public class ProdutoJFrame extends JFrame {

    private DefaultTableModel defaultTableModel;
    private JTable tabela;
    private JTextField txtProdutoID;
    private JTextField txtProdutoNome;
    private JTextField txtProdutoPreco;
    private JTextField txtProdutoQTD;
    private JButton btnSalvar;
    private JButton btnAtualizar;
    private JButton btnExcluir;
    private JButton btnLimpar;
    private produtoDAO produtoDAO;

    public ProdutoJFrame() {

        produtoDAO = new produtoDAO();

        setTitle("Cadastro de Produtos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        formPanel.add(new JLabel("produtoID:"));
        txtProdutoID = new JTextField();
        txtProdutoID.setEditable(false);
        formPanel.add(txtProdutoID);

        formPanel.add(new JLabel("produtoNome:"));
        txtProdutoNome = new JTextField();
        formPanel.add(txtProdutoNome);

        formPanel.add(new JLabel("produtoPreco:"));
        txtProdutoPreco = new JTextField();
        formPanel.add(txtProdutoPreco);

        formPanel.add(new JLabel("produtoQTD:"));
        txtProdutoQTD = new JTextField();
        formPanel.add(txtProdutoQTD);


        JPanel buttonPanel = new JPanel();
        btnSalvar = new JButton("Salvar");
        btnAtualizar = new JButton("Atualizar");
        btnExcluir = new JButton("Excluir");
        btnLimpar = new JButton("Limpar");

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnAtualizar);
        buttonPanel.add(btnExcluir);
        buttonPanel.add(btnLimpar);

        defaultTableModel = new DefaultTableModel(new Object[]{"produtoID", "produtoNome", "produtoPreco", "produtoQTD"}, 0);
        tabela = new JTable(defaultTableModel);
        JScrollPane scrollPane = new JScrollPane(tabela);

        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);


        adicionarListeners();

        atualizarTabela();

    }

    private void adicionarListeners() {

        btnSalvar.addActionListener(e -> {


            String produtoNome = txtProdutoNome.getText();
            String produtoPreco = txtProdutoPreco.getText();
            String produtoQTD = txtProdutoQTD.getText();


            if (produtoNome.isEmpty() || produtoPreco.isEmpty() || produtoQTD.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Não pode ter variaveis nulas!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Produto novoProduto = new Produto();
            novoProduto.setProdutoNome(produtoNome);
            novoProduto.setProdutoPreco(Double.parseDouble(produtoPreco));
            novoProduto.setProdutoQTD(Integer.parseInt(produtoQTD));
            produtoDAO.criarProduto(novoProduto);
            atualizarTabela();
            limparCampos();
        });


        btnAtualizar.addActionListener(e -> {

            String idStr = txtProdutoID.getText();

            if (idStr.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Este campo é obrigato!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Produto produtoAtualizado = new Produto();
            produtoAtualizado.setProdutoID(Integer.parseInt(idStr));
            produtoAtualizado.setProdutoNome(txtProdutoNome.getText());
            produtoAtualizado.setProdutoPreco(Double.parseDouble(txtProdutoPreco.getText()));
            produtoAtualizado.setProdutoQTD(Integer.parseInt(txtProdutoQTD.getText()));
            produtoDAO.update(produtoAtualizado);

            atualizarTabela();
            limparCampos();
        });

        btnExcluir.addActionListener(e -> {

            String idStr = txtProdutoID.getText();

            if (idStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Este campo é obrigatório!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir o produto?");

            if (confirm == JOptionPane.YES_OPTION) {

                produtoDAO.DeleteByID(Integer.parseInt(idStr));
                atualizarTabela();
                limparCampos();

            }

        });

        btnLimpar.addActionListener(e -> limparCampos());


        tabela.getSelectionModel().addListSelectionListener(e -> {

            int selectedRow = tabela.getSelectedRow();
            if (selectedRow != -1) {

                String produtoID = defaultTableModel.getValueAt(selectedRow, 0).toString();
                String produtoNome = defaultTableModel.getValueAt(selectedRow, 1).toString();
                String produtoPreco = defaultTableModel.getValueAt(selectedRow, 2).toString();
                String produtoQTD = defaultTableModel.getValueAt(selectedRow, 3).toString();

                txtProdutoID.setText(produtoID);
                txtProdutoNome.setText(produtoNome);
                txtProdutoPreco.setText(produtoPreco);
                txtProdutoQTD.setText(produtoQTD);

            }

        });


    }

    private void atualizarTabela() {

        defaultTableModel.setRowCount(0);
        List<Produto> produtos = produtoDAO.findAll();


        for (Produto produto : produtos) {
            defaultTableModel.addRow(new Object[]{produto.getProdutoID(), produto.getProdutoNome(), produto.getProdutoPreco(), produto.getProdutoQTD()});
        }

    }

    private void limparCampos() {
        txtProdutoID.setText("");
        txtProdutoNome.setText("");
        txtProdutoPreco.setText("");
        txtProdutoQTD.setText("");
        tabela.getSelectionModel().clearSelection();
    }

    public static void main(String[]args){

        SwingUtilities.invokeLater(() ->{

            ProdutoJFrame frame = new ProdutoJFrame();
            frame.setVisible(true);
        });


    }

}

