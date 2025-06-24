package kalitek.solutions.usermanagement.service;

import kalitek.solutions.common.exception.BusinessException;
import kalitek.solutions.usermanagement.model.Identifiable;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;

public class GenericService<T extends Identifiable<ID>, ID> {

    private final JpaRepository<T, ID> repo;
    private final String entityName;

    public GenericService(JpaRepository<T, ID> repo, String entityName) {
        this.repo = repo;
        this.entityName = entityName;
    }

    public T create(T entity) {
        return repo.save(entity);
    }

    public T read(ID id) {
        validateId(id);
        return repo.findById(id)
                .orElseThrow(() -> new BusinessException(
                        String.format("Aucun %s trouvé avec l'ID %s.", entityName, id)));
    }

    public T update(T entity) {
        ID id = entity.getId();
        validateId(id);

        T existing = repo.findById(id)
                .orElseThrow(() -> new BusinessException(
                        String.format("Aucun %s trouvé avec l'ID %s.", entityName, id)));

        BeanUtils.copyProperties(entity, existing, "id");

        return repo.save(existing);
    }

    public void delete(ID id) {
        validateId(id);

        repo.findById(id).ifPresentOrElse(
                e -> repo.deleteById(id),
                () -> { throw new BusinessException(
                        String.format("Aucun %s trouvé avec l'ID %s.", entityName, id)); }
        );
    }

    private void validateId(ID id) {
        if (id == null) {
            throw new BusinessException(
                    String.format("L'ID de %s est requis.", entityName));
        }
    }
}
