package com.gametracker.softball;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class SoftballGameController {
    public SoftballGameService softballGameService;

    public SoftballGameController(SoftballGameService softballGameService) {
        this.softballGameService = softballGameService;
    }

    @PostMapping("/createGame")
    public String createSoftballGame(@RequestBody SoftballGameCreate softballGame)
            throws InterruptedException, ExecutionException {
        return softballGameService.createSoftballGame(softballGame);
    }

    @GetMapping("/getGameById")
    @CrossOrigin()
    public SoftballGame getSoftballGame(@RequestParam String documentId)
            throws InterruptedException, ExecutionException {
        return softballGameService.getSoftballGameById(documentId);
    }

    @GetMapping("/getAllActiveGames")
    @CrossOrigin()
    public List<SoftballGame> getSoftballGame() throws InterruptedException, ExecutionException {
        return softballGameService.getAllActiveSoftballGames();
    }

    @PutMapping("/updateGame")
    public String updateSoftballGame(@RequestBody SoftballGame softballGame)
            throws InterruptedException, ExecutionException {
        return softballGameService.updateSoftballGame(softballGame);
    }

    @DeleteMapping("/deleteGame")
    public String deleteSoftballGame(@RequestParam String documentId) throws InterruptedException, ExecutionException {
        return softballGameService.deleteSoftballGame(documentId);
    }
}
