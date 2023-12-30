package com.ynov.muscleup.service;

import com.ynov.muscleup.model.Exercise;
import com.ynov.muscleup.model.utils.IdRequest;
import com.ynov.muscleup.repository.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private static final Logger logger = LogManager.getLogger(ExerciseService.class);

    @Autowired
    ExerciseRepository exerciseRepository;

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
}
