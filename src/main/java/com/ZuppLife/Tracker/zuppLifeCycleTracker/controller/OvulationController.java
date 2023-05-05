package com.ZuppLife.Tracker.zuppLifeCycleTracker.controller;

import com.ZuppLife.Tracker.zuppLifeCycleTracker.data.dto.OvulationResultDto;
import com.ZuppLife.Tracker.zuppLifeCycleTracker.data.models.Cycle;
import com.ZuppLife.Tracker.zuppLifeCycleTracker.data.models.OvulationInfo;
import com.ZuppLife.Tracker.zuppLifeCycleTracker.service.OvulationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin
@AllArgsConstructor
public class OvulationController {
    private final OvulationService ovulationService;

    @PostMapping("/ovulation")
    public OvulationResultDto calculateOvulation(@RequestBody Cycle cycle){
        return ovulationService.calculateOvulation(cycle);
    }
    @PostMapping("/ovulation-info-year")
    public ResponseEntity<List<OvulationResultDto>> calculateOvulationInfoForYear(@RequestBody Cycle cycle){
        List<OvulationResultDto> ovulationResultList = ovulationService.calculateOvulationInfoForYear(cycle);
        return new ResponseEntity<>(ovulationResultList, HttpStatus.OK);
    }

}
