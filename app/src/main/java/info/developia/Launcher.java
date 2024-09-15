package info.developia;

import info.developia.dotoo.api.persistence.Persistence;
import info.developia.dotoo.api.server.GraphqlService;
import info.developia.gti.Gti;

public class Launcher {

    public static void main(String[] args) {
        Persistence persistence = new Persistence("info.developia.dotoo.api.repository.mapper");

        Gti.inject()
                .with(persistence)
                .startOn(GraphqlService.class).start();
    }
}
