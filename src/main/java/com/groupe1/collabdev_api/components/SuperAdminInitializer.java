package com.groupe1.collabdev_api.components;

import com.groupe1.collabdev_api.services.AdministrateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SuperAdminInitializer implements CommandLineRunner {


    AdministrateurService administrateurService;
    SuperAdminInitializer(AdministrateurService administrateurService){
        this.administrateurService = administrateurService;
    }

    @Override
    public void run(String... args) throws Exception {
        administrateurService.superAdmin();
    }
}
