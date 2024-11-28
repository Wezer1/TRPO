package net.proselyte.trpo.mapper;

import net.proselyte.trpo.dto.ClientDTO;
import net.proselyte.trpo.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ClientMapper {

    public abstract Client toEntity(ClientDTO clientDTO);

    public abstract ClientDTO toDto(Client client);
}
