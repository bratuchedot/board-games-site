package mk.ukim.finki.board.games.site.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 1000)
    private String shortDescription;

    @Column(length = 4000)
    private String description;

    private Integer numberOfPlayers;

    private Integer playingTimeInMinutes;

    private Integer ageRating;

    private LocalDate releaseDate;

    private Integer likes;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Category> categories;

    @ManyToOne
    private Publisher publisher;

    public Game(String name, String shortDescription, String description, Integer numberOfPlayers, Integer playingTimeInMinutes, Integer ageRating, LocalDate releaseDate, List<Category> categories, Publisher publisher) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.description = description;
        this.numberOfPlayers = numberOfPlayers;
        this.playingTimeInMinutes = playingTimeInMinutes;
        this.ageRating = ageRating;
        this.releaseDate = releaseDate;
        this.likes = 0;
        this.categories = categories;
        this.publisher = publisher;
    }
}
