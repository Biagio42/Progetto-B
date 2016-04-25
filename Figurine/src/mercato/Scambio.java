package mercato;

import collezionabili.*;
import utenti.*;

import java.util.*;

public class Scambio implements Carrello {
	
	private Utente Utente1,Utente2;
	private ArrayList<Figurina> Offerta1,Offerta2;
	public Stato s;
	public int feed1,feed2;
	public int Ids;                     //Identificativo dello scambio
	public boolean Ok1,Ok2;

	
	//Costruttore
	public Scambio(Utente u1,Utente u2){
		
		Utente1=u1;
		Utente2=u2;
		feed1=0;
		feed2=0;
		Ok1=false;
		Ok2=false;
		s=Stato.NUOVO;
		
		//inserire gestione dell'ids
		//Programmare l'inserimento e classificazione degli utenti dello scambio
		
	}
	
	//ritorna lo stato dello scambio
	public Stato getStatoScambio(){
		
		return s;
	}
	
	//Metodo d'utilità che ottiene gli utenti dello scambio, uso esclusivo di Scambio
	
	private Utente getUtente(int id){
		
		if(id==1) return Utente1;
		if(id==2) return Utente2;
		
		return null;
		
	}
	
	//Metodo che inserisce e calcola i feedback lasciati, uso esclusivo di Scambio
	private void setFeedback(){
		
		if(s==Stato.ACCETTATO && Ok1==true && Ok2==true){
			
			Utente1.FeedBack+=feed1;
			Utente2.FeedBack+=feed1;
			
		}
		
	}
	
	
	
	//Un utente aggiunge una figurina alla sua offerta
	public boolean addFigurina(Utente u,Figurina f) {
		
		Figurina ftemp;
		
		//Verifica sulla possessione della figurina
		ftemp=u.getFigurinabyId(f.getId());
		if(ftemp==null) return false;
		
		//aggiunta della figurina all'offerta
        u.getOffertaFigurine().add(f);
        if(s==Stato.NUOVO) s=Stato.IN_CORSO;
		return true;
	}
	
	//Un utente rimuove una figurina dalla sua offerta
	public boolean removeFigurina(Utente u,int i) {

		if(u.getOffertaFigurine().get(i)==null) return false;		
		else {
			u.getOffertaFigurine().remove(i);
			return true;
		}
		
	
	}
	
	//Ritorna l'offerta corrente del compagno di scambio
	public ArrayList<Figurina> getOffertaGiocatore(Utente u){
		
		if(u.getUser()==Utente1.getUser()) return Utente2.getOffertaFigurine();
		else return Utente1.getOffertaFigurine();
	}
	
	//Imposta su true il valore di conferma dello scambio
	public void ConfermaScambio(Utente u){
		
		if(u.getUser()==Utente1.getUser()) Ok1=true;
		else Ok2=true;
		
	}

	
    //Aggiunge il credito a una offerta
	public boolean addCredito(Utente u,double c) {
	
		//Non ammesso in uno scambio
		
		return false;  
	}





	public boolean removeCredito(Utente u,double c) {
		// Non ammesso in uno scambio
		
		return false;
	}

	//Lascia un feedback a scambio terminato
	public void giveFeedback(Utente u,int feed) {
		
		if(u.getUser()==Utente1.getUser()) feed2=feed;
		else feed1=feed;
 
		
	}
	

}