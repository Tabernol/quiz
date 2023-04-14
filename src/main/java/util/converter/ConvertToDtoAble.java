package util.converter;

import org.apache.log4j.spi.RepositorySelector;

import java.util.Optional;

public interface ConvertToDtoAble<Dto,E> {

    Dto mapToDto(E entity);
}
