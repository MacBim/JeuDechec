package modele;

import java.util.List;

public abstract class Observable {
	
	protected List<ObserveurEchec> observers;
	
	public void subscribe(ObserveurEchec obs){
		this.observers.add(obs);
		//System.out.println(this.observers.size());
	}
	
	public void notifyAllObservers(){		
		for(int i = 0; i < this.observers.size(); i++){
			this.observers.get(i).notifyObserver();
		}
	}
}
