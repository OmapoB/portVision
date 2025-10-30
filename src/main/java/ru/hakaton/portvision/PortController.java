package ru.hakaton.portvision;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PortController {

    private final PortService portService;

    @GetMapping("/port/{id}")
    public PortDto getById(@PathVariable Integer id) {
        return portService.findById(id);
    }

    @GetMapping("/port")
    public List<PortDto> getAll() {
        return portService.findAll();
    }
}
