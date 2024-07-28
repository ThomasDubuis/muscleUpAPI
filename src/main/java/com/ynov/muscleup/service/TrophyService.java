package com.ynov.muscleup.service;

import com.ynov.muscleup.model.Seance;
import com.ynov.muscleup.model.Series;
import com.ynov.muscleup.model.trophy.Trophy;
import com.ynov.muscleup.model.trophy.TrophyName;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class TrophyService {
    private static final Logger logger = LogManager.getLogger(TrophyService.class);

    private SeanceService seanceService;

    public List<Trophy> getMyTrophy() {
        List<Trophy> trophyList = new ArrayList<>();

        List<Seance> allSeances = seanceService.getAllSeanceByCustomer();
        List<Series> allSeries = allSeances.stream()
                .flatMap(seance -> seance.getProgramSeances().stream())
                .flatMap(programSeance -> programSeance.getSeries().stream())
                .toList();

        for (TrophyName trophyName : TrophyName.values()) {
            switch (trophyName) {
                case FIRST_SEANCE -> trophyList.add(trophyFirstSeance(allSeances));
                case TEN_SEANCE -> trophyList.add(trophyTenSeance(allSeances));
                case FIFTY_SEANCE -> trophyList.add(trophyFiftySeance(allSeances));
                case HUNDRED_SEANCE -> trophyList.add(trophyHundredSeance(allSeances));
                case THOUSAND_SEANCE -> trophyList.add(trophyThousandSeance(allSeances));
                case ASSIDUITY_ONE_PER_WEEK_STREAK_5 -> trophyList.add(trophyAssiduityOnePerWeekStreak5(allSeances));
                case ONE_TON -> trophyList.add(trophyOneTon(allSeries));
                case TEN_TON -> trophyList.add(trophyTenTon(allSeries));
                case HUNDRED_TON -> trophyList.add(trophyHundredTon(allSeries));
                case THOUSAND_TON -> trophyList.add(trophyThousandTon(allSeries));
                case TEN_THOUSAND_TON -> trophyList.add(trophyTenThousandTon(allSeries));
            }
            logger.debug("treated trophy: {}", trophyName);
        }
        return trophyList;
    }

    private Trophy trophyTenThousandTon(List<Series> allSeries) {
        double weight = allSeries.stream().mapToDouble(Series::getWeight).sum();
        return new Trophy("TenThousandTon", weight > 10000, weight + "/10000.0 tons");
    }

    private Trophy trophyThousandTon(List<Series> allSeries) {
        double weight = allSeries.stream().mapToDouble(Series::getWeight).sum();
        return new Trophy("ThousandTon", weight > 1000, weight + "/1000.0 tons");
    }

    private Trophy trophyHundredTon(List<Series> allSeries) {
        double weight = allSeries.stream().mapToDouble(Series::getWeight).sum();
        return new Trophy("HundredTon", weight > 100, weight + "/100.0 tons");
    }

    private Trophy trophyTenTon(List<Series> allSeries) {
        double weight = allSeries.stream().mapToDouble(Series::getWeight).sum();
        return new Trophy("TenTon", weight > 10, weight + "/10.0 tons");
    }

    private Trophy trophyOneTon(List<Series> allSeries) {
        double weight = allSeries.stream().mapToDouble(Series::getWeight).sum();
        return new Trophy("OneTon", weight > 1, weight + "/1.0 ton");
    }

    private Trophy trophyAssiduityOnePerWeekStreak5(List<Seance> allSeances) {
        boolean isStreak5 = false;
        Date lastSeanceDate = null;
        int streak = 0;
        for (Seance seance : allSeances.stream().sorted(Comparator.comparing(Seance::getStartDate)).toList()) {
            if (lastSeanceDate == null) {
                lastSeanceDate = seance.getStartDate();
                streak = 1;
            }else if (lastSeanceDate.getTime() - seance.getStartDate().getTime() > 5 * 60 * 1000) {
                streak++;
                if (streak >= 5) {
                    isStreak5 = true;
                }
            }else {
                lastSeanceDate = null;
                isStreak5 = false;
            }
        }

        return new Trophy("assiduityOnePerWeekStreak5", isStreak5, streak + "/5");
    }

    private Trophy trophyThousandSeance(List<Seance> allSeances) {
        return new Trophy("thousandSeance", allSeances.size() >= 1000, allSeances.size() + "/1000 seances");
    }

    private Trophy trophyHundredSeance(List<Seance> allSeances) {
        return new Trophy("hundredSeance", allSeances.size() >= 100, allSeances.size() + "/100 seances");
    }

    private Trophy trophyFiftySeance(List<Seance> allSeances) {
        return new Trophy("fiftySeance", allSeances.size() >= 50, allSeances.size() + "/50 seances");
    }

    private Trophy trophyTenSeance(List<Seance> allSeances) {
        return new Trophy("tenSeance", allSeances.size() >= 10, allSeances.size() + "/10 seances");
    }

    private Trophy trophyFirstSeance(List<Seance> allSeances) {
        return new Trophy("firstSeance", !allSeances.isEmpty(), allSeances.size() + "/1 seance");
    }
}
