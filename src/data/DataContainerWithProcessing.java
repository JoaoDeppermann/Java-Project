package data;

import java.io.IOException;
import fitters.DichotomicScaler;

public final class DataContainerWithProcessing extends DataContainer {

    public DataContainerWithProcessing(String csvFileName) throws IOException {
        super(csvFileName);
        
        calculatingOccupancy();
        
    }
    
    //Develop an occupancy estimator based on laptop power consumptions 
    //Develop an occupancy estimator based on motion detections
    
    public void calculatingOccupancy() {
        
        //Criando vetores de objetos double para calcular a occupancy total dos laptops
        Double[] laptop_1 = super.getData("power_laptop1_zone1");
        Double[] laptop_2 = super.getData("power_laptop1_zone2");
        Double[] laptop_3 = super.getData("power_laptop2_zone2");
        Double[] laptop_4 = super.getData("power_laptop3_zone2");

        Double[] occupancy_from_laptops = new Double[super.getNumberOfSamples()];
        for (int i = 0; i < super.getNumberOfSamples(); i++) {
            occupancy_from_laptops[i] = 0.0;
        }
        
        //Se a potência for superior à poteência de standBy de 15W nós supomos que alguém está usando o PC
        
        for (int i = 0; i < super.getNumberOfSamples(); i++) {
            if (laptop_1[i] > 15) {
                occupancy_from_laptops[i]++;
            }
            if (laptop_2[i] > 15) {
                occupancy_from_laptops[i]++;
            }
            if (laptop_3[i] > 15) {
                occupancy_from_laptops[i]++;
            }
            if (laptop_4[i] > 15) {
                occupancy_from_laptops[i]++;
            }
        }
        
        super.addData("occupancy_from_laptops", occupancy_from_laptops);

        DichotomicScaler dichotomScal = new DichotomicScaler(occupancy_from_laptops, super.getData("detected_motions"), 0, 0.3, 100);
        double dic = dichotomScal.getBestScale();
        Double[] occupancy_from_motions = new Double[super.getNumberOfSamples()];

        for (int i = 0; i < super.getNumberOfSamples(); i++) {
            occupancy_from_motions[i] = dic * getData("detected_motions")[i];
        }
        
        //Printando a dichotomie
        System.out.println("Value:" + dichotomScal.getBestScale());
        System.out.println("NumberOfIterations:" + dichotomScal.getNumberOfIterations());

        super.addData("occupancy_from_motions", occupancy_from_motions);
    }

    public static void main(String[] args) {
        try {
            DataContainer dataContainer = new DataContainer("office.csv");
            System.out.println(dataContainer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}