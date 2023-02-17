export class persona{
    id?: number;
    nombre: string;
    apellido: string;
    urlfoto: string;

    constructor(nombre: string , apellido: string , urlfoto: string){
       
        this.nombre = nombre;
        this.apellido = apellido;
        this.urlfoto = urlfoto;
    }
}