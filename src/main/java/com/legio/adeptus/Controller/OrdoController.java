package com.legio.adeptus.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.legio.adeptus.Entity.OrdoMalleus;
import com.legio.adeptus.Repo.OrdoReposite;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/chaos")
@CrossOrigin(origins = "http://localhost:3000/", maxAge = 3600)
public class OrdoController {
	@Autowired
	private OrdoReposite ard;
	
	@GetMapping
	public ResponseEntity<List<OrdoMalleus>> getAllEmp(){
		return ResponseEntity.ok(ard.findAll());
	}
	
	@PostMapping
	public ResponseEntity<?> createKnight(@Valid @RequestBody OrdoMalleus ox){
		OrdoMalleus neuNight = ard.save(ox);
		return new ResponseEntity<OrdoMalleus>(neuNight,HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") int id){
		Optional<OrdoMalleus> zeal = ard.findById(id);
		if(zeal.isEmpty()) {
			String error = "Unable to find Knights info on:"+id;
			return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(error);
		}else {
			return ResponseEntity.ok(zeal.get());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateKnight(@Valid @RequestBody OrdoMalleus ox, @PathVariable("id") int id){
		Optional<OrdoMalleus> zeal = ard.findById(id);
		if(zeal.isEmpty()) {
			String error = "Unable to find Knights info on:"+id;
			return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(error);
		}else {
			OrdoMalleus extdata = zeal.get();
			extdata.setName(ox.getName());
			extdata.setAge(ox.getAge());
			extdata.setAddress(ox.getAddress());
			extdata.setEmail(ox.getEmail());
			extdata.setDoj(ox.getDoj());
			extdata.setPhone(ox.getPhone());
			
			return new ResponseEntity<OrdoMalleus>(ard.save(extdata), HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") int id){
		Optional<OrdoMalleus> zeal = ard.findById(id);
		if(zeal.isEmpty()) {
			String error = "Unable to find Knights info on:"+id;
			return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(error);
		}else {
			this.ard.deleteById(id);
			return ResponseEntity.ok().body("Deleted Successfully");
		}
	}
}
