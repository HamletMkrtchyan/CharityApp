package pl.coderslab.charity.service;

import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;

@Service
public class InstitutionService {
    private final InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public List<Institution> getAllInstitutions(){
        return institutionRepository.findAll();
    }

    public void deleteInstitution(Long id){
        Institution institution = institutionRepository.findById(id).orElseThrow(() -> new NotFoundException("Institution not found"));
        institution.setStatus(false);
        institutionRepository.save(institution);
    }
}
