package org.juang.test.springboot.app.controller;

import org.juang.test.springboot.app.models.Wine;
import org.juang.test.springboot.app.response.WineResponseRest;
import org.juang.test.springboot.app.service.WineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class WineController {

    @Autowired
    private WineService wineService;

    @GetMapping("/wines/")
    public ResponseEntity<List<Wine>> getAllWines() {
        ResponseEntity<List<Wine>> wines = wineService.getAllWines();
        return ResponseEntity.ok(wines.getBody());
    }

    @GetMapping("/wine/{id}")
    public ResponseEntity<Wine> getWineById(@PathVariable Long id) {
        Wine wine = wineService.getWineById(id).getBody();
        return wine != null ? ResponseEntity.ok(wine) : ResponseEntity.notFound().build();
    }

    @PostMapping("/wine/")
    public ResponseEntity<Wine> createWine(@Valid @RequestBody Wine wine) {
        Wine createdWine = wineService.createWine(wine).getBody();
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWine);
    }

    @PutMapping("/wine/{id}")
    public ResponseEntity<Wine> updateWineById(@PathVariable Long id,@Valid @RequestBody Wine wine) {
        Wine updatedWine = wineService.updateWineById(id, wine).getBody();
        return updatedWine != null ? ResponseEntity.ok(updatedWine) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/wine/{id}")
    public ResponseEntity<WineResponseRest> deleteWineById(@PathVariable Long id) {
        ResponseEntity<WineResponseRest> response = wineService.deleteWineById(id);
        return response;
    }
}
