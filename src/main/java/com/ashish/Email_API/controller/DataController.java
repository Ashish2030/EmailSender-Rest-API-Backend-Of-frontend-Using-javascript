package com.ashish.Email_API.controller;

import com.ashish.Email_API.model.Data;
import com.ashish.Email_API.service.DataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class DataController
{
    @Autowired
    private DataService dataservice;
    @PostMapping("/data")
    public ResponseEntity<?> sendEmail(@RequestBody String data1) throws JsonProcessingException
    {

            Data data = new ObjectMapper().readValue(data1, Data.class);

        boolean result=this.dataservice.emailSender(data.getSubject(),data.getMessage(),data.getTo(),data.getFrom(),data.getPassword());
        if(result)
        {
          return ResponseEntity.ok("Email is Sent Successfully");
        }
        else
            {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Email not Sent");
        }
    }

}
