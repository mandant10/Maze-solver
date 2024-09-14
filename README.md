# Program do Rozwiązywania Labiryntów (2024)

## Opis

Program służy do wczytywania, rozwiązywania oraz zapisywania labiryntów. Jest on drugą wersją programu dostępnego na GitHubie: [PROJEKT-JIMP-2](https://github.com/bciupak/PROJEKT-JIMP-2). Aplikacja umożliwia pracę z plikami tekstowymi oraz binarnymi, a także oferuje możliwość zapisu rozwiązania labiryntu w formie tekstowej lub graficznej. Program posiada intuicyjny graficzny interfejs użytkownika (GUI), który pozwala na łatwe zarządzanie plikami labiryntów oraz wyświetla powiadomienia o sukcesach i błędach operacji.

### Główne funkcjonalności:

- **Wczytywanie labiryntów** z plików tekstowych (.txt) oraz binarnych (.bin).
- **Rozwiązywanie labiryntów** przy użyciu wbudowanych algorytmów.
- **Zapis rozwiązania** w formie tekstowej lub graficznej (obraz labiryntu z zaznaczoną ścieżką).
- **Panel powiadomień**, który wyświetla komunikaty o stanie operacji – sukcesy (zielone) i błędy (czerwone).

## Interfejs użytkownika

Graficzny interfejs użytkownika zawiera:

- **Menu** z opcjami do wczytywania i zapisywania plików.
- **Przycisk do rozwiązywania labiryntu**.
- **Panel wydarzeń**, w którym wyświetlane są informacje o wykonanych operacjach.
- **Pole graficzne**, w którym wyświetlany jest wczytany labirynt, a po jego rozwiązaniu – zaznaczona ścieżka.

Użytkownik może wybierać pliki do wczytania lub zapisania przy użyciu menu. Labirynt można rozwiązać po jego wczytaniu, a wyniki zapisać w wybranej formie (tekstowej lub graficznej).

### Zapis labiryntu w formie tekstowej

Labirynt zapisywany jest jako siatka znaków, gdzie:
- `X` oznacza ścianę,
- puste miejsca oznaczają możliwe drogi,
- `P` to punkt startowy,
- `K` to punkt końcowy.

Przykładowy format labiryntu w formie tekstowej znajduje się w pliku test.txt

## Wymagania systemowe

- System operacyjny: Windows
- Java JDK zainstalowane na komputerze

## Szczegółowa Dokumentacja

Więcej szczegółów technicznych dotyczących implementacji klas, metod i algorytmów rozwiązywania labiryntów znajduje się w dokumentach:
- **DF** (PDF)
- **Labirynt - GUI** (PDF)

Te dokumenty opisują dokładną strukturę kodu oraz wyjaśniają, jak działają poszczególne elementy programu.
