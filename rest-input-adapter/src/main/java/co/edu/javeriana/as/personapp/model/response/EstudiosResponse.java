package co.edu.javeriana.as.personapp.model.response;

import co.edu.javeriana.as.personapp.model.request.EstudiosRequest;

public class EstudiosResponse extends EstudiosRequest {
    private String Status;

    public EstudiosResponse(Integer personId, Integer professionId, String graduationDate, String universityName,
            String database, String status) {
        super(personId, professionId, graduationDate, universityName, database);
        this.Status = status;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
