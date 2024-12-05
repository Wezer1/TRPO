package net.proselyte.trpo.mapper;

import net.proselyte.trpo.dto.ClientDTO;
import net.proselyte.trpo.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring")
public abstract class ClientMapper {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Named("encodePassword")
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "password", target = "password", qualifiedByName = "encodePassword")
    public abstract Client toEntity(ClientDTO clientDTO);

    public abstract ClientDTO toDto(Client client);
}
