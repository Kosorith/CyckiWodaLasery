package pl.lodz.p.ftims.geocaching.logic.challenges;

import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.Hint;

import java.util.List;

/**
 * Usługa kontrolująca proces tworzenia wyzwania. Trzyma u siebie tworzone wyzwanie
 * i pozwala je na końcu wysłać na serwer.
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public interface ChallengeCreationService {

    /**
     * Zwraca aktualnie edytowane w tej usłudze wyzwanie.
     * @return Aktualnie edytowane wyzwanie.
     */
    Challenge getEditedChallenge();

    /**
     * Ustawia przekazane wyzwanie jako aktualnie edytowane w tej usłudze.
     * @param challenge Nowe edytowane wyzwanie.
     */
    void setEditedChallenge(Challenge challenge);

    /**
     * Resetuje stan usługi, pozwalając rozpocząć kreację na nowo, z pustym wyzwaniem.
     */
    void initCreation();

    /**
     * Wysyła edytowane wyzwanie na serwer.
     * @return Odpowiedź, czy wysyłka się udała i została zaakceptowana przez serwer.
     */
    boolean saveChallenge();

    /**
     * Zwraca listę wskazówek aktualnie edytowanego wyzwania.
     * @return Lista wskazówek aktualnie edytowanego wyzwania.
     */
    List<Hint> getHintList();

    /**
     * Dodaje wskazówkę do edytowanego wyzwania.
     * @param hint Nowa wskazówka.
     */
    void addHint(Hint hint);

    /**
     * Usuwa wskazówkę z edytowanego wyzwania.
     * @param hint Wskazówka do usunięcia.
     */
    void removeHint(Hint hint);

    /**
     * Weryfikuje wstępnie edytowane wyzwanie i odpowiada, czy jest sens w ogóle je wysyłać na serwer, czy
     * na pewno zostanie odrzucone.
     * @return Wynik weryfikacji.
     */
    boolean preverifyChallenge();

}
