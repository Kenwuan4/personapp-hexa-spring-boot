package co.edu.javeriana.as.personapp.terminal.mapper;

import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Profession;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.terminal.model.EstudiosModelCli;

public class EstudiosMapperCli {

    public EstudiosModelCli fromDomainToAdapterCli(Study study) {
        EstudiosModelCli estudiosModelCli = new EstudiosModelCli();
        estudiosModelCli.setFechaGraduacion(study.getGraduationDate());
        estudiosModelCli.setUniversidad(study.getUniversityName());
        estudiosModelCli.setPersonaId(study.getPerson().getIdentification());
        estudiosModelCli.setProfesionId(study.getProfession().getIdentification());
        return estudiosModelCli;
    }

    public Study fromAdapterToDomainCli(EstudiosModelCli estudiosModelCli, Person person, Profession profession) {
        return new Study(person, profession, estudiosModelCli.getFechaGraduacion(), estudiosModelCli.getUniversidad());
    }

}
