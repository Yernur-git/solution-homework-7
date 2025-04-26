import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Season implements Iterable<Episode> {
    private List<Episode> episodes = new ArrayList<>();

    public Season() {
        this.episodes = new ArrayList<>();
    }

    public void addEpisode(Episode e) {
        episodes.add(e);
    }

    public int episodeCount() {
        return episodes.size();
    }

    public Episode getEpisode(int index) {
        return episodes.get(index);
    }

    public EpisodeIterator normalIterator() {
        return new SeasonIterator(episodes);
    }

    public EpisodeIterator reverseIterator() {
        return new ReverseSeasonIterator(episodes);
    }

    public EpisodeIterator shuffleIterator() {
        return new ShuffleSeasonIterator(episodes);
    }

    @Override
    public Iterator<Episode> iterator() {
        return new Iterator<Episode>() {
            private EpisodeIterator it = normalIterator();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Episode next() {
                return it.next();
            }
        };
    }
}
