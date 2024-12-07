package com.his.service;

import java.util.List;

import com.his.dto.PlanDTO;

public interface PlanService {
	boolean createPlan(PlanDTO plan);

	PlanDTO getPlan(Integer id);

	List<PlanDTO> getPlans();

	boolean updatePlan(Integer planId, String status);

	//boolean deletePlan(Integer id);
}
