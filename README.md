# Estructuras de Datos

Esta guía te ayudará a entender las estructuras de datos más fundamentales en la programación. Aquí encontrarás explicaciones teóricas y ejemplos prácticos en C++, Python y Java para que puedas reforzar tus conocimientos de manera autodidacta.

## Introducción

Las estructuras de datos son fundamentales en la programación, ya que nos permiten organizar y manipular datos de manera eficiente. En esta guía, exploraremos las estructuras de datos lineales más comunes, centrándonos en sus características, ventajas y desventajas.

## Tipos de Estructuras de Datos

Las estructuras de datos se pueden clasificar en dos categorías principales: lineales y no lineales. Las estructuras lineales son aquellas en las que los elementos están organizados en una secuencia, mientras que en las no lineales, los elementos pueden no estar en una secuencia contigua.

* Estructuras de Datos Lineales
  * Listas
  * Pilas
  * Colas
* Estructuras de Datos No Lineales
  * Árboles
  * Grafos

## Listas

Las operaciones de **inserción**, **eliminación** y **búsqueda** son comunes en las listas, las cuales las vamos a ir viendo en el transcurso mientras programamos juntos.

En la vida real las listas tienen aplicaciones en la gestión de tareas, donde cada tarea puede ser un elemento de la lista, o en la representación de una playlist de música.

### 1. Listas Estáticas (Arreglos)

Un arreglo es una de las estructuras de datos más simples, ideal para almacenar una colección de elementos del mismo tipo de forma contigua en memoria. Son rápidos para leer y actualizar datos, pero su tamaño es fijo.

### 2. Listas Enlazadas

A diferencia de los arreglos, las listas enlazadas no guardan sus elementos de forma contigua. Cada elemento, llamado "nodo", contiene el dato y un apuntador al siguiente nodo en la secuencia.

### 3. Listas Doblemente Enlazadas

Esta estructura es una mejora de la lista enlazada simple. Cada nodo tiene dos apuntadores: uno al nodo siguiente y otro al nodo anterior, lo que permite recorrer la lista en ambas direcciones.

### 4. Listas Circulares

Una lista circular es una lista enlazada donde el último nodo apunta de nuevo al primero, formando un bucle. Son útiles para aplicaciones donde necesitas recorrer la lista continuamente, como un carrusel o una cola de tareas.

## Pilas

Las pilas son estructuras de datos que siguen el principio LIFO (Last In, First Out), lo que significa que el último elemento en entrar es el primero en salir. Se pueden implementar utilizando arreglos o listas enlazadas. Las operaciones principales en una pila son **push** (agregar un elemento) y **pop** (eliminar el elemento superior).

Una aplicación común de las pilas es en la gestión de la memoria, donde se utilizan para realizar un seguimiento de las funciones activas y sus variables locales.

## Colas

Las colas son estructuras de datos que siguen el principio FIFO (First In, First Out), lo que significa que el primer elemento en entrar es el primero en salir. Se pueden implementar utilizando arreglos o listas enlazadas. Las operaciones principales en una cola son **enqueue** (agregar un elemento al final) y **dequeue** (eliminar el elemento del frente).

En la vida real las colas tienen muchas aplicaciones, como en la gestión de tareas en segundo plano, la impresión de documentos y la atención al cliente en un centro de llamadas.

## Grafos

Los grafos son estructuras de datos que consisten en un conjunto de nodos (o vértices) y un conjunto de aristas (o bordes) que conectan pares de nodos. Los grafos pueden ser dirigidos o no dirigidos, y pueden tener pesos en sus aristas. Son útiles para representar relaciones complejas, como redes sociales, mapas y más.

Los grafos son una poderosa herramienta para modelar problemas del mundo real, como la optimización de rutas en sistemas de navegación o la representación de relaciones en bases de datos.

Un ejemplo sería Google Maps, que utiliza grafos para representar las intersecciones y caminos de una ciudad, permitiendo calcular rutas óptimas entre diferentes ubicaciones.