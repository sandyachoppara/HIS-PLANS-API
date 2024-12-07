package com.his.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dto.PlanDTO;
import com.his.entity.Plan;
import com.his.repository.PlanRepository;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	PlanRepository planRepo;

	@Override
	public boolean createPlan(PlanDTO planDto) {
		Plan plan = new Plan();
		BeanUtils.copyProperties(planDto, plan);
		Plan savedPlan = planRepo.save(plan);
		if (savedPlan != null)
			return true;
		else
			return false;
	}

	@Override
	public PlanDTO getPlan(Integer id) {
		 Plan plan = planRepo.findById(id).orElseThrow();
		PlanDTO planDto = new PlanDTO();
			BeanUtils.copyProperties(plan, planDto);
			return planDto;
	}

	@Override
	public List<PlanDTO> getPlans() {
		List<Plan> plansList = planRepo.findAll();
		List<PlanDTO> planDtoList = new ArrayList<PlanDTO>();
		plansList.forEach(plan -> {
			PlanDTO planDto = new PlanDTO();
			BeanUtils.copyProperties(plan, planDto);
			planDtoList.add(planDto);
		});
		return planDtoList;
	}

	@Override
	public boolean updatePlan(Integer planId, String status) {
		Optional<Plan> optionalPlan = planRepo.findById(planId);
		if (optionalPlan.isPresent()) {
			Plan plan = optionalPlan.get();
			plan.setActiveSw(status);
			planRepo.save(plan);
			return true;
		}
		return false;
	}

}
