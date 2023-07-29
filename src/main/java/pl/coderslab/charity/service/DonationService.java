package pl.coderslab.charity.service;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;

@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final CategoryRepository categoryRepository;
    private final InstitutionRepository institutionRepository;


    public DonationService(DonationRepository donationRepository, CategoryRepository categoryRepository, InstitutionRepository institutionRepository) {
        this.donationRepository = donationRepository;
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
    }

    public int sumOfAllDonatedBags() {
        int sum = 0;
        List<Donation> donations = donationRepository.findAll();
        for(Donation donation : donations) {
            sum = sum + donation.getQuantity();
        }
        return sum;
    }

    public int totalDonations(){
        return donationRepository.findAll().size();
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category){
        return categoryRepository.save(category);

    }

    public List<Institution> getAllInstitutions(){
        return institutionRepository.findAll();
    }
    public Donation saveDonation(Donation donation){
        return donationRepository.save(donation);


    }

}
