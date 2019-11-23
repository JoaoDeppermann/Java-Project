/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.DataContainer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joaod
 */
public class PlotFactory {
    
    //Definindo o formato da data recebido de cordo com o arquivo .csv
    DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-d HH:mm:ss");
    //Cria obj JPanel que será utilizado/retornado na nossa função getPlot
    JPanel panel;
    //DataContainer como sempre é o objeto de onde vem os dados
    DataContainer dataContainer;

    public PlotFactory(DataContainer data) {
        
        this.dataContainer = data;
    
    }

    //Função que vai retornar o gráfico solicitado a partir das info obtidas na classe MainFrame
    public JPanel getPlot(String[] variableNames) {

        try {
            //Pega todas as datas do .csv (eixo x)
            Date[] times = dataContainer.getDates();
            //Objeto que contém o TimeSeries, objeto de classe déjà pronta do java, por isos tá importada
            TimeSeriesCollection timeSeriesCollection = new TimeSeriesCollection();
            
            for (String variableName : variableNames) {
                
                Double[] value = dataContainer.getData(variableName);
                TimeSeries timeSeries = new TimeSeries(variableName);
                
                for (int i = 0; i < value.length; i++) {
                    timeSeries.add(new Hour(times[i]), value[i]);
                }
                
                timeSeriesCollection.addSeries(timeSeries);
            }
            
            TimeSeries[] timeSeriesTab = new TimeSeries[variableNames.length];
            Date[] datesTab = new Date[variableNames.length];
            
            //Formatado de acordo com o modelo pronto Internet
            JPanel chartPanel = new ChartPanel(ChartFactory.createTimeSeriesChart("Plot", "x_label", "y_label", timeSeriesCollection, true, true, false));
            
            return chartPanel;
        } catch (ParseException ex) { //Mesma coisa com PlotTimeChart fornecido
            //Catch Professor
            Logger.getLogger(PlotFactory.class.getName()).log(Level.SEVERE, null, ex);
            //Em caso de erro é necessário cancelar a execução
            System.exit(-1);
            return null;
        }
    }
}


