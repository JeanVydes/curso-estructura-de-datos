#include <iostream>

int main() {
    // Declarar un arreglo estatico que contenga 5 numeros enteros
    int numeros[5];

    // Aqui asignamos valores para cada posicion del arreglo
    numeros[0] = 10;
    numeros[1] = 20;
    numeros[2] = 30;
    numeros[3] = 40;
    numeros[4] = 50;

    // Accedementos al elemento en la posicion 1 de este arreglo
    std::cout << "El segundo elemento es: " << numeros[1] << std::endl;
    // El valor esperado a imprimir es 20

    // Ahora compliquemos un poco las cosas
    // Queremos mostrar todos los elementos
    // Entonces tendremos que recorrer todo el arreglo
    std::cout << "Todos los elementos: ";

    // 5 porque tenemos 5 elementos en el arreglo (recordar que los indices comienzan en 0)
    for (int i = 0; i < 5; ++i) {
        std::cout << numeros[i] << " ";
    }

    return 0;
}