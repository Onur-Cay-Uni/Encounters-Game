package PlayerPackage;

import EncounterPackage.Observer;

public interface Subject {

    public void registerObserver(Observer observer);
    public void removeObserver(Observer observer);
    public void notiftyObservers();
}
