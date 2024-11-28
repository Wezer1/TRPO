package net.proselyte.trpo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.proselyte.trpo.dto.BoxDTO;
import net.proselyte.trpo.dto.ClientDTO;
import net.proselyte.trpo.entity.Box;
import net.proselyte.trpo.entity.Client;
import net.proselyte.trpo.exceptions.NoSuchException;
import net.proselyte.trpo.mapper.BoxMapper;
import net.proselyte.trpo.repository.BoxRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoxService {
    private final BoxRepository boxRepository;
    private final BoxMapper boxMapper;

    @Transactional
    public List<BoxDTO> getAllBoxes() {
        log.info("Get all boxes");
        if(boxRepository.findAll().isEmpty()){
            throw new NoSuchException("No boxes");
        }
        return boxRepository.findAll().stream().map(boxMapper :: toDto).collect(Collectors.toList());
    }

    @Transactional
    public BoxDTO getBoxById(Integer boxId){
        log.info("Get box by id: {} ", boxId);
        Optional<Box> boxOptional = Optional.ofNullable(boxRepository.findById(boxId)
                .orElseThrow(()->new NoSuchException("There is no box with ID = " + boxId + " in DB")));
        return boxMapper.toDto(boxOptional.get());
    }

    @Transactional
    public void deleteBox(Integer boxId) {
        log.info("Delete box");
        if(boxRepository.findById(boxId).isEmpty()){
            throw new NoSuchException("There is no box with ID = "+ boxId + " in Database");
        }
        boxRepository.deleteById(boxId);
    }

    @Transactional
    public BoxDTO changeBox(Integer boxId, BoxDTO boxDTO){
        Optional<Box> optionalBox = boxRepository.findById(boxId);
        if(optionalBox.isEmpty()){
            throw new NoSuchException("There is no box with ID = "+ boxId + " in Database");
        }else{
            Box existingBox = optionalBox.get();
            Box updatedClient = boxMapper.toEntity(boxDTO);
            existingBox.setPrice(updatedClient.getPrice());
            existingBox.setWeight(updatedClient.getWeight());
            existingBox.setIs_occupied(updatedClient.getIs_occupied());

            return boxMapper.toDto(boxRepository.save(existingBox));
        }
    }

    @Transactional
    public BoxDTO saveBox(BoxDTO boxDTO) {
        log.info("Saving box: {}", boxDTO);
        Box savedBox = boxRepository.save(boxMapper.toEntity(boxDTO));
        return boxMapper.toDto(savedBox);
    }
}
