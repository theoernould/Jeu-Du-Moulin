package horizons_ihm;

public class Lines{
	
protected boolean blocked;
private static final int DUREE = 3;

public Lines(){
    this.blocked=false;
}

public boolean exist(Square s1, Square s2, BoardGame terrain) {
    if(s1.X==s2.X){
        if(s1.Y==1 && s2.Y==(terrain.getNbSides()*2) || s2.Y==1 && s1.Y==(terrain.getNbSides()*2)){
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

public boolean alignement(Square s1, Square s2, Square s3, BoardGame terrain) {
    if(s1.X==s2.X && s2.X==s3.X){
        if((s1.Y%2==0 && s2.Y%2!=0 && s3.Y%2!=0)){
            return true;
        }else if((s1.Y%2!=0 && s2.Y%2==0 && s3.Y%2!=0)){
            return true;
        }else if((s1.Y%2!=0 && s2.Y%2!=0 && s3.Y%2==0)){
            return true;
        }else{
            return false;
        }
    } else if(s1.Y==s2.Y && s2.Y==s3.Y && s1.Y%2==0){
        return true;
    }else{
        return false;
    }
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