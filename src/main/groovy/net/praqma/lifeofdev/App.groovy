package net.praqma.lifeofdev


class App {
    String getGreeting() {
        return 'Hello world.'
    }

    static void main(String[] args) {
        GameLoop g = new GameLoop()
        g.addActor(new Developer())
        //do 10 iterations:
        (0..10).each {
          g.step()
          g.print_state()
        }
        
    }
}
