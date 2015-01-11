package pl.lodz.p.ftims.geocaching.logic.challenges;

import pl.lodz.p.ftims.geocaching.logic.patterns.Subject;
import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.ChallengeStub;
import pl.lodz.p.ftims.geocaching.model.Hint;
import pl.lodz.p.ftims.geocaching.model.Solution;

import java.util.List;

/**
 * Usługa zarządzająca procesem wyboru i rozwiązywania pojedynczego wyzwania.
 * Pozwala podjąć się jednego z wyzwań, a następnie zarejestrować obserwatorów
 * do odbierania wskazówek.
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public interface ChallengeSolvingService extends Subject<HintsObserver> {

    /**
     * Rozpoczyna proces realizacji danego wyzwania.
     * @param challenge Podjęte wyzwanie.
     */
    void startChallenge(ChallengeStub challenge);

    /**
     * Próbuje rozpocząć proces realizacji danego wyzwania zabezpieczonego hasłem.
     * @param challenge Podjęte wyzwanie.
     * @return True jak udało się podjąć wyzwanie.
     */
    boolean startChallenge(ChallengeStub challenge, String password);

    /**
     * Zwraca aktualnie podjęte wyzwanie.
     * @return Aktualnie podjęte wyzwanie.
     */
    Challenge getActiveChallenge();

    /**
     * Wysyła rozwiązanie wyzwania i zwraca odpowiedź, czy zostało ono zaakceptowane.
     * @param password Hasło - rozwiązanie znalezione w miejscu - celu wyzwania.
     * @return True jak hasło zostało zaakceptowane przez serwer.
     */
    boolean solveChallenge(String password);

    /**
     * Zwraca aktualny tryb naprowadzania na cel z hasłem.
     * @return Jeden z trybów naprowadzania.
     */
    SolvingMode getMode();

    /**
     * Ustawia tryb naprowadzania na podany.
     * @param solvingMode Nowy tryb naprowadzania.
     */
    void setMode(SolvingMode solvingMode);
}
