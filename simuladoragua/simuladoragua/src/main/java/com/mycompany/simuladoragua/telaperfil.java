package com.mycompany.simuladoragua;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class telaperfil extends JFrame {

    private int usuarioId;
    
    String nomeUsuario = "";
    String emailUsuario = "";
    int consumoAguaTotal = 0;
    
    public telaperfil(String nomeUsuario, String emailUsuario, int consumoAguaTotal, int usuarioId) throws SQLException, ClassNotFoundException {
        
        this.usuarioId = usuarioId;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/gerenciadorAgua", "root", "Penelope_11");
            
            String sql = "SELECT nome, email FROM usuario where id = ?";
            String sql2 = "SELECT banhoDia FROM consumoChuveiro where id = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            PreparedStatement stmt2 = conexao.prepareStatement(sql2);
            stmt.setInt(1,usuarioId);
            stmt2.setInt(1, usuarioId);
            
            ResultSet rs = stmt.executeQuery();
            ResultSet rs2 = stmt2.executeQuery();
            
            if (rs.next()){
                nomeUsuario = rs.getString("nome");
                emailUsuario = rs.getString("email");
            }else{
                JOptionPane.showMessageDialog(null, "Usuario não encontro.");
                dispose();
                return;
            }
            
            if (rs2.next()){
                consumoAguaTotal = rs2.getInt("banhoDia");
            }
            
            rs.close();
            rs2.close();
            stmt.close();
            stmt2.close();
            conexao.close();
            
        }catch(ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null, "Usuario não encontro." + ex.getMessage());
            dispose();
            return;
        }
        
       
 
        setTitle("AquaLerta - Perfil");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // BOTÃO MENU SUPERIOR (---)
        JButton btnToggleMenu = new JButton("---");
        btnToggleMenu.setFocusPainted(false);
        btnToggleMenu.setBackground(new Color(125,181,180));
        btnToggleMenu.setForeground(Color.WHITE);
        btnToggleMenu.setFont(new Font("Arial", Font.BOLD, 18));
        btnToggleMenu.setMaximumSize(new Dimension(50,40));
        btnToggleMenu.setAlignmentX(Component.LEFT_ALIGNMENT);

        // MENU LATERAL
        JPanel menuLateral = new JPanel();
        menuLateral.setLayout(new BoxLayout(menuLateral, BoxLayout.Y_AXIS));
        menuLateral.setBackground(new Color(125, 181, 180));
        menuLateral.setPreferredSize(new Dimension(200, getHeight()));

        // Ação do botão do menu
        btnToggleMenu.addActionListener(e -> {
            boolean visivel = menuLateral.isVisible();
            menuLateral.setVisible(!visivel);
            this.revalidate();
            this.repaint();
        });

        // Adiciona botão ao menu lateral
        menuLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        menuLateral.add(btnToggleMenu);

        // Botões do menu lateral
String[] opcoesMenu = {"Gastos", "Dicas", "Início"};
for (String opcao : opcoesMenu) {
    JButton btn = new JButton(opcao);
    btn.setMaximumSize(new Dimension(180, 40));
    btn.setAlignmentX(Component.CENTER_ALIGNMENT);
    btn.setBackground(new Color(125, 181, 180));
    btn.setForeground(Color.WHITE);
    btn.setFont(new Font("Arial", Font.BOLD, 14));
    btn.setFocusPainted(false);
    btn.setBorderPainted(false);

    // ActionListener - é a ação que cada botão vai executar
    btn.addActionListener(e -> {
        switch (opcao) {
            case "Gastos":
                dispose();
                break;
            case "Dicas":
                dispose();
                break;
            case "Início":
                new telainicio(usuarioId).setVisible(true);
                dispose();
                break;
        }
    });

    menuLateral.add(Box.createRigidArea(new Dimension(0, 15)));
    menuLateral.add(btn);
}

        // PAINEL CENTRAL
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBackground(Color.WHITE);
        painelCentral.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Logo
        // Carrega a imagem
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/com/mycompany/simuladoragua/imagens/logo-aqualerta.png"));

        // Redimensiona a imagem para 200x100
        Image scaledImage = logoIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);

        // Cria um novo ImageIcon com a imagem redimensionada
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        // Cria o JLabel com a imagem redimensionada
        JLabel logoLabel = new JLabel(resizedIcon, JLabel.CENTER);

        // Adiciona ao painel central
        painelCentral.add(logoLabel, BorderLayout.NORTH);

        // Título
        JLabel labelTitulo = new JLabel("Perfil do Usuário");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        labelTitulo.setForeground(new Color(0, 153, 136));
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCentral.add(labelTitulo);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        // Informações do usuário
        JLabel labelNome = new JLabel("Nome: " + nomeUsuario);
        JLabel labelEmail = new JLabel("Email: " + emailUsuario);
        JLabel labelConsumo = new JLabel("Registro total de água: " + consumoAguaTotal + " litros");

        JLabel[] labels = {labelNome, labelEmail, labelConsumo};
        for (JLabel label : labels) {
            label.setFont(new Font("SansSerif", Font.PLAIN, 16));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            painelCentral.add(label);
            painelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        // Adiciona componentes na janela
        add(menuLateral, BorderLayout.WEST);
        add(painelCentral, BorderLayout.CENTER);

        setVisible(true);
    }

    // Para testar a tela
    public static void main(String[] args) {
        
    }

    
}
