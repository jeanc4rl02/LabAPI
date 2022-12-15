package com.LabAPI.laboratoryAPI.Controller;

import com.LabAPI.laboratoryAPI.services.AffiliateService;
import com.LabAPI.laboratoryAPI.entities.Affiliate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/controller/affiliate")
public class AffiliateController {
    private AffiliateService affiliateService;
    @Autowired
    public AffiliateController(AffiliateService affiliateService) {
        this.affiliateService = affiliateService;
    }

    @GetMapping
    public ResponseEntity<List<Affiliate>> getList(){
        List<Affiliate> findAffiliates = affiliateService.getAllAffiliates();
        if(findAffiliates.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(affiliateService.getAllAffiliates());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Affiliate> getById(@PathVariable Long id){
        Optional<Affiliate> findAffiliate = affiliateService.getOneAffiliate(id);
        if(findAffiliate.isPresent()){
            return ResponseEntity.ok(findAffiliate.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Affiliate> post(@RequestBody Affiliate affiliate){
        Affiliate saveAffiliate= affiliateService.postAffilliate(affiliate);
        if(saveAffiliate.getMail() != null){
            affiliateService.postAffilliate(affiliate);
            return ResponseEntity.status(201).body(affiliate);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping
    public ResponseEntity<String> put(@RequestBody Affiliate affiliate){
        Optional<Affiliate> findAffiliate= affiliateService.getOneAffiliate(affiliate.getId());
        if(findAffiliate.isPresent()){
            affiliateService.putAffiliate(affiliate);
            return ResponseEntity.status(201).body("Updated the affiliate with ID: "+affiliate.getId());
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No affiliate with ID: "+affiliate.getId()+ " was found in the database.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Affiliate> findAffiliate = affiliateService.getOneAffiliate(id);
        if(findAffiliate.isPresent()){
            affiliateService.deleteAffiliate(id);
            return ResponseEntity.ok("The removal of the affiliate with the ID: " +id+ " has been successfully completed.");
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No affiliate with ID: "+id+ " was found in the database.");
        }
    }
}