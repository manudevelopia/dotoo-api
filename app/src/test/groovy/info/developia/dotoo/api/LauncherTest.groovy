package info.developia.dotoo.api

import info.developia.Launcher
import spock.lang.Specification

class LauncherTest extends Specification {
    def "application should be started"() {
        when:
        new Launcher()
        then:
        noExceptionThrown()
    }
}
