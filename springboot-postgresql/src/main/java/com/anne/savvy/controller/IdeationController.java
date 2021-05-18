package com.anne.savvy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anne.savvy.exception.ResourceNotFoundException;
import com.anne.savvy.model.Ideation;
import com.anne.savvy.repository.IdeationRepository;

@RestController
@RequestMapping("/api/v1/")
public class IdeationController {
	
	@Autowired
	private IdeationRepository ideationRepository;
	
	//get ideation
	@GetMapping("ideation")
	public List<Ideation> getAllIdeation(){
		return this.ideationRepository.findAll();
	}
	
	
	//get ideation by id
	
    @GetMapping("/ideation/{id}")
    public ResponseEntity<Ideation> getIdeationId(@PathVariable(value = "id") Long ideationId)
        throws ResourceNotFoundException {
        Ideation ideation = ideationRepository.findById(ideationId)
          .orElseThrow(() -> new ResourceNotFoundException("Ideation not found for this id :: " + ideationId));
        return ResponseEntity.ok().body(ideation);
    }
    
    //save ideation
    @PostMapping("/ideation")
    public Ideation createIdeation(@RequestBody Ideation ideation) {
        return this.ideationRepository.save(ideation);
    }

    //update ideation
    @PutMapping("/ideation/{id}")
    public ResponseEntity<Ideation> updateIdeation(@PathVariable(value = "id") Long ideationId,
         @RequestBody Ideation ideationDetails) throws ResourceNotFoundException {
        Ideation ideation = ideationRepository.findById(ideationId)
        .orElseThrow(() -> new ResourceNotFoundException("Ideation not found for this id :: " + ideationId));

        ideation.setTopic(ideationDetails.getTopic());
        ideation.setDescription(ideationDetails.getDescription());
        ideation.setFirstName(ideationDetails.getFirstName());
        ideation.setLastName(ideationDetails.getLastName());
        final Ideation updatedIdeation = ideationRepository.save(ideation);
        return ResponseEntity.ok(updatedIdeation);
    }

    //delete ideation
    @DeleteMapping("/ideation/{id}")
    public Map<String, Boolean> deleteIdeation(@PathVariable(value = "id") Long ideationId)
         throws ResourceNotFoundException {
        Ideation ideation = ideationRepository.findById(ideationId)
       .orElseThrow(() -> new ResourceNotFoundException("Ideation not found for this id :: " + ideationId));

        ideationRepository.delete(ideation);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
