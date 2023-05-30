package co.edu.javeriana.as.personapp.model.response;

import co.edu.javeriana.as.personapp.model.request.ProfesionRequest;

public class ProfesionResponse extends ProfesionRequest {
    private String status;

    public ProfesionResponse(Integer id, String name, String description, String database, String status) {
        super(id, name, description, database);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}