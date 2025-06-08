package com.mycompany.simuladoragua;

import javax.swing.*;
import java.awt.*;

public class telabanho extends JFrame {

    public telabanho() {
        setTitle("AquaLerta - Banho");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // BOTAO FORA MENULATERAL
        JButton btnToggleMenu = new JButton("MENU");
        btnToggleMenu.setFocusPainted(false);
        btnToggleMenu.setBackground(new Color(125, 181, 180));
        btnToggleMenu.setForeground(Color.WHITE);
        btnToggleMenu.setFont(new Font("Arial", Font.BOLD, 18));
        btnToggleMenu.setMaximumSize(new Dimension(50, 40));
        btnToggleMenu.setAlignmentX(Component.LEFT_ALIGNMENT);

        // MENU LATERAL ---------------------------------------------------
        JPanel painelMenu = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelMenu.setBackground(Color.WHITE);

        JPanel menuLateral = new JPanel();
        menuLateral.setLayout(new BoxLayout(menuLateral, BoxLayout.Y_AXIS));
        menuLateral.setBackground(new Color(125, 181, 180));
        menuLateral.setPreferredSize(new Dimension(200, getHeight()));

        btnToggleMenu.addActionListener(e -> {
            boolean visivel = menuLateral.isVisible();
            menuLateral.setVisible(!visivel);
            this.revalidate();
            this.repaint();
        });

        menuLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        menuLateral.add(btnToggleMenu);

        String[] opcoesMenu = {"Histórico de Gastos", "Gastos", "Dicas", "Perfil"};
        for (String opcao : opcoesMenu) {
            JButton btn = new JButton(opcao);
            btn.setMaximumSize(new Dimension(180, 40));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setBackground(new Color(125, 181, 180));
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Arial", Font.BOLD, 14));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            menuLateral.add(Box.createRigidArea(new Dimension(0, 15)));
            menuLateral.add(btn);
        }

        // PAINEL CENTRAL
        JPanel painelCentral = new JPanel();
        painelCentral.setBackground(Color.WHITE);
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));

        // Logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/com/mycompany/simuladoragua/imagens/logo-aqualerta.png"));
        Image scaledImage = logoIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(resizedIcon, JLabel.CENTER);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCentral.add(Box.createVerticalStrut(10));
        painelCentral.add(logoLabel);

        // Frase
        JLabel frase = new JLabel("<html><center>Controle seu uso de água com inteligência e contribua<br>para um futuro mais consciente e sustentável.</center></html>", JLabel.CENTER);
        frase.setFont(new Font("SansSerif", Font.BOLD, 16));
        frase.setForeground(new Color(0, 230, 118));
        frase.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCentral.add(Box.createVerticalStrut(10));
        painelCentral.add(frase);

        // Painel do contador visual
        JPanel painelContador = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        painelContador.setBackground(Color.WHITE);

        JButton btnMais = new JButton("+");
        btnMais.setPreferredSize(new Dimension(50, 50));
        btnMais.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel contadorLabel = new JLabel("0", SwingConstants.CENTER);
        contadorLabel.setPreferredSize(new Dimension(60, 60));
        contadorLabel.setFont(new Font("Arial", Font.BOLD, 24));
        contadorLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        contadorLabel.setOpaque(true);
        contadorLabel.setBackground(Color.WHITE);

        JButton btnMenos = new JButton("-");
        btnMenos.setPreferredSize(new Dimension(50, 50));
        btnMenos.setFont(new Font("Arial", Font.BOLD, 24));

        painelContador.add(btnMais);
        painelContador.add(contadorLabel);
        painelContador.add(btnMenos);

        painelCentral.add(Box.createVerticalStrut(10));
        painelCentral.add(painelContador);

        // Texto para informar onde registrar o dado e unidade de medida
        JLabel consumoLabel = new JLabel("consumo em minutos:");
        consumoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        consumoLabel.setForeground(Color.GRAY);
        consumoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCentral.add(consumoLabel);

        // Botão de registro
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotoes.setBackground(Color.WHITE);
        panelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton btnBanho = new JButton("Registrar Banho");
        btnBanho.setPreferredSize(new Dimension(200, 50));
        btnBanho.setBackground(new Color(125, 181, 180));
        btnBanho.setForeground(Color.WHITE);
        btnBanho.setFont(new Font("Arial", Font.BOLD, 14));
        btnBanho.setFocusPainted(false);
        btnBanho.addActionListener(e -> JOptionPane.showMessageDialog(this, "Banho registrado com sucesso!"));

        panelBotoes.add(btnBanho);
        painelCentral.add(panelBotoes);

        // Adiciona os painéis principais
        add(menuLateral, BorderLayout.WEST);
        add(painelCentral, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(telabanho::new);
    }
}
