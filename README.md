# Estimation of room occupancy based on temperature, CO2 concentration, electricity consumption and other inputs

This is the first data science project I have worked on. 

Even though the project is quite simple and was used for learning purposes (I was an engineering student in 2019), I find it relevant to record it here in my portifolio.

## General objective

The objective is to develop a tool in Java that is able to estimate, based on the .csv file, the occupancy of the following office:

![image](https://user-images.githubusercontent.com/49452402/139596994-101fa9d1-5486-4ad3-b401-18aae12d03d6.png)
Markup : ![picture alt](http://via.placeholder.com/200x150 "Title is optional")

## Chosen approach

Given the data provided in the .csv file, it is possible to estimate the occupation of the office in 3 different ways:

         1. Laptop power consumption
            The consumption of each one of the 4 laptops is measured by a power meter (variable ‘power_laptopX_zoneY’). If the average consumption is greater than the standby consumption (15W), it is considered that someone is working on the computer
            
   
         2. Motion detections
            It is considered that the number of motions detected within an hour is proportional to the number of occupants in the office. In order to determine the best proportional coefficient, a simple dichotomy algorithm (considering the laptop consumption based estimation as the actual occupancy) was implemented in the "DichotomicScaler" class.
![image](https://user-images.githubusercontent.com/49452402/139597896-ea42ce53-ac92-48b7-97c2-e9db23d03f0c.png)

         3. CO2 concentration
            In order to estimate the occupancy based on the CO2 level, it is necessary to study the air mass balance. The air flow exchanged from indoor and outdoor can be decomposed into:
            Qout = Qout(0) + ζwindow*Qwindow_out
            Qout --> Air flow exchanged from indoor and outdoor.
            Qout(0) --> Air infiltration
            ζwindow*Qwindow_out --> Average constant renewal air flow going through the window when it is opened
            ζwindow --> It is worth 0 when it is always closed and 1 when it is opened
            
            
