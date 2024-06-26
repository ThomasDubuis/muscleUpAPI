package com.ynov.muscleup.service;

import com.ynov.muscleup.model.*;
import com.ynov.muscleup.model.seance.SeanceRequest;
import com.ynov.muscleup.model.utils.IdRequest;
import com.ynov.muscleup.repository.ProgramSeanceRepository;
import com.ynov.muscleup.repository.SeanceRepository;
import com.ynov.muscleup.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Autowired
    SeriesRepository seriesRepository;

    public ResponseEntity<BaseResponse<IdRequest>> postCompleteSeance(SeanceRequest seanceRequest) {
        Customer customer = customerService.getCurrentCustomer();

        Gym gym = gymService.getGymIfCustomerIsRegistered(customer, seanceRequest.getGymId());
        if (gym == null) {
            logger.error("Gym id not exist in database or customer not register in this gym : {}", seanceRequest.getGymId());
            return BaseResponse.error("Gym id not exist in database or customer not register in this gym : " + seanceRequest.getGymId());
        }

        List<ProgramSeance> programSeanceList = new ArrayList<>();

        double weight = 0d;
        double score = 0d;
        for (SeanceRequest.ProgramSeanceRequest programSeanceRequest : seanceRequest.getProgramSeances()) {
            if(exerciseService.checkIfExerciseExist(programSeanceRequest.getExerciseId())) {
                Exercise exercise = exerciseService.getExerciseById(programSeanceRequest.getExerciseId());
                List<Series> seriesList = new ArrayList<>();
                for (SeanceRequest.SeriesRequest seriesRequest : programSeanceRequest.getSeries()) {
                    Series series= Series.builder()
                            .numberOfRep(seriesRequest.getNumberOfRep())
                            .weight(seriesRequest.getWeight())
                            .build();
                    seriesList.add(series);


                    double diffWeight = seriesRequest.getWeight() - exercise.getBasicWeight();
                    Double calculatedOneRepScore;

                    if(diffWeight > 0) {
                        calculatedOneRepScore = exercise.getOneRepScore() + (diffWeight * exercise.getWeightMultiplier());
                    } else if(diffWeight < 0) {
                        calculatedOneRepScore = exercise.getOneRepScore() - (Math.abs(diffWeight) * exercise.getWeightMultiplier());
                    }else {
                        calculatedOneRepScore = exercise.getOneRepScore();
                    }
                    score += seriesRequest.getNumberOfRep() * calculatedOneRepScore;
                    weight += seriesRequest.getWeight();
                }


                ProgramSeance programSeance = new ProgramSeance();
                programSeance.setExercise(Exercise.builder().id(programSeanceRequest.getExerciseId()).build());
                programSeance.setSeries(seriesList);

                programSeanceList.add(programSeance);
            }else {
                logger.error("Exercise Id not exist in database : {}", programSeanceRequest.getExerciseId());
                return BaseResponse.error("Exercise Id not exist in database : " + programSeanceRequest.getExerciseId());
            }
        }

        Seance seance = Seance.builder()
                .score(score)
                .startDate(seanceRequest.getStartDate())
                .endDate(seanceRequest.getEndDate())
                .customer(customer)
                .gym(gym)
                .weight(weight)
                .build();

        Seance seanceRegistered = seanceRepository.save(seance);

        for (ProgramSeance programSeance: programSeanceList) {
            programSeance.setSeance(seanceRegistered);
            ProgramSeance programSeanceRegistred = programSeanceRepository.save(programSeance);
            for (Series series : programSeance.getSeries()){
                series.setProgramSeance(programSeanceRegistred);
                seriesRepository.save(series);
            }
        }

        return BaseResponse.ok(new IdRequest(seanceRegistered.getId()));
        //TODO : Voir pk lors du FindById dans cette methode ne get pas les program seance
        // alors que lorsque l'endpoint est appelé il les get bien
    }

    public Seance getSeanceById(String seanceId) {
        Optional<Seance> seance = seanceRepository.findById(seanceId);
        if (seance.isEmpty()){
            return null;
        }
        Customer customer = customerService.getCurrentCustomer();

        if (seance.get().getCustomer().getEmail().equals(customer.getEmail())){
            return seance.get();
        }else {
            return null;
        }
    }

    public List<Seance> getAllSeanceByCustomer() {
        Customer customer = customerService.getCurrentCustomer();
        return seanceRepository.findAllByCustomer(customer);
    }
}
