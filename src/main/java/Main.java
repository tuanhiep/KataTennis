import controller.Service;
import model.GameOverException;
import model.Match;
import view.TennisDisplay;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, GameOverException {

        Match match = new Match();

        Service service = new Service(match);

        Reader reader = new InputStreamReader(System.in);

        Writer writer = new OutputStreamWriter(System.out);

        TennisDisplay controller = new TennisDisplay(service, reader, writer);

        controller.begin();

    }

}
