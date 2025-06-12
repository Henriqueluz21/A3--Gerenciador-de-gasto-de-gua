package com.mycompany.simuladoragua;

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class telainicio extends JFrame {

    private int usuarioId;
    

    public telainicio(int usuarioId) {

        this.usuarioId = usuarioId;
        
        setTitle("AquaLerta - Início");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

       //BOTAO FORA MENULATERAL
        JButton btnToggleMenu = new JButton("---"); // icone menu
        btnToggleMenu.setFocusPainted(false);
        btnToggleMenu.setBackground(new Color(125,181,180));
        btnToggleMenu.setForeground(Color.WHITE);
        btnToggleMenu.setFont(new Font("Arial", Font.BOLD, 18));
        btnToggleMenu.setMaximumSize(new Dimension(50,40));
        btnToggleMenu.setAlignmentX(Component.LEFT_ALIGNMENT); 
        
       // MENU LATERAL ---------------------------------------------------

        JPanel painelMenu = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelMenu.setBackground(Color.WHITE);

        JPanel menuLateral = new JPanel();
        menuLateral.setLayout(new BoxLayout(menuLateral, BoxLayout.Y_AXIS)); // azul claro
        menuLateral.setBackground(new Color(125, 181, 180));
        menuLateral.setPreferredSize(new Dimension(200, getHeight()));

        // BOTÃO MENU SUPERIOR (---)
        btnToggleMenu.setFocusPainted(false);
        btnToggleMenu.setBackground(new Color(125, 181, 180));
        btnToggleMenu.setForeground(Color.WHITE);
        btnToggleMenu.setFont(new Font("Arial", Font.BOLD, 18));
        btnToggleMenu.setMaximumSize(new Dimension(50, 40));
        btnToggleMenu.setAlignmentX(Component.LEFT_ALIGNMENT);

        // AÇÃO BOTÃO MENU
        btnToggleMenu.addActionListener(e -> {
            boolean visivel = menuLateral.isVisible();
            menuLateral.setVisible(!visivel);
            this.revalidate();
            this.repaint();
            
        });

        // ADICIONAR BOTÃO AO MENU
        menuLateral.add(Box.createRigidArea(new Dimension(0, 10)));
        menuLateral.add(btnToggleMenu);

// BOTÕES DO MENU LATERAL
String[] opcoesMenu = {"Gastos", "Dicas", "Perfil"};
for (String opcao : opcoesMenu) {
    JButton btn = new JButton(opcao);
    btn.setMaximumSize(new Dimension(180, 40));
    btn.setAlignmentX(Component.CENTER_ALIGNMENT);
    btn.setBackground(new Color(125, 181, 180));
    btn.setForeground(Color.WHITE);
    btn.setFont(new Font("Arial", Font.BOLD, 14)); // negrito e tamanho da letra
    btn.setFocusPainted(false);
    btn.setBorderPainted(false);

    // AÇÃO DOS BOTÕES MENU
    String finalOpcao = opcao;
    btn.addActionListener(e -> {
        switch (finalOpcao) {
            case "Gastos":
                new telagastos(usuarioId).setVisible(true);
                dispose();
                break;
            case "Dicas":
                JOptionPane.showMessageDialog(this, "Abrir tela de Dicas");
                //new teladicas().setVisible(true);
                dispose();
                break;
            case "Perfil":
            {
                try {
                    new telaperfil("Usuário Exemplo", "email@exemplo.com", 123.45, usuarioId).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(telainicio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(telainicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
                dispose();
                break;

        }
    });

    menuLateral.add(Box.createRigidArea(new Dimension(0, 15))); // espaçamento
    menuLateral.add(btn);
}

        
 
        
        // PAINEL CENTRAL
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(Color.WHITE);

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


        // Frase central verde
        JLabel frase = new JLabel("<html><center>Controle seu uso de água com inteligência e contribua<br>para um futuro mais consciente e sustentável.</center></html>", JLabel.CENTER);
        frase.setFont(new Font("SansSerif", Font.BOLD, 16));
        frase.setForeground(new Color(0, 230, 118)); // verde
        frase.setBorder(BorderFactory.createEmptyBorder(0,0,20,0));
        painelCentral.add(frase, BorderLayout.CENTER);

        
        
        
        // Botões das atividades
        JPanel panelBotoes = new JPanel(new GridLayout(3, 3, 10, 10));
        panelBotoes.setBackground(Color.WHITE);
        panelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // margem interna

        String[] atividades = {"Banho", "Lavar Louça", "Escovar os Dentes", "Lavar Roupa", "Descargas", "Regar Plantas", "Lavar Quintal", "Outros"};

        for (String atividade : atividades) {

            JButton botao = new JButton(atividade);
            botao.setPreferredSize(new Dimension(0, 65));
            botao.setBackground(new Color(125, 181, 180));
            botao.setForeground(Color.WHITE);
            botao.setFont(new Font("Arial", Font.BOLD, 14));
            botao.setFocusPainted(false);

            botao.addActionListener(e -> {
                switch (atividade) {
                    case "Banho":
                        new telabanho(usuarioId).setVisible(true);
                        this.dispose();
                        break;
                    case "Lavar Louça":
                        new telalavarlouca(usuarioId).setVisible(true);
                        this.dispose();
                        break;
                    case "Escovar os Dentes":
                        new telaescovardentes(usuarioId).setVisible(true);
                        break;

                    // outros casos botões
                    default:
                        JOptionPane.showMessageDialog(this, "Atividade selecionada: " + atividade);
                        break;
                }
            });

    panelBotoes.add(botao);
}

painelCentral.add(panelBotoes, BorderLayout.SOUTH);

        
        
        // Adiciona menu lateral e painel central na janela
        add(menuLateral, BorderLayout.WEST);
        add(painelCentral, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        
    }

    private void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}