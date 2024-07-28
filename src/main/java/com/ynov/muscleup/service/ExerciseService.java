package com.ynov.muscleup.service;

import com.ynov.muscleup.model.*;
import com.ynov.muscleup.model.utils.IdRequest;
import com.ynov.muscleup.repository.ExerciseRepository;
import com.ynov.muscleup.repository.SeanceRepository;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ExerciseService {

    private static final Logger logger = LogManager.getLogger(ExerciseService.class);

    private ExerciseRepository exerciseRepository;
    private CustomerService customerService;
    private SeanceRepository seanceRepository;

    public Exercise addExercise(Exercise exercise) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findByName(exercise.getName());
        if (exerciseOptional.isEmpty()) {
            return exerciseRepository.save(exercise);
        }else {
            logger.warn("Exercise already exist : {}", exercise.getName());
            return exerciseOptional.get();
        }
    }

    public Exercise deleteExercise(IdRequest request) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(request.getId());
        if (exerciseOptional.isPresent()) {
            exerciseRepository.delete(exerciseOptional.get());
            return exerciseOptional.get();
        }else {
            logger.error("Id does not exist in Exercise");
            return null;
        }
    }

    public Exercise updateExercise(Exercise request) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(request.getId());
        if (exerciseOptional.isPresent()) {
            Exercise exercise = exerciseOptional.get();
            exercise.updateIfNotNull(request);

            return exerciseRepository.save(exercise);
        }else {
            logger.error("Id does not exist in Exercise");
            return null;
        }
    }

    public List<Exercise> getExercises() {
        return exerciseRepository.findAll();
    }

    public boolean checkIfExerciseExist(String exerciseId) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(exerciseId);
        return exerciseOptional.isPresent();
    }

    public Exercise getExerciseById(String exerciseId) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(exerciseId);
        return exerciseOptional.orElse(null);
    }

    public List<MyExercise> getMyExercises() {
        Map<String, MyExercise> myExercises = new HashMap<>();
        Customer customer = customerService.getCurrentCustomer();

        for (Seance seance : seanceRepository.findAllByCustomer(customer)) {
            Date startDate = seance.getStartDate();
            for (ProgramSeance programSeance : seance.getProgramSeances()) {
                String idExercise = programSeance.getExercise().getId();
                String nameExercise = programSeance.getExercise().getName();
                Double weight = null;
                for (Series series : programSeance.getSeries()) {
                    if (weight == null || series.getWeight() > weight) {
                        weight = series.getWeight();
                    }
                }
                myExercises.putIfAbsent(idExercise, new MyExercise(idExercise, nameExercise, new ArrayList<>()));
                myExercises.get(idExercise).getHistory().add(new MyExercise.ExerciseHistory(weight, startDate));
            }
        }
        return new ArrayList<>(myExercises.values());
    }
}
