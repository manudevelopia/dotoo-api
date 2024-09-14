package info.developia.dotoo.api

import info.developia.dotoo.api.model.Task
import spock.lang.Specification

class LauncherTest extends Specification {
    def "application should be started"() {
        when:
        def task = new Task('b3c7e8ca-f7d2-45a1-9bb1-73afba4aa909', 'title 1', false)
        then:
        with(task) {
            id == 'b3c7e8ca-f7d2-45a1-9bb1-73afba4aa909'
            title == 'title 1'
            done == false
        }
    }
}
