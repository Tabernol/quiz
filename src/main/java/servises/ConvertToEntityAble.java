package servises;

public interface ConvertToEntityAble<E, Dto> {

    E mapToEntity(Dto dto);
}
