import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        System.out.println("Задание №1: ");
        long minors = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println("Среди жителей Лондона найдено " + minors + " несовереннолетних.");
        System.out.println();

        System.out.println("Задание №2: ");
        List<String> conscripts = persons.stream()
                .filter(person -> person.getSex().equals(Sex.MAN))
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() <= 27)
                .map(Person::getFamily)
                .toList();
        System.out.println("Лондонцы призывного возраста: " + conscripts);
        System.out.println();

        System.out.println("Задание №3: ");
        List<String> employable = persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> person.getAge() > 18)
                .filter(person -> person.getSex().equals(Sex.WOMAN) & person.getAge() < 60 ||
                        person.getSex().equals(Sex.MAN) & person.getAge() < 65)
                .map(Person::getFamily)
                .sorted()
                .toList();
        System.out.println("Потенциально работоспособные жители Лондона: " + employable);
    }
}
