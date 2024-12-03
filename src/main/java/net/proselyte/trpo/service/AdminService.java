package net.proselyte.trpo.service;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.proselyte.trpo.dto.AdminDTO;
import net.proselyte.trpo.dto.BoxDTO;
import net.proselyte.trpo.entity.Admin;
import net.proselyte.trpo.entity.Box;
import net.proselyte.trpo.exceptions.NoSuchException;
import net.proselyte.trpo.mapper.AdminMapper;
import net.proselyte.trpo.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final AdminMapper adminMapper;

    @Transactional
    public List<AdminDTO> getAllAdmins() {
        log.info("Get all boxes");
        if(adminRepository.findAll().isEmpty()){
            throw new NoSuchException("No admins");
        }
        return adminRepository.findAll().stream().map(adminMapper :: toDto).collect(Collectors.toList());
    }
    @Transactional
    public AdminDTO getAdmin(Integer adminId) {
        log.info("Get admin by id: {} ", adminId);
        Optional<Admin> adminOptional = Optional.ofNullable(adminRepository.findById(adminId)
                .orElseThrow(()->new NoSuchException("There is no admin with ID = " + adminId + " in DB")));
        return adminMapper.toDto(adminOptional.get());
    }
    @Transactional
    public AdminDTO saveAdmin(AdminDTO adminDTO) {
        log.info("Saving admin: {}", adminDTO);
        Admin savedAdmin = adminRepository.save(adminMapper.toEntity(adminDTO));
        return adminMapper.toDto(savedAdmin);
    }

    @Transactional
    public void deleteAdmin(Integer adminId) {
        log.info("Delete admin");
        if(adminRepository.findById(adminId).isEmpty()){
            throw new NoSuchException("There is no admin with ID = "+ adminId + " in Database");
        }
        adminRepository.deleteById(adminId);
    }

}

