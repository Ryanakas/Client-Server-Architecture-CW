/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.w1908599smartcampuscw.service;

import com.mycompany.w1908599smartcampuscw.dao.GenericDao;
import com.mycompany.w1908599smartcampuscw.mappers.LinkedResourceNotFoundException;
import com.mycompany.w1908599smartcampuscw.models.Database;
import com.mycompany.w1908599smartcampuscw.models.Room;
import com.mycompany.w1908599smartcampuscw.models.Sensor;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author ryand
 */
public class SensorService {
    
    
    
    private GenericDao<Sensor> sensorDao =
        new GenericDao<>(Database.SENSORLIST);
    
     private GenericDao<Room> roomDao =
        new GenericDao<>(Database.ROOMLIST);
    
    
    public String addSensor(Sensor sensor){
        
        Room sensorRoom  = roomDao.getById(sensor.getRoomId());

        if (sensorRoom == null) {
                     
            throw new LinkedResourceNotFoundException("Room doesnt exist, unable to process request.");

        
        }
        else{
            
            Sensor sensorCopy = sensorDao.getById(sensor.getId());
            
            if (sensorCopy == null) {
                
                sensorDao.add(sensor);
                sensorRoom.addSensorId(sensor.getId());
                roomDao.update(sensorRoom);
                return "Sensor Created";
                
            }
            else{
                
                return "Sensor Conflict";
            
             
            }
                       
        }
        
    
    }
    
    public List<Sensor> getAllSensors(String type){
        
        List<Sensor> filteredResponse = new ArrayList<>();
                
        if (type == null){
        
            List<Sensor> response = sensorDao.getAll();
            return response;
            
        } 
        else{
            
            List<Sensor> response = sensorDao.getAll();
            for (int i = 0; i < response.size(); i++){
                Sensor currentSensor = response.get(i);
                if(currentSensor.getType().equals(type)){
                filteredResponse.add(currentSensor);
               
                }
            
            }
            
            return filteredResponse;
        }
        
    }
    
    
    
}
