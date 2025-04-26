import java.util.List;
import java.util.NoSuchElementException;

public class BingeIterator implements EpisodeIterator {
    private List<Season> seasons;
    private int currentSeason = 0;
    private EpisodeIterator currentIterator = null;

    public BingeIterator(List<Season> seasons) {
        this.seasons = seasons;
    }

    @Override
    public boolean hasNext() {
        if (currentIterator != null && currentIterator.hasNext()) {
            return true;
        }

        while (currentSeason < seasons.size()) {
            Season season = seasons.get(currentSeason);
            if (season.episodeCount() > 0) {
                currentIterator = season.normalIterator();
                currentSeason++;
                return true;
            }
            currentSeason++;
        }

        return false;
    }

    @Override
    public Episode next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return currentIterator.next();
    }
}
