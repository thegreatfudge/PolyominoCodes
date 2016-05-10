import java.util.Set;

public class Polyomino {
    private Set<Monomino> monominos;
    private Monomino endOfPolyomino;
    private Monomino beginningOfPolyomino;

    public Set<Monomino> getMonominos() {
        return monominos;
    }

    public void setMonominos(Set<Monomino> monominos) {
        this.monominos = monominos;
    }

    public Polyomino(Set<Monomino> monominos) {
        this.monominos = monominos;

        for(Monomino mono : monominos){
            if(mono.isEnd())
                this.endOfPolyomino = mono;
            else if(mono.isBeginning())
                this.beginningOfPolyomino = mono;
        }
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
}
