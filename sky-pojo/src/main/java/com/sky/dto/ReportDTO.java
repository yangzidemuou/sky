package com.sky.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportDTO {
    private LocalDate time;
    private Object total;
}
