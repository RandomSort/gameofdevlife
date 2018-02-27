package net.praqma.lifeofdev.git

import net.praqma.lifeofdev.Work
import net.praqma.lifeofdev.actor.Developer
import spock.lang.Specification

class RepositoryTest extends Specification {

    def "First MakeCommit works on master branch"() {

        setup:
        def r = new Repository()

        when:
        r.makeCommit(new Work(), new Developer(), "some message")

        Branch b = (Branch) r.HEAD

        then:
        r.HEAD.class == Branch

        b.name == "master"

        r.commits.size() == 1

    }

    def "addCommit updates HEAD properly on new Repository"() {

        setup:
        def r = new Repository()

        def c = r.newRepoCommit(new Work(), new Developer(), "some message")

        when:
        r.addCommit(c)

        Branch b = (Branch) r.HEAD // HEAD is 'master'-branch

        then:

        b.commit == c

    }
}
