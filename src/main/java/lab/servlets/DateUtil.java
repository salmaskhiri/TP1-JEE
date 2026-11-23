package lab.servlets;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/** Conversion des dates saisies dans les formulaires (format demande : mm/dd/yyyy). */
public final class DateUtil {

    private static final DateTimeFormatter US = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private DateUtil() {
    }

    /** Accepte mm/dd/yyyy (format du TP) ou yyyy-mm-dd (input type="date"). Retourne null si invalide. */
    public static LocalDate parse(String s) {
        if (s == null || s.isBlank()) {
            return null;
        }
        try {
            return LocalDate.parse(s.trim(), US);
        } catch (DateTimeParseException e) {
            try {
                return LocalDate.parse(s.trim());
            } catch (DateTimeParseException e2) {
                return null;
            }
        }
    }

    /** Nombre de jours entre aujourd'hui et le prochain anniversaire (0 = c'est aujourd'hui). */
    public static long daysToNextBirthday(LocalDate birth, LocalDate today) {
        LocalDate next = birth.withYear(today.getYear());
        if (next.isBefore(today)) {
            next = birth.withYear(today.getYear() + 1); // le 29/02 devient le 28/02 les annees non bissextiles
        }
        return ChronoUnit.DAYS.between(today, next);
    }
}
