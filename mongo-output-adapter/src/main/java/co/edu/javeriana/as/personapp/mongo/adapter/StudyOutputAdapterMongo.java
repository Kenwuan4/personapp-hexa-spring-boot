package co.edu.javeriana.as.personapp.mongo.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.mongodb.MongoWriteException;
import com.mongodb.lang.NonNull;

import co.edu.javeriana.as.personapp.application.port.out.StudyOutputPort;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.domain.Study;
import co.edu.javeriana.as.personapp.mongo.document.EstudiosDocument;
import co.edu.javeriana.as.personapp.mongo.mapper.EstudiosMapperMongo;
import co.edu.javeriana.as.personapp.mongo.repository.EstudiosRepositoryMongo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter("studyOutputPortMongo")
public class StudyOutputAdapterMongo implements StudyOutputPort {

    @Autowired
    private EstudiosRepositoryMongo estudiosRepositoryMongo;
    @Autowired
    private EstudiosMapperMongo estudiosMapperMongo;

    @Override
    public Study save(Study study) {
        log.debug("Into save on Adapter MongoDB");
        try {
            EstudiosDocument persistedPersona = estudiosRepositoryMongo
                    .save(estudiosMapperMongo.fromDomainToAdapter(study));
            return estudiosMapperMongo.fromAdapterToDomain(persistedPersona);
        } catch (MongoWriteException e) {
            log.warn(e.getMessage());
            return study;
        }
    }

    @Override
    public Boolean delete(Integer person_identification, Integer profession_identification) {
        log.debug("Into delete on Adapter MongoDB");
        estudiosRepositoryMongo.deleteById(validId(person_identification, profession_identification));
        return estudiosRepositoryMongo.findById(validId(person_identification, profession_identification)).isEmpty();
    }

    @Override
    public List<Study> find() {
        log.debug("Into find on Adapter MongoDB");
        return estudiosRepositoryMongo.findAll().stream().map(estudiosMapperMongo::fromAdapterToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Study findById(Integer person_identification, Integer profession_identification) {
        log.debug("Into findById on Adapter MongoDB");
        if (estudiosRepositoryMongo.findById(validId(person_identification, profession_identification)).isEmpty()) {
            return null;
        } else {
            return estudiosMapperMongo.fromAdapterToDomain(
                    estudiosRepositoryMongo.findById(validId(person_identification, profession_identification))
                            .get());
        }
    }

    private String validId(@NonNull Integer identificationPerson, @NonNull Integer identificationProfession) {
        return identificationPerson + "-" + identificationProfession;
    }

}
