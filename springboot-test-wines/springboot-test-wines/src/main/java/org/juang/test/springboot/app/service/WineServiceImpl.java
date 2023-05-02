package org.juang.test.springboot.app.service;

import java.util.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.juang.test.springboot.app.models.Owner;
import org.juang.test.springboot.app.models.Wine;
import org.juang.test.springboot.app.repository.WineRepository;
import org.juang.test.springboot.app.response.WineResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;

@Service
public class WineServiceImpl implements WineService {

    @Autowired
    private WineRepository wineRepository;


    public WineServiceImpl() {
    }

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
    public ResponseEntity<WineResponseRest> save(Wine wine) {
        WineResponseRest response = new WineResponseRest();

        if (wine.getId() != null) {
            response.setMetadata("Response Status BAD_REQUEST", "400", "ID field should not be sent");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (wine.getName() == null) {
            response.setMetadata("Response Status BAD_REQUEST", "400", "Invalid name field");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (wine.getWinery() == null) {
            response.setMetadata("Response Status BAD_REQUEST", "400", "Invalid winery field");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (wine.getAño() == 0 || wine.getAño() <= 1601 || wine.getAño() >=2023) {
            response.setMetadata("Response Status BAD_REQUEST", "400", "Invalid year field : field year must be present and greater than 1601 or the current year");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        // Saving the Wine entity to the database
        try {
            List<Wine> list = new ArrayList<>();
            Wine wineGuardado = wineRepository.save(wine);

            // Creating the response with the saved Wine entity
            if (wineGuardado != null) {
                list.add(wineGuardado);
                response.getWineResponse().setWine(list);
                response.setMetadata("Response Status OK", "200", "Successfully saved wine in database");
            }

        } catch (Exception e) {
            e.getStackTrace();
            response.setMetadata("Response Status INTERNAL_SERVER_ERROR", "500", "INTERNAL_SERVER_ERROR");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
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
    public ResponseEntity<WineResponseRest> updateWineById(Long id,Wine wine) {
        
        WineResponseRest response = new WineResponseRest();
        Optional<Wine> optionalWine = wineRepository.findById(id);

        if (optionalWine.isPresent()) {

            Wine existingWine = optionalWine.get();

            if (wine.getName() == null || wine.getName().isEmpty()) {
                response.setMetadata("Response Status BAD_REQUEST", "400", "Invalid name field");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            if (wine.getWinery()  == null || wine.getWinery().isEmpty()) {
                response.setMetadata("Response Status BAD_REQUEST", "400", "Invalid winery field");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            if (wine.getId() != null) {
                response.setMetadata("Response Status BAD_REQUEST", "400", "ID field should not be sent");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            if (wine.getAño() <= 0) {
                response.setMetadata("Response Status BAD_REQUEST", "400", "Invalid año field");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }


            try {

                existingWine.setName(wine.getName());
                existingWine.setWinery(wine.getWinery());
                existingWine.setAño(wine.getAño());

                Wine updatedWine = wineRepository.save(existingWine);

                if (updatedWine != null) {
                    List<Wine> wineList = new ArrayList<>();
                    wineList.add(updatedWine);
                    response.getWineResponse().setWine(wineList);
                    response.setMetadata("Response Status OK", "200", "Successfully updated wine in database");
                }
            } catch (Exception e) {
                response.setMetadata("Response Status INTERNAL_SERVER_ERROR", "500", "Could not save wine in database");
                return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            response.setMetadata("Response Status NOT_FOUND","404" , "Could not delete wine id");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
        /*
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
        }*/
    }

}

