package com.ynov.muscleup.service;

import com.ynov.muscleup.model.Gym;
import com.ynov.muscleup.model.utils.IdRequest;
import com.ynov.muscleup.repository.GymRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GymService {

    private static final Logger logger = LogManager.getLogger(GymService.class);

    @Autowired
    GymRepository gymRepository;

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
            logger.warn("Id does not exist in Gym");
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
            logger.warn("Id does not exist in Gym");
            return null;
        }
    }
}
