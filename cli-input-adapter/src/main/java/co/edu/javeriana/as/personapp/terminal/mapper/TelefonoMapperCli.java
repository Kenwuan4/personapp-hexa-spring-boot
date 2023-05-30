package co.edu.javeriana.as.personapp.terminal.mapper;

import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.terminal.model.TelefonoModelCli;

public class TelefonoMapperCli {

    public TelefonoModelCli fromDomainToAdapterCli(Phone phone) {
        TelefonoModelCli telefonoModelCli = new TelefonoModelCli();
        telefonoModelCli.setNumero(phone.getNumber());
        telefonoModelCli.setCompania(phone.getCompany());
        telefonoModelCli.setDuenioId(phone.getOwner().getIdentification());
        return telefonoModelCli;
    }

    public Phone fromAdapterToDomainCli(TelefonoModelCli telefonoModelCli, Person owner) {
        Phone phone = new Phone();
        phone.setCompany(telefonoModelCli.getCompania());
        phone.setNumber(telefonoModelCli.getNumero());
        phone.setOwner(owner);
        return phone;
    }

}
