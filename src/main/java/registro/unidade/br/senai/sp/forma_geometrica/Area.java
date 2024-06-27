package forma_geometrica;

public class Area {

    private double area;

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double triangulo(double b, double h){

        return this.area = b * h / 2;

    }

    public double quadrado(double l){

        return this.area = Math.pow(l, 2);

    }

    public double retangulo(double b, double h){

        return this.area = b * h;

    }

    public double circulo(double r){

        return this.area = Math.PI * Math.pow(r, 2);

    }

    public double pentagono(double l, double a){

        return this.area = 5 * l * a;

    }

}
