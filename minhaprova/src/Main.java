import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Jogador {
    String nome;
    int votos;

    Jogador(String nome) {
        this.nome = nome;
        this.votos = 0;
    }

    public void incrementaUmVoto() {
        this.votos = getVotos() + 1;
    }

    public int getVotos() {
        return this.votos;
    }

    public String getNome() {
        return this.nome;
    }
}

public class Main extends JFrame {
    private JPanel painel = new JPanel();
    private JButton jButtonVotar = new JButton("Votar");
    private JButton jButtonApurar = new JButton("Apurar");
    private ArrayList<Jogador> jogadores = new ArrayList<>();

    public Main() {
        this.setTitle("Eliminacao BBB - Interface Gráfica");
        this.setSize(400, 200);
        painel.setBackground(new Color(102, 102, 51));
        painel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));

        painel.add(jButtonVotar);
        painel.add(jButtonApurar);

        jButtonVotar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Votar();
            }
        });

        jButtonApurar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Apurar();
            }
        });

        this.add(painel);
    }

    private void Votar() {
        String votoEm = JOptionPane.showInputDialog(null,"Em quem voce vota para sair da casa?");
        if (votoEm != null && !votoEm.isEmpty()) {
            boolean jogadorEncontrado = false;
            for (Jogador jogador : jogadores) {
                if (jogador.getNome().equalsIgnoreCase(votoEm)) {
                    jogador.incrementaUmVoto();
                    jogadorEncontrado = true;
                    break;
                }
            }
            if (!jogadorEncontrado) {
                Jogador novoJogador = new Jogador(votoEm);
                novoJogador.incrementaUmVoto();
                jogadores.add(novoJogador);
            }
            JOptionPane.showMessageDialog(null, "Voto computado com sucesso");
        } else {
            JOptionPane.showMessageDialog(null, "Que pena, não vai votar? ");
        }
    }

    private void Apurar() {
        Jogador jogadorEliminado = null;
        int totalVotos = 0;
        for (Jogador jogador : jogadores) {
            totalVotos += jogador.getVotos();
            if (jogadorEliminado == null || jogador.getVotos() > jogadorEliminado.getVotos()) {
                jogadorEliminado = jogador;
            }
        }
        JOptionPane.showMessageDialog(null, "Total de votos registrados: " + totalVotos);
        if (jogadorEliminado != null && jogadorEliminado.getVotos() >= 10) {
            JOptionPane.showMessageDialog(null, "Se eu conseguir mover montanhas, se eu conseguir surfar um tsunami, se eu conseguir\n" +
                    "domar o sol, se eu conseguir fazer o mar virar sertão, e o sertão virar mar, se eu\n" +
                    "conseguir dizer o que eu nunca vou conseguir dizer, aí terá chegado o dia em que eu\n" +
                    "vou conseguir te eliminar com alegria. Com " + jogadorEliminado.getVotos() + " votos, é você quem sai " + jogadorEliminado.getNome());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main janela = new Main();
            janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            janela.setLocationRelativeTo(null);
            janela.setVisible(true);
        });
    }
}
