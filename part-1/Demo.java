public class Demo {
    public static void main(String[] args) {
        Series series = new Series();

        Season season1 = new Season();
        season1.addEpisode(new Episode("Pilot", 45));
        season1.addEpisode(new Episode("Second Episode", 42));
        season1.addEpisode(new Episode("Cliffhanger", 48));
        series.addSeason(season1);

        Season season2 = new Season();
        season2.addEpisode(new Episode("Season Premiere", 46));
        season2.addEpisode(new Episode("Midseason", 44));
        season2.addEpisode(new Episode("Finale", 50));
        series.addSeason(season2);

        System.out.println("=== Normal Order ===");
        EpisodeIterator normalIt = season1.normalIterator();
        while (normalIt.hasNext()) {
            System.out.println(normalIt.next());
        }

        System.out.println("\n=== Reverse Order ===");
        EpisodeIterator reverseIt = season1.reverseIterator();
        while (reverseIt.hasNext()) {
            System.out.println(reverseIt.next());
        }

        System.out.println("\n=== Shuffle Order ===");
        EpisodeIterator shuffleIt = season1.shuffleIterator();
        while (shuffleIt.hasNext()) {
            System.out.println(shuffleIt.next());
        }

        System.out.println("\n=== For-each (Iterable) ===");
        for (Episode e : season1) {
            System.out.println(e);
        }

        System.out.println("\n=== Binge Mode (Whole Series) ===");
        EpisodeIterator bingeIt = series.bingeIterator();
        while (bingeIt.hasNext()) {
            System.out.println(bingeIt.next());
        }
    }
}
