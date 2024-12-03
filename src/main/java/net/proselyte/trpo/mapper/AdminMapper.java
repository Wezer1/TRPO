package net.proselyte.trpo.mapper;

import net.proselyte.trpo.dto.AdminDTO;
import net.proselyte.trpo.entity.Admin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AdminMapper {

    public abstract Admin toEntity(AdminDTO adminDTO);

    public abstract AdminDTO toDto(Admin admin);
}
