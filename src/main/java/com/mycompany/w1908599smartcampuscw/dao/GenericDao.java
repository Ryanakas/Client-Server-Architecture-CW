/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.w1908599smartcampuscw.dao;

import com.mycompany.w1908599smartcampuscw.models.BaseModel;
import java.util.*;

/**
 *
 * @author ryand
 */

public class GenericDao<T extends BaseModel> {     
    private final List<T> items;

    public GenericDao(List<T> items) {
    this.items = items; // 
    }

    public List<T> getAll() {
        return items;
    }

    public T getById(String id) {
       for (T item : items) {
           if (item.getId().equals(id)) {
               return item;
           }
        }
       return null;
    }
    
     
    public void add(T item) {
        

        
        items.add(item);
    }

    public void update(T updatedItem) {
        for (int i = 0; i < items.size(); i++) {
            T item = items.get(i);

            if (item.getId().equals(updatedItem.getId())) {
                items.set(i, updatedItem);
                return;
            }
        }
    }

     public void delete(String id) {
     items.removeIf(item -> item.getId().equals(id));
     }
}



    

