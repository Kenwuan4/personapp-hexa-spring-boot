package co.edu.javeriana.as.personapp.terminal.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudiosModelCli {
    private Integer profesionId;
    private Integer personaId;
    private LocalDate fechaGraduacion;
    private String universidad;
}
