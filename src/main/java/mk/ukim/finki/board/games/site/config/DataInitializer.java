package mk.ukim.finki.board.games.site.config;

import mk.ukim.finki.board.games.site.model.Category;
import mk.ukim.finki.board.games.site.model.Game;
import mk.ukim.finki.board.games.site.model.Publisher;
import mk.ukim.finki.board.games.site.model.User;
import mk.ukim.finki.board.games.site.model.enumerations.Role;
import mk.ukim.finki.board.games.site.service.CategoryService;
import mk.ukim.finki.board.games.site.service.GameService;
import mk.ukim.finki.board.games.site.service.PublisherService;
import mk.ukim.finki.board.games.site.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Make sure to comment the line 23: @Component if you're starting the application for the
 * second time while using PostgreSQL. This code id used only to initialize some values for testing...
 * */
@Component
public class DataInitializer {
    private final CategoryService categoryService;
    private final GameService gameService;
    private final PublisherService publisherService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(CategoryService categoryService, GameService gameService, PublisherService publisherService, UserService userService, PasswordEncoder passwordEncoder) {
        this.categoryService = categoryService;
        this.gameService = gameService;
        this.publisherService = publisherService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initData() {

        // TODO: Add Publishers
        // Gloomhaven's
        Publisher cephalofairGames = new Publisher(
                "Cephalofair Games",
                "Jasperpassage 036, 2 hoog, 3254 KZ, Dinther, Gelderland, Netherlands");
        this.publisherService.addPublisher(cephalofairGames);

        // Pandemic Legacy: Season 1's
        Publisher zManGames = new Publisher(
                "Z-Man Games",
                "274 Orin Trace, Apt. 531, XF44 1ME, West Bennett, Arizona, Great Britain");
        this.publisherService.addPublisher(zManGames);

        // 7 Wonders Duel's
        Publisher reposProduction = new Publisher(
                "Repos Production",
                "Johannessenveien 3, Oppgang A, 6216, Lostrand,  Norway");
        this.publisherService.addPublisher(reposProduction);

        // Orleans's Manitoba's
        Publisher dlpGames = new Publisher(
                "dlp Games",
                "Florastr. 49a, Zimmer 596, 45077, Saschascheid, Bayern, Germany");
        this.publisherService.addPublisher(dlpGames);

        // Santorini's
        Publisher roxley = new Publisher(
                "Roxley",
                "29910 Ανδρέας Glen, Suite 865, 70895-5162, Port Χρήστος, Maine, Greece");
        this.publisherService.addPublisher(roxley);

        // Other
        Publisher catanGames = new Publisher(
                "Catan Games",
                "56081 Kertzmann Cape, Suite 985, 81990, New Willowhaven, Maine, United States");
        this.publisherService.addPublisher(catanGames);

        Publisher hasbro = new Publisher(
                "Hasbro",
                "522 Axel Prairie, Apt. 692, 34957, Ornfort, Alabama, United States");
        this.publisherService.addPublisher(hasbro);

        Publisher angryLionGames = new Publisher(
                "Angry Lion Games",
                "743 Priscilla Ramp, Suite 323, 19812, Rhiannaborough, Virginia, United States");
        this.publisherService.addPublisher(angryLionGames);

        Publisher arrakisGames = new Publisher(
                "Arrakis Games",
                "Paulstr. 6, Apt. 596, 88982, Alealand, Bremen, Germany");
        this.publisherService.addPublisher(arrakisGames);

        // TODO: Add Categories
        Category abstractStrategy = new Category(
                "Abstract Strategy",
                "Abstract Strategy games are often (but not always): theme-less (without storyline), built on simple and/or straightforward design and mechanics, perfect information games, games that promote one player overtaking their opponent(s), little to no elements of luck, chance, or random occurrence.");
        this.categoryService.addCategory(abstractStrategy);

        Category adventure = new Category(
                "Adventure",
                "Adventure games often have themes of heroism, exploration, and puzzle-solving. The storyline behind such games often have fantastical elements, and involve the characters in some sort of quest.");
        this.categoryService.addCategory(adventure);

        Category exploration = new Category(
                "Exploration",
                "Exploration games often encourage players to discover and search new areas or territories for particular objects or goods, and/or to search for people to become trading partners with.");
        this.categoryService.addCategory(exploration);

        Category fantasy = new Category(
                "Fantasy",
                "Fantasy games are those that have themes and scenarios that exist in a fictional world. It is a genre that uses magic and other supernatural forms as a primary element of plot, theme, and/or setting. Fantasy is generally distinguished from science fiction and horror by the expectation that it steers clear of scientific and macabre themes, respectively, though there can be a great deal of overlap between the three.");
        this.categoryService.addCategory(fantasy);

        Category cardGame = new Category(
                "Card Game",
                "Card Games often use cards as its sole or central component. There are stand-alone card games, in which all the cards necessary for gameplay are purchased at once. There are also Collectible Card Games (CCGs), where players purchase starter and \"booster\" packs in an effort to compile a more and more powerful deck of cards to compete with.");
        this.categoryService.addCategory(cardGame);

        Category civilization = new Category(
                "Civilization",
                "Civilization games often have players developing and managing a society of people. The aim of each player is usually to employ citizens in ways that are beneficial to society, and have them progress throughout the game so that their civilization gains superiority over others. Civilization games may have each player build their society independently, or through warfare and diplomacy, each player may find themselves benefiting or suffering from the actions of others.");
        this.categoryService.addCategory(civilization);

        Category murderMystery = new Category(
                "Murder/Mystery",
                "Murder/Mystery games often involve an unsolved murder or murders. A requirement of these games is usually for players to investigate these crimes, and determine the criminal details and/or perpetrator(s).");
        this.categoryService.addCategory(murderMystery);

        Category medieval = new Category(
                "Medieval",
                "Medieval games that have themes or storylines set in Europe or Asia, between the 5th century (476, the fall of the Western Roman Empire) and the 15th century (1492, the beginnings of European overseas colonization).");
        this.categoryService.addCategory(medieval);

        Category medical = new Category(
                "Medical",
                "Medical games have themes related to the science of natural healing. Themes may include surgery, cures, recovery/recuperation/physical therapy, psychiatry, pharmaceutical prescription, and other medicine-related items.");
        this.categoryService.addCategory(medical);

        Category puzzle = new Category(
                "Puzzle",
                "Puzzle games are those in which the players are trying to solve a puzzle. Many Puzzle games require players to use problem solving, pattern recognition, organization and/or sequencing to reach their objectives.");
        this.categoryService.addCategory(puzzle);

        Category cityBuilding = new Category(
                "City Building",
                "City Building games compel players to construct and manage a city in a way that is efficient, powerful, and/or lucrative.");
        this.categoryService.addCategory(cityBuilding);

        // TODO: Add category lists for every game
        // Gloomhaven's
        List<Category> gloomhavenCategoryList = new ArrayList<>();
        gloomhavenCategoryList.add(adventure);
        gloomhavenCategoryList.add(exploration);
        gloomhavenCategoryList.add(fantasy);

        // Pandemic Legacy: Season 1's
        List<Category> pandemicLegacyCategoryList = new ArrayList<>();
        pandemicLegacyCategoryList.add(medical);

        // 7 Wonders Duel's
        List<Category> sevenWondersDuelCategoryList = new ArrayList<>();
        sevenWondersDuelCategoryList.add(civilization);
        sevenWondersDuelCategoryList.add(cardGame);
        sevenWondersDuelCategoryList.add(cityBuilding);

        // Orleans's
        List<Category> orleansCategoryList = new ArrayList<>();
        orleansCategoryList.add(medieval);
        orleansCategoryList.add(abstractStrategy);

        // Santorini's
        List<Category> santoriniCategoryList = new ArrayList<>();
        santoriniCategoryList.add(cityBuilding);
        santoriniCategoryList.add(abstractStrategy);

        // Manitoba's
        List<Category> manitobaCategoryList = new ArrayList<>();
        manitobaCategoryList.add(abstractStrategy);
        manitobaCategoryList.add(civilization);
        manitobaCategoryList.add(cardGame);


        // TODO: Add Games
        // Gloomhaven
        this.gameService.addGame(new Game(
                "Gloomhaven",
                "Gloomhaven is a game of Euro-inspired tactical combat in a persistent world of shifting motives. Players will take on the role of a wandering adventurer with their own special set of skills and their own reasons for traveling to this dark corner of the world. Players must work together out of necessity to clear out menacing dungeons and forgotten ruins. In the process, they will enhance their abilities with experience and loot, discover new locations to explore and plunder, and expand an ever-branching story fueled by the decisions they make.",
                "Gloomhaven is a game of Euro-inspired tactical combat in a persistent world of shifting motives. Players will take on the role of a wandering adventurer with their own special set of skills and their own reasons for traveling to this dark corner of the world. Players must work together out of necessity to clear out menacing dungeons and forgotten ruins. In the process, they will enhance their abilities with experience and loot, discover new locations to explore and plunder, and expand an ever-branching story fueled by the decisions they make.\n" +
                        "\n" +
                        "This is a game with a persistent and changing world that is ideally played over many game sessions. After a scenario, players will make decisions on what to do, which will determine how the story continues, kind of like a “Choose Your Own Adventure” book. Playing through a scenario is a cooperative affair where players will fight against automated monsters using an innovative card system to determine the order of play and what a player does on their turn.\n" +
                        "\n" +
                        "Each turn, a player chooses two cards to play out of their hand. The number on the top card determines their initiative for the round. Each card also has a top and bottom power, and when it is a player’s turn in the initiative order, they determine whether to use the top power of one card and the bottom power of the other, or vice-versa. Players must be careful, though, because over time they will permanently lose cards from their hands. If they take too long to clear a dungeon, they may end up exhausted and be forced to retreat.",
                4,
                120,
                14,
                LocalDate.now(),
                gloomhavenCategoryList,
                cephalofairGames));

        // Pandemic Legacy: Season 1
        this.gameService.addGame(new Game(
                "Pandemic Legacy: Season 1",
                "Pandemic Legacy is a co-operative campaign game, with an overarching story-arc played through 12-24 sessions, depending on how well your group does at the game. At the beginning, the game starts very similar to basic Pandemic, in which your team of disease-fighting specialists races against the clock to travel around the world, treating disease hotspots while researching cures for each of four plagues before they get out of hand.",
                "Pandemic Legacy is a co-operative campaign game, with an overarching story-arc played through 12-24 sessions, depending on how well your group does at the game. At the beginning, the game starts very similar to basic Pandemic, in which your team of disease-fighting specialists races against the clock to travel around the world, treating disease hotspots while researching cures for each of four plagues before they get out of hand.\n" +
                        "\n" +
                        "During a player's turn, they have four actions available, with which they may travel around in the world in various ways (sometimes needing to discard a card), build structures like research stations, treat diseases (removing one cube from the board; if all cubes of a color have been removed, the disease has been eradicated), trade cards with other players, or find a cure for a disease (requiring five cards of the same color to be discarded while at a research station). Each player has a unique role with special abilities to help them at these actions.\n" +
                        "\n" +
                        "After a player has taken their actions, they draw two cards. These cards can include epidemic cards, which will place new disease cubes on the board, and can lead to an outbreak, spreading disease cubes even further. Outbreaks additionally increase the panic level of a city, making that city more expensive to travel to.\n" +
                        "\n" +
                        "Each month in the game, you have two chances to achieve that month's objectives. If you succeed, you win and immediately move on to the next month. If you fail, you have a second chance, with more funding for beneficial event cards.\n" +
                        "\n" +
                        "During the campaign, new rules and components will be introduced. These will sometimes require you to permanently alter the components of the game; this includes writing on cards, ripping up cards, and placing permanent stickers on components. Your characters can gain new skills, or detrimental effects. A character can even be lost entirely, at which point it's no longer available for play.",
                4,
                60,
                13,
                LocalDate.now(),
                pandemicLegacyCategoryList,
                zManGames));

        // 7 Wonders Duel
        this.gameService.addGame(new Game(
                "7 Wonders Duel",
                "In many ways 7 Wonders Duel resembles its parent game 7 Wonders as over three ages players acquire cards that provide resources or advance their military or scientific development in order to develop a civilization and complete wonders.",
                "In many ways 7 Wonders Duel resembles its parent game 7 Wonders as over three ages players acquire cards that provide resources or advance their military or scientific development in order to develop a civilization and complete wonders.\n" +
                        "\n" +
                        "What's different about 7 Wonders Duel is that, as the title suggests, the game is solely for two players, with the players not drafting cards simultaneously from hands of cards, but from a display of face-down and face-up cards arranged at the start of a round. A player can take a card only if it's not covered by any others, so timing comes into play as well as bonus moves that allow you to take a second card immediately. As in the original game, each card that you acquire can be built, discarded for coins, or used to construct a wonder.\n" +
                        "\n" +
                        "Each player starts with four wonder cards, and the construction of a wonder provides its owner with a special ability. Only seven wonders can be built, though, so one player will end up short.\n" +
                        "\n" +
                        "Players can purchase resources at any time from the bank, or they can gain cards during the game that provide them with resources for future building; as you acquire resources, the cost for those particular resources increases for your opponent, representing your dominance in this area.\n" +
                        "\n" +
                        "A player can win 7 Wonders Duel in one of three ways: each time you acquire a military card, you advance the military marker toward your opponent's capital, giving you a bonus at certain positions; if you reach the opponent's capital, you win the game immediately; similarly, if you acquire any six of seven different scientific symbols, you achieve scientific dominance and win immediately; if none of these situations occurs, then the player with the most points at the end of the game wins.",
                2,
                30,
                10,
                LocalDate.now(),
                sevenWondersDuelCategoryList,
                reposProduction));

        // Orleans
        this.gameService.addGame(new Game(
                "Orleans",
                "During the medieval goings-on around Orléans, you must assemble a following of farmers, merchants, knights, monks, etc. to gain supremacy through trade, construction and science in medieval France.",
                "During the medieval goings-on around Orléans, you must assemble a following of farmers, merchants, knights, monks, etc. to gain supremacy through trade, construction and science in medieval France.\n" +
                        "\n" +
                        "In Orléans, you will recruit followers and put them to work to make use of their abilities. Farmers and Boatmen supply you with money and goods; Knights expand your scope of action and secure your mercantile expeditions; Craftsmen build trading stations and tools to facilitate work; Scholars make progress in science; Traders open up new locations for you to use your followers; and last but not least, it cannot hurt to get active in monasteries since with Monks on your side you are much less likely to fall prey to fate.\n" +
                        "\n" +
                        "You will always want to take more actions than possible, and there are many paths to victory. The challenge is to combine all elements as best as possible with regard to your strategy.",
                4,
                90,
                12,
                LocalDate.now(),
                orleansCategoryList,
                dlpGames));

        // Santorini
        this.gameService.addGame(new Game(
                "Santorini",
                "Santorini is a re-imagining of the purely abstract 2004 edition. Since its original inception over 30 years ago, Santorini has been continually developed, enhanced and refined by designer Gordon Hamilton.",
                "Santorini is a re-imagining of the purely abstract 2004 edition. Since its original inception over 30 years ago, Santorini has been continually developed, enhanced and refined by designer Gordon Hamilton.\n" +
                        "\n" +
                        "Santorini is an accessible strategy game, simple enough for an elementary school classroom while aiming to provide gameplay depth and content for hardcore gamers to explore, The rules are simple. Each turn consists of 2 steps:\n" +
                        "\n" +
                        "1. Move - move one of your builders into a neighboring space. You may move your Builder Pawn on the same level, step-up one level, or step down any number of levels.\n" +
                        "\n" +
                        "2. Build - Then construct a building level adjacent to the builder you moved. When building on top of the third level, place a dome instead, removing that space from play.\n" +
                        "\n" +
                        "Winning the game - If either of your builders reaches the third level, you win.\n" +
                        "\n" +
                        "Variable player powers - Santorini features variable player powers layered over an otherwise abstract game, with 40 thematic god and hero powers that fundamentally change the way the game is played.",
                4,
                20,
                8,
                LocalDate.now(),
                santoriniCategoryList,
                roxley));

        // Manitoba
        this.gameService.addGame(new Game(
                "Manitoba",
                "The Canadian province of Manitoba is wild, fascinating, and almost without any limits: countless lakes, majestic mountains, a vast tundra in the north and endless prairies in the south. Manitoba — a country whose name derives directly from Manitou, the big spirit of creation of the American natives.",
                "The Canadian province of Manitoba is wild, fascinating, and almost without any limits: countless lakes, majestic mountains, a vast tundra in the north and endless prairies in the south. Manitoba — a country whose name derives directly from Manitou, the big spirit of creation of the American natives.\n" +
                        "\n" +
                        "In Manitoba, players become clan leaders of the Cree tribe and try to become the chieftain of them all, but this is not easy as they have to cope with the capriciousness of nature and must provide material as well as spiritual richness for their clans.\n" +
                        "\n" +
                        "In the course of the seasons, everyone has to consider several things: When and where do I plan to get active in the hunting grounds of Manitoba? Or would it be better now to emphasize spiritual development and walk on mystical paths? How can I exchange and exploit my resources cleverly? Players can also manipulate the wooden disks of the totem to influence the possibilities of all players strongly. Many situations demand wise decisions...\n" +
                        "\n" +
                        "—description from the publisher",
                4,
                75,
                12,
                LocalDate.now(),
                manitobaCategoryList,
                dlpGames));

        // TODO: Add Users
        // ADMIN
        this.userService.addUser(new User(
                "web.admin",
                passwordEncoder.encode("admin"),
                "Web",
                "Admin",
                "web.admin@boardgamessite.com",
                "077777777",
                "https://www.bootdey.com/app/webroot/img/Content/avatar/avatar1.png",
                Role.ROLE_ADMIN));

        // USER
        this.userService.addUser(new User(
                "emilijan.koteski",
                passwordEncoder.encode("ek"),
                "Emilijan",
                "Koteski",
                "emilijan.koteski@students.finki.ukim.mk",
                "078888888",
                "https://www.bootdey.com/app/webroot/img/Content/avatar/avatar7.png",
                Role.ROLE_USER));
    }


}
