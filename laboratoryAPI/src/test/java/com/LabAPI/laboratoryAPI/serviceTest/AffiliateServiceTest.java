package com.LabAPI.laboratoryAPI.serviceTest;

import com.LabAPI.laboratoryAPI.entities.Affiliate;
import com.LabAPI.laboratoryAPI.services.AffiliateService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class AffiliateServiceTest {
    @Autowired
    private AffiliateService affiliateService;
    // TEST OK -------------------------------------------------------------------------------------------------------
    @Test
    @Order(1)
    public void postAffiliateTestOK(){
        Affiliate affiliateToSave= new Affiliate("Jean", 22, "jeanc4rl02@gmail.com");
        Affiliate affilateSaved=affiliateService.postAffilliate(affiliateToSave);
        assertEquals(1L,affilateSaved.getId());
    }

    @Test
    @Order(2)
    public void findAffiliateByIdTestOK(){
        Long idToFind=1L;
        Optional<Affiliate> affiliateSearched = affiliateService.getOneAffiliate(idToFind);
        assertNotNull(affiliateSearched.get());
    }
    @Test
    @Order(3)
    public void findAffiliatesTestOK(){
        List<Affiliate> affiliates = affiliateService.getAllAffiliates();
        assertEquals(1,affiliates.size());
    }
    @Test
    @Order(4)
    public void putAffiliateTestOK(){
        Affiliate affiliateToUpdate= new Affiliate(1L,"Carlos", 23, "jean.carrillo@udea.edu.co");
        affiliateService.putAffiliate(affiliateToUpdate);
        Optional<Affiliate> affiliateUpdated=affiliateService.getOneAffiliate(1L);
        assertEquals("Carlos", affiliateUpdated.get().getName());
    }
    @Test
    @Order(5)
    public void deleteAffiliateTestOK() {
        Long idToDelete = 1L;
        affiliateService.deleteAffiliate(idToDelete);
        Optional<Affiliate> affiliateRemoved = affiliateService.getOneAffiliate(idToDelete);
        assertFalse(affiliateRemoved.isPresent());
    }

    //BAD TEST <-----------------------------------------------------------------------------------------------------------

    @Test
    @Order(6)
    public void postAffiliateBADtest(){
        Affiliate affiliateToSave= new Affiliate("Jean", 22, "jeanc4rl02@gmail.com");
        Affiliate affilateSaved=affiliateService.postAffilliate(affiliateToSave);
        assertNotEquals(1L, affilateSaved.getId());
    }

    @Test
    @Order(7)
    public void findAffiliateByIdBADtest(){
        Long idToFind=2L;
        Optional<Affiliate> affiliateSearched = affiliateService.getOneAffiliate(idToFind);
        assertNotEquals(affiliateSearched.get(), null);
    }

    @Test
    @Order(8)
    public void findAffiliatesBADtest(){
        List<Affiliate> affiliates = affiliateService.getAllAffiliates();
        assertNotEquals(2,affiliates.size());
    }

    @Test
    @Order(9)
    public void putAffiliateBADtest(){
        Affiliate affiliateToUpdate= new Affiliate(2L,"Carlos", 23, "jean.carrillo@udea.edu.co");
        affiliateService.putAffiliate(affiliateToUpdate);
        Optional<Affiliate> affiliateUpdated=affiliateService.getOneAffiliate(2L);
        assertNotSame("Jean", affiliateUpdated.get().getName());
    }

    @Test
    @Order(10)
    public void deleteAffiliateBADtest() {
        Long idToDelete = 2L;
        affiliateService.deleteAffiliate(idToDelete);
        Optional<Affiliate> affiliateRemoved = affiliateService.getOneAffiliate(idToDelete);
        assertTrue(!affiliateRemoved.isPresent());
    }
}
