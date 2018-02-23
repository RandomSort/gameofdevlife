package net.praqma.lifeofdev


class App {
    String getGreeting() {
        return 'Hello world.'
    }

    static void main(String[] args) {
        println new App().greeting
    }
}
