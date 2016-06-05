import sun.util.resources.cldr.fr.CalendarData_fr_MQ;

import java.util.HashSet;
import java.util.Set;

public class Polyomino {

    private Set<Monomino> monominos;
    private Monomino endOfPolyomino;
    private Monomino beginningOfPolyomino;

    public Polyomino(Set<Monomino> monominos) {

        this.monominos = monominos;

        for(Monomino mono : monominos){

            if (mono.isEnd())
                this.endOfPolyomino = mono;

            else if (mono.isBeginning())
                this.beginningOfPolyomino = mono;
        }
    }

    public Polyomino copy(){
        Set<Monomino> monominostmp = new HashSet<>();
        for(Monomino monomino: monominos){
            monominostmp.add(monomino.copy());
        }
        return new Polyomino(monominostmp);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof Polyomino) {
            Polyomino tmp = (Polyomino) obj;

            return (monominos.size() == tmp.monominos.size() && monominos.containsAll(tmp.monominos));
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        for (Monomino monomino : monominos) {
            hash ^= (monomino.hashCode() * 19);
        }

        return hash;
    }

    public Monomino getEndOfPolyomino() {
        return endOfPolyomino;
    }

    public void setEndOfPolyomino(Monomino endOfPolyomino) {
        this.endOfPolyomino = endOfPolyomino;
    }

    public Monomino getBeginningOfPolyomino() {
        return beginningOfPolyomino;
    }

    public void setBeginningOfPolyomino(Monomino beginningOfPolyomino) {
        this.beginningOfPolyomino = beginningOfPolyomino;
    }

    public Set<Monomino> getMonominos() {
        return monominos;
    }

    public void setMonominos(Set<Monomino> monominos) {
        this.monominos = monominos;
    }
}
