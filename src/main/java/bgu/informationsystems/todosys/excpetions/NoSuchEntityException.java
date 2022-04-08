package bgu.informationsystems.todosys.excpetions;

import java.util.NoSuchElementException;

public class NoSuchEntityException extends NoSuchElementException {

    private String entityType;
    private String id;

    public NoSuchEntityException(String entityType, String id) {
        this.entityType = entityType;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format("A %s with the id '%s' does not exist.", entityType, id);
    }

}
