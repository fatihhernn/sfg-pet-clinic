package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

//CommandLineRunner : we can initialize data

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService,
                      VetService vetService,
                      PetTypeService petTypeService,
                      SpecialityService specialityService,
                      VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService=petTypeService;
        this.specialityService=specialityService;
        this.visitService=visitService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count=petTypeService.findAll().size();
        if (count==0){
            loadData();
        }
    }

    private void loadData() {
        PetType dog=new PetType();
        dog.setName("Dog");

        PetType savedDogPetType=petTypeService.save(dog);

        PetType cat=new PetType();
        dog.setName("Cat");

        PetType savedCatPetType=petTypeService.save(cat);

        Speciality radiology=new Speciality();
        radiology.setDescription("radiology");
        Speciality savedRadiology=specialityService.save(radiology);

        Speciality surgery=new Speciality();
        surgery.setDescription("surgery");
        Speciality savedSurgery=specialityService.save(surgery);

        Speciality dentistry=new Speciality();
        surgery.setDescription("dentistry");
        Speciality saveDentistry=specialityService.save(dentistry);

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

        Visit catVisit=new Visit();
        catVisit.setPet(fionasPet);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("Snezzy kitty");

        visitService.save(catVisit);

        System.out.println("---LOADED OWNERS----");

        Vet vet1=new Vet();
        vet1.setFirstName("Furkan");
        vet1.setLastName("Eren");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2=new Vet();
        vet2.setFirstName("Hasan");
        vet2.setLastName("Eren");
        vet2.getSpecialities().add(savedSurgery);

        vetService.save(vet2);

        System.out.println("---LOADED VETS----");
    }
}
