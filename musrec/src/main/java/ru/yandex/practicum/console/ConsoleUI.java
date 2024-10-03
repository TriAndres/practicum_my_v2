package ru.yandex.practicum.console;

import ru.yandex.practicum.recommender.Recommender;
import ru.yandex.practicum.recommender.RecommenderImpl;
import ru.yandex.practicum.recommender.Track;

import java.util.Scanner;

public class ConsoleUI {

    public static void main(String[] args) {
        Recommender recommender = new RecommenderImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Вас приветствует уникальная программадля рекомендации музыкальных треков");

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    readAndSaveTrack(scanner, recommender);
                    break;
                case "2":
                    readTrackAndPredictMark(scanner, recommender);
                    break;
                case "3":
                    return;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выбирите команду:");
        System.out.println("1 - Добавить оценку для трека");
        System.out.println("2 - Спрогназировать оценку для трека");
        System.out.println("3 - Выход");
    }

    private static void readAndSaveTrack(Scanner scanner, Recommender recommender) {
        Track track = readTrack(scanner);

        System.out.println("Введите оценку:");
        int mark = scanner.nextInt();
        track.setMark(mark);

        recommender.addLikedTrack(track);
    }

    private static void readTrackAndPredictMark(Scanner scanner, Recommender recommender) {
        Track track = readTrack(scanner);

        double predictedMark = recommender.predictMark(track);

        System.out.println("Продолжительная оценка для этего трека: " + predictedMark);
    }

    private static Track readTrack(Scanner scanner) {
        System.out.println("Введите название трека:");
        String title = scanner.nextLine();
        System.out.println("Введите автора трека:");
        String author = scanner.nextLine();
        System.out.println("Введите год создания трека:");
        int creationYear = scanner.nextInt();

        Track track = new Track(title, author, creationYear);
        return track;
    }
}