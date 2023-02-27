package com.urise.webapp;

import com.urise.webapp.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] arg) {
        Resume r = new Resume("Name1");

        r.addContact(ContactType.PHONE, "+7(921) 855-04-82");
        r.addContact(ContactType.EMAIL, "gkislin@yandex.ru");
        r.addContact(ContactType.SKYPE, "skype:grigory.kislin");
        r.addContact(ContactType.LINKEDIN, "Профиль LinkedIn");
        r.addContact(ContactType.GITHUB, "Профиль GitHub");
        r.addContact(ContactType.STACKOVERFLOW, "Профиль Stackoverflow");
        r.addContact(ContactType.HOME_PAGE, "Домашняя страница");

        r.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного " +
                "обучения по Java Web и Enterprise технологиям"));
        r.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума"));

        r.addSection(SectionType.ACHIEVEMENT, new ListSection(Arrays.asList(
                "\n- Организация команды",
                "\n- Разработка Web приложения",
                "\n- Реализация двухфакторной аутентификации",
                "\n- Налаживание процесса разработки")));
        r.addSection(SectionType.QUALIFICATIONS, new ListSection(Arrays.asList(
                "\n- JEE",
                "\n- Version control",
                "\n- DB",
                "\n- Languages: Java")));


        r.addSection(SectionType.EXPERIENCE, new OrganizationSection(new ArrayList<>(List.of(
                (new Organization("RIT Center", "", new ArrayList<>(
                        List.of(new Period("09/1997", "01/2004",
                                        "Инженер по аппаратному и программному тестированию",
                                        "Тестирование, отладка, внедрение ПО цифровой " +
                                                "телефонной станции Alcatel 1000 S12 (CHILL, ASM)."),
                                new Period("01/2004", "01/2005",
                                        "Ведущий инженер по аппаратному и программному тестированию",
                                        "Тестирование, отладка, внедрение ПО цифровой " +
                                                "телефонной станции Alcatel 1000 S12 (CHILL, ASM)."))))),
                new Organization("Siemens AG", "", new ArrayList<>(
                        List.of(new Period("09/1997", "01/2004",
                                "Разработчик ПО",
                                "Разработка информационной модели, проектирование интерфейсов, " +
                                        "реализация и отладка ПО на мобильной IN платформе Siemens "))))))));

        r.addSection(SectionType.EDUCATION, new OrganizationSection(new ArrayList<>(List.of(
                (new Organization("Alcatel", "", new ArrayList<>(
                        List.of(new Period("09/1997", "03/1998",
                                "6 месяцев обучения цифровым телефонным сетям (Москва)",
                                ""))))), new Organization("Siemens AG", "",
                        new ArrayList<>(List.of(new Period("01/2005", "04/2005",
                                "3 месяца обучения мобильным IN сетям (Берлин)",
                                ""))))))));

        System.out.println(ContactType.PHONE.getTitle() + ": " + r.getContact(ContactType.PHONE));
        System.out.println();

        System.out.println(SectionType.PERSONAL.getTitle() + ": " + r.getSection(SectionType.PERSONAL));
        System.out.println();
        System.out.println(SectionType.OBJECTIVE.getTitle() + ": " + r.getSection(SectionType.OBJECTIVE));
        System.out.println();

        System.out.println(SectionType.ACHIEVEMENT.getTitle() + ": " + r.getSection(SectionType.ACHIEVEMENT));
        System.out.println();
        System.out.println(SectionType.QUALIFICATIONS.getTitle() + ": " + r.getSection(SectionType.QUALIFICATIONS));
        System.out.println();

        System.out.println(SectionType.EXPERIENCE.getTitle() + ": " + r.getSection(SectionType.EXPERIENCE));
        System.out.println();
        System.out.println(SectionType.EDUCATION.getTitle() + ": " + r.getSection(SectionType.EDUCATION));
        System.out.println();

        r.getAllContacts().forEach((k, v) -> System.out.printf("%s : %s%n", k, v));
        System.out.println();
        r.getAllSections().forEach((k, v) -> System.out.printf("%s : %s%n", k, v));
    }
}



