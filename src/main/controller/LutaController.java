import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lutas")
public class LutaController {

    private final LutaRepository lutaRepository;

    @Autowired
    public LutaController(LutaRepository lutaRepository) {
        this.lutaRepository = lutaRepository;
    }

    @GetMapping
    public List<Luta> getAllLutas() {
        return lutaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Luta getLutaById(@PathVariable Long id) {
        return lutaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Luta não encontrada com o ID: " + id));
    }

    @PostMapping
    public Luta createLuta(@RequestBody Luta luta) {
        return lutaRepository.save(luta);
    }

    @PutMapping("/{id}")
    public Luta updateLuta(@PathVariable Long id, @RequestBody Luta updatedLuta) {
        return lutaRepository.findById(id)
                .map(luta -> {
                    luta.setLocal(updatedLuta.getLocal());
                    luta.setVitoria(updatedLuta.getVitoria());
                    return lutaRepository.save(luta);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Luta não encontrada com o ID: " + id));
    }

    @DeleteMapping("/{id}")
    public void deleteLuta(@PathVariable Long id) {
        Luta existingLuta = lutaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Luta não encontrada com o ID: " + id));
        lutaRepository.delete(existingLuta);
    }
}

