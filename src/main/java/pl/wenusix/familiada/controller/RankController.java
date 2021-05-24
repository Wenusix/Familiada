package pl.wenusix.familiada.controller;

import org.springframework.web.bind.annotation.*;
import pl.wenusix.familiada.model.RankModel;
import pl.wenusix.familiada.service.RankService;

import java.util.List;

@RestController
@RequestMapping("rank")
@CrossOrigin
public class RankController {

    private final RankService rankService;

    public RankController(RankService rankService) {
        this.rankService = rankService;
    }


    @GetMapping
    public List<RankModel> getAllRankPlayers(){
        return rankService.getAllRankPlayers();

    }

    @GetMapping("{rankId}")
    public RankModel getRankByRankId(@PathVariable long rankId){
        return rankService.getOnceRankPlayer(rankId);
    }
}
