package org.juang.test.springboot.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.juang.test.springboot.app.models.Wine;
import org.juang.test.springboot.app.repository.WineRepository;
import org.juang.test.springboot.app.response.WineResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WineServiceImpl implements WineService {

    @Autowired
    private WineRepository wineRepository;

    @Override
    public ResponseEntity<List<Wine>> getAllWines() {
        List<Wine> wines = wineRepository.findAll();
        return new ResponseEntity<>(wines, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Wine> getWineById(Long id) {
        Optional<Wine> optionalWine = wineRepository.findById(id);
        if (optionalWine.isPresent()) {
            Wine wine = optionalWine.get();
            return new ResponseEntity<>(wine, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Wine> createWine(Wine wine) {
        if (wine.getName() == null || wine.getWinery() == null || wine.getAño() == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Wine savedWine = wineRepository.save(wine);
        return new ResponseEntity<>(savedWine, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<WineResponseRest> deleteWineById(Long id) {
        WineResponseRest response = new WineResponseRest();
        List<Wine> list = new ArrayList<>();

        try {
            Optional<Wine> wineBuscado = wineRepository.findById(id);

            if (wineBuscado.isPresent()) {
                list.add(wineBuscado.get());
                response.getWineResponse().setWine(list);
                wineRepository.deleteById(id);
                response.setMetadata("Response Status Ok", "200", "Successfully Response");
            } else {
                response.setMetadata("Response Status NOT_FOUND","404" , "Could not delete wine id");
                return new ResponseEntity<WineResponseRest>(response, HttpStatus.NOT_FOUND);
            }
            }catch (Exception e){
            e.getStackTrace();
            response.setMetadata("Response Status INTERNAL_SERVER_ERROR","500" , "INTERNAL_SERVER_ERROR");

            return new ResponseEntity<WineResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);


        }
        return new ResponseEntity<WineResponseRest>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Wine> updateWineById(Long id,Wine wine) {
        Optional<Wine> optionalWine = wineRepository.findById(id);
        if (optionalWine.isPresent()) {
            Wine existingWine = optionalWine.get();
            existingWine.setName(wine.getName());
            existingWine.setWinery(wine.getWinery());
            existingWine.setAño(wine.getAño());
            Wine updatedWine = wineRepository.save(existingWine);
            return new ResponseEntity<>(updatedWine, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
