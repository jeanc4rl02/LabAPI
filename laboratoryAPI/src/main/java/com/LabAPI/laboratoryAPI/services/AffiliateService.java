package com.LabAPI.laboratoryAPI.services;

import com.LabAPI.laboratoryAPI.entities.Affiliate;
import org.springframework.beans.factory.annotation.Autowired;
import com.LabAPI.laboratoryAPI.repositories.AffiliateRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AffiliateService {
    private AffiliateRepository affiliateRepository;

    @Autowired
    public AffiliateService(AffiliateRepository affiliateRepository) {
        this.affiliateRepository = affiliateRepository;
    }

    public Affiliate postAffilliate(Affiliate affiliate){
        return affiliateRepository.save(affiliate);
    }

    public Optional<Affiliate> getOneAffiliate(Long id){
        return affiliateRepository.findById(id);
    }

    public List<Affiliate> getAllAffiliates(){
        return affiliateRepository.findAll();
    }

    public void putAffiliate(Affiliate affiliate){
        affiliateRepository.save(affiliate);
    }

    public void deleteAffiliate(Long id){
        affiliateRepository.deleteById(id);
    }
}
