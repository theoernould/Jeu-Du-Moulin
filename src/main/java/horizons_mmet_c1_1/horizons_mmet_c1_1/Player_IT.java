
package horizons_mmet_c1_1.horizons_mmet_c1_1;

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

    public void search(){
        idx ++;
       
        if(!hasNext()){
            idx = 0;
        }

        //Rajouter une condition de fin de partie ? Tant que la partie n'est pas terminée on change de joueur
        
    }

    public boolean hasNext(){
        return idx < this.lesJoueurs.size();
    }

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
