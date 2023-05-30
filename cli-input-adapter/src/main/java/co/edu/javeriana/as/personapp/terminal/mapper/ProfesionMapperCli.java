package co.edu.javeriana.as.personapp.terminal.mapper;

import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.terminal.model.ProfesionModelCli;

public class ProfesionMapperCli {

    public ProfesionModelCli fromDomainToAdapterCli(Profession profession) {
        return new ProfesionModelCli(profession.getIdentification(), profession.getName(), profession.getDescription());
    }

    public Profession fromAdapterToDomain(ProfesionModelCli profesionModelCli) {
        Profession profession = new Profession();
        profession.setIdentification(profesionModelCli.getId());
        profession.setName(profesionModelCli.getNombre());
        profesionModelCli.setDescripcion(profesionModelCli.getDescripcion());
        return profession;
    }
}
