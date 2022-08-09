package hackothon.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
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

import hackothon.exception.ResourceNotFoundException;
import hackothon.model.HackothonParticpant;
import hackothon.repository.HackothonRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
public class HackothonController {
	@Autowired
	private HackothonRepository hackothonRepository;

	@GetMapping("/hackothon")
	public List<HackothonParticpant> getAllhackothon() {
		return hackothonRepository.findAll();
	}

	@GetMapping("/hackothon/{id}")
	public ResponseEntity<HackothonParticpant> gethackothonById(@PathVariable(value = "id") Long hackothonId)
			throws ResourceNotFoundException {
		HackothonParticpant hackothon = hackothonRepository.findById(hackothonId)
				.orElseThrow(() -> new ResourceNotFoundException("hackothon not found for this id :: " + hackothonId));
		return ResponseEntity.ok().body(hackothon);
	}

	@PostMapping("/hackothon")
	public HackothonParticpant createhackothon(@RequestBody HackothonParticpant hackothon) {
		return hackothonRepository.save(hackothon);
	}

	@PutMapping("/hackothon/{id}")
	public ResponseEntity<HackothonParticpant> updatehackothon(@PathVariable(value = "id") Long hackothonId,
			@RequestBody HackothonParticpant hackothonDetails) throws ResourceNotFoundException {
		HackothonParticpant hackothon = hackothonRepository.findById(hackothonId)
				.orElseThrow(() -> new ResourceNotFoundException("hackothon not found for this id :: " + hackothonId));

		hackothon.setEmailId(hackothonDetails.getEmailId());
		hackothon.setLastName(hackothonDetails.getLastName());
		hackothon.setFirstName(hackothonDetails.getFirstName());
	    hackothon.setStartDate(hackothonDetails.getStartDate());
		
		final HackothonParticpant updatedhackothon = hackothonRepository.save(hackothon);
		return ResponseEntity.ok(updatedhackothon);
	}

	@DeleteMapping("/hackothon/{id}")
	public Map<String, Boolean> deletehackothon(@PathVariable(value = "id") Long hackothonId)
			throws ResourceNotFoundException {
		HackothonParticpant hackothon = hackothonRepository.findById(hackothonId)
				.orElseThrow(() -> new ResourceNotFoundException("hackothon not found for this id :: " + hackothonId));

		hackothonRepository.delete(hackothon);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
