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
    private String[] sensorArray = {"0", "0", "0", "0"};
    private String note;
    private String error;
    
    // Public properties
    public String getErrorDescription() {
        return error;
    }
    
    public void setErrorDescription(String error) {
        this.error = error;
    }        

    public Boolean getError() {
        if( null != this.error){
            return (this.error.length() > 0);
        }
        return false;
    }

    public void setError(Boolean set) {
        if( false == set ){
            this.error = "";
        }  
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSensor1() {
        return sensorArray[0];
    }
    public void setSensor1(String val) {
        sensorArray[0] = val;
    }
    
    public String getSensor2() {
        return sensorArray[1];
    }
        
    public void setSensor2(String val) {
        sensorArray[1] = val;
    }
    
    public String getSensor3() {
        return sensorArray[2];
    }
    
    public void setSensor3(String val) {
        sensorArray[2] = val;
    }

    public String getSensor4() {
        return sensorArray[2];
    }
    
    public void setSensor4(String val) {
        sensorArray[3] = val;
    }
    
    public String getNote() {
        return this.note;
    }
    
    public void setNote(String note) {
        this.note = note;
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

        Float sensor1, sensor2, sensor3, sensor4;
        
        // Format checks
        try{
            sensor1 = Float.parseFloat(sensorArray[0]);
            sensor2 = Float.parseFloat(sensorArray[1]);
            sensor3 = Float.parseFloat(sensorArray[2]);
            sensor4 = Float.parseFloat(sensorArray[3]);
        } 
        catch(NumberFormatException e){
            System.out.println("Read measurement failed: " + e.getMessage());
            this.setErrorDescription("Error: " + e.getMessage() );
            return "createmeasurement";
        }                
        
        // Boundary checks
        if( ( sensor1 > 99.999 || sensor1 < -99.999 ) ||
            ( sensor2 > 99.999 || sensor2 < -99.999 ) ||
            ( sensor3 > 99.999 || sensor3 < -99.999 ) ||
            ( sensor4 > 99.999 || sensor4 < -99.999 ) ||
            note.length() > 50 )
        {
            this.setErrorDescription("Invalid value for field.");
            return "createmeasurement";
        }
        
        try{
            // Add date to new entry, this forces it to SQL CREATE
            measurement.setDate(new Date());
            measurement.setSensor1(sensor1);
            measurement.setSensor2(sensor2);
            measurement.setSensor3(sensor3);
            measurement.setSensor4(sensor4);
            measurement.setNote(note);
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

        int _id;
        try{
            _id = Integer.parseInt(this.id);
        } 
        catch(NumberFormatException e){
            System.out.println("Read measurement failed: " + e.getMessage());
            this.setErrorDescription("Error: " + e.getMessage() );
            return "readmeasurement";
        }
        
        List<Measurement> msmnts = this.measurementFacade.findAll();
       
        for( Measurement meas : msmnts ) {
            if( meas.getId() == _id ) {
                measurement = meas;
                System.out.println("Measurement " + _id + " read");
           }
        }
        return "readmeasurement";
    }

    // Update measurement
    public String updateMeasurement(){       
        
        System.out.println("Update measurement " + this.id);
        Float sensor1, sensor2, sensor3, sensor4;
        int _id;
        
        // Format checks
        try{
            _id = Integer.parseInt(this.id);
            sensor1 = Float.parseFloat(sensorArray[0]);
            sensor2 = Float.parseFloat(sensorArray[1]);
            sensor3 = Float.parseFloat(sensorArray[2]);
            sensor4 = Float.parseFloat(sensorArray[3]);
        } 
        catch(NumberFormatException e){
            System.out.println("Read measurement failed: " + e.getMessage());
            this.setErrorDescription("Error: " + e.getMessage() );
            return "updatemeasurement";
        }                
        
        // Boundary checks
        if( ( sensor1 > 99.999 || sensor1 < -99.999 ) ||
            ( sensor2 > 99.999 || sensor2 < -99.999 ) ||
            ( sensor3 > 99.999 || sensor3 < -99.999 ) ||
            ( sensor4 > 99.999 || sensor4 < -99.999 ) )
        {
            this.setErrorDescription("Invalid value for field.");
            return "updatemeasurement";
        }
        
        List<Measurement> msmnts = this.measurementFacade.findAll();
       
        for( Measurement meas : msmnts ) {
            if( meas.getId() == _id ) {
                meas.setSensor1(sensor1);
                meas.setSensor2(sensor2);
                meas.setSensor3(sensor3);
                meas.setSensor4(sensor4);
                meas.setNote(measurement.getNote());
                this.measurementFacade.edit(meas);
                System.out.println("Measurement " + id + " updated");
           }          
       }
       return "updatemeasurement";
    }
    
    // Delete measurement
    public String deleteMeasurement(){              
        System.out.println("Delete measurement " + this.id);
        
        int _id;
        try{
            _id = Integer.parseInt(this.id);
        } 
        catch(NumberFormatException e){
            System.out.println("Delete measurement failed: " + e.getMessage());
            this.setErrorDescription("Error: " + e.getMessage() );
            return "deletemeasurement";
        }
        
        List<Measurement> msmnts = this.measurementFacade.findAll();
        
        for( Measurement meas : msmnts ) {
            if( meas.getId() == _id ) {
                System.out.println("Measurement " + _id + " deleted");
                this.measurementFacade.remove(meas);
            }
        }       
        return "deletemeasurement";
    }
}
