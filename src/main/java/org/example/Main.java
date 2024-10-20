package org.example;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Client> listaClientes = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader("clientes.csv"));){
            String linea;

            br.readLine();

            while ((linea = br.readLine())!=null){
                String[] datos = linea.split(",");
                Client client= new Client(
                        Integer.parseInt(datos[0]),
                        datos[1],
                        Integer.parseInt(datos[2]),
                        datos[3]
                );

                listaClientes.add(client);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(FileOutputStream fs= new FileOutputStream("clientes.dat");
        ObjectOutputStream oos= new ObjectOutputStream(fs)){

            oos.writeObject(listaClientes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        for (Client cliente: listaClientes){
            System.out.println(cliente);
        }

    }
}

