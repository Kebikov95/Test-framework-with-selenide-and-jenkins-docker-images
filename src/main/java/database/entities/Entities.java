package database.entities;

import java.util.List;

public class Entities {

    private Entities() {
    }

    public static boolean isListContainsEntity(List<Entity> entities, Entity findEntity) {
        for (Entity entity : entities)
            if (entity.equals(findEntity)) return true;
        return false;
    }
}
