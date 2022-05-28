package com.example.geektrust.dto;

import com.example.geektrust.enums.PlanType;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Objects;

public class RenewalDetailsDTO {
    @Getter
    private LocalDate date;
    @Getter
    private PlanType planType;

    public RenewalDetailsDTO(LocalDate date, PlanType planType) {
        this.date = date;
        this.planType = planType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RenewalDetailsDTO that = (RenewalDetailsDTO) o;
        return Objects.equals(date, that.date) && planType == that.planType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, planType);
    }

    @Override
    public String toString() {
        return "RenewalDetailsDTO{" +
                "date=" + date +
                ", planType=" + planType +
                '}';
    }
}
