package com.ynov.muscleup.service;

import com.ynov.muscleup.model.Gym;
import com.ynov.muscleup.model.InscriptionGym;
import com.ynov.muscleup.model.utils.IdRequest;
import com.ynov.muscleup.repository.GymRepository;
import com.ynov.muscleup.repository.InscriptionGymRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InscriptionGymService {
    private static final Logger logger = LogManager.getLogger(InscriptionGymService.class);

    @Autowired
    GymRepository gymRepository;

    @Autowired
    InscriptionGymRepository inscriptionGymRepository;
    @Autowired
    CustomerService customerService;

    public InscriptionGym signUpToGym(IdRequest gymId) {
        //TODO Test this method
        Date date = new Date();
        Optional<Gym> gym = gymRepository.findById(gymId.getId());

        if (gym.isEmpty()) {
            logger.error("Id does not exist in Gym");
            return null;
        }

        InscriptionGym inscriptionGym = new InscriptionGym();
        inscriptionGym.setGym(gym.get());
        inscriptionGym.setDate(date);
        inscriptionGym.setCustomer(customerService.getCurrentCustomer());

        return inscriptionGymRepository.save(inscriptionGym);



    }
}
