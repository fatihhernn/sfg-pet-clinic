package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

//CommandLineRunner : we can initialize data

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService,PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService=petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog=new PetType();
        dog.setName("Dog");

        PetType savedDogPetType=petTypeService.save(dog);

        PetType cat=new PetType();
        dog.setName("Cat");

        PetType savedCatPetType=petTypeService.save(cat);

        Owner owner1=new Owner();
        owner1.setFirstName("Fatih");
        owner1.setLastName("Eren");
        owner1.setAddress("123 Eyüpsultan");
        owner1.setCity("İstanbul");
        owner1.setTelephone("1234567890");

        Pet fatihsPet=new Pet();
        fatihsPet.setPetType(savedDogPetType);
        fatihsPet.setOwner(owner1);
        fatihsPet.setBirthDate(LocalDate.now());
        fatihsPet.setName("Rosco");
        owner1.getPets().add(fatihsPet);

        ownerService.save(owner1);

        Owner owner2=new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Anderson");
        owner2.setAddress("123 Bostancı");
        owner2.setCity("İzmir");
        owner2.setTelephone("09875326478");

        Pet fionasPet=new Pet();
        fionasPet.setPetType(savedCatPetType);
        fionasPet.setOwner(owner2);
        fionasPet.setBirthDate(LocalDate.now());
        fionasPet.setName("Minnak");
        owner2.getPets().add(fionasPet);

        ownerService.save(owner2);

        System.out.println("---LOADED OWNERS----");

        Vet vet1=new Vet();
        vet1.setFirstName("Furkan");
        vet1.setLastName("Eren");

        vetService.save(vet1);

        Vet vet2=new Vet();
        vet2.setFirstName("Hasan");
        vet2.setLastName("Eren");

        vetService.save(vet2);

        System.out.println("---LOADED VETS----");
    }
}
