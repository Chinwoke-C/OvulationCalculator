package com.ZuppLife.Tracker.zuppLifeCycleTracker.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OvulationTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate nextFlowDate;
    private LocalDate ovulationDate;
    @ElementCollection
    private List<LocalDate> safePeriods;
    @ElementCollection
    private List<LocalDate> fertilePeriods;
}
