package com.ZuppLife.Tracker.zuppLifeCycleTracker.data.dto;

import ch.qos.logback.core.pattern.color.CyanCompositeConverter;
import com.ZuppLife.Tracker.zuppLifeCycleTracker.data.models.Cycle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OvulationResultDto {
    private String nextFlowDate;
    private String ovulationDate;
    private String errorMessage;
    private List<String> safePeriods;
    private List<String> fertilePeriods;

    public LocalDate getNextFlowDateAsLocalDate(Cycle cycle) {
        LocalDate startDate = cycle.getStartDate();
        int cycleLength = cycle.getCycleLength();
        return startDate.plusDays(cycleLength);
    }
//      private LocalDate nextFlowDate;
//      private LocalDate ovulationDate;
//      private List<LocalDate> safePeriods;
//      private List<LocalDate> fertilePeriods;
}
