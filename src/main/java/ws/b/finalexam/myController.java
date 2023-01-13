/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ws.b.finalexam;

import com.fasterxml.jackson.databind.ObjectMapper;
import static jakarta.annotation.Resource.AuthenticationType.APPLICATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author Zahran Rafif-20200140073
 */
@RestController
@CrossOrigin
public class myController {
    
    //Objek
    Person ps = new Person();
    PersonJpaController pjpa = new PersonJpaController();
    
    //GET ALL
    @RequestMapping(value="/GETALL")
    public List<Person> getAll(){
        List<Person> dummy = new ArrayList();
        try{
            dummy = pjpa.findPersonEntities();
        } catch (Exception e) {
            dummy = List.of();
        }
        return dummy;
    }
    
    //GET BY ID
    @RequestMapping(value="/GET/{id}",
            method = RequestMethod.GET)
    public String getNameById(@PathVariable("id") int id) {
        try{
            ps = pjpa.findPerson(id);
        }catch (Exception error) {return "error";
        }
        return ps.getNama() + " "+ ps.getNik() + " "+ ps.getTl() + " " + ps.getTimestamp();
    }
    
    //POST
    @RequestMapping(value="/POST", 
            method = RequestMethod.POST, 
            consumes = APPLICATION_JSON_VALUE)
    public String createData(HttpEntity<String> paket){
        String message = "no action";
        
        try {
            String json_receive = paket.getBody();
            ObjectMapper map = new ObjectMapper();
            Person data = new Person();
            data = map.readValue(json_receive, Person.class);
            
            pjpa.create(data);
            message = data.getNama() + " Data SAVED ";
        
        } catch (Exception e) {message="Failed";}
        return message;
    }
    
    //DELETE
    @RequestMapping(value="/DELETE",
            method = RequestMethod.DELETE,
            consumes = APPLICATION_JSON_VALUE)
    public String deleteData(HttpEntity<String> kiriman){
        String message = "no action";
        try{
           String json_receive = kiriman.getBody();
           ObjectMapper mapper = new ObjectMapper();
           Person newdatas = new Person();
           newdatas = mapper.readValue(json_receive, Person.class);
           pjpa.destroy(newdatas.getId());
           message = newdatas.getNama() + " Has Been Deleted ";  
        
        }catch(Exception error){return "Product deleted is error";
        }
        return message;
    }
    
    //PUT
    @RequestMapping(value="/PUT", 
            method = RequestMethod.PUT, 
            consumes = APPLICATION_JSON_VALUE)
    public String editData(HttpEntity<String> kiriman) throws Exception{
        String message="no action";
        try {
            String json_receive = kiriman.getBody();
            ObjectMapper mapper = new ObjectMapper();
            
            Person newdatas = new Person();
            newdatas = mapper.readValue(json_receive, Person.class);
            pjpa.edit(newdatas);
            message = newdatas.getNama() + " Hass been updated";
        } catch (Exception e) {
        }
        return message;
    }
}
