package art.vol.b1.vol;

import java.util.List;

import org.springframework.stereotype.Service;
import art.vol.b1.common.VolIntrouvableException;
import  org.springframework.transaction.annotation.Transactional;

@Service 
public class VolService {
    private final VolRepository volRepository;

    public VolService(VolRepository volRepository) {
        this.volRepository = volRepository;
    }
    @Transactional 
    public VolResponse creerVol(VolRequest volRequest) {
        Vol vol = new Vol(volRequest.numero(), volRequest.origine(), volRequest.destination(), volRequest.dateDepart(), volRequest.placesDisponibles());
        return VolResponse.from(volRepository.save(vol));

    }
    @Transactional(readOnly = true)
    public VolResponse getVolById(Long id) {
        Vol vol = volRepository.findById(id)
                .orElseThrow(() -> new VolIntrouvableException(id));
        return VolResponse.from(vol);
    }

    @Transactional(readOnly = true)
    public List<VolResponse> rechercher(String origine) {
        List<Vol> vols;
        if(origine == null || origine.isBlank())
            vols = volRepository.findAll();
        else 
            vols = volRepository.findByOrigine(origine);
            
        return vols.stream()
                    .map(VolResponse::from)
                    .toList();
        
       
    }



}
