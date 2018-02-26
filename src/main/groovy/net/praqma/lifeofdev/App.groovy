package net.praqma.lifeofdev
import net.praqma.lifeofdev.git.*

class App {
    String getGreeting() {
        return 'Hello world.'
    }

    static void main(String[] args) {
        GameLoop g = new GameLoop()
        Developer a = new Developer()
        Work w = new Work()
        def changes = [w]

        Commit c = new Commit([], changes, a, "this is a random string")
        println(c)




    }
}
