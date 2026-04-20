/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.w1908599smartcampuscw.service;

import com.mycompany.w1908599smartcampuscw.dao.GenericDao;
import com.mycompany.w1908599smartcampuscw.mappers.RoomNotEmptyException;
import com.mycompany.w1908599smartcampuscw.models.Database;
import com.mycompany.w1908599smartcampuscw.models.Room;
import java.util.List;

/**
 *
 * @author ryand
 */
public class RoomService {
    
    private GenericDao<Room> roomDao =
        new GenericDao<>(Database.ROOMLIST);
    
    
    public List<Room>  getAllRooms(){
        
        List<Room> response = roomDao.getAll();
        return response;
        
    }
    
    public boolean addRoom(Room room){
    
        String id = room.getId();
        Room roomCopy = roomDao.getById(id);

        if (roomCopy == null) {
            roomDao.add(room);          
            return true;
        
        }
        else{
            
            return false;           
        }
    }
    
    public Room getRoom(String roomId){
        
        return roomDao.getById(roomId);
    
    }
    
    
    public boolean deleteRoom(String roomId){
    
        Room roomSearch = roomDao.getById(roomId);
        
        if (roomSearch == null) {
            return false ;
                           
        }
        else if(!roomSearch.getSensorIds().isEmpty()){
            
            throw new RoomNotEmptyException("Room cant be deleted as there are active sensors inside");
        
        }else{
            roomDao.delete(roomId);
            return true ;                         
        }
        
        
    }   
}
