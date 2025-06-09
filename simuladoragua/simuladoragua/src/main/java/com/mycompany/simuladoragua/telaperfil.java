package com.mycompany.simuladoragua;

import javax.swing.*;
import java.awt.*;

public class telaperfil extends JFrame {

    public telaperfil(String nomeUsuario, String emailUsuario, double consumoAguaTotal) {
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
                new telainicio().setVisible(true);
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
        SwingUtilities.invokeLater(() -> new telaperfil("shadow", "shadow@gmail.com", 235.6));
    }
}
