import java.util.Random;
import java.util.Scanner;

public class HomeWork4 {
    public static Random random = new Random();
    public static Scanner scanner = new Scanner(System.in);

    public static char player = '@';
    public static int playerHP = 100;
    public static int playerStr = 15;
    public static int playerCoins = 0;
    public static int playerScore = 0;
    public static int playerExp = 0;
    public static int playerX;
    public static int playerY;

    public static final int playerMoveUp = 8;
    public static final int playerMoveDown = 2;
    public static final int playerMoveLeft = 4;
    public static final int playerMoveRight = 6;

    public static int mapHeight;
    public static int mapWidth;
    public static char[][] map;
    public static char[][] invisibleMap;
    public static int mapSizeMin = 3;
    public static int mapSizeMax = 4;
    public static char emptyCell = '_';

    public static char coin = '$';

    public static char enemy = 'E';
    public static int enemyHP;
    public static int enemyStr;
    public static int enemyValueMin = 20;
    public static int enemyValueMax = 25;

    public static int enemiesCount;
    public static int coinCount;
    public static int gameLevel = 0;

    public static void main(String[] args) {

        while (isPlayerAlive()) {
            ++gameLevel;
            System.out.println("START GAME LEVEL " + gameLevel);
            levelCycle();
        }
        System.out.println("GAME OVER! Player Score " + playerScore);
    }

    public static void levelCycle() {
        createMap();
        createPlayer();
        spawnEnemies();
        spawnCoin();

        while (true) {
            printMap();
            playerMoveAction();

            if (!isPlayerAlive()) {
                System.out.println("Player dead. Game over");
                break;
            }

            if (!isEnemyExist()) {
                System.out.println("Player Win. Player Score " + playerScore + ". Level finish");
                break;
            }
        }
        printMap();
    }

    public static void createMap() {
        mapWidth = randomValue(mapSizeMin, mapSizeMax);
        mapHeight = randomValue(mapSizeMin, mapSizeMax);
        map = new char[mapHeight][mapWidth];
        invisibleMap = new char[mapHeight][mapWidth];

        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                map[y][x] = emptyCell;
                invisibleMap[y][x] = emptyCell;
            }
        }
        System.out.println("Map " + mapWidth + "x" + mapHeight + " has been created.");
    }

    public static void createPlayer() {
        playerX = randomValue(0, mapWidth - 1);
        playerY = randomValue(0, mapHeight - 1);
        map[playerY][playerX] = player;
        System.out.println("Player has been created in [" + (playerX + 1) + ":" + (playerY + 1) + "]");
        System.out.println("Player HP=" + playerHP);
    }

    public static void spawnEnemies() {
        enemiesCount = (mapWidth + mapHeight) / 2;

        enemyHP = randomValue(enemyValueMin, enemyValueMax);
        enemyStr = randomValue(enemyValueMin, enemyValueMax);

        int enemyPosX;
        int enemyPosY;

        for (int i = 1; i <= enemiesCount; i++) {

            do {
                enemyPosX = random.nextInt(mapWidth);
                enemyPosY = random.nextInt(mapHeight);
            } while (!isEmptyCell(enemyPosX, enemyPosY, map) || !isEmptyCell(enemyPosX, enemyPosY, invisibleMap));
            invisibleMap[enemyPosY][enemyPosX] = enemy;

        }
        System.out.println("Enemies has been created. Count is " + enemiesCount);
    }
    public static void spawnCoin() {
        coinCount = (mapWidth + mapHeight) / 2;
        int coinPosX;
        int coinPosY;

        for (int i = 1; i <= coinCount; i++) {

            do {
                coinPosX = random.nextInt(mapWidth);
                coinPosY = random.nextInt(mapHeight);
            } while (!isEmptyCell(coinPosX, coinPosY, map) || !isEmptyCell(coinPosX, coinPosY, invisibleMap));
            invisibleMap[coinPosY][coinPosX] = coin;
        }
        System.out.println("Coins has been created.  Count is " + coinCount);
    }

    public static void playerMoveAction() {
        int playerLastX = playerX;
        int playerLastY = playerY;

        int playerDestination;

        do {
            System.out.print("Enter your destination: UP(" + playerMoveUp +
                    ") | DOWN(" + playerMoveDown +
                    ") | LEFT(" + playerMoveLeft +
                    ") | RIGHT(" + playerMoveRight + ") > ");
            playerDestination = scanner.nextInt();

            switch (playerDestination) {
                case playerMoveUp:
                    playerY -= 1;
                    break;
                case playerMoveDown:
                    playerY += 1;
                    break;
                case playerMoveLeft:
                    playerX -= 1;
                    break;
                case playerMoveRight:
                    playerX += 1;
                    break;
            }
        } while (!isValidPlayerMoveNextCell(playerX, playerY, playerLastX, playerLastY));

        playerNextCellCheck();
        map[playerLastY][playerLastX] = emptyCell;
        map[playerY][playerX] = player;

    }

    public static void playerNextCellCheck() {
        if (invisibleMap[playerY][playerX] == enemy) {
            playerHP -= enemyStr;
            playerScore++;
            enemiesCount--;
            System.out.println("Player has been attack on " + enemyStr + ". Player HP now is " + playerHP);
        }
        if ((invisibleMap[playerY][playerX] == coin)) {
            playerCoins += coinCount;
            System.out.println("Player get " + coinCount + " coins. All coins " + playerCoins);
        }
    }

    public static boolean isValidPlayerMoveNextCell(int nextCellX, int nextCellY, int lastCellX, int lastCellY) {
        if (isValidCell(nextCellX, nextCellY)) {
            System.out.println("Player move to [" + (nextCellX + 1) + ":" + (nextCellY + 1) + "]");
            return true;
        } else {
            System.out.println("Invalid move");
            playerX = lastCellX;
            playerY = lastCellY;
            return false;
        }
    }

    public static void printMap() {
        System.out.println("===== MAP =====");
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                System.out.print(map[y][x] + "|");
            }
            System.out.println();
        }
        System.out.println("===============");
    }

    public static int randomValue(int min, int max) {
        return min + random.nextInt(max - min + 1);
    }

    public static boolean isEmptyCell(int x, int y, char[][] map) {
        return map[y][x] == emptyCell;
    }

    public static boolean isPlayerAlive() {
        return playerHP > 0;
    }

    public static boolean isValidCell(int x, int y) {
        return x >= 0 && x < mapWidth && y >= 0 && y < mapHeight;
    }

    public static boolean isEnemyExist() {
        return enemiesCount > 0;
    }
}
