package info.developia;

import info.developia.dotoo.api.persistence.Persistence;
import info.developia.dotoo.api.server.GraphqlService;
import info.developia.gti.Gti;

public class Launcher {
    public static final Persistence persistence = new Persistence("info.developia.dotoo.api.repository.mapper");

    public static void main(String[] args) {
        Gti.inject().with(persistence).startOn(GraphqlService.class).start();
    }
}
