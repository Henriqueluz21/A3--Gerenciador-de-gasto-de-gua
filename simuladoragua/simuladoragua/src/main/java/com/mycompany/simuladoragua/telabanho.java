package com.mycompany.simuladoragua;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class telabanho extends JFrame {
    private int contador = 0;
    public int consumoMin = 0;
    
    private int usuarioId;
    private int consumoAguaTotal;
    
    public telabanho(int usuarioId) {
        
        this.usuarioId = usuarioId;
        
        setTitle("AquaLerta - Banho");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        
        // BOTAO FORA MENU LATERAL (toggle)
        JButton btnToggleMenu = new JButton("---");
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

        // Menu completo igual às outras telas, incluindo "Histórico de Gastos" e "Início"
        String[] opcoesMenu = {"Início","Gastos", "Dicas", "Perfil"};
        for (String opcao : opcoesMenu) {
            JButton btn = new JButton(opcao);
            btn.setMaximumSize(new Dimension(180, 40));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setBackground(new Color(125, 181, 180));
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Arial", Font.BOLD, 14));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);

            // Ações dos botões do menu
            btn.addActionListener(e -> {
                switch (opcao) {
                    case "Início":
                        new telainicio(usuarioId).setVisible(true);
                        this.dispose();
                        break;
                    case "Gastos":
                        new telagastos(usuarioId).setVisible(true);
                        this.dispose();
                        break;
                    case "Dicas":
                        JOptionPane.showMessageDialog(this, "Abrir tela de Dicas (não implementado)");
                        // ex: new teladicas().setVisible(true);
                        this.dispose();
                        break;
                    case "Perfil":
                    {
                        try {
                            new telaperfil("Usuário Exemplo", "email@exemplo.com", consumoAguaTotal , usuarioId).setVisible(true);
                        } catch (SQLException ex) {
                            Logger.getLogger(telabanho.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(telabanho.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        this.dispose();
                        break;

                }
            });

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

        btnMais.addActionListener(e -> {
            contador++;
            contadorLabel.setText(String.valueOf(contador));
        });
        
        btnMenos.addActionListener(e -> {
            if (contador > 0) contador--; 
            contadorLabel.setText(String.valueOf(contador));
        });
        
        
        painelCentral.add(Box.createVerticalStrut(10));
        painelCentral.add(painelContador);
        
        JButton btnBanho = new JButton("Registrar Banho");
        
        // Texto para informar o dado e unidade de medida
        JLabel consumoLabel = new JLabel("consumo em minutos:" + consumoMin + "L" );
        consumoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        consumoLabel.setForeground(Color.GRAY);
        consumoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelCentral.add(consumoLabel);
        
        btnBanho.addActionListener(e -> {
            consumoMin = contador * 12;
            consumoLabel.setText("consumo em minutos: " + consumoMin + "L");
            Random rand = new Random();
            int idAleatorio = rand.nextInt(99000) + 1000;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/gerenciadorAgua", "root", "Penelope_11");

                String sql = "INSERT INTO consumoChuveiro (Id, banhoDia, tempoGasto, UsuarioId) VALUES (?, ?, ?, ?)";
                java.sql.PreparedStatement stmt = conexao.prepareStatement(sql);

                stmt.setInt(1, idAleatorio);               
                stmt.setInt(2, consumoMin);               
                stmt.setInt(3, contador);                 
                stmt.setInt(4, usuarioId);                        
                

                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Consumo registrado com sucesso!");

                conexao.close();
            } catch (Exception ex) {
                 ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Erro ao registrar banho: " + ex.getMessage());
            }
});

        
        // Botão de registro
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBotoes.setBackground(Color.WHITE);
        panelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        
        btnBanho.setPreferredSize(new Dimension(200, 50));
        btnBanho.setBackground(new Color(125, 181, 180));
        btnBanho.setForeground(Color.WHITE);
        btnBanho.setFont(new Font("Arial", Font.BOLD, 14));
        btnBanho.setFocusPainted(false);

        
        panelBotoes.add(btnBanho);
        painelCentral.add(panelBotoes);

        // Adiciona os painéis principais
        add(menuLateral, BorderLayout.WEST);
        add(painelCentral, BorderLayout.CENTER);

        setVisible(true);
    }
    
   
 
    public static void main(String[] args) {
        
    }
    
}