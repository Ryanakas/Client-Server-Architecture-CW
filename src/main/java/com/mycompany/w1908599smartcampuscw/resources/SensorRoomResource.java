package com.mycompany.w1908599smartcampuscw.resources;


import com.mycompany.w1908599smartcampuscw.models.Room;
import com.mycompany.w1908599smartcampuscw.service.RoomService;
import java.net.URI;
import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;



/**
 * 
 *
 * @author 
 */
@Path("/rooms")
public class SensorRoomResource {
   
    private RoomService service = new RoomService();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRooms(){
        List<Room> response = service.getAllRooms();
        
    
        return  Response.ok()
                        .entity(response)
                        .build();
    }
    
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String testRooms() {
//        return "rooms endpoint works";
//    }
    
    
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)//produces jsonclm
    public Response addRoom(Room room){
        boolean response = service.addRoom(room);
        
        if (response == true) {
            
        
            URI newUri = URI.create("/rooms/" + room.getId());
            return Response.created(newUri)
                           .entity("Room Created")
                           .build(); //returns header with room location
        }
        else{
            return Response.status(Response.Status.CONFLICT)
                       .entity("Room with this ID already exists")
                       .build();
        }
        
    }
    
    @GET
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoom(
    @PathParam("roomId") String roomId
    ){
        Room room = service.getRoom(roomId);

        if (room == null) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Room not found")
                           .build();
        }

        return Response.ok(room).build();
    }
    
    @DELETE
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRoom(
    @PathParam("roomId") String roomId
    ){
            
        boolean roomDelete = service.deleteRoom(roomId);
        if (roomDelete == false) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Room not found")
                           .build();
        }
        else{
            
            return Response.ok()
                        .entity("Room " + roomId + " Deleted.")
                        .build();
        }
            
    }
    
    
            
            
            
            
    }
