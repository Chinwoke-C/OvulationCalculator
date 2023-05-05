package com.ZuppLife.Tracker.zuppLifeCycleTracker.service;

import com.ZuppLife.Tracker.zuppLifeCycleTracker.data.dto.OvulationResultDto;
import com.ZuppLife.Tracker.zuppLifeCycleTracker.data.models.Cycle;
import com.ZuppLife.Tracker.zuppLifeCycleTracker.data.models.OvulationInfo;

import java.time.LocalDate;
import java.util.List;

public interface OvulationService {
    OvulationResultDto calculateOvulation(Cycle cycle);
//    OvulationInfo calculateOvulationInfo(Cycle cycle);
//    public List<OvulationInfo> calculateOvulationInfoForYear(Cycle cycle);
 OvulationInfo calculateOvulationInfo(Cycle cycle);
     List<OvulationResultDto> calculateOvulationInfoForYear(Cycle cycle);
}
