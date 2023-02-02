export class persona{
    id?: number;
    nombre: String;
    apellido: String;
    urlfoto: String;

    constructor(nombre: String , apellido: String , urlfoto: String){
       
        this.nombre = nombre;
        this.apellido = apellido;
        this.urlfoto = urlfoto;
    }
}