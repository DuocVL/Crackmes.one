class Roullete {
    int rand(){
        this.seed ^= (this.seed << 7) & 65535;
        this.seed ^= this.seed >>> 9;
        this.seed ^= (this.seed << 8) & 65535;
        return this.seed;
    }
    int seed;
    public void main(){
        seed = ((int) System.nanoTime()) & 65535;

        System.out.println("Seed:" + seed);
        System.out.println("Seed:" + rand());
    }
}