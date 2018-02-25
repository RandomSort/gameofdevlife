package net.praqma.lifeofdev


class App {
    String getGreeting() {
        return 'Hello world.'
    }

    static void main(String[] args) {
        GameLoop g = new GameLoop()
        g.addActor(new Developer())
        g.addActor(new Developer())
        g.step()
        g.step()
    }
}
