/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fitters.models;

/**
 *
 * @author joaod
 */

//J'AI ESSAYÉ MAIS J'AI PAS RÉUSSI

public class CO2OfficeModel {
    
    Double[] doorOpening; 
    Double[] corridorCO2Concentration; 
    Double[] officeCO2Concentration; 
    Double[] windowOpening; 
    Double[] estimatedOccupation; 
    double roomVolume = 55;
    double co2BreathProduction = 4;
    double samplePeriod = 3600; 
    double outdoorCO2concentration = 395;
    
    public CO2OfficeModel (Double[] windowOpening, 
            Double[] doorOpening, Double[] officeCO2Concentration,
            Double[] corridorCO2Concentration) {
        
}
    public Double[] simulate (double[] airFlows){
        
        
        
        
        
        return null; //add retorno obj double
    
    
    }
}
