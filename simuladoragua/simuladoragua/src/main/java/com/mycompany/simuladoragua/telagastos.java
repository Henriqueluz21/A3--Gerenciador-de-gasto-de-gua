package com.mycompany.simuladoragua;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class telagastos extends JFrame {

    private int usuarioId;
    
    public telagastos(int usuarioId) {
        
        this.usuarioId = usuarioId;
        
        setTitle("AquaLerta - Gastos Médios");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

       // BOTÃO DE MENU
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

        // Ação do botão menu (abre/fecha o menu)
        btnToggleMenu.addActionListener(e -> {
            menuLateral.setVisible(!menuLateral.isVisible());
            this.revalidate();
            this.repaint();
        });

        // Adiciona botão menu ao painel
        menuLateral.add(Box.createRigidArea(new Dimension(0,10)));
        menuLateral.add(btnToggleMenu);

        // Botões do menu lateral com ações
        String[] opcoesMenu = {"Dicas", "Perfil", "Início"};
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

            // Ações específicas de cada botão
            btn.addActionListener(e -> {
                switch (opcao) {
                    case "Dicas":
                        JOptionPane.showMessageDialog(this, "Abrir Dicas");
                        //new teladicas().setVisible(true);
                        dispose();
                        break;
                    case "Perfil":
                    {
                        try {
                            new telaperfil("Usuário Exemplo", "email@exemplo.com", 123, usuarioId).setVisible(true);
                        } catch (SQLException ex) {
                            Logger.getLogger(telagastos.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(telagastos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        dispose();
                        break;

                    case "Início":
                        new telainicio(usuarioId).setVisible(true);
                        dispose();
                        break;
                }
            });
        }

        
        // PAINEL CENTRAL
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.setBackground(Color.WHITE);

        // LOGO
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/com/mycompany/simuladoragua/imagens/logo-aqualerta.png"));
        Image scaledImage = logoIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);
        JLabel logoLabel = new JLabel(resizedIcon, JLabel.CENTER);
        painelCentral.add(logoLabel, BorderLayout.NORTH);

        // FRASE
        JLabel frase = new JLabel("<html><center>Acompanhe seus gastos e otimize seu consumo!</center></html>", JLabel.CENTER);
        frase.setFont(new Font("SansSerif", Font.BOLD, 16));
        frase.setForeground(new Color(0, 230, 118));
        frase.setBorder(new EmptyBorder(0, 0, 20, 0));
        painelCentral.add(frase, BorderLayout.CENTER);

        // TABELA DE GASTOS
        String[] colunas = {"Atividade", "Tempo Médio(min)", "Consumo (L)"};
        Object[][] dados = {
            {"Banho", "-", "-", "-"},
            {"Escovar Dentes", "-", "-", "-"},
            {"Lavar Louça", "-", "-", "-"},
            {"Lavar Roupas", "-", "-", "-"},
            {"Descarga", "-", "-", "-"}
        };

        JTable tabela = new JTable(new DefaultTableModel(dados, colunas));
        tabela.setBackground(Color.WHITE);
        tabela.setOpaque(true);
        
        tabela.setEnabled(false);
        tabela.setRowHeight(28);
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setOpaque(true);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);

        scrollPane.setBorder(null);
        scrollPane.setBorder(new EmptyBorder(10, 50, 10, 50));
        JPanel conteudoVertical = new JPanel();
        conteudoVertical.setLayout(new BoxLayout(conteudoVertical, BoxLayout.Y_AXIS));
        conteudoVertical.setBackground(Color.WHITE);
        

        // Espaço e centralização
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frase.setAlignmentX(Component.CENTER_ALIGNMENT);
        conteudoVertical.add(Box.createVerticalStrut(30)); // espaço superior
        conteudoVertical.add(logoLabel);
        conteudoVertical.add(Box.createVerticalStrut(10));
        conteudoVertical.add(frase);
        conteudoVertical.add(Box.createVerticalStrut(40)); // espaço entre frase e tabela
        conteudoVertical.add(scrollPane);

        painelCentral.add(conteudoVertical, BorderLayout.CENTER);


        // ADICIONAR COMPONENTES À JANELA
        add(menuLateral, BorderLayout.WEST);
        add(painelCentral, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        
    }
}
