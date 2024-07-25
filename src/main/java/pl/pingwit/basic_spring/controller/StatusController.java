package pl.pingwit.basic_spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pingwit.basic_spring.service.StatusService;

import java.util.List;

@RequestMapping
@RestController
public class StatusController {
    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public List<StatusDto> findAllStatuses(){
        return statusService.findAll();
    }
}