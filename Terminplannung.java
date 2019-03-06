import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Terminplannung {
	
	public class Benutzer{
		String ID;
		String Name;
		List<Termin> BenutzerTermine;
		
		
		public Benutzer(String iD, String name) {
			ID = iD;
			Name = name;
		}
		
		
	}
	
	public class Termin{
		
		String Name;
		List<Benutzer> Teilnehmer;
		Datum Startzeitpunkt;
		Datum Endzeitpunkt;
		
		public Termin(String name, List<Benutzer> teilnehmer, Datum startzeitpunkt, Datum endzeitpunkt) {
			
			while(Kiter.hasNext()) {
				if(teilnehmer.contains(Kiter.next())) {
					while(Kiter.next().BenutzerTermine.iterator().hasNext()) {
						Termin t = Kiter.next().BenutzerTermine.iterator().next();
						
						if(t.Startzeitpunkt.Jahr==startzeitpunkt.Jahr && 
							t.Startzeitpunkt.Monat == startzeitpunkt.Monat &&
							t.Startzeitpunkt.Tag == startzeitpunkt.Tag) {
							if((t.Startzeitpunkt.Stunde <= endzeitpunkt.Stunde && t.Startzeitpunkt.Stunde >= startzeitpunkt.Stunde) ||
								(t.Endzeitpunkt.Stunde <= endzeitpunkt.Stunde && t.Endzeitpunkt.Stunde >= startzeitpunkt.Stunde)) {
								
								teilnehmer.remove(Kiter.next());
								
							}
						}
					}
				}
			}
			
			
			
			Name = name;
			Teilnehmer = teilnehmer;
			Startzeitpunkt = startzeitpunkt;
			Endzeitpunkt = endzeitpunkt;
		}
		
	}
	
	public class Datum{
		
		
		
		Integer Tag;
		Integer Monat;
		Integer Jahr;
		Integer Stunde;
		Integer Minute;
		
		public Datum(Integer tag, Integer monat, Integer jahr, Integer stunde, Integer minute) {
			
			Tag = tag;
			Monat = monat;
			Jahr = jahr;
			Stunde = stunde;
			Minute = minute;
		}
	}
	
	
	ArrayList<Benutzer> Kunden = new ArrayList<Benutzer>();
	ArrayList<Termin> Termine = new ArrayList<Termin>();
	
	Iterator<Benutzer> Kiter = Kunden.iterator();
	Iterator<Termin> Titer = Termine.iterator();
	
	//User Story 1
	
	public boolean benutzerHinzufügen(String id, String name) {
		
		if(Kunden.add(new Benutzer(id, name))==true) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean benutzerLöschen(String id) {
		
		while(Kiter.hasNext()) {
			if(Kiter.next().ID.equals(id)) {
				Kunden.remove(Kiter.next());
				
				deleteBenutzerfromTermine(Kiter.next());
				
				return true;
			}
		}
		return false;
		
	}
	
	private void deleteBenutzerfromTermine(Benutzer b) {
		while(Titer.hasNext()) {
			if(Titer.next().Teilnehmer.contains(b)){
				Titer.next().Teilnehmer.remove(b);
			}
		}
		
	}

	public boolean benutzerBearbeiten(String id, String name) {
		
		if(name!=null) {
		while(Kiter.hasNext()) {
			if(Kiter.next().ID.equals(id)) {
				Kiter.next().Name = name;
				return true;
			}
			
		}
		}
		return false;
		
	}
	
	public Benutzer getBenutzer(String id) {
		
		while(Kiter.hasNext()) {
			if(Kiter.next().ID.equals(id)) {
				return Kiter.next();
			}
		}
		return null;
	}
	
	public List<Benutzer> getAllBenutzer() {
		return Kunden;
	}
	
	//User Story 2
	
	public boolean terminHinzufügen(Termin t) {
		
		
		
		
		
		if(Termine.add(t)==true) return true;
		
		return false;
	}

	public boolean terminAendern(Termin t1, Termin t2) {
		
		while(Titer.hasNext()) {
			if(Titer.next().equals(t1)) {
				Termine.remove(t1);
				Termine.add(t2);
				return true;
			}
		}
		return false;
		
	}

	public boolean terminLöschen(Termin t) {
		if(Termine.remove(t)==true) return true;
		
		return false;
	}

	public List<Termin> getAllTermine(){
		
		return Termine;
	}

	public List<Termin> getTermineFromUser(Benutzer b){
		
		List<Termin> l = new ArrayList<Termin>();
		
		while(Titer.hasNext()) {
			if(Titer.next().Teilnehmer.contains(b)) {
				l.add(Titer.next());
			}
		}
		
		return l;
		
	}


}
