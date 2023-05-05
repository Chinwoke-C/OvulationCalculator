package com.ZuppLife.Tracker.zuppLifeCycleTracker.data.repositories;

import com.ZuppLife.Tracker.zuppLifeCycleTracker.data.models.OvulationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OvulationInfoRepository extends JpaRepository<OvulationInfo, Long> {
}
