package com.his.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.his.dto.PlanDTO;
import com.his.service.PlanService;

@RestController
@RefreshScope
public class PlansRestController {

	@Autowired
	PlanService planService;
	
	Logger logger= LoggerFactory.getLogger(PlansRestController.class);

	@PostMapping("/plan")
	public ResponseEntity<String> createPlan(@RequestBody PlanDTO planDto) {
		logger.info("Create plan execution started...");
		boolean createPlan = planService.createPlan(planDto);
		if (createPlan) {
			logger.info("Create plan execution completed...");
			return new ResponseEntity<>("Plan saved Successfully!", HttpStatus.CREATED);			
		}
		
		return new ResponseEntity<>("Error in saving the Plan Successfully!", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

	@GetMapping("/plan/{id}")
	public ResponseEntity<?> getPlan(@PathVariable("id") Integer id) {
		logger.info("Get plan execution started......................................................................");
		PlanDTO plan = planService.getPlan(id);
		if (plan != null)
		{
			logger.info("Get plan execution completed...");
			return new ResponseEntity<>(plan, HttpStatus.OK);
		}
		return new ResponseEntity<>("No Plan is available!", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<PlanDTO>> getPlans() {
		logger.info("Get plans execution started...");
		List<PlanDTO> plans = planService.getPlans();
		logger.info("Get plans execution completed...");
			return new ResponseEntity<>(plans, HttpStatus.OK);
	}

	@PutMapping("/plan/{planId}/{activeSw}")
	public ResponseEntity<String> updatePlan(@PathVariable("planId")Integer planId, @PathVariable("activeSw") String activeSw) {
		logger.info("Update plan execution started...");
		boolean status = planService.updatePlan(planId, activeSw);
		if (status) {
			logger.info("Update plan execution completed...");
			return new ResponseEntity<>("Plan updated Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("No Plan is available!", HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
