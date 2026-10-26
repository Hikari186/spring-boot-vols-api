package art.vol.b1.vol;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController @RequestMapping ("/api/vols")
public class VolController {

    private final VolService volService;

    public VolController(VolService volService) {
        this.volService = volService;
    }

    @GetMapping
    public List<VolResponse> getVols(@RequestParam(required = false) String origine) {
        return volService.rechercher(origine);
    }

    @GetMapping ("/{id}")
    public VolResponse getVolById(@PathVariable Long id) {
        return volService.getVolById(id);
    }

    @PostMapping 
    public ResponseEntity<VolResponse> createVol(@Valid @RequestBody VolRequest volRequest) {
        VolResponse vol = volService.creerVol(volRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(vol.id())
                .toUri();
        return ResponseEntity.created(location).body(vol);
    }



}