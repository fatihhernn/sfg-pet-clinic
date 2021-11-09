package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import guru.springframework.sfgpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//CommandLineRunner : we can initialize data

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;


    public DataLoader() {
        vetService=new VetServiceMap();
        ownerService=new OwnerServiceMap();

    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1=new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Fatih");
        owner1.setLastName("Eren");

        ownerService.save(owner1);

        Owner owner2=new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Anderson");

        ownerService.save(owner2);

        System.out.println("---LOADED OWNERS----");

        Vet vet1=new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Furkan");
        vet1.setLastName("Eren");

        vetService.save(vet1);

        Vet vet2=new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Hasan");
        vet2.setLastName("Eren");

        vetService.save(vet2);

        System.out.println("---LOADED VETS----");
    }
}
