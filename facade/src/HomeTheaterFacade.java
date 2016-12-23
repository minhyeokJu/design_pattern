public class HomeTheaterFacade {
    private Amplifier amplifier;
    private Tuner tuner;
    private CDPlayer cdPlayer;
    private DVDPlayer dvdPlayer;
    private Screen screen;
    private PopcornMachine popcornMachine;
    private Light light;
    private Projector projector;

    public void watchMovie(Dvd dvd) {
        popcornMachine.on();
        popcornMachine.pop();

        light.dim(5);

        screen.down();

        projector.on();
        projector.setInput(dvd);
        projector.wideScreenMode();

        amplifier.on();
        amplifier.setDvd(dvd);
        amplifier.setSurroundSounde());
        amplifier.setVolume(5);

        dvdPlayer.on();
        dvdPlayer.play();
    }

    // etc.. endMovie(), listenToCD(), endCD(), listendToRadio(), endRadio()
}
