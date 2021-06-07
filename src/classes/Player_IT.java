
package classes;

import java.util.Iterator;
import java.util.List;

public class Player_IT  implements Iterator<Player>{

	private List<Player> lesJoueurs;
    private int idx;

    /**Constructeur de l'itérateur*/
    public Player_IT(List<Player> joueurs){
        
        this.lesJoueurs = joueurs;
        this.idx = -1;
        search();
    }

    /**Cherche le prochain indice dans l�it�rateur*/
    public void search(){
        idx ++;
       
        if(!hasNext()){
            idx = 0;
        }

        //Rajouter une condition de fin de partie ? Tant que la partie n'est pas terminée on change de joueur
        
    }
	
	/**Retourne l'indice du joueur suivant */ 
    public boolean hasNext(){
        return idx < this.lesJoueurs.size();
    }

	/**Retourne le joueur suivant
	* @param p un joueur */
    public Player next(){
       Player p = this.lesJoueurs.get(idx);
       search();
       return p;
    }

    /**Getteur LesJoueurs*/
    public List<Player> getLesJoueurs(){
        return this.lesJoueurs;
    }

} 
