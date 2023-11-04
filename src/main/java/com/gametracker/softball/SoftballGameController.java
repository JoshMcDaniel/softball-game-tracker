package com.gametracker.softball;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class SoftballGameController {
    public SoftballGameService softballGameService;

    public SoftballGameController(SoftballGameService softballGameService) {
        this.softballGameService = softballGameService;
    }

    @PostMapping("/create")
    public String createSoftballGame(@RequestBody SoftballGame softballGame) throws  InterruptedException, ExecutionException {
        return softballGameService.createSoftballGame(softballGame);
    }

    @GetMapping("/get")
    public SoftballGame getSoftballGame(@RequestParam String documentId) throws  InterruptedException, ExecutionException {
        return softballGameService.getSoftballGame(documentId);
    }

    @PutMapping("/update")
    public String updateSoftballGame(@RequestBody SoftballGame softballGame) throws  InterruptedException, ExecutionException {
        return softballGameService.updateSoftballGame(softballGame);
    }

    @DeleteMapping("/delete")
    public String deleteSoftballGame(@RequestParam String documentId) throws  InterruptedException, ExecutionException {
        return softballGameService.deleteSoftballGame(documentId);
    }
}
