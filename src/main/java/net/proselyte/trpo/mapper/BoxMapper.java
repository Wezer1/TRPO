package net.proselyte.trpo.mapper;

import net.proselyte.trpo.dto.BoxDTO;
import net.proselyte.trpo.entity.Box;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class BoxMapper {
    public abstract Box toEntity(BoxDTO boxDTO);

    public abstract BoxDTO toDto(Box box);
}
