package br.com.ViniciusGuedes.LaborLawsuitControl.domain.dtos.progress;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record ProgressResponseDto(
        Long progressId,
        LocalDate progressDate,
        LocalDate expectedDate,
        LocalTime expectedTime,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) { }
