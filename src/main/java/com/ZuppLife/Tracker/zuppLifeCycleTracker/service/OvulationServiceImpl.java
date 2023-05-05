package com.ZuppLife.Tracker.zuppLifeCycleTracker.service;

import com.ZuppLife.Tracker.zuppLifeCycleTracker.data.dto.OvulationResultDto;
import com.ZuppLife.Tracker.zuppLifeCycleTracker.data.models.Cycle;
import com.ZuppLife.Tracker.zuppLifeCycleTracker.data.models.DateRange;
import com.ZuppLife.Tracker.zuppLifeCycleTracker.data.models.OvulationInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class OvulationServiceImpl implements OvulationService{
    @Override
    public OvulationResultDto calculateOvulation(Cycle cycle) {

        LocalDate startDate = cycle.getStartDate();
        int cycleLength = cycle.getCycleLength();
        //calculate the next flow date

        if(startDate == null) {
            OvulationResultDto resultDto = new OvulationResultDto();
            resultDto.setNextFlowDate("");
            resultDto.setOvulationDate("");
            resultDto.setSafePeriods(Collections.emptyList());
            resultDto.setFertilePeriods(Collections.emptyList());
            resultDto.setErrorMessage("Invalid input: Start date is null");
            return resultDto;
        }

        LocalDate nextFlowDate = startDate.plusDays(cycleLength);
        //calculate ovulation date

        LocalDate ovulationDate = startDate.plusDays(cycleLength - 14);
        //calculate the safe period
        List<LocalDate> safePeriods = new ArrayList<>();
        safePeriods.add(startDate.plusDays(1));
        safePeriods.add(startDate.plusDays(cycleLength - 8));

        List<LocalDate> fertilePeriods = new ArrayList<>();
        fertilePeriods.add(startDate.plusDays(cycleLength - 14));
        fertilePeriods.add(startDate.plusDays(cycleLength - 12));

        OvulationResultDto resultDto = new OvulationResultDto();
        resultDto.setNextFlowDate(nextFlowDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        resultDto.setOvulationDate(ovulationDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        List<String> safePeriodsString = new ArrayList<>();
        for (LocalDate date : safePeriods) {
            safePeriodsString.add(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        resultDto.setSafePeriods(safePeriodsString);
        List<String> fertilePeriodsString = new ArrayList<>();
        for (LocalDate date : fertilePeriods) {
            fertilePeriodsString.add(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        }
        resultDto.setFertilePeriods(fertilePeriodsString);

        return resultDto;

    }




    public OvulationInfo calculateOvulationInfo(Cycle cycle) {
        LocalDate startDate = cycle.getStartDate();
        int cycleLength = cycle.getCycleLength();
        LocalDate nextFlowDate = startDate.plusDays(cycleLength);
        LocalDate ovulationDate = nextFlowDate.minusDays(14);
        DateRange safePeriod = new DateRange(startDate.plusDays(18), nextFlowDate.minusDays(10));
        DateRange fertilePeriod = new DateRange(nextFlowDate.minusDays(19), nextFlowDate.minusDays(10));

        return new OvulationInfo(nextFlowDate, ovulationDate, safePeriod, fertilePeriod);
    }
@Override
public List<OvulationResultDto> calculateOvulationInfoForYear(Cycle cycle) {
    List<OvulationResultDto> ovulationResultDtoList = new ArrayList<>();

    //LocalDate startDate = cycle.getStartDate();
    int cycleLength = cycle.getCycleLength();

    // Calculate the ovulation info for the current month
    OvulationResultDto currentMonthResult = calculateOvulation(cycle);
    ovulationResultDtoList.add(currentMonthResult);

    // Calculate the ovulation info for the remaining months of the year
    LocalDate nextFlowDate = currentMonthResult.getNextFlowDateAsLocalDate(cycle);
    for (int i = 1; i < 12; i++) {
        if (nextFlowDate == null) {
            break; // or handle the null case as appropriate
        }
        nextFlowDate = nextFlowDate.plusDays(cycleLength);
        OvulationResultDto ovulationResultDto = calculateOvulation(new Cycle(nextFlowDate, cycleLength));
        ovulationResultDtoList.add(ovulationResultDto);
    }

    return ovulationResultDtoList;
}

//    public List<OvulationResultDto> calculateOvulationInfoForYear(Cycle cycle) {
//        String startDate = String.valueOf(cycle.getStartDate());
//        List<OvulationResultDto> ovulationResultList = new ArrayList<>();
//        for (int i = 0; i < 12; i++) {
//            OvulationResultDto ovulationResult = calculateOvulation(cycle);
//            ovulationResultList.add(ovulationResult);
//            startDate = ovulationResult.getNextFlowDate();
//           // getNextFlowDate().formatted(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
//
//        }
//
//    return ovulationResultList;
//    }

}


