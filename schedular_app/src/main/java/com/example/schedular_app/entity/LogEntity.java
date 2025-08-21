package com.example.schedular_app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LOG_ENTITY")
public class LogEntity {

    @Id
//    @SequenceGenerator(name = "log_id_generator", allocationSize = 10,initialValue = 10)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "log_id_generator")
    private Long logId;

    private LocalDateTime logTimeStamp;

    @Enumerated(EnumType.STRING)
    private LogType logType;

    private String schedularType;
//    private SchedularType schedularType;

    private String description;
}
