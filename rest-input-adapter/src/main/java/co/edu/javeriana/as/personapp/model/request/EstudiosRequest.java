package co.edu.javeriana.as.personapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstudiosRequest {
    private Integer personId;
    private Integer professionId;
    private String graduationDate;
    private String universityName;
    private String database;
}
