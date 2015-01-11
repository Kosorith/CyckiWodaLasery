package pl.lodz.p.ftims.geocaching.logic.challenges;

import pl.lodz.p.ftims.geocaching.dao.IChallengeAccess;
import pl.lodz.p.ftims.geocaching.model.Challenge;
import pl.lodz.p.ftims.geocaching.model.Hint;

import java.util.Collections;
import java.util.List;

/**
 * Usługa kontrolująca proces tworzenia wyzwania z wykorzystaniem innych usług i dao.
 * @author Michał Sośnicki, Andrzej Kurczewski
 */
public class ChallengeCreationServiceImpl implements ChallengeCreationService {

    private IChallengeAccess challengeAccess;
    private Challenge editedChallenge;

    public ChallengeCreationServiceImpl(IChallengeAccess challengeAccess) {
        this.challengeAccess = challengeAccess;
    }

    @Override
    public Challenge getEditedChallenge() {
        return editedChallenge;
    }

    @Override
    public void setEditedChallenge(Challenge challenge) {
        editedChallenge = challenge;
    }

    @Override
    public void initCreation() {
        editedChallenge = new Challenge();
    }

    @Override
    public boolean saveChallenge() {
        return preverifyChallenge() && challengeAccess.sendNewChallenge(editedChallenge);

    }

    @Override
    public List<Hint> getHintList() {
        return Collections.unmodifiableList(editedChallenge.getHints());
    }

    @Override
    public void addHint(Hint hint) {
        editedChallenge.getHints().add(hint);
    }

    @Override
    public void removeHint(Hint hint) {
        editedChallenge.getHints().remove(hint);
    }

    @Override
    public boolean preverifyChallenge() {
        return editedChallenge != null
            && notEmpty(editedChallenge.getStub().getName())
            && !editedChallenge.getHints().isEmpty();
    }

    private boolean notEmpty(String string) {
        return string != null && !string.isEmpty();
    }
}
