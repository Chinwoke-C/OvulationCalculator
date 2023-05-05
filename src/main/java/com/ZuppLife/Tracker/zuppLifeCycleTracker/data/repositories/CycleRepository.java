package com.ZuppLife.Tracker.zuppLifeCycleTracker.data.repositories;

import com.ZuppLife.Tracker.zuppLifeCycleTracker.data.models.Cycle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CycleRepository extends JpaRepository<Cycle, Long> {

}
