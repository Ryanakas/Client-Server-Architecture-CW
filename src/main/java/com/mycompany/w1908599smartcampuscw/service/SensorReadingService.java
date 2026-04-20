/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.w1908599smartcampuscw.service;

import com.mycompany.w1908599smartcampuscw.dao.GenericDao;
import com.mycompany.w1908599smartcampuscw.mappers.SensorUnavailableException;
import com.mycompany.w1908599smartcampuscw.models.Database;
import com.mycompany.w1908599smartcampuscw.models.Sensor;
import com.mycompany.w1908599smartcampuscw.models.SensorReading;

/**
 *
 * @author ryand
 */
public class SensorReadingService {
    
    private GenericDao<SensorReading> sensorReadingDao =
        new GenericDao<>(Database.SENSORREADINGLIST);    
    
    private GenericDao<Sensor> sensorDao =
        new GenericDao<>(Database.SENSORLIST);
    
    public String addSensorReading(SensorReading newSensorReading,String sensorId){
        
        SensorReading oldSensorReading = sensorReadingDao.getById(newSensorReading.getId());
        Sensor sensor = sensorDao.getById(sensorId);
        
        if( "MAINTENANCE".equals(sensor.getStatus())){
                throw new SensorUnavailableException("This sensor currently marked with the status --MAINTENANCE--"
                        + " its physically disconnected and cannot accept new readings.");

        }
        else if(oldSensorReading == null){
            sensorReadingDao.add(newSensorReading);
            
            
            if (sensor != null){
                sensor.setCurrentValue(newSensorReading.getValue());
                sensorDao.update(sensor);
            }
            return "Readding added";
        }else{
            sensorReadingDao.update(newSensorReading);
            if (sensor != null){
                sensor.setCurrentValue(newSensorReading.getValue());
                sensorDao.update(sensor);
            }
            return "Reading updated";
        }
    
    }
    
    
    public SensorReading getSensorReading(String sensorReadingId){
        return sensorReadingDao.getById(sensorReadingId);
    }
}
