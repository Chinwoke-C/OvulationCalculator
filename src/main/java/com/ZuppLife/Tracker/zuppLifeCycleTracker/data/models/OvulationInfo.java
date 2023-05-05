package com.ZuppLife.Tracker.zuppLifeCycleTracker.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class OvulationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate nextFlowDate;
    private LocalDate ovulationDate;
    @OneToOne
    private DateRange safePeriod;
    @OneToOne
    private DateRange fertilePeriod;

    public OvulationInfo(LocalDate nextFlowDate, LocalDate ovulationDate, DateRange safePeriod, DateRange fertilePeriod) {
        this.nextFlowDate = nextFlowDate;
        this.ovulationDate = ovulationDate;
        this.safePeriod = safePeriod;
        this.fertilePeriod = fertilePeriod;
    }
}
