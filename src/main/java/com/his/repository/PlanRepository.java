package com.his.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.his.entity.Plan;

public interface PlanRepository  extends JpaRepository<Plan, Integer>{

}
