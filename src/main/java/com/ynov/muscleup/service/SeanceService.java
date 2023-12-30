package com.ynov.muscleup.service;

import com.ynov.muscleup.model.*;
import com.ynov.muscleup.model.seance.SeanceRequest;
import com.ynov.muscleup.repository.ProgramSeanceRepository;
import com.ynov.muscleup.repository.SeanceRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SeanceService {
    private static final Logger logger = LogManager.getLogger(SeanceService.class);

    @Autowired
    GymService gymService;

    @Autowired
    CustomerService customerService;

    @Autowired
    SeanceRepository seanceRepository;

    @Autowired
    ExerciseService exerciseService;

    @Autowired
    ProgramSeanceRepository programSeanceRepository;

    public Seance postCompleteSeance(SeanceRequest seanceRequest) {
        Customer customer = customerService.getCurrentCustomer();

        Gym gym = gymService.getGymIfCustomerIsRegistered(customer, seanceRequest.getGymId());
        if (gym == null) {
            logger.error("Gym id not exist in database or customer not register in this gym : {}", seanceRequest.getGymId());
            return null;
        }

        List<ProgramSeance> programSeances = new ArrayList<>();

        for (SeanceRequest.ProgramSeanceRequest programSeanceRequest : seanceRequest.getProgramSeances()) {
            if(exerciseService.checkIfExerciseExist(programSeanceRequest.getExerciseId())) {
                ProgramSeance programSeance = ProgramSeance.builder()
                        .exercise(Exercise.builder().id(programSeanceRequest.getExerciseId()).build())
                        .numberOfRep(programSeanceRequest.getNumberOfRep())
                        .weight(programSeanceRequest.getWeight())
                        .build();

                programSeances.add(programSeance);
            }else {
                logger.error("Exercise Id not exist in database : {}", programSeanceRequest.getExerciseId());
                return null;
            }
        }

        Seance seance = Seance.builder()
                .score(123d)
                .date(seanceRequest.getDate())
                .customer(customer)
                .gym(gym)
                .build();

        //TODO : mettre les programSeance dans l'objet seance pour pouvoir uniquement retourner seance
        Seance seanceRegistered = seanceRepository.save(seance);

        for (ProgramSeance programSeance: programSeances) {
            programSeance.setSeance(seanceRegistered);
        }
        programSeanceRepository.saveAll(programSeances);

        //TODO : Calculate new rank (créer une methode calculate new rank dans rankService qui permet de mettre a jour tous les ranks)

        return seanceRegistered;
    }

    public Optional<Seance> getSeanceById(String seanceId) {
        return seanceRepository.findById(seanceId);
    }
}
