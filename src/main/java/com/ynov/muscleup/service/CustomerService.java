package com.ynov.muscleup.service;

import com.ynov.muscleup.model.*;
import com.ynov.muscleup.model.rank_args.Category;
import com.ynov.muscleup.repository.CustomerRepository;
import com.ynov.muscleup.repository.RankRepository;
import com.ynov.muscleup.repository.SeanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;
    private SeanceRepository seanceRepository;
    private RankRepository rankRepository;

    public Customer getCurrentCustomer() {
        Authentication authenticationToken = SecurityContextHolder.getContext().getAuthentication();
        return (Customer)authenticationToken.getPrincipal();
    }

    public Customer updateCustomer(Customer customer) {
        Customer customerToUpdate = getCurrentCustomer();
        customerToUpdate.updateIfNotNull(customer);
        return customerRepository.save(customerToUpdate);
    }

    public StatisticsResponse getStatistics() {
        Customer customer = getCurrentCustomer();
        List<Seance> seances = seanceRepository.findAllByCustomer(customer);
        StatisticsResponse statisticsResponse = new StatisticsResponse();
        statisticsResponse.setTotalSeance(seances.size());
        long time = 0;
        Double seanceWeight = 0d;
        int seriesCount = 0;
        int numberOfRep = 0;
        for (Seance seance : seances) {
            time += seance.getEndDate().getTime() - seance.getStartDate().getTime();
            seanceWeight += seance.getWeight();
            for (ProgramSeance programSeance : seance.getProgramSeances()) {
                for (Series series : programSeance.getSeries()) {
                    numberOfRep += series.getNumberOfRep();
                }
                seriesCount += programSeance.getSeries().size();
            }
        }
        statisticsResponse.setAverageSeanceTime(time/seances.size() / 1000);//for second
        statisticsResponse.setAverageSeanceWeight(seanceWeight/seances.size());
        statisticsResponse.setAverageSeriesRep((double)numberOfRep/(double)seriesCount);

        return statisticsResponse;
    }

    public List<RankResponse> getRank() {
        Customer customer = getCurrentCustomer();
        List<RankResponse> rankResponseList = new ArrayList<>();

        List<Seance> seances = seanceRepository.findAllByCustomer(customer);
        //Category ASSIDUITY
        RankResponse rankResponseAssiduity = new RankResponse();
        rankResponseAssiduity.setCategory(Category.ASSIDUITY);
        rankResponseAssiduity.setRanks(rankRepository.findAllByCategory(Category.ASSIDUITY));
        rankResponseAssiduity.setScore((double) seances.size());

        //Category PERFORMANCE
        RankResponse rankResponsePerformance = new RankResponse();
        rankResponsePerformance.setCategory(Category.PERFORMANCE);
        rankResponsePerformance.setRanks(rankRepository.findAllByCategory(Category.PERFORMANCE));
        rankResponsePerformance.setScore(seances.stream().mapToDouble(Seance::getScore).sum());

        rankResponseList.add(rankResponseAssiduity);
        rankResponseList.add(rankResponsePerformance);
        return rankResponseList;
    }
}
