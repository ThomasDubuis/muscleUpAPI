package com.ynov.muscleup.service;

import com.ynov.muscleup.model.Customer;
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
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GymService {

    private static final Logger logger = LogManager.getLogger(GymService.class);

    private static final String GYM_ID_NOT_EXIST = "Id does not exist in Gym";

    @Autowired
    GymRepository gymRepository;

    @Autowired
    InscriptionGymRepository inscriptionGymRepository;

    @Autowired
    CustomerService customerService;


    public Gym addGym(Gym gym) {
        Optional<Gym> gymOptional = gymRepository.findByName(gym.getName());
        if (gymOptional.isEmpty()) {
            return gymRepository.save(gym);
        }else {
            logger.warn("Gym already exist : {}", gym.getName());
            return gymOptional.get();
        }
    }

    public Gym deleteGym(IdRequest request) {
        Optional<Gym> gymOptional = gymRepository.findById(request.getId());
        if (gymOptional.isPresent()) {
            gymRepository.delete(gymOptional.get());
            return gymOptional.get();
        }else {
            logger.error(GYM_ID_NOT_EXIST);
            return null;
        }
    }

    public Gym updateGym(Gym request) {
        Optional<Gym> gymOptional = gymRepository.findById(request.getId());
        if (gymOptional.isPresent()) {
            Gym gym = gymOptional.get();
            gym.updateIfNotNull(request);
            return gymRepository.save(gym);
        }else {
            logger.error(GYM_ID_NOT_EXIST);
            return null;
        }
    }

    public List<Gym> getGyms() {
        return gymRepository.findAll();
    }

    public List<Gym> getMyGyms() {
        Customer customer = customerService.getCurrentCustomer();
        List<InscriptionGym> inscriptionGyms = inscriptionGymRepository.findInscriptionGymsByCustomer(customer);
        return inscriptionGyms.stream().map(InscriptionGym::getGym).toList();
    }

    public InscriptionGym signUpToGym(IdRequest gymId) {
        Date date = new Date();
        Optional<Gym> gym = gymRepository.findById(gymId.getId());

        if (gym.isEmpty()) {
            logger.error(GYM_ID_NOT_EXIST);
            return null;
        }

        InscriptionGym inscriptionGym = new InscriptionGym();
        inscriptionGym.setGym(gym.get());
        inscriptionGym.setDate(date);
        inscriptionGym.setCustomer(customerService.getCurrentCustomer());

        List<InscriptionGym> inscriptionGymSubscribeList = inscriptionGymRepository.findInscriptionGymsByCustomer(inscriptionGym.getCustomer());

        if (inscriptionGymSubscribeList.isEmpty() || inscriptionGymSubscribeList.stream().noneMatch(e -> e.getGym().getId().equals(inscriptionGym.getGym().getId()))) {
            return inscriptionGymRepository.save(inscriptionGym);
        }else {
            logger.error("Customer already subscribed to gym");
            return null;
        }

    }

    public Gym getGymIfCustomerIsRegistered(Customer customer, String gymId) {
        List<InscriptionGym> inscriptionGyms = inscriptionGymRepository.findInscriptionGymsByCustomer(customer);
        Gym gym = null;

        for (InscriptionGym inscriptionGym : inscriptionGyms) {
            if (gymId.equals(inscriptionGym.getGym().getId())){
                gym = inscriptionGym.getGym();
            }
        }
        return gym;
    }

}
