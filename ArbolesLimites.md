Lo de los límites, ¿es necesario?

Sí, es absolutamente necesario. Aquí te explico de la forma más simple posible por qué.

Imagina que solo comprobamos si un hijo es correcto con respecto a su padre directo. Podríamos tener un árbol como este:

      (20)
      /
    (10)
       \
        (25)  <-- ¡ERROR!

        
    ¿Es 10 menor que su padre 20? Sí. Correcto.

    ¿Es 25 mayor que su padre 10? Sí. Correcto.

Si solo miramos padre-hijo, el árbol parece válido. Pero es un ABB inválido, porque el 25 está en el subárbol izquierdo del 20, lo cual rompe la regla fundamental (todo a la izquierda del 20 debe ser menor que 20).

El sistema de límites [min, max] soluciona esto. Cuando bajamos del 20 al 10 (por la izquierda), le decimos a todo ese subárbol: "Nadie aquí puede ser mayor que 20". El 25 viola esta regla heredada de su "abuelo", y por eso los límites son indispensables.