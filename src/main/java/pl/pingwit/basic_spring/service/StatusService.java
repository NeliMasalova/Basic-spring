package pl.pingwit.basic_spring.service;

import org.springframework.stereotype.Service;
import pl.pingwit.basic_spring.controller.StatusDto;
import pl.pingwit.basic_spring.repository.StatusEntity;
import pl.pingwit.basic_spring.repository.StatusRepository;

import java.util.List;

@Service
public class StatusService {
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<StatusDto> findAll() {
        List<StatusEntity> all = statusRepository.findAll();
        return all.stream()
                .map(entity -> new StatusDto(entity.getId(), entity.getName(), entity.getDescription()))
                .toList();
    }
}