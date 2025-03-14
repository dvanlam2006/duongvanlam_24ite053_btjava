package oop1;

public class MyComplex {
    private double real = 0.0;
    private double imag = 0.0;
    public MyComplex() {}
    public MyComplex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImag() {
        return imag;
    }

    public void setImag(double imag) {
        this.imag = imag;
    }
    public void setValue(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    @Override
    public String toString() {
        return "MyComplex{" +
                "real=" + real +
                ", imag=" + imag +
                '}';
    }
    public boolean isReal() {
        return real == 0.0;
    }
    public boolean isImaginary() {
        return imag == 0.0;
    }
    public boolean equals(double real, double imag) {
        return this.real == real && this.imag == imag;
    }
    public boolean equals(MyComplex another) {
        return this.real == another.real && this.imag == another.imag;
    }
    public double magnitude() {
        return Math.sqrt(real * real + imag * imag);
    }

    // Cộng số phức khác vào số phức hiện tại
    public MyComplex addInto(MyComplex right) {
        this.real += right.real;
        this.imag += right.imag;
        return this;
    }

    // Cộng số phức hiện tại với số phức khác và trả về một số phức mới
    public MyComplex addNew(MyComplex right) {
        return new MyComplex(this.real + right.real, this.imag + right.imag);
    }
}
