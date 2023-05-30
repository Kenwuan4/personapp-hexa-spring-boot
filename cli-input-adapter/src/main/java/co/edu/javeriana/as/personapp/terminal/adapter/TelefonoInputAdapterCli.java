package co.edu.javeriana.as.personapp.terminal.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import co.edu.javeriana.as.personapp.application.port.in.PersonInputPort;
import co.edu.javeriana.as.personapp.application.port.in.PhoneInputPort;
import co.edu.javeriana.as.personapp.application.port.out.PersonOutputPort;
import co.edu.javeriana.as.personapp.application.port.out.PhoneOutputPort;
import co.edu.javeriana.as.personapp.application.usecase.PersonUseCase;
import co.edu.javeriana.as.personapp.application.usecase.PhoneUseCase;
import co.edu.javeriana.as.personapp.common.annotations.Adapter;
import co.edu.javeriana.as.personapp.common.exceptions.InvalidOptionException;
import co.edu.javeriana.as.personapp.common.setup.DatabaseOption;
import co.edu.javeriana.as.personapp.domain.Person;
import co.edu.javeriana.as.personapp.domain.Phone;
import co.edu.javeriana.as.personapp.terminal.mapper.TelefonoMapperCli;
import co.edu.javeriana.as.personapp.terminal.model.TelefonoModelCli;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Adapter
public class TelefonoInputAdapterCli {

    @Autowired
    @Qualifier("phoneOutputAdapterMaria")
    private PhoneOutputPort phoneOutputPortMaria;

    @Autowired
    @Qualifier("personOutputAdapterMaria")
    private PersonOutputPort personOutputPortMaria;

    @Autowired
    @Qualifier("personOutputAdapterMongo")
    private PersonOutputPort personOutputPortMongo;

    @Autowired
    @Qualifier("phoneOutputAdapterMongo")
    private PhoneOutputPort phoneOutputPortMongo;

    @Autowired
    private TelefonoMapperCli telefonoMapperCli;

    PhoneInputPort phoneInputPort;

    PersonInputPort personInputPort;

    public void setPhoneOutputPortInjection(String dboption) throws InvalidOptionException {
        if (dboption.equalsIgnoreCase(DatabaseOption.MARIA.toString())) {
            phoneInputPort = new PhoneUseCase(phoneOutputPortMaria);
            personInputPort = new PersonUseCase(personOutputPortMaria);
        } else if (dboption.equalsIgnoreCase(DatabaseOption.MONGO.toString())) {
            phoneInputPort = new PhoneUseCase(phoneOutputPortMongo);
            personInputPort = new PersonUseCase(personOutputPortMongo);
        } else {
            throw new InvalidOptionException("Invalid option " + dboption);
        }
    }

    public void historial() {
        log.info("Into historial PersonaEntity in Input Adapter");
        phoneInputPort.findAll().stream()
                .map(telefonoMapperCli::fromDomainToAdapterCli)
                .forEach(System.out::println);
    }

    public void crearTelefono(TelefonoModelCli telefonoModelCli) {
        try {
            Person person = personInputPort.findOne(telefonoModelCli.getDuenioId());
            Phone phone = phoneInputPort.create(telefonoMapperCli.fromAdapterToDomainCli((telefonoModelCli), person));
            System.out.println("Telefono creado exitosamente");
            System.out.println(phone);
        } catch (Exception e) {
            System.out.println("El telefono no se pudo crear");
        }
    }

    public void obtenerTelefono(String numero) {
        try {
            System.out.println(phoneInputPort.findOne(numero));
        } catch (Exception e) {
            System.out.println("El telefono " + numero + " no se pudo encontrar");
        }
    }

    public void editarNumero(TelefonoModelCli telefonoModelCli) {
        try {
            Person person = personInputPort.findOne(telefonoModelCli.getDuenioId());
            Phone phone2 = phoneInputPort.edit(telefonoModelCli.getNumero(),
                    telefonoMapperCli.fromAdapterToDomainCli(telefonoModelCli, person));
            System.out.println("Telefono actualizado correctamente " + phone2);

        } catch (Exception e) {
            System.out.println("El telefono " + telefonoModelCli.getNumero() + " no se pudo encontrar");
        }
    }

    public void eliminarNumero(String numero) {
        try {
            phoneInputPort.drop(numero);
            System.out.println("El numero " + numero + " se ha eliminado correctamente");
        } catch (Exception e) {
            System.out.println("No se pudo eliminar el numero " + numero);
        }
    }

}
