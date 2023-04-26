package org.juang.test.springboot.app.service;

import org.juang.test.springboot.app.models.Wine;
import org.juang.test.springboot.app.response.WineResponseRest;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface WineService {
    ResponseEntity<List<Wine>> getAllWines();
    ResponseEntity<Wine> getWineById(Long id);
    ResponseEntity<Wine> createWine(Wine wine);
    ResponseEntity<WineResponseRest> deleteWineById(Long id);
    ResponseEntity<Wine> updateWineById(Long id,Wine wine);
}
