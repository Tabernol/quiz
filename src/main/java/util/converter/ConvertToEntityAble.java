package util.converter;

public interface ConvertToEntityAble<E, Dto> {

    E mapToEntity(Dto dto);
}
