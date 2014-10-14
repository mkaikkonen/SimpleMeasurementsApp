/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.presentation;

import Measurement.Measurement;
import boundary.MeasurementFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Kaikkonen
 */
@ManagedBean(name = "MeasurementView")
@RequestScoped
public class MeasurementView {
    @EJB
    private MeasurementFacade measurementFacade;
    private Measurement measurement;   
    private String id;        

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Creates a new instance of MeasurementView
     */
    public MeasurementView() {
        System.out.println("Create Measurement View");
        this.measurement = new Measurement();
    }
    
    // Calls getMeasurement to retrieve the measurement
    public Measurement getMeasurement() {
       return measurement;
    }
        
    // Returns list of measurements
    public List<Measurement> getMeasurements() {
        return this.measurementFacade.findAll();
    }
    
    // Returns the total number of measurements
    public int getNumberOfMeasurements(){
       return this.measurementFacade.findAll().size();
    }        

    // Cerate measurement
    public String createMeasurement(){        
        System.out.println("Create measurement");
        
        try{
            // Add date to new entry, this forces it to SQL CREATE
            measurement.setDate(new Date());
            this.measurementFacade.create(measurement);
            System.out.println("Measurement created");
        }
        catch(Exception e)
        {
        System.out.println("Measurement creation failed: " + e.getMessage());
        }
        
       return "createmeasurement";
    }   

    // Read measurement
    public String readMeasurement() {
        System.out.println("Read measurement: " + this.id);

        List<Measurement> msmnts = this.measurementFacade.findAll();
       
        for( Measurement meas : msmnts ) {
            if( meas.getId() == Integer.parseInt(this.id) ) {
                measurement = meas;
                System.out.println("Measurement " + this.id + " read");
           }
        }
        return "readmeasurement";
    }

    // Update measurement
    public String updateMeasurement(){       
        
        System.out.println("Update measurement " + this.id);
        
        List<Measurement> msmnts = this.measurementFacade.findAll();
       
        for( Measurement meas : msmnts ) {
            if( meas.getId() == Integer.parseInt(this.id ) ) {
                meas.setSensor1(measurement.getSensor1());
                meas.setSensor2(measurement.getSensor2());
                meas.setSensor3(measurement.getSensor3());
                meas.setSensor4(measurement.getSensor4());
                meas.setNote(measurement.getNote());
                this.measurementFacade.edit(meas);
                System.out.println("Measurement " + this.id + " updated");
           }          
       }
       return "updatemeasurement";
    }
    
    // Delete measurement
    public String deleteMeasurement(){       
       
        System.out.println("Delete measurement " + this.id);
        
        List<Measurement> msmnts = this.measurementFacade.findAll();
       
       for( Measurement meas : msmnts ) {
           if( meas.getId() == Integer.parseInt(this.id ) ) {
               System.out.println("Measurement " + this.id + " deleted");
               this.measurementFacade.remove(meas);
               }
       }       
       return "deletemeasurement";
    }

}
