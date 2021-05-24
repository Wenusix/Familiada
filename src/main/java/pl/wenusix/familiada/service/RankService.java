package pl.wenusix.familiada.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.wenusix.familiada.domain.Rank;
import pl.wenusix.familiada.model.RankModel;
import pl.wenusix.familiada.repository.RankRepository;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Service
public class RankService {

    private final RankRepository rankRepository;

    public RankService(RankRepository rankRepository) {
        this.rankRepository = rankRepository;
    }

    public List<RankModel> getAllRankPlayers(){
        AtomicInteger atomicInteger = new AtomicInteger(1);
        return rankRepository
                .findAll()
                .stream()
                .sorted(Comparator.comparingInt(Rank::getPoints).reversed())
                .map(v -> new RankModel(v, atomicInteger.getAndIncrement()))
                .collect(Collectors.toList());

    }

    public RankModel getOnceRankPlayer(long rankId){
        return getAllRankPlayers()
                .stream()
                .filter(v -> v.getRankId() == rankId)
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
