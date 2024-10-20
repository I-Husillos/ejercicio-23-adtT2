package org.example;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Creamos un array de tipo CLient para guardar a los clientes
        ArrayList<Client> listaClientes = new ArrayList<>();
        //leemos mediante bufferreader linea a linea el archivo, creando un FileReader que sirve para leer archivos de texto
        try(BufferedReader br = new BufferedReader(new FileReader("clientes.csv"));){
            //String que usaremos mas adelante para usar metodos String
            String linea;

            //esta linea lee la primera linea de contenido del archivo para saltarsela ya que no tiene datos que queremos, de forma que al leeerla no se guarda y asi nos la saltamos
            br.readLine();

            //almacenando en la variable linea cada linea que se lee medainte readLine
            //mientras la linea leida no sea null se ejecuta el bucle
            while ((linea = br.readLine())!=null){

                //cada linea que se lee se guarda en un array de tipo String, separando cada dato por ","
                String[] datos = linea.split(",");
                //con cada iteracion del bucle se crea un objeto de tipo Client y se introduce cada vector del array que corresponde a cada dato que solicita el constructor
                Client client= new Client(
                        Integer.parseInt(datos[0]),//los campos que se requiere int se convierten a int, ya que el contenido del archivo es texto en su totalidad
                        datos[1],
                        Integer.parseInt(datos[2]),
                        datos[3]
                );

                //una vez hecho el objeto se añade al array que creamos al rpincipio para almacenar cada cliente
                listaClientes.add(client);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //se crea un archivo FileOutputStream llamado "cliente.dat" y se guada en fs
        // y se escribe dicho archivo mediante la creacion del objeto ObjectOutputStream que permite escribir en el archivo que se pase como paramaetro
        try(FileOutputStream fs= new FileOutputStream("clientes.dat");
        ObjectOutputStream oos= new ObjectOutputStream(fs)){

            //en el objeto ObjectOutputStream llamado oos se escribe la lista de clientes con todos los objetos Client que añadimos antes a dicha lista
            oos.writeObject(listaClientes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //imprimir cada cliente por consola, automaticamente java llama al metodo toString
        for (Client cliente: listaClientes){
            System.out.println(cliente);
        }

    }
}

