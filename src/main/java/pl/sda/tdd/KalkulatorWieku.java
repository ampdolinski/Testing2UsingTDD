package pl.sda.tdd;

import java.util.Scanner;

public class KalkulatorWieku {


    private static final String FORMAT_DATY = "\\d{2}-\\d{2}-\\d{4}";
    private static final int INDEKS_ROK = 2;
    private static final int INDEKS_MSC = 1;
    private static final int INDEKS_DZIEN = 0;

    /**
     * Calculates age of person in specified day
     *
     * @param dataUrodzenia - format dd-mm-yyyy
     * @param wybranaData   - format dd-mm-yyyy
     * @return age of person in specified day
     * wybranaData >= dataUrodzenia
     * calculated age cannot be higher than 120
     * throws RuntimeException
     */
    public static int obliczWiek(String dataUrodzenia, String wybranaData) {

        sprawdzFormatDat(dataUrodzenia, wybranaData);

        String[] urTab = dataUrodzenia.split("-");
        String[] wybDat = wybranaData.split("-");

        int lata = obliczWiek(urTab, wybDat);

        sprawdzPoprawnoscWieku(lata);

        return lata;
    }

    private static int obliczWiek(String[] urTab, String[] wybDat) {
        int lata = obliczRoznice(wybDat[INDEKS_ROK], urTab[INDEKS_ROK]);
        int miesiace = obliczRoznice(wybDat[INDEKS_MSC], urTab[INDEKS_MSC]);
        if (miesiace < 0) {
            lata--;
        }
        int dni = obliczRoznice(wybDat[INDEKS_DZIEN], urTab[INDEKS_DZIEN]);
        if (miesiace == 0 && dni < 0) {
            lata--;
        }
        return lata;
    }

    private static void sprawdzPoprawnoscWieku(int lata) {
        if (lata < 0) {
            throw new RuntimeException("Data urodzenia nie może być później niż wybrana data");
        } else if (lata > 120) {
            throw new RuntimeException("Osoba nie może mieć więcej niż 120 lat");
        }
    }

    private static int obliczRoznice(String a, String b) {
        return Integer.parseInt(a) - Integer.parseInt(b);
    }

    private static void sprawdzFormatDat(String dataUrodzenia, String wybranaData) {
        if (!dataUrodzenia.matches(FORMAT_DATY)) {
            throw new RuntimeException("Data urodzenia jest niepoprawna");
        } else if (!wybranaData.matches(FORMAT_DATY)) {
            throw new RuntimeException("Wybrana data jest niepoprawna");
        }
    }

    public static void main(String[] args) {
        KalkulatorWieku kalkulatorWieku = new KalkulatorWieku();
//        Scanner scanner = new Scanner(System.in);

        int testWiek = kalkulatorWieku.obliczWiek("16-10-1990", "26-01-2021");

        System.out.printf("Obliczony wiek to: %d", testWiek);

    }

}
