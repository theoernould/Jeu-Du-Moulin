package horizons_mmet_c1_1.horizons_mmet_c1_1;

public class Lines{
	
protected boolean blocked;
    Lines(){
        this.blocked=false;
    }

    public boolean exist(Square s1, Square s2, BoardGame terrain) {
        if(s1.X==s2.X){
            if(s1.Y==1 && s2.Y==(terrain.nbSides*2) || s2.Y==1 && s1.Y==(terrain.nbSides*2)){
                return true;
            }else if(s1.Y==s2.Y-1 || s1.Y==s2.Y+1 || s2.Y==s1.Y-1 || s2.Y==s1.Y+1){
                return true;
            }else{
                return false;
            }
        } else if(s1.Y==s2.Y && s1.Y%2==0 && (s1.X==s2.X-1 || s1.X==s2.X+1 || s2.X==s1.X-1 || s2.X==s1.X+1)){
            return true;
        }else{
            return false;
        }
    }
    public void blocage() {
        if(this.blocked==true){
            this.blocked=false;
        }
    }
    public boolean isBlocked() {
        return this.blocked;
    }
    
    /**Méthode toString pour l'affichage*/
    public String toString() {
        if(this.blocked){
            return "Cet arc est bloqué";
        }else{
            return "Cet arc n'est pas bloqué";
        }
    }
}