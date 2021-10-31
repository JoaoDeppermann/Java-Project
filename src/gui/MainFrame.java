/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.DataContainerWithProcessing;
import data.DataContainer;
import java.awt.Color;
import java.awt.GridLayout;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author joaod
 */

public final class MainFrame extends JFrame implements ActionListener {
    
    JCheckBox[] vectorCheckBoxes;     //Criando vetor de opções CheckBox
    JButton plotButton;     //Criando botão
    DataContainer dataContainer;    //Obj DataContainer para puxar dados
    PlotFactory plotFactory;        //Obj PlotFactory para criar gráficos a partir das CheckBoxes selecionadas
    
    //PlotFactory será acionado com a função actionPerformed que verifica se o botão está pressionado ou não 
    
    int plotCounter;
    
    public MainFrame (DataContainer dataContainer) { //Construtot MainFrame
        
        plotFactory = new PlotFactory(dataContainer);
        this.dataContainer = dataContainer;
        this.plotCounter = 0;
        iniciarComponentes();
    
    }
    
    public void iniciarComponentes(){
        
        JPanel panelOptions = new JPanel();
        
        super.setTitle("Plotter");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setBounds(200, 90, 350, 800);
        super.setBackground(Color.red); //Why color this does not work?
        
        
        ImageIcon ense3 = new ImageIcon("ense3.jpg", null);
        JLabel labelImage = new JLabel(ense3, JLabel.CENTER);
        panelOptions.add(labelImage);
        
        plotButton = new JButton("Plot");
        int numberOfLinesPainel = dataContainer.getNumberOfVariables() + 3;
        
        //+2 porque adicionei um botão embaixo e uma label no topo 
        
        //GridLayout(int rows, int cols) --> Constructor GridLayout
        GridLayout layout = new GridLayout(numberOfLinesPainel, 1); //Somente 1 coluna
        panelOptions.setLayout(layout);
        
        // Criando uma label e add no topo do painel inicial, somente um subtitulo
        JLabel label = new JLabel("                                 Graph plotting in Java"); 
        panelOptions.add(label);
        
        //Cria vetor de checkBoxes e já adiociona no JFrame inicial
        //Foram usadas as funções já prontas advindas da classe DataContainer
        
        this.vectorCheckBoxes = new JCheckBox[dataContainer.getNumberOfVariables()];
        for (int k = 0; k < dataContainer.getNumberOfVariables(); k++) {
            vectorCheckBoxes[k] = new JCheckBox(dataContainer.getAvailableVariables()[k]);
            panelOptions.add(vectorCheckBoxes[k]);
        }
        
        //Adiciona botão embaixo e depois puxa a função para verficar se ele tá pressionado
        panelOptions.add(plotButton);
        getContentPane().add(panelOptions);
        super.setVisible(true);
        
        plotButton.addActionListener(this);     //Relação com a tarefa que o programa deve realizar quando o programa for pressionado
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    
        ArrayList<String> variableToBePlot = new ArrayList<>();
        //Adicionamos em um vetor String o nome de todas as variáveis que vão ser plotadas
        for (JCheckBox tabBox1 : vectorCheckBoxes) 
        {
            if (tabBox1.isSelected()) {
                variableToBePlot.add(tabBox1.getText());
            }
        }

        String[] variableNames = new String[variableToBePlot.size()];
        
        //Função que vai converter um tableau dynamique em tableau statique
        variableToBePlot.toArray(variableNames); 
        
        //Agora, nós vamos chamar a função getPlot corretamente com o tableau de strings
        //Esse é o panel que nós vamos colocar na nossa frame
        JPanel chartPanel = plotFactory.getPlot(variableNames);

        JFrame janela = new JFrame("Voilà votre graphique !");
        
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.getContentPane().add(chartPanel);
        janela.pack();
        janela.setResizable(true);
        janela.setVisible(true);
    }
    
    
    public static void main(String[] args) throws IOException {
        
        DataContainer dataContainer = new DataContainerWithProcessing("office.csv");
        MainFrame frame = new MainFrame(dataContainer);
    
    }
}
