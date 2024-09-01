package info.developia.dotoo.api

import info.developia.dotoo.api.model.Task
import spock.lang.Specification

class LauncherTest extends Specification {
    def "application should be started"() {
        when:
        def task = new Task(1,'title 1', false)
        then:
        with(task){
            id == 1
            title == 'title 1'
            done == false
        }
    }
}
