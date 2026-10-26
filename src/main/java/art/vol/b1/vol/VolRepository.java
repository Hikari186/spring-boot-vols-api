package art.vol.b1.vol;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VolRepository extends JpaRepository<Vol, Long> {
    
    List<Vol> findByOrigine(String origine);

}
